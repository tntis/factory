package com.example.factory2.factory;

import com.example.factory2.client.LoginType;
import com.example.factory2.service.LoginService;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

//@Component
public class LoginServiceFactoryV2 implements LoginServiceFactory{

   private final List<LoginService> loginServices;

    public LoginServiceFactoryV2(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }


    public LoginService find(LoginType loginType){

       return loginServices.stream()
                .filter(s -> s.supports(loginType))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("Cannot find LoginService of : "+ loginType));
    }
}
