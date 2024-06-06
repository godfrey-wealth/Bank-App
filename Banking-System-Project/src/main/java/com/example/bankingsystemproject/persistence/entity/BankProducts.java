package com.example.bankingsystemproject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "home")
public class BankProducts {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    private String name;
    private String description;

    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private  AccountType type;

    @Lob
    @Column(length = 500000000)
    private byte[] prodImages;
}
