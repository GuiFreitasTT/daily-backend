package com.daily.user.dto;

import com.daily.user.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
