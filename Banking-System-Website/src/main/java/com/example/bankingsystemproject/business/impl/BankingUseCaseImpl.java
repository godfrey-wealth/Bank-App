//package com.example.bankingsystemproject.business.impl;
//
//import com.example.bankingsystemproject.business.BankingUseCase;
//import com.example.bankingsystemproject.business.UserIdValidator;
//import com.example.bankingsystemproject.config.exception.AlreadyExistsExceptionMessage;
//import com.example.bankingsystemproject.configuration.exception.InvalidUserException;
//import com.example.bankingsystemproject.dto.*;
//import com.example.bankingsystemproject.persistence.AccountRepository;
//import com.example.bankingsystemproject.persistence.BankRepository;
//import com.example.bankingsystemproject.persistence.UserRepository;
//import com.example.bankingsystemproject.persistence.entity.Account;
//import com.example.bankingsystemproject.persistence.entity.Banking;
//import com.example.bankingsystemproject.persistence.entity.RoleEnum;
//import com.example.bankingsystemproject.persistence.entity.User;
//import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
//import com.example.bankingsystemproject.security.auth.util.DateUtil;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//import java.util.Optional;
//import java.util.stream.Collectors;
//
//@RequiredArgsConstructor
//@Service
//public class BankingUseCaseImpl implements BankingUseCase {
//
//    private final BankRepository bankRepository;
//    private final UserRepository userRepository;
//    private final AccountRepository accountRepository;
//    private final AccessTokenDTO requestAccessToken;
//    private final UserIdValidator userIdValidator;
//
//    @Override
//    public BankingRequestDto createBank(BankingRequestDto request) {
//
//        if (bankRepository.existsByIbanCardNo(request.getIbanCardNo())) {
//            throw new AlreadyExistsExceptionMessage();
//        }
//
//        if (!requestAccessToken.getUserId().equals(request.getUserId())) {
//            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
//        }
//
//        userIdValidator.validateId(request.getUserId());
//
//
//        // Create and save account
//        Banking newBank = saveNewBank(request);
//
//        // Return success response
//        return BankingRequestDto.builder()
//               // .message("Bank created successfully")
//                .Id(newBank.getId())
//               // .accountId(newBank.getAccount().getId())
//                .accountHolderName(newBank.getAccountHolderName())
//                .ibanCardNo(newBank.getIbanCardNo())
//                .balance(newBank.getBalance())
//                .Date(setDate(DateUtil.getCurrentDateTime()))
//                .status(HttpStatus.CREATED)
//                .build();
//    }
//
//
//
//    private Banking saveNewBank(BankingRequestDto request) {
//        Account account = accountRepository.findById(request.getAccountId())
//       // User userEntity = userRepository.findById(request.getUserId())
//                .orElseThrow(() -> new IllegalArgumentException("account not found with id: " + request.getAccountId()));
//
//         User userEntity = userRepository.findById(request.getUserId())
//                 .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));
//
//
//        Banking newAccount = Banking.builder()
//                //.id(request.getId())
//                .account(account)
//                .user(userEntity)
//                .accountHolderName(request.getAccountHolderName())
//                .ibanCardNo(request.getIbanCardNo())
//                .pinNumber(request.getPinNumber())
//                .balance(request.getBalance())
//                .Date(DateUtil.getCurrentDateTime())
//
//                .build();
//
//        return bankRepository.save(newAccount);
//    }
//
//    private String setDate(String dateTime) {
//        // Your logic to set the date as a string
//        return dateTime;
//    }
//
//
//
//    @Override
//    public BankingDTO getBankById(Long id) {
//
//        AccountRequestDto requestDto = new AccountRequestDto();
//
//        if (!requestAccessToken.getUserId().equals(requestDto.getUserId())) {
//            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
//        }
//
//        Optional<Banking> bankingOptional = bankRepository.findById(id);
//        return bankingOptional.map(BankingDTO::new).orElse(null);
//    }
//
//    @Override
//    public BankingDTO deposit(Long bankId, double amount) {
//        AccountRequestDto requestDto = new AccountRequestDto();
//        Optional<Banking> bankingOptional = bankRepository.findById(bankId);
//        if (!requestAccessToken.getUserId().equals(requestDto.getUserId())) {
//            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
//        }
//        if (bankingOptional.isPresent()) {
//            Banking banking = bankingOptional.get();
//            banking.setBalance(banking.getBalance() + amount);
//            bankRepository.save(banking);
//            return new BankingDTO(banking);
//        }
//        return null;
//    }
//
//    @Override
//    public BankingDTO withdraw(Long bankId, double amount) {
//        Optional<Banking> bankingOptional = bankRepository.findById(bankId);
//        AccountRequestDto requestDto = new AccountRequestDto();
//
//        if (!requestAccessToken.getUserId().equals(requestDto.getUserId())) {
//            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
//        }
//        if (bankingOptional.isPresent()) {
//            Banking banking = bankingOptional.get();
//            double newBalance = banking.getBalance() - amount;
//            if (newBalance >= 0) {
//                banking.setBalance(newBalance);
//                bankRepository.save(banking);
//                return new BankingDTO(banking);
//            }
//        }
//        return null;
//    }
//
//    @Override
//    public List<BankingDTO> getAllBanks() {
//        List<Banking> banks = bankRepository.findAll();
//        return banks.stream().map(BankingDTO::new).collect(Collectors.toList());
//    }
//
//    @Override
//    public void deleteAccount(Long id) {
//        accountRepository.deleteById(id);
//    }
//
//    @Override
//    public BankingDTO transferAmount(Long senderId, Long recipientId, double amount) {
//        Optional<Banking> senderOptional = bankRepository.findById(senderId);
//        Optional<Banking> recipientOptional = bankRepository.findById(recipientId);
//        if (senderOptional.isPresent() && recipientOptional.isPresent()) {
//            Banking sender = senderOptional.get();
//            Banking recipient = recipientOptional.get();
//            double senderNewBalance = sender.getBalance() - amount;
//            if (senderNewBalance >= 0) {
//                sender.setBalance(senderNewBalance);
//                recipient.setBalance(recipient.getBalance() + amount);
//                bankRepository.save(sender);
//                bankRepository.save(recipient);
//                return new BankingDTO(sender);
//            }
//        }
//        return null;
//    }
//}

