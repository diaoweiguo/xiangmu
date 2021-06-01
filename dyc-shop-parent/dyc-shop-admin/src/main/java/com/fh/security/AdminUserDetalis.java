package com.fh.security;

import com.fh.admin.entity.UmsAdmin;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class AdminUserDetalis implements UserDetails {

    private List<String> permissionList;
    private List<String> roleList;
    private UmsAdmin umsAdmin;

    public AdminUserDetalis(List<String> permissionList,List<String> roleList,UmsAdmin umsAdmin){
      this.permissionList=permissionList;
      this.roleList=roleList;
      this.umsAdmin=umsAdmin;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority>list=new ArrayList<>();
        permissionList.forEach(permission->{
            list.add(new SimpleGrantedAuthority(permission));
        });
        roleList.forEach(role->{
            list.add(new SimpleGrantedAuthority("ROLE_"+role));
        });
        return list;
    }

    @Override
    public String getPassword() {
        return umsAdmin.getPassword();
    }

    @Override
    public String getUsername() {
        return umsAdmin.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return umsAdmin.getStatus().equals(1);
    }

    public List<String> getPermissionList() {
        return permissionList;
    }

    public void setPermissionList(List<String> permissionList) {
        this.permissionList = permissionList;
    }

    public List<String> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<String> roleList) {
        this.roleList = roleList;
    }

    public UmsAdmin getUmsAdmin() {
        return umsAdmin;
    }

    public void setUmsAdmin(UmsAdmin umsAdmin) {
        this.umsAdmin = umsAdmin;
    }
}
