package com.ibm.pio.auth;

import com.ibm.pio.user.CreateUserRequest;
import com.ibm.pio.user.UserService;

import jakarta.inject.Inject;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;

@Path("/api/v1/auth")
public class AuthResource {

    @Inject
    public AuthService authService;

    @Inject
    public UserService userService;

    @POST
    public AuthResponse auth(AuthRequest request) {
        return authService.auth(request);
    }

    @POST
    @Path("register")
    public AuthResponse register(CreateUserRequest request) {
        var user = userService.create(request);
        var auth = new AuthRequest(user.email, request.password());
        return authService.auth(auth);
    }
}
