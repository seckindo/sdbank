package org.perscholas.sdbank.security;

import org.perscholas.sdbank.models.AuthGroup;
import org.perscholas.sdbank.models.MyUser;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class MyUserPrincipal implements UserDetails {

    private MyUser myUser;
    private List<AuthGroup> authGroup;


    public MyUserPrincipal(MyUser myUser, List<AuthGroup> authGroup) {
        this.myUser = myUser;
        this.authGroup = authGroup;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authGroup.stream().map( auth -> new SimpleGrantedAuthority(auth.getRole())).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return myUser.getPassword();
    }

    @Override
    public String getUsername() {
        return myUser.getEmail();
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
