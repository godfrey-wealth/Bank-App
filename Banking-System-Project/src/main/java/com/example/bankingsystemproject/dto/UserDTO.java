package com.example.bankingsystemproject.dto;

import com.example.bankingsystemproject.persistence.entity.User;
import com.example.bankingsystemproject.persistence.entity.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id;
    private String email;
    private String firstname;
    private String lastname;
    private String username;

    private String password;


    private UserRoleDTO role;

    @Lob
    @Column(length = 50000000)
    private byte[] userImages;

    public UserDTO(User user) {

        this.id = user.getId();
        this.firstname = user.getFirstname();
        this.lastname = user.getLastname();
        this.username = user.getUsername();
        this.email = user.getEmail();
        this.userImages = user.getUserImages();

    }
}
