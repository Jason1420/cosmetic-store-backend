package com.store.cosmetic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.store.cosmetic.entity.invoice.Invoice;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String username;
    private String password;
    private Boolean locked = false;
    private Boolean enabled = false;
    private Boolean changedPassword = false;
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @OneToMany(mappedBy = "customer")
    @JsonIgnore
    private Set<Invoice> invoices;

    @OneToOne(mappedBy = "users")
    @JsonIgnore
    private Customer customer;


    public UserEntity(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public UserEntity(String username) {
        this.username = username;
    }

    public UserEntity(String username, String password, Set<Role> roles, Customer customer) {
        this.username = username;
        this.password = password;
        this.roles = roles;
        this.customer = customer;
    }
}
