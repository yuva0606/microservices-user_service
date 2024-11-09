package com.yuva.user.dto;

import com.yuva.user.model.Role;

public record RegisterResponse(Long userId, String username, String email, Role role) {

}
