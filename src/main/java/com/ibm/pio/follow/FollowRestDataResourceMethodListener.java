package com.ibm.pio.follow;

import com.ibm.pio.auth.AuthService;

import io.quarkus.hibernate.orm.rest.data.panache.RestDataResourceMethodListener;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

@ApplicationScoped
public class FollowRestDataResourceMethodListener implements RestDataResourceMethodListener<Follow> {

    @Inject
    public AuthService authService;

    @Override
    public void onBeforeAdd(Follow resource) {
        resource.follower = authService.getCurrentSession();
    }
}
