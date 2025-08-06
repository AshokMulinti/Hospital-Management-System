package com.ashok.Hospital_Management.service.implementation;

import com.ashok.Hospital_Management.dto.CreateUserRequest;
import com.ashok.Hospital_Management.dto.RoleResponse;
import com.ashok.Hospital_Management.dto.UpdateRequest;
import com.ashok.Hospital_Management.dto.UserResponse;
import com.ashok.Hospital_Management.entities.Role;
import com.ashok.Hospital_Management.entities.User;
import com.ashok.Hospital_Management.exceptions.UserAlreadyExistException;
import com.ashok.Hospital_Management.exceptions.UserNotFoundException;
import com.ashok.Hospital_Management.repository.RoleRepository;
import com.ashok.Hospital_Management.repository.UserRepository;
import com.ashok.Hospital_Management.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class AdminServiceImpl implements AdminService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserResponse createNewUser(CreateUserRequest request){
        Optional<User> userRepo = userRepository.findByEmail(request.email());

        if(userRepo.isPresent()){
            throw new UserAlreadyExistException("User Already Exists");
        }
        Role userRole = roleRepository.findByRole(request.role().toUpperCase())
                .orElseThrow(()->new RuntimeException("Role "+ request.role()+" not found"));
        User user = User.builder()
                .username(request.username())
                .email(request.email())
                .password(passwordEncoder.encode(request.password()))
                .phoneNo(request.phoneNo())
                .roles(new HashSet<>(List.of(userRole)))
                .build();
        User saved = userRepository.save(user);
        return new UserResponse(saved.getId(),saved.getUsername(),saved.getEmail(),saved.getPassword(),saved.getPhoneNo(),saved.getRoles());
    }

    @Override
    public List<UserResponse> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream()
                .map(user -> new UserResponse(
                        user.getId(),
                        user.getUsername(),
                        user.getEmail(),
                        user.getPassword(),
                        user.getPhoneNo(),
                        user.getRoles()
                ))
                .toList();
    }

    @Override
    public UserResponse getUserById(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));
        return new UserResponse(
                user.getId(),
                user.getUsername(),
                user.getEmail(),
                user.getPassword(),
                user.getPhoneNo(),
                user.getRoles()
        );
    }
    @Override
    public UserResponse updateUser(UpdateRequest request){
        User user = userRepository.findById(request.id())
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + request.id()));
        Role userRole = roleRepository.findByRole(request.role().toUpperCase())
                .orElseThrow(()->new RuntimeException("Role "+ request.role()+" not found"));
        user.setId(request.id());
        user.setUsername(request.username());
        user.setEmail(request.email());
        user.setPassword(passwordEncoder.encode(request.password()));
        user.setPhoneNo(request.phoneNo());
        user.setRoles(new HashSet<>(List.of(userRole)));
        User updatedUser = userRepository.save(user);

        return new UserResponse(
                updatedUser.getId(),
                updatedUser.getUsername(),
                updatedUser.getEmail(),
                updatedUser.getPassword(),
                updatedUser.getPhoneNo(),
                updatedUser.getRoles()
        );
    }
    @Override
    public UserResponse deleteUser(Long id){
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " + id));

        userRepository.delete(user);

        return new UserResponse(user.getId(), user.getUsername(), user.getEmail(), user.getPhoneNo(), user.getPassword(),user.getRoles());
    }
    @Override
    public List<RoleResponse> getAllRoles() {
        List<Role> roles = roleRepository.findAll();

        return roles.stream()
                .map(role -> new RoleResponse(role.getId(), role.getRole()))
                .toList();
    }
}
