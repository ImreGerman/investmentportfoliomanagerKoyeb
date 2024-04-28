var totalUnrealizedProfit = 0.00;
var totalRoiWithDividend = 0.00;
var totalSumTransactionAmountInForeignCurrencyElement;
var totalSumTransactionAmountInForeignCurrency;

document.addEventListener('DOMContentLoaded', function () {

    var sumSoldTransactionAmountInForeignCurrency = document.getElementById('sumSoldTransactionAmountInForeignCurrency').value;
    var stockSymbols = document.querySelectorAll('[data-symbol]');
    var sumStockQuantities = document.querySelectorAll('[data-sum-stock-quantity]');
    var sumRemainingStockQuantities = document.querySelectorAll('[data-sum-remaining-stock-quantity]');
    var averageCostPrices = document.querySelectorAll('[data-average-cost-price]');
    var sumTransactionAmountInForeignCurrencies = document.querySelectorAll('[data-sum-transaction-amount-in-foreign-currency]');
    var sumDividendPrices = document.querySelectorAll('[data-sum-dividend-price]');
    totalSumTransactionAmountInForeignCurrencyElement = document.querySelector('[total-sum-transaction-amount-in-foreign-currency]');
    totalSumTransactionAmountInForeignCurrency = Number(totalSumTransactionAmountInForeignCurrencyElement.textContent);


    stockSymbols.forEach(function (stockSymbolElement, index) {
        var sumStockQuantityElement = sumStockQuantities[index];
        var sumRemainingStockQuantityElement = sumRemainingStockQuantities[index];
        var averageCostPriceElement = averageCostPrices[index];
        var sumTransactionAmountInForeignCurrencyElement = sumTransactionAmountInForeignCurrencies[index];
        var sumDividendPriceElement = sumDividendPrices[index];

        if (stockSymbolElement && sumStockQuantityElement && averageCostPriceElement && sumTransactionAmountInForeignCurrencyElement) {
            var priceCell = stockSymbolElement.closest('tr').cells[8];
            var unrealizedProfitCell = stockSymbolElement.closest('tr').cells[9];
            var roiCell = stockSymbolElement.closest('tr').cells[10];
            var roiWithDividendCell = stockSymbolElement.closest('tr').cells[12];

            fetchAndUpdatePrice(stockSymbolElement, sumStockQuantityElement, sumRemainingStockQuantityElement, averageCostPriceElement, sumTransactionAmountInForeignCurrencyElement,
                                sumDividendPriceElement, sumSoldTransactionAmountInForeignCurrency, priceCell, unrealizedProfitCell, roiCell, roiWithDividendCell);
            totalUnrealizedProfit = 0.00;
            totalRoiWithDividend = 0.00;
            setInterval(function () {
                fetchAndUpdatePrice(stockSymbolElement, sumStockQuantityElement, sumRemainingStockQuantityElement, averageCostPriceElement, sumTransactionAmountInForeignCurrencyElement,
                                    sumDividendPriceElement, sumSoldTransactionAmountInForeignCurrency, priceCell, unrealizedProfitCell, roiCell, roiWithDividendCell);
                totalUnrealizedProfit = 0.00;
                totalRoiWithDividend = 0.00;
            }, 300000);
        } else {
            console.error('Some elements are missing in the row.');
        }
    });
});

function fetchAndUpdatePrice(stockSymbolElement, sumStockQuantityElement, sumRemainingStockQuantityElement, averageCostPriceElement, sumTransactionAmountInForeignCurrencyElement,
                             sumDividendPriceElement, sumSoldTransactionAmountInForeignCurrency, priceCell, unrealizedProfitCell, roiCell, roiWithDividendCell) {

    const apiUrl = 'https://financialmodelingprep.com/api/v3/stock/real-time-price/';
    const apiKey = document.getElementById('apiKeys').getAttribute('data-api-key');
    const apiKeyWithParam = '?apikey=' + apiKey;

    fetch(apiUrl + stockSymbolElement.textContent.trim() + apiKeyWithParam)
        .then(response => response.json())
        .then(data => {
            if (data.companiesPriceList && data.companiesPriceList.length > 0) {
                var price = data.companiesPriceList[0].price;

                if (priceCell) {
                    priceCell.textContent = price.toFixed(2);

                    var sumSoldTransactionAmountInForeignCurrencyNumber = parseFloat(sumSoldTransactionAmountInForeignCurrency);
                    console.log(sumSoldTransactionAmountInForeignCurrencyNumber);
                    var unRealizedProfit = Number((price * sumRemainingStockQuantityElement.textContent - averageCostPriceElement.textContent * sumRemainingStockQuantityElement.textContent  + sumSoldTransactionAmountInForeignCurrencyNumber).toFixed(2));
                    totalUnrealizedProfit += unRealizedProfit;
                    unrealizedProfitCell.textContent = unRealizedProfit;

                    var roi = Number((unRealizedProfit / sumTransactionAmountInForeignCurrencyElement.textContent * 100).toFixed(2));
                    roiCell.textContent = roi;

                    var sumDividendPriceValue = sumDividendPriceElement ? Number(sumDividendPriceElement.textContent) : 0;
                    var roiWithDividend = Number(((unRealizedProfit + sumDividendPriceValue) / sumTransactionAmountInForeignCurrencyElement.textContent * 100).toFixed(2));
                    roiWithDividendCell.textContent = roiWithDividend;
                    totalRoiWithDividend += roiWithDividend;

                } else {
                    console.error('The price column is not found.');
                }
                updateThymeleafTotalUnrealizedProfit(totalUnrealizedProfit);
                updateThymeleafTotalRoi(totalUnrealizedProfit);
                updateThymeleafTotalRoiWithDividend(totalRoiWithDividend)

            } else {
                console.error('The response does not contain companiesPriceList or is empty.');
            }
        })
        .catch(error => console.error('An error occurred: ', error));
}

function updateThymeleafTotalUnrealizedProfit(totalUnrealizedProfit) {
    var totalUnrealizedProfitCell = document.querySelector('[data-total-unrealized-profit]');
    if (totalUnrealizedProfitCell) {
        totalUnrealizedProfitCell.textContent = totalUnrealizedProfit.toFixed(2);
    }
    return totalUnrealizedProfit;
}

function updateThymeleafTotalRoi(totalUnrealizedProfit) {
    var totalRoiCell = document.querySelector('[data-total-roi]');
    if(totalRoiCell) {
        totalRoiCell.textContent = (totalUnrealizedProfit / totalSumTransactionAmountInForeignCurrency * 100).toFixed(2);
    }
}

function updateThymeleafTotalRoiWithDividend(totalUnrealizedProfit) {
    var totalRoiCell = document.querySelector('[data-total-roi-with-dividend]');
    if(totalRoiCell) {
        totalRoiCell.textContent = (totalUnrealizedProfit / totalSumTransactionAmountInForeignCurrency * 100).toFixed(2);
    }
}
