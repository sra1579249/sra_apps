package com.example.basicjwtapp.service;

import com.example.basicjwtapp.config.JwtProvider;
import com.example.basicjwtapp.dao.RoleRepository;
import com.example.basicjwtapp.dao.UserRepository;
import com.example.basicjwtapp.dto.LoginDto;
import com.example.basicjwtapp.dto.ResponseDto;
import com.example.basicjwtapp.model.Role;
import com.example.basicjwtapp.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class JwtServiceImpl implements JwtService{

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtProvider jwtProvider;

    @Override
    public User save(User user) {
        Set<Role> roles = new HashSet<>();
        user.getRoles().stream().forEach(role -> roles.add(roleRepository.findByRole(role.getRole())));
        user.setRoles(roles);

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    @Override
    public ResponseDto login(LoginDto loginDto) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                loginDto.getUsername(), loginDto.getPassword()
        ));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        ResponseDto response = new ResponseDto();
        response.setToken(token);

        return response;
    }
}
