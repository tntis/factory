package com.example.factory2.factory;

import com.example.factory2.client.LoginType;
import com.example.factory2.service.GoogleLoginService;
import com.example.factory2.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;

@Component
public class LoginServiceFactoryV4 implements LoginServiceFactory{
    private static final Logger log = LoggerFactory.getLogger(LoginServiceFactoryV4.class);
   private final List<LoginService> loginServices;

   public LoginServiceFactoryV4(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    @PostConstruct
    void postConstruct(){
       log.info(">>>> postConstruct");
        Set<LoginType> loginTypes = EnumSet.allOf(LoginType.class);
        loginTypes.forEach(loginType -> {
            try {
                LoginServiceCache.put(loginType, getLoginService(loginType));
            } catch (NoSuchElementException e) {
                log.warn(getNotFoundMessage(loginType));
                // 어플리케이션이 중단이 되지않고 에러만 표출되게 할 경우 try/catch 사용
                // 사용하지 않을시 시스템 중단됨?. 이어플리케이션 실행시에 확인작업
            }
        });
    }

    private String getNotFoundMessage(LoginType loginType){
       return "Cannot find LoginService of : "+ loginType;
    }

    @Override
    public LoginService find(LoginType loginType){
        return LoginServiceCache.get(loginType)
                .orElseGet(()->{
                    log.info(">>> No Cache : {}", loginType);
                    LoginService loginService = getLoginService(loginType);
                    LoginServiceCache.put(loginType,loginService);
                    return loginService;
                });

    }

    private LoginService getLoginService(LoginType loginType) {
        return loginServices.stream()
                 .filter(s -> s.supports(loginType))
                 .findFirst()
                 .orElseThrow(() -> new NoSuchElementException(getNotFoundMessage(loginType)));
    }

    private static class LoginServiceCache{
        private static final Map<LoginType, LoginService> cachedLoginServiceMap = new EnumMap<>(LoginType.class);

        public static Optional<LoginService> get(LoginType loginType){
            return Optional.ofNullable(cachedLoginServiceMap.get(loginType));
        }

        public static void put(LoginType loginType, LoginService loginService){
            cachedLoginServiceMap.put(loginType,loginService);
        }


    }

}

