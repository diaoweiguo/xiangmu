package com.fh;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
class DycShopAdminApplicationTests {

    @Test
    void contextLoads() {
        String token = createToken();
        System.out.println(token);
    }

    public static String createToken(){
        //token包含三部分值
        //头部信息，
        Map<String,Object> headMap=new HashMap<>();
        headMap.put("alg","HS256");
        headMap.put("type","JWT");
        //有效载荷
        Map<String,Object> payLoad=new HashMap<>();
        payLoad.put("userName","买女孩的小火柴");
        //失效时间
        long startTime=System.currentTimeMillis();
        long endTime=startTime+120000;
        //签名
        String signature ="diaoweiguo";
        String token= Jwts.builder()
                .setHeader(headMap)
                .setClaims(payLoad)
                .setExpiration(new Date(endTime))
                .signWith(SignatureAlgorithm.HS256,signature)
                .compact();
        return token;
    }

    @Test
    public void  ttt(){
        getClaimsFromToken("eyJ0eXBlIjoiSldUIiwiYWxnIjoiSFMyNTYifQ.eyJ1c2VyTmFtZSI6IuS5sOWls-WtqeeahOWwj-eBq-aftCIsImV4cCI6MTYyMTE0NzgyNn0.3017iT-ObHONcQ8qnpFtG8xjzA0i478u1slo7uhcfWg");
    }

    public  void   getClaimsFromToken(String token){
        try {
            Claims diaoweiguo = Jwts.parser()
                    .setSigningKey("diaoweiguo")
                    .parseClaimsJws(token)
                    .getBody();
            System.out.println(diaoweiguo.get("userName"));
        }catch (SecurityException e){
            System.out.println("无效签名");
        }
        catch (ExpiredJwtException e){
            System.out.println("token失效");
        }catch (Exception e){
            System.out.println("不符合token的命明规则");
        }

    }


    @Test
    public void  password(){
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        String encode = bCryptPasswordEncoder.encode("123456");
        System.out.println(encode);
    }




}
