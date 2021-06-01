package com.fh.security;

import com.fh.login.token.ProcessTokenUtils;
import com.fh.login.token.TokenCommons;
import com.fh.result.ResultObject;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JwtAuthenticationTokenFiler extends OncePerRequestFilter {

    @Autowired
    private ProcessTokenUtils processTokenUtils;

    @Autowired
    private RedisTemplate redisTemplate;
    
    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String token=request.getHeader("Authorization-token");
        if (token!=null){
            ResultObject resultObject = processTokenUtils.resolverToken(token);
            //判断token值是否有效
            if (resultObject.getCode()==200){
            //判断token是否超时
                Claims claims = (Claims) resultObject.getData();
                String username = (String) claims.get("username");
                String typeName = TokenCommons.getDeviceType(request);
                String redisKey = TokenCommons.redisTokenKey(username, typeName, token);
                if (redisTemplate.hasKey(redisKey) &&  SecurityContextHolder.getContext().getAuthentication()== null){
                    //创建spring security的userNamePasswordToken
                    UserDetails userDetails = userDetailsService.loadUserByUsername(username);
                    UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
                    SecurityContextHolder.getContext().setAuthentication(authenticationToken);
                }
            }
        }
        filterChain.doFilter(request,response);
    }
}
