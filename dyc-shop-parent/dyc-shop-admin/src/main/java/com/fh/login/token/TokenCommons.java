package com.fh.login.token;

import javax.servlet.http.HttpServletRequest;

public class TokenCommons {


    public static final  String   token_pre="token:";


    public static final Long    exp_time=30l;

    public static final String   user_key_pre="COURENTUSER:";


    public static String  redisTokenKey(String username,String typeName,String token){
        return  token_pre+username+":"+typeName+":"+token;
    }

   public static String  redisTokenByKey(String username,String typeName){
        return  token_pre+username+":"+typeName+":*";
    }

    public static String  userKey(String username,String typeName){
        return  user_key_pre+username+":"+typeName;
    }


    public static String getDeviceType(HttpServletRequest request){
        String User_Agent = request.getHeader("User-Agent");
        String typeName="";
        if (User_Agent.contains("Android")||User_Agent.contains("Linux")) {
            System.out.println("Android移动客户端");
            typeName="Android";
        } else if (User_Agent.contains("iPhone")) {
            System.out   .println("iPhone移动客户端");
            typeName="iPhone";
        } else if (User_Agent.contains("iPad")) {
            System.out.println("iPad客户端");
            typeName="iPad";
        } else if(User_Agent.contains("Windows")){
            System.out.println("Windows");
            typeName="Windows";
        }
        return typeName;
    }








}
