package com.example.factory2.factory;

import com.example.factory2.client.LoginType;
import com.example.factory2.service.LoginService;

public interface LoginServiceFactory {
    LoginService find(LoginType loginType);
}
