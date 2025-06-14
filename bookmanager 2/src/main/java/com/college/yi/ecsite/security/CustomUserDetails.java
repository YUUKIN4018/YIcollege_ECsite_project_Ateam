package com.college.yi.ecsite.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.college.yi.ecsite.entity.User;

public class CustomUserDetails implements UserDetails {

    private final User user;

    public CustomUserDetails(User user) {
        this.user = user;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Short roleValue = user.getRole();  // SMALLINTで返ってくる役割
        String roleName;

        if (roleValue == 1) {
            roleName = "ROLE_USER";
        } else if (roleValue == 2) {
            roleName = "ROLE_ADMIN";
        } else {
            roleName = "ROLE_GUEST"; // デフォルトの権限
        }

        return Collections.singleton(new SimpleGrantedAuthority(roleName));
    }

    @Override
    public String getPassword() {
        return user.getPasswordHash();
    }

    @Override
    public String getUsername() {
        return user.getEmail();
    }

    // 以下は必要に応じてtrue固定などでOK
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
        return true;
    }
}
