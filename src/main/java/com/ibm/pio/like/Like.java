package com.ibm.pio.like;

import java.util.UUID;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.ibm.pio.post.Post;
import com.ibm.pio.user.User;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name = "like_", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "post", "user" })
})
public class Like extends PanacheEntityBase {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    public UUID id;

    @ManyToOne
    @JsonBackReference
    public Post post;

    @ManyToOne
    public User user;
}
