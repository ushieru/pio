package com.ibm.pio.user;

import java.util.UUID;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.MessageDigestAlgorithms;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;

@ApplicationScoped
public class UserService {

    public User findById(UUID id) {
        var user = User.<User>findById(id);
        if (user == null)
            throw new NotFoundException();
        return user;

    }

    @Transactional
    public User create(CreateUserRequest request) {
        var passwordSHA3_256 = new DigestUtils(MessageDigestAlgorithms.SHA3_256).digestAsHex(request.password());
        var user = new User();
        user.name = request.name();
        user.email = request.email();
        user.password = passwordSHA3_256;
        user.persistAndFlush();
        return user;
    }
}
