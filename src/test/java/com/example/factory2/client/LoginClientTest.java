package com.example.factory2.client;

import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class LoginClientTest {
    @Autowired
    LoginClient loginClient;



    @ParameterizedTest
    @EnumSource
    void login(LoginType loginType){
        loginClient.login(loginType);
    }

    @ParameterizedTest
    @EnumSource
    void login2(LoginType loginType){
        loginClient.login(loginType);
    }
}