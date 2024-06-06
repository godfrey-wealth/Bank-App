//package com.example.bankingsystemproject.business.impl;
//
//import com.example.bankingsystemproject.business.AccountUseCase;
//import com.example.bankingsystemproject.config.exception.AlreadyExistsExceptionMessage;
//import com.example.bankingsystemproject.dto.AccountRequestDto;
//import com.example.bankingsystemproject.dto.AccountResponseDto;
//import com.example.bankingsystemproject.persistence.AccountRepository;
//import com.example.bankingsystemproject.persistence.UserRepository;
//import com.example.bankingsystemproject.persistence.entity.Account;
//import com.example.bankingsystemproject.persistence.entity.User;
//import lombok.AllArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.Random;
//
//@RequiredArgsConstructor
//@Service
//public class AccountUseCaseImpl implements AccountUseCase {
//
//    private static final String IBAN_PREFIX = "NL";
//    private static final String IBAN_INSTITUTION_IDENTIFIER = "INGB";
//    private static final int IBAN_LENGTH = 18; // Including the prefix and institution identifier
//    private static final int PIN_LENGTH = 6;
//    private static final Random random = new Random();
//
//    private final AccountRepository accountRepository;
//    private final UserRepository userRepository;
//
//    @Override
//    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {
//
//        if (accountRepository.existsByFirstName(accountRequestDto.getFirstName())) {
//            throw new AlreadyExistsExceptionMessage();
//        }
//
//        if (accountRepository.existsByIbanCardno(accountRequestDto.getIbanCardno())) {
//            throw new AlreadyExistsExceptionMessage();
//        }
//
//        if (accountRepository.existsByPinNumber(accountRequestDto.getIbanCardno())) {
//            throw new AlreadyExistsExceptionMessage();
//        }
//
//        // Generate IBAN card number
//        String ibanCardNo = generateIban();
//        // Generate PIN number
//        String pinNumber = generatePin();
//
//        if (!isValidIban(ibanCardNo)) {
//            // Handle invalid IBAN
//            return AccountResponseDto.builder()
//                    .message("Failed to create account: Invalid IBAN")
//                    .status(HttpStatus.BAD_REQUEST)
//                    .build();
//        }
//        if (!isValidPin(pinNumber)) {
//            // Handle invalid PIN
//            return AccountResponseDto.builder()
//                    .message("Failed to create account: Invalid PIN")
//                    .status(HttpStatus.BAD_REQUEST)
//                    .build();
//        }
//
//        // Create and save account
//        Account newAccount = saveNewAccount(accountRequestDto, ibanCardNo, pinNumber);
//
//        // Return success response
//
//        return AccountResponseDto.builder()
//                .message("Account created successfully")
//                .accountId(newAccount.getId())
//                .firstname(newAccount.getFirstName())
//                .pinNumber(newAccount.getPinNumber())
//                .ibanCardno(newAccount.getIbanCardno())
//                .status(HttpStatus.CREATED)
//                .build();
//    }
//
//    private Account saveNewAccount(AccountRequestDto request, String ibanCardNo, String pinNumber) {
//        User userEntity = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));
//
//        Account newAccount = Account.builder()
//                .user(userEntity)
//                .ibanCardno(ibanCardNo)
//                .pinNumber(pinNumber)
//                .firstName(request.getFirstName())
//                .email(request.getEmail())
//                .address(request.getAddress())
//                .zipcode(request.getZipcode())
//                .gender(request.getGender())
//                .passportNo(request.getPassportNo())
//                .type(request.getType())
//                .build();
//
//        return accountRepository.save(newAccount);
//    }
//
//    private String generateIban() {
//        StringBuilder iban = new StringBuilder(IBAN_PREFIX);
//        for (int i = IBAN_PREFIX.length(); i < IBAN_LENGTH - IBAN_INSTITUTION_IDENTIFIER.length(); i++) {
//            iban.append(random.nextInt(10)); // Add random digits
//        }
//        iban.append(IBAN_INSTITUTION_IDENTIFIER); // Add institution identifier
//        return iban.toString();
//    }
//
//    private String generatePin() {
//        StringBuilder pin = new StringBuilder();
//        for (int i = 0; i < PIN_LENGTH; i++) {
//            pin.append(random.nextInt(10)); // Add random digits
//        }
//        return pin.toString();
//    }
//
//    private boolean isValidIban(String iban) {
//        // Perform validation logic for IBAN (if needed)
//        // Example: check length, format, checksum, etc.
//        return true; // Replace with actual validation logic
//    }
//
//    private boolean isValidPin(String pin) {
//        // Perform validation logic for PIN (if needed)
//        // Example: check length, format, etc.
//        return true; // Replace with actual validation logic
//    }
//}


// New CODE

package com.example.bankingsystemproject.business.impl;

import com.example.bankingsystemproject.business.AccountUseCase;
import com.example.bankingsystemproject.business.UserIdValidator;
import com.example.bankingsystemproject.config.exception.AlreadyExistsExceptionMessage;
import com.example.bankingsystemproject.configuration.exception.InvalidUserException;
import com.example.bankingsystemproject.dto.AccessTokenDTO;
import com.example.bankingsystemproject.dto.AccountDTO;
import com.example.bankingsystemproject.dto.AccountRequestDto;
import com.example.bankingsystemproject.dto.AccountResponseDto;
import com.example.bankingsystemproject.persistence.AccountRepository;
import com.example.bankingsystemproject.persistence.BankRepository;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.Account;
import com.example.bankingsystemproject.persistence.entity.Banking;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.persistence.entity.User;
import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Random;

