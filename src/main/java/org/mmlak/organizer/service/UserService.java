package org.mmlak.organizer.service;

//@Service
public class UserService{ //} implements UserDetailsService {

//    private final UserDao userDao;
//
//    public UserService(final UserDao userDao){
//        this.userDao = userDao;
//    }
//
//    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        final User user = userDao.findById(username).orElseThrow(() -> new UsernameNotFoundException(format("User with name [{}] not found.", username)));
//        final List<SimpleGrantedAuthority> roles = user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role.name())).collect(Collectors.toList());
//        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), roles);
//    }
}
