package com.example.bankingsystemproject.persistence.entity;

import jakarta.persistence.*;
import lombok.*;
import org.apache.coyote.http11.filters.SavedRequestInputFilter;

import java.util.Set;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Builder
@Table(name = "banking")
public class Banking {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "account_id")
    private Account account;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


//    @OneToMany(mappedBy = "banking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private Set<Account> accounts;

//@OneToMany(mappedBy = "banking", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//private Set<User> users;

    private String accountHolderName;
    private  double amount;
    private double balance;
    private String ibanCardNo;
    private String pinNumber;

    private String Date;


//    public Banking(Long bankingId) {
//
//        bankingId = bankingId;
//    }
}