@RequiredArgsConstructor
@Service
public class AccountUseCaseImpl implements AccountUseCase {

    private static final String IBAN_PREFIX = "NL";
    private static final String IBAN_INSTITUTION_IDENTIFIER = "INGB";
    private static final int IBAN_LENGTH = 18; // Including the prefix and institution identifier
    private static final int PIN_LENGTH = 6;
    private static final Random random = new Random();

    private final AccountRepository accountRepository;
    private final UserRepository userRepository;
    private final AccessTokenDTO requestAccessToken;
    private final UserIdValidator userIdValidator;

    private final BankRepository bankRepository;

    @Override
    public AccountResponseDto createAccount(AccountRequestDto accountRequestDto) {

        if (accountRepository.existsByAddress(accountRequestDto.getAddress())) {
            throw new AlreadyExistsExceptionMessage();
        }
//        if (accountRepository.existsById(accountRequestDto.getUserId())) {
//            throw new AlreadyExistsExceptionMessage();
//        }

        if (!requestAccessToken.getUserId().equals(accountRequestDto.getUserId())) {
            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
        }

        userIdValidator.validateId(accountRequestDto.getUserId());

        // Generate IBAN card number
        String ibanCardNo = generateIban();
        // Generate PIN number
        String pinNumber = generatePin();

        if (!isValidIban(ibanCardNo)) {
            // Handle invalid IBAN
            return AccountResponseDto.builder()
                    .message("Failed to create account: Invalid IBAN")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if (!isValidPin(pinNumber)) {
            // Handle invalid PIN
            return AccountResponseDto.builder()
                    .message("Failed to create account: Invalid PIN")
                    .status(HttpStatus.BAD_REQUEST)
                    .build();
        }

        // Create and save account
        Account newAccount = saveNewAccount(accountRequestDto, ibanCardNo, pinNumber);

        // Return success response
        return AccountResponseDto.builder()
                .message("Account created successfully")
                .accountId(newAccount.getId())
                .firstname(newAccount.getFirstName())
                .pinNumber(newAccount.getPinNumber())
                .ibanCardno(newAccount.getIbanCardno())
                .status(HttpStatus.CREATED)
                .build();
    }

    @Override
    public Optional<AccountDTO> getAccountDetails(Long Id) {

        Optional<Account> account = accountRepository.findById(Id);

        if (account.isEmpty()) {
            throw new InvalidUserException("THAT_ORDER_ID_DOES_NOT_EXISTS");
        }

        if (!requestAccessToken.hasRole(RoleEnum.ADMIN.name())) {
            Long userId = requestAccessToken.getUserId();

            // Fetch the order and check if it belongs to the logged-in user
            Optional<Account> userAccount = accountRepository.findByIdAndUserId(Id, userId);

            // If the order doesn't belong to the user, throw UnauthorizedDataAccessException
            if (userAccount.isEmpty()) {
                throw new UnauthorizedDataAccessException("USER_DOES_NOT_HAVE_ACCESS_TO_ACCOUNT");
            }
        }

        return account.map(AccountDTOConverter::convertAccountToDTO);
        //return Optional.empty();
    }

    private Account saveNewAccount(AccountRequestDto request, String ibanCardNo, String pinNumber) {

        User userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));


        Account newAccount = Account.builder()

                .firstName(request.getFirstName())
                .email(request.getEmail())
                .address(request.getAddress())
                .gender(request.getGender())
                .passportNo(request.getPassportNo())
                .type(request.getType())
                .ibanCardno(ibanCardNo)
                .zipcode(request.getZipcode())
                .pinNumber(pinNumber)
                .user(userEntity)
                //.banking(banking)
                .build();

        // Set user and banking IDs
       // newAccount.setUser(new User(request.getUserId()));

        // Save the account entity
        return accountRepository.save(newAccount);
//                .user(userEntity)
//                .ibanCardno(ibanCardNo)
//                .pinNumber(pinNumber)
//                .firstName(request.getFirstName())
//                .email(request.getEmail())
//                .address(request.getAddress())
//                .zipcode(request.getZipcode())
//                .gender(request.getGender())
//                .passportNo(request.getPassportNo())
//                .type(request.getType())
//                .build();

      //  return accountRepository.save(newAccount);
    }

    private String generateIban() {
        StringBuilder iban = new StringBuilder("NL");
        // Generate random two-digit number between 10 and 99
        int randomTwoDigits = random.nextInt(90) + 10;
        iban.append(String.format("%02d", randomTwoDigits)); // Ensure two digits are always present
        iban.append("INGB");
        // Generate 10 random digits
        for (int i = 0; i < 10; i++) {
            iban.append(random.nextInt(10));
        }
        return iban.toString();
    }

    private String generatePin() {
        StringBuilder pin = new StringBuilder();
        for (int i = 0; i < PIN_LENGTH; i++) {
            pin.append(random.nextInt(10)); // Add random digits
        }
        return pin.toString();
    }

    private boolean isValidIban(String iban) {
        // Perform validation logic for IBAN (if needed)
        // Example: check length, format, checksum, etc.
        return true; // Replace with actual validation logic
    }

    private boolean isValidPin(String pin) {
        // Perform validation logic for PIN (if needed)
        // Example: check length, format, etc.
        return true; // Replace with actual validation logic
    }
}
