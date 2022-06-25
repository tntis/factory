package com.example.factory2.client;

import com.example.factory2.factory.LoginServiceFactory;
import com.example.factory2.factory.LoginServiceFactoryV1;
import com.example.factory2.service.LoginService;
import com.example.factory2.service.MobileLoginService;
import com.example.factory2.service.WebLoginService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;
@Component
public class LoginClient {

    private final LoginServiceFactory loginServiceFactory;

    public LoginClient(LoginServiceFactory loginServiceFactory) {
        this.loginServiceFactory = loginServiceFactory;
    }

    public void login(LoginType loginType){
        LoginService loginService = loginServiceFactory.find(loginType);
        loginService.login();
    }
}
