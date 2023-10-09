package com.store.cosmetic.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.Set;


@Entity
@Table(name = "invoice")
@Data
@NoArgsConstructor
public class InvoiceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String invoiceNumber;
    private Double totalPrice;
    @Column(columnDefinition = "TEXT")
    private String description;
    private String status;
    @ManyToOne
    @JoinColumn(name = "customer", referencedColumnName = "id")
    private UserEntity customer;
    private Date invoiceDate;
    @OneToMany(mappedBy = "invoiceNumber")
    @JsonIgnore
    private Set<InvoiceItemEntity> invoiceItems;
}
