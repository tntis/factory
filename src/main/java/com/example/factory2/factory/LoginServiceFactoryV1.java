package com.example.factory2.factory;

import com.example.factory2.client.LoginType;
import com.example.factory2.service.GoogleLoginService;
import com.example.factory2.service.LoginService;
import com.example.factory2.service.MobileLoginService;
import com.example.factory2.service.WebLoginService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

//@Component
public class LoginServiceFactoryV1 implements LoginServiceFactory{

    private final WebLoginService webLoginService;
    private final MobileLoginService mobileLoginService;
    private final GoogleLoginService googleLoginService;

    public LoginServiceFactoryV1(WebLoginService webLoginService, MobileLoginService mobileLoginService, GoogleLoginService googleLoginService) {
        this.webLoginService = webLoginService;
        this.mobileLoginService = mobileLoginService;
        this.googleLoginService = googleLoginService;
    }

    public LoginService find(LoginType loginType){
        if(loginType == LoginType.WEB){
            return webLoginService;
        }else if (loginType == LoginType.MOBILE){
            return mobileLoginService;
        }else if (loginType == LoginType.GOOGLE){
            return googleLoginService;
        }else{
            throw new NoSuchElementException("Cannot find LoginService of : "+ loginType);
        }

    }


}
