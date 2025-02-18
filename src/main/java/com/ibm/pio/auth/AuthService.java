package com.ibm.pio.auth;

import java.time.Duration;
import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;
import org.eclipse.microprofile.jwt.JsonWebToken;

import com.ibm.pio.user.User;
import com.ibm.pio.user.UserService;

import io.smallrye.jwt.build.Jwt;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.BadRequestException;

@ApplicationScoped
public class AuthService {

    @Inject
    public JsonWebToken jwt;

    @Inject
    public UserService userService;

    public AuthResponse auth(AuthRequest request) {
        var passwordSHA = new DigestUtils(MessageDigestAlgorithms.SHA3_256).digestAsHex(request.password());
        return User.<User>find("email = ?1 and password = ?2", request.email(), passwordSHA)
                .firstResultOptional()
                .map(user -> new AuthResponse(
                        Jwt.subject(user.id.toString()).groups("user").expiresIn(Duration.ofDays(7)).sign()))
                .orElseThrow(BadRequestException::new);
    }

    public User getCurrentSession() {
        var userId = UUID.fromString(jwt.getSubject());
        var user = userService.findById(userId);
        return user;
    }

}
