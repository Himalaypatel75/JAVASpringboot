package com.skylink.gympro.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "users")

public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userId;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phoneNo;
    @Column(name = "is_deleted")
    private boolean deleted = false; // default value is false
    @Enumerated(EnumType.STRING)
    private UserType type;

    public void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return this.deleted;
    }
}

enum UserType {
    CUSTOMER,
    ADMIN
}