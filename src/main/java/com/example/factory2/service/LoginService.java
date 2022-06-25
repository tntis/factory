package com.example.factory2.service;

import com.example.factory2.client.LoginType;

public interface LoginService {
    boolean supports(LoginType loginType);

    void login();
}
