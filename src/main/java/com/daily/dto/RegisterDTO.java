package com.daily.dto;

import com.daily.model.UserRole;

public record RegisterDTO(String login, String password, UserRole role) {
}
