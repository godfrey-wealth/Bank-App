package com.example.bankingsystemproject.persistence.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.hibernate.validator.constraints.Length;

import java.util.Set;

@Entity
@Table(name = "customer")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
      //  @Column(name = "id")

    @NotBlank(message = "Firstname cannot be empty")
    @Column(name = "firstname")
    @Length(max = 50)
    private String firstname;



    @NotBlank(message = "lastname cannot be empty")
    @Column(name = "lasttname")

    @Length(max = 50)
    private String lastname;

//    @NotBlank(message = "Username cannot be empty")
    @Column(name = "username")
    @Length(min = 6, max = 50)

    private String username;

    @NotBlank(message = "Email cannot be empty")
    @Email(message = "Invalid email Address Provided")
    @Column(name = "email")
    private String email;



    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

//    @NotBlank(message = "Password must be minimal 8 character")
    @Column(name = "password")

//    @Length(min =8, max= 50)
    private String  password;

    @Lob
    @Column(length = 50000000)
    private byte[] userImages;

//    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
//    private Set<Account> accounts;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, orphanRemoval = true)
    @JoinColumn(name = "user_id")
   private Set<UserRole> userRoles;

//    @ManyToOne
//    @JoinColumn(name = "user_id")
//    private Banking banking;


}

