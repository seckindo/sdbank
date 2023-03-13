package org.perscholas.sdbank.security;

import org.perscholas.sdbank.dao.AuthGroupRepoI;
import org.perscholas.sdbank.dao.MyUserRepoI;
import org.perscholas.sdbank.models.AuthGroup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {

    MyUserRepoI myUserRepoI;
    AuthGroupRepoI authGroupRepoI;

    @Autowired
    public MyUserDetailService(MyUserRepoI myUserRepoI, AuthGroupRepoI authGroupRepoI) {
        this.myUserRepoI = myUserRepoI;
        this.authGroupRepoI = authGroupRepoI;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        List<AuthGroup> authGroupList = authGroupRepoI.findByEmail(username);

        return new MyUserPrincipal(myUserRepoI.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Email Not Found")),
                authGroupRepoI.findByEmail(username));
    }
}
