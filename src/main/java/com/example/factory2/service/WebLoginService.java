package com.example.factory2.service;

import com.example.factory2.client.LoginType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class WebLoginService implements LoginService{
    private static final Logger log = LoggerFactory.getLogger(WebLoginService.class);

    @Override
    public boolean supports(LoginType loginType) {
        return loginType == LoginType.WEB;
    }

    @Override
    public void login(){
        log.info(">>>> login()");
    }
}