package com.example.bankingsystemproject.business.impl;

import com.example.bankingsystemproject.business.BankingUseCase;
import com.example.bankingsystemproject.business.UserIdValidator;
import com.example.bankingsystemproject.config.exception.AlreadyExistsExceptionMessage;
import com.example.bankingsystemproject.configuration.exception.InvalidUserException;
import com.example.bankingsystemproject.dto.*;
import com.example.bankingsystemproject.persistence.AccountRepository;
import com.example.bankingsystemproject.persistence.BankRepository;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.Account;
import com.example.bankingsystemproject.persistence.entity.Banking;
import com.example.bankingsystemproject.persistence.entity.User;
import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
import com.example.bankingsystemproject.security.auth.util.DateUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class BankingUseCaseImpl implements BankingUseCase {

    private final BankRepository bankRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;
    private final AccessTokenDTO requestAccessToken;
    private final UserIdValidator userIdValidator;

    @Override
    public BankingRequestDto createBank(BankingRequestDto request) {
        if (bankRepository.existsByIbanCardNo(request.getIbanCardNo())) {
            throw new AlreadyExistsExceptionMessage();
        }

        if (!requestAccessToken.getUserId().equals(request.getUserId())) {
            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
        }

        userIdValidator.validateId(request.getUserId());

        Banking newBank = saveNewBank(request);

        return BankingRequestDto.builder()
                .Id(newBank.getId())
                .accountHolderName(newBank.getAccountHolderName())
                .ibanCardNo(newBank.getIbanCardNo())
                .balance(newBank.getBalance())
                .Date(setDate(DateUtil.getCurrentDateTime()))
                .status(HttpStatus.CREATED)
                .build();
    }

    private Banking saveNewBank(BankingRequestDto request) {
        Account account = accountRepository.findById(request.getAccountId())
                .orElseThrow(() -> new IllegalArgumentException("account not found with id: " + request.getAccountId()));

        User userEntity = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));

        Banking newAccount = Banking.builder()
                .account(account)
                .user(userEntity)
                .accountHolderName(request.getAccountHolderName())
                .ibanCardNo(request.getIbanCardNo())
                .pinNumber(request.getPinNumber())
                .balance(request.getBalance())
                .Date(DateUtil.getCurrentDateTime())
                .build();

        return bankRepository.save(newAccount);
    }

    private String setDate(String dateTime) {
        return dateTime;
    }

    @Override
    public BankingDTO getBankById(Long id) {
        Optional<Banking> bankingOptional = bankRepository.findById(id);

        if (bankingOptional.isPresent() && bankingOptional.get().getUser().getId().equals(requestAccessToken.getUserId())) {
            return bankingOptional.map(BankingDTO::new).orElse(null);
        } else {
            throw new UnauthorizedDataAccessException("UNAUTHORIZED_ACCESS_TO_BANK_INFORMATION");
        }
    }

    @Override
    public BankingDTO deposit(Long bankId, double amount) {
        Optional<Banking> bankingOptional = bankRepository.findById(bankId);

        if (bankingOptional.isPresent() && bankingOptional.get().getUser().getId().equals(requestAccessToken.getUserId())) {
            Banking banking = bankingOptional.get();

            // Set the new amount
            banking.setAmount(amount);
            banking.setDate(banking.getDate()); // set Date

            banking.setBalance(banking.getBalance() + amount);
           // banking.setAmount(banking.getAmount());

           // banking.setBalance(banking.getBalance() + banking.getAmount());
            bankRepository.save(banking);
            return new BankingDTO(banking);
        } else {
            throw new UnauthorizedDataAccessException("UNAUTHORIZED_DEPOSIT_OPERATION");
        }
    }

    @Override
    public BankingDTO withdraw(Long bankId,  double amount) {
        Optional<Banking> bankingOptional = bankRepository.findById(bankId);

        if (bankingOptional.isPresent() && bankingOptional.get().getUser().getId().equals(requestAccessToken.getUserId())) {
            Banking banking = bankingOptional.get();
           // banking = bankingOptional.get();
            banking.setAmount(amount);// set the Amount;
            banking.setDate(banking.getDate()); // Ste the Date too
            double newBalance = banking.getBalance() - amount;
            if (newBalance >= 0) {
                banking.setBalance(newBalance);
                bankRepository.save(banking);
                return new BankingDTO(banking);
            } else {
                throw new IllegalArgumentException("Insufficient funds");
            }
        } else {
            throw new UnauthorizedDataAccessException("UNAUTHORIZED_WITHDRAW_OPERATION");
        }
    }

    @Override
    public List<BankingDTO> getAllBanks() {
        List<Banking> banks = bankRepository.findAll();
        return banks.stream().map(BankingDTO::new).collect(Collectors.toList());
    }

    @Override
    public void deleteAccount(Long id) {
        accountRepository.deleteById(id);
    }



    // Helper method to check authorization
    private boolean isAuthorized(Long bankId) {
        // Implement your authorization logic here, e.g., check if the bank belongs to the logged-in user
        // For demonstration, let's assume that the user has access to all banks (replace it with your actual logic)
        return true;
    }

    @Override
    public BankingDTO transferAmount(Long senderId, Long recipientId, double amount) {

//        if (!isAuthorized(senderId)) {
//            throw new UnauthorizedDataAccessException("UNAUTHORIZED_TRANSFER_OPERATION");
//        }
       // Optional<User> userOptional = userRepository.findById(senderId);
        Optional<Banking> senderOptional = bankRepository.findById(senderId);
        Optional<Banking> recipientOptional = bankRepository.findById(recipientId);

        if(!senderOptional.get().getUser().getId().equals(requestAccessToken.getUserId())){
            throw new UnauthorizedDataAccessException("UNAUTHORIZED_TRANSFER_OPERATION");
        }

        if (senderOptional.isPresent() && recipientOptional.isPresent()) {
            Banking sender = senderOptional.get();
            Banking recipient = recipientOptional.get();

            sender.setAmount(amount); // set Amount
            double senderNewBalance = sender.getBalance() - amount;

            sender.getAccountHolderName();
            sender.getIbanCardNo();

            sender.getUser().getUserImages();

            if (senderNewBalance >= 0) {
                sender.setBalance(senderNewBalance);

                sender.setAmount(amount); // amount is set When money is transfer
                recipient.setBalance(recipient.getBalance() + amount);
                sender.getAccountHolderName();
                sender.getIbanCardNo();
                bankRepository.save(sender);
                bankRepository.save(recipient);
                return new BankingDTO(sender);
            } else {
                throw new IllegalArgumentException("Insufficient funds for transfer");
            }
        } else {
            throw new IllegalArgumentException("Sender or recipient bank not found");
        }
    }
}
