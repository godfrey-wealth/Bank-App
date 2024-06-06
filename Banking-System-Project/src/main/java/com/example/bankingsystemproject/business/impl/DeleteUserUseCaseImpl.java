package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.DeleteUserUseCase;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.User;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DeleteUserUseCaseImpl implements DeleteUserUseCase {

    @Autowired
    private UserRepository userRepository;


    @Override
    @Transactional
    public void deleteUser(Long id) {
        Optional<User> deleteUser = userRepository.findById(id);

        userRepository.deleteById(deleteUser.get().getId());

    }
}
