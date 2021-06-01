package com.fh.aop;

import com.fh.login.token.ProcessTokenUtils;
import com.fh.login.token.TokenCommons;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultObject;
import io.jsonwebtoken.Claims;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.concurrent.TimeUnit;

/*@Component
@Order(5)
@Aspect*/
public class LoginAuthentication {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private  ProcessTokenUtils processTokenUtils;

    @Autowired
    private RedisTemplate redisTemplate;


    @Around(value = "execution(* com.fh.*.controller.*.*(..))")
    private Object aroundLoginAuth(ProceedingJoinPoint joinPoint){
        Object obj=null;
        try {
            String token = request.getHeader("Authorization-token");
            if (StringUtils.isEmpty(token)){
                obj=  ResultObject.error(ResponseEnum.TOKEN_INVALID);
                return  obj;
            }
            ResultObject resultObject = processTokenUtils.resolverToken(token);
            if (resultObject.getCode()!=200){
                return resultObject;
            }
            //给redis 中的touken值续签
            Claims claims = (Claims) resultObject.getData();
            String username = (String) claims.get("username");
            String typeName = TokenCommons.getDeviceType(request);
            String redisTokenByKey = TokenCommons.redisTokenKey(username, typeName,token);
            if (!redisTemplate.hasKey(redisTokenByKey)){
                obj=  ResultObject.error(ResponseEnum.TOKEN_INVALID);
                return  obj;
            }
            redisTemplate.expire(redisTokenByKey,TokenCommons.exp_time, TimeUnit.MINUTES);
            obj=joinPoint.proceed();
        }catch (Throwable throwable){
            throwable.printStackTrace();
        }
        return obj;
    }

}
