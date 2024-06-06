package com.example.bankingsystemproject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Entity
@Builder
@Table(name = "accounts")
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

//    @ManyToOne
//    @JoinColumn(name = "banking_id")
//    private Banking banking;
//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private User user;

    private String firstName;
    private String email;
    private String address;
    private String gender;
    private String passportNo;
    @Column(name="type")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String ibanCardno;
    private String zipcode;
    private String pinNumber;


    // Getters and setters
}

