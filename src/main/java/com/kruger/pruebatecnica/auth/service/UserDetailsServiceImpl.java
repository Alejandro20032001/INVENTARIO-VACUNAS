package com.kruger.pruebatecnica.auth.service;

import com.kruger.pruebatecnica.auth.model.entity.User;
import com.kruger.pruebatecnica.auth.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = null;
        user = userRepository.findByUsername(username).
                orElseThrow(()-> new UsernameNotFoundException("User not found"));

        UserDetailImpl userDetail = new UserDetailImpl(user);
        return userDetail;
    }
}
