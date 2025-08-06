package com.ashok.Hospital_Management.service.interfaces;

import com.ashok.Hospital_Management.dto.UserRequest;

public interface AuthService {
    String login(UserRequest request);
}
