package com.ibm.pio.like;

import com.ibm.pio.auth.AuthService;

import io.quarkus.hibernate.orm.rest.data.panache.RestDataResourceMethodListener;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class LikeRestDataResourceMethodListener implements RestDataResourceMethodListener<Like> {

    @Inject
    public AuthService authService;

    @Override
    public void onBeforeAdd(Like like) {
        like.user = authService.getCurrentSession();
    }
}
