package com.ibm.pio.comment;

import java.time.LocalDateTime;
import java.util.UUID;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ibm.pio.post.Post;
import com.ibm.pio.user.User;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Comment extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;
    
    public String comment;

    @ManyToOne
    @JsonBackReference
    public Post post;

    @ManyToOne
    public User user;
    
    @CreationTimestamp
    public LocalDateTime createdAt;
}
