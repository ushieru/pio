package com.ibm.pio.post;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.ibm.pio.comment.Comment;
import com.ibm.pio.like.Like;
import com.ibm.pio.user.User;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

@Entity
public class Post extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    public String media;

    public String text;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    public List<Like> likes;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference
    public List<Comment> comments;

    @ManyToOne
    public User user;

    @CreationTimestamp
    public LocalDateTime createdAt;
}
