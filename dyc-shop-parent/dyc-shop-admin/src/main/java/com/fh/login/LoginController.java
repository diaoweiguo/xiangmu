package com.fh.login;

import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.service.IUmsAdminService;
import com.fh.login.token.ProcessTokenUtils;
import com.fh.login.token.TokenCommons;
import com.fh.md5.MD5Util;
import com.fh.result.ResponseEnum;
import com.fh.result.ResultObject;
import com.fh.security.AdminUserDetalis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.prefs.BackingStoreException;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private IUmsAdminService   iumsAdminService;

    @Autowired
    private ProcessTokenUtils  processTokenUtils;

    @Autowired
    private RedisTemplate  redisTemplate;

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping
    public ResultObject loginAction(@RequestParam String username, @RequestParam String password, HttpServletRequest request)  {
            if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
                return ResultObject.error(ResponseEnum.IS_NULL);
            }
       /* UmsAdmin admin= iumsAdminService.getUserByName(username);
        if (admin==null){
            return ResultObject.error(ResponseEnum.ACCOUNT_NOT_EXIST);
        }
        if (!MD5Util.encoder(password).equals(admin.getPassword())){
            return ResultObject.error(ResponseEnum.PASSWORD_ERROR);
        }*/
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        if (!passwordEncoder.matches(password,userDetails.getPassword())){
            throw new BadCredentialsException("用户密码不正确");
        }
        /*UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);*/
        AdminUserDetalis adminUserDetails= (AdminUserDetalis) userDetails;
        UmsAdmin admin=adminUserDetails.getUmsAdmin();
        //获取用户使用类型
        String typeName = TokenCommons.getDeviceType(request);
        //获取token
        String token = processTokenUtils.createToken(admin);
        //获取redis的key值
        String redisTokenKey = TokenCommons.redisTokenKey(admin.getUsername(), typeName, token);
        String redisTokenByKey = TokenCommons.redisTokenByKey(admin.getUsername(), typeName);
        Set<String> set=redisTemplate.keys(redisTokenByKey);
        if (set.size()>0){
            redisTemplate.delete(set);
        }
        redisTemplate.opsForValue().set(redisTokenKey,System.currentTimeMillis(),TokenCommons.exp_time,TimeUnit.MINUTES);
        return ResultObject.success(token);
    }

}
