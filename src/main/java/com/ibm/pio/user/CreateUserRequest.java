package com.ibm.pio.user;

public record CreateUserRequest(
        String name,
        String email,
        String password) {
}
