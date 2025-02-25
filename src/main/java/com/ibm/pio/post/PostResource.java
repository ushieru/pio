package com.ibm.pio.post;

import jakarta.annotation.security.RolesAllowed;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;

import java.util.List;
import java.util.UUID;

import com.ibm.pio.auth.AuthService;
import com.ibm.pio.user.User;

import io.quarkus.panache.common.Sort;

@Path("/api/v1/posts")
@RolesAllowed("user")
public class PostResource {

    @Inject
    public AuthService authService;

    @GET
    @Path("/feed")
    public List<Post> feed() {
        var session = authService.getCurrentSession();
        var query = "SELECT p FROM Post p " +
                "JOIN Follow f ON p.user = f.following " +
                "WHERE f.follower.id = ?1 " +
                "ORDER BY p.createdAt DESC";
        return Post.find(query, session.id).list();
    }

    @GET
    @Path("/users/{id}")
    public List<Post> findByUserId(@PathParam("id") UUID id) {
        return Post.find("user", User.<User>findById(id)).list();
    }

    @GET
    public List<Post> read() {
        return Post.listAll(Sort.by("createdAt"));
    }

    @POST
    @Transactional
    public Post create(Post entity) {
        entity.user = authService.getCurrentSession();
        Post.persist(entity);
        return entity;
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") UUID id) {
        Post.deleteById(id);
    }
}