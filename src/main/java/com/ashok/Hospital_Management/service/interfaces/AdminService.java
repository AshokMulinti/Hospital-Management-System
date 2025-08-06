package com.ashok.Hospital_Management.service.interfaces;

import com.ashok.Hospital_Management.dto.CreateUserRequest;
import com.ashok.Hospital_Management.dto.RoleResponse;
import com.ashok.Hospital_Management.dto.UpdateRequest;
import com.ashok.Hospital_Management.dto.UserResponse;
import com.ashok.Hospital_Management.entities.Role;


import java.util.List;

public interface AdminService {
    List<UserResponse> getAllUsers();
    UserResponse createNewUser(CreateUserRequest request);
    UserResponse getUserById(Long id);
    UserResponse updateUser(UpdateRequest request);
    UserResponse deleteUser(Long id);
    List<RoleResponse> getAllRoles();
}
