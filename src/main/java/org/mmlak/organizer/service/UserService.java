package org.mmlak.organizer.service;

import org.mmlak.organizer.repository.UserDao;
import org.mmlak.organizer.repository.entity.User;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

import static java.lang.String.format;

@Service
public class UserService implements UserDetailsService {

    private final UserDao userDao;

    public UserService(final UserDao userDao){
        this.userDao = userDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final User user = userDao.findById(username).orElseThrow(() -> new UsernameNotFoundException(format("User with name [{}] not found.", username)));
        final List<SimpleGrantedAuthority> roles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
    }
}
