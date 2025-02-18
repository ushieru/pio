package com.ibm.pio.auth;

public record AuthRequest(
        String email,
        String password) {
}
