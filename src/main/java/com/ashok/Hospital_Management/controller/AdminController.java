package com.ashok.Hospital_Management.controller;

import com.ashok.Hospital_Management.dto.CreateUserRequest;
import com.ashok.Hospital_Management.dto.RoleResponse;
import com.ashok.Hospital_Management.dto.UpdateRequest;
import com.ashok.Hospital_Management.dto.UserResponse;
import com.ashok.Hospital_Management.entities.Role;
import com.ashok.Hospital_Management.service.interfaces.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
@RequiredArgsConstructor
public class AdminController {

    private final AdminService adminService;

    @GetMapping("/users")
    public ResponseEntity<List<UserResponse>> getAllUsers(){
        List<UserResponse> response = adminService.getAllUsers();
        return ResponseEntity.ok(response);
    }
    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody CreateUserRequest request){
        UserResponse response = adminService.createNewUser(request);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/users/{id}")
    public ResponseEntity<UserResponse> getUserById(@PathVariable Long id) {
        UserResponse response = adminService.getUserById(id);
        return ResponseEntity.ok(response);
    }
    @PutMapping("/users/{id}")
    public ResponseEntity<UserResponse> updateUser(@RequestBody UpdateRequest request) {
        UserResponse response = adminService.updateUser(request);
        return ResponseEntity.ok(response);
    }
    @DeleteMapping("/users/{id}")
    public ResponseEntity<UserResponse> deleteUser(@PathVariable Long id) {
        UserResponse response = adminService.deleteUser(id);
        return ResponseEntity.ok(response);
    }
    @GetMapping("/roles")
    public ResponseEntity<List<RoleResponse>> getAllRoles() {
        List<RoleResponse> roles = adminService.getAllRoles();
        return ResponseEntity.ok(roles);
    }

}
