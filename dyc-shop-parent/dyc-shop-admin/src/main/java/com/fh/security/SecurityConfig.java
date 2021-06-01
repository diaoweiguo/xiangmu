package com.fh.security;

import com.alibaba.fastjson.JSON;
import com.fh.admin.entity.UmsAdmin;
import com.fh.admin.service.IUmsAdminService;
import com.fh.login.token.TokenCommons;
import com.fh.permission.service.IUmsPermissionService;
import com.fh.role.service.IUmsRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.xml.bind.util.JAXBSource;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private IUmsAdminService  iumsAdminService;

    @Autowired
    private IUmsPermissionService  iumsPermissionService;

    @Autowired
    private IUmsRoleService  iumsRoleService;

    @Autowired
    private RestAuthenticationEntryPoint restAuthenticationEntryPoint;

    @Autowired
    private RestfulAccessDeniedHandler restfulAccessDeniedHandler;

    @Autowired
    private RedisTemplate  redisTemplate;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                    .antMatchers("/login/**","/upload/**","/userPhoto/**").permitAll()//放过请求不用拦截
                     .antMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyRequest().authenticated();//其他请求都要被拦截需要登陆认证
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//关闭csrf拦截
       //禁用缓存
        http.headers().cacheControl();
        //把自定义的拦截器加载过来
        http.addFilterBefore(jwtAuthenticationTokenFiler(), UsernamePasswordAuthenticationFilter.class);
        //自定义未登入或者未授权的返回结果
        http.exceptionHandling().accessDeniedHandler(restfulAccessDeniedHandler)
                 .authenticationEntryPoint(restAuthenticationEntryPoint);
    }

    /*
    *
    * 从数据库查询用户拥有的角色权限
    *
    * */
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
    }

        /*
        * 链接数据库的
        * */
    @Override
    @Bean
    protected UserDetailsService userDetailsService() {
        return username->{
            UmsAdmin umsAdmin =null;
            String userKey = TokenCommons.userKey(username, "user");
            if (redisTemplate.hasKey(userKey)){
                umsAdmin = (UmsAdmin) redisTemplate.opsForValue().get(userKey);
            }else {
                umsAdmin = iumsAdminService.getUserByName(username);
                redisTemplate.opsForValue().set(userKey,umsAdmin,TokenCommons.exp_time, TimeUnit.MINUTES);
            }
            if (umsAdmin != null){
              Long userId=umsAdmin.getId();
              String permissionKey = TokenCommons.userKey(username, "permission");
                List<String> permissionList =new ArrayList<String>();
              if (redisTemplate.hasKey(permissionKey)){
                   permissionList = (List<String>) redisTemplate.opsForValue().get(permissionKey);
              }else {
                  permissionList = iumsPermissionService.getPermissionByUserId(userId);
                  redisTemplate.opsForValue().set(permissionKey,permissionList,TokenCommons.exp_time, TimeUnit.MINUTES);
              }

              String roleKey = TokenCommons.userKey(username, "role");
                List<String> roleList =new ArrayList<String>();
              if (redisTemplate.hasKey(roleKey)){
                   roleList = (List<String>) redisTemplate.opsForValue().get(roleKey);
              }else {
                  roleList = iumsRoleService.getRoleByUserId(userId);
                  redisTemplate.opsForValue().set(roleKey,roleList,TokenCommons.exp_time, TimeUnit.MINUTES);
              }
              return new AdminUserDetalis(permissionList,roleList,umsAdmin);
            }else {
                throw  new UsernameNotFoundException("该用户不存在");
            }
        };
    }

    @Bean
    public JwtAuthenticationTokenFiler jwtAuthenticationTokenFiler(){
        return new JwtAuthenticationTokenFiler();
    }


    /*
          生命密码的加密方式
        */
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
