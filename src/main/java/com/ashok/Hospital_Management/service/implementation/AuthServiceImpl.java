package com.ashok.Hospital_Management.service.implementation;

import com.ashok.Hospital_Management.dto.UserRequest;
import com.ashok.Hospital_Management.entities.Role;
import com.ashok.Hospital_Management.entities.User;
import com.ashok.Hospital_Management.exceptions.UserNotFoundException;
import com.ashok.Hospital_Management.repository.RoleRepository;
import com.ashok.Hospital_Management.repository.UserRepository;
import com.ashok.Hospital_Management.security.JwtUtil;
import com.ashok.Hospital_Management.service.interfaces.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;


    @Override
    public String login(UserRequest request){
        User user = userRepository.findByEmail(request.email())
                .orElseThrow(() -> new UserNotFoundException("Incorrect Email or Password"));
        if (!passwordEncoder.matches(request.password(), user.getPassword())) {
            throw new UserNotFoundException("Incorrect Email or Password");
        }
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.email(),request.password()));
        List<String>roles = user.getRoles().stream()
                .map(Role::getRole)
                .toList();
        return jwtUtil.generateToken(request.email(), roles);
    }
}
