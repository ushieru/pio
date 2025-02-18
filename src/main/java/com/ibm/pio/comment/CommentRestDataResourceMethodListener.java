package com.ibm.pio.comment;

import com.ibm.pio.auth.AuthService;

import io.quarkus.hibernate.orm.rest.data.panache.RestDataResourceMethodListener;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class CommentRestDataResourceMethodListener implements RestDataResourceMethodListener<Comment> {

    @Inject
    public AuthService authService;

    @Override
    public void onBeforeAdd(Comment comment) {
        comment.user = authService.getCurrentSession();
    }
}
