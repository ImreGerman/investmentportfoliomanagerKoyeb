package hu.imregerman.investmentportfoliomanager.controller;

import org.springframework.stereotype.Controller;

@Controller
public class UserControllerImpl implements UserController {

    @Override
    public String login() {
        return "login";
    }
}
