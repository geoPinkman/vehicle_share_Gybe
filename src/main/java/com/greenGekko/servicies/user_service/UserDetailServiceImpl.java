package com.greenGekko.servicies.user_service;

import com.greenGekko.models.JUser;
import lombok.AllArgsConstructor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;


@AllArgsConstructor
@Component
@Slf4j
public class UserDetailServiceImpl implements UserDetailsService {

    private UsersService usersService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        JUser user = usersService.getUserByEmail(email);

        Set<GrantedAuthority> authoritySet = new HashSet<>();
        for (JUser.JRole jRole : user.getRolesSet()) {
            authoritySet.add(new SimpleGrantedAuthority(jRole.getRole()));
        }

        log.info("User with email {}", email);
        log.info("User like JUser email={}, password={}, set roles={}", user.email, user.userPassword, user.rolesSet.toString());
        log.info("Auth roles {}", authoritySet);
        return new User(user.email, user.userPassword, authoritySet);
    }
}
