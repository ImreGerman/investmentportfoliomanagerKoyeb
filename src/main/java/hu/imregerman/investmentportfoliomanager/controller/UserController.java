package hu.imregerman.investmentportfoliomanager.controller;

import org.springframework.web.bind.annotation.GetMapping;

public interface UserController {

    @GetMapping("/login")
    String login();
}
