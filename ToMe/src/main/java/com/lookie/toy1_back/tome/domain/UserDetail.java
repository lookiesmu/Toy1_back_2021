package com.lookie.toy1_back.tome.domain;

import com.lookie.toy1_back.tome.role.UserRole;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetail implements UserDetails {

    private final String username;
    private final String password;

    private final UserRole roles;

    public UserDetail(User user) {
        this.username= user.getUsername();
        this.password= user.getPassword();
        this.roles= user.getRole();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        // TODO Auto-generated method stub
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(this.roles.toString()));

        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }
    @Override
    public String getUsername() {
        return username;
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
        return true;
    }

}
