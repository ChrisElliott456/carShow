package com.binarychris.carShow.service;

import com.binarychris.carShow.repository.UserRepository;
import com.binarychris.carShow.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class UserServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        org.springframework.security.core.userdetails.User.UserBuilder builder = null; // made null to access as something else later in if statement
        Optional<User> user = userRepository.findUserByUsername(username);
        if (user.isPresent()){
            User currentUser = user.get();
            builder = org.springframework.security.core.userdetails.User.withUsername(username);// UserBuilder = var
            builder.password(currentUser.getPassword());
            builder.roles(currentUser.getRole());
        } else {
            throw new UsernameNotFoundException("User not found");
        }
        return builder.build();
    }
}
