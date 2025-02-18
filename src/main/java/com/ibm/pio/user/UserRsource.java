package com.ibm.pio.user;

import java.util.List;
import java.util.UUID;

import jakarta.annotation.security.RolesAllowed;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

@Path("/api/v1/users")
@RolesAllowed("user")
public class UserRsource {

    @GET
    @Path("{id}/followers")
    public List<User> readFollowers(@PathParam("id") UUID id) {
        return User.<User>find("SELECT f.follower FROM Follow f WHERE f.following.id = ?1", id).list();
    }

    @GET
    @Path("{id}/following")
    public List<User> readFollowing(@PathParam("id") UUID id) {
        return User.<User>find("SELECT f.following FROM Follow f WHERE f.follower.id = ?1", id).list();
    }
    
}
