package com.foodsaver.foodsaver_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Enumerated(EnumType.STRING)
    private OrganizationType organizationType;

    public enum Role {
        ADMIN, USER
    }

    public enum OrganizationType {
        MARRIAGE_HALL,
        PARTY_HALL,
        NGO,
        TRUST,
        ORPHANAGE
    }

    // getters & setters
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public Role getRole() { return role; }
}
