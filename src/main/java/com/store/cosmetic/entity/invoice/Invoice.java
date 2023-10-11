package com.store.cosmetic.entity.invoice;

import com.store.cosmetic.entity.UserEntity;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String code;

    private String customerName;
    private String customerEmail;
    private String customerPhoneNumber;
    private String customerAddress;
    private Boolean status;


    @OneToOne(cascade = CascadeType.ALL)

    private CartItem cartItem;

    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private UserEntity customer;

    public Invoice(String code, String customerName, String customerEmail,
                   String customerPhoneNumber, String customerAddress,
                   Boolean status, CartItem cartItem, UserEntity customer) {
        this.code = code;
        this.customerName = customerName;
        this.customerEmail = customerEmail;
        this.customerPhoneNumber = customerPhoneNumber;
        this.customerAddress = customerAddress;
        this.status = status;
        this.cartItem = cartItem;
        this.customer = customer;
    }
}
