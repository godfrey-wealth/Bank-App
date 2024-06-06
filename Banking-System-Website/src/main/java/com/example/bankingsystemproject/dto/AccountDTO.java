package com.example.bankingsystemproject.dto;

import com.example.bankingsystemproject.persistence.entity.Account;
import com.example.bankingsystemproject.persistence.entity.AccountType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {

    private Long id;
    private String firstName;
    private String email;
    private String address;
    private String gender;
    private String passportNo;

    @Column(name="accountType")
    @Enumerated(EnumType.STRING)
    private AccountType type;
    private String zipcode;

   // @NotBlank(message = "keep numbers and letters only")
    @Pattern(regexp = "^NL\\d{2}INGB\\d{10}$", message = "Invalid IBAN card number format. It should start with 'NL' followed by 2 digits, 'INGB', and 10 digits.")
    @Length(min = 14, max = 18)
    private String ibanCardno;

    private String pinNumber;

    private UserDTO user;

    private BankingDTO bankingDTO;


    public AccountDTO(Account account) {
        if (account.getUser() != null) {
            this.user = new UserDTO(account.getUser());
        }
        this.id = account.getId();
        this.firstName = account.getFirstName();
        this.email = account.getEmail();
        this.address = account.getAddress();
        this.gender = account.getGender();
        this.passportNo = account.getPassportNo();
        this.type = account.getType();
        this.zipcode = account.getZipcode();
        this.ibanCardno = account.getIbanCardno();
        this.pinNumber = account.getPinNumber();
    }

}
