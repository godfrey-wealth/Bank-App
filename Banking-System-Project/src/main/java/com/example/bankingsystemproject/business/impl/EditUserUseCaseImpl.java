package com.example.bankingsystemproject.business.impl;



import com.example.bankingsystemproject.business.EditUserUseCase;
import com.example.bankingsystemproject.configuration.exception.InvalidUserException;
import com.example.bankingsystemproject.dto.AccessTokenDTO;
import com.example.bankingsystemproject.dto.EditUserRequestDTO;
import com.example.bankingsystemproject.persistence.UserRepository;
import com.example.bankingsystemproject.persistence.entity.RoleEnum;
import com.example.bankingsystemproject.persistence.entity.User;
import com.example.bankingsystemproject.security.auth.UnauthorizedDataAccessException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EditUserUseCaseImpl implements EditUserUseCase {
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    private  final AccessTokenDTO requestAccessToken;

//    @Override
//    public User editUser(EditUserRequestDTO request, Long Id) {
//
//        return userRepository.findById(Id)
//
//                .map(user->{
//
//                    user.setFirstname(request.getFirstname());
//                    user.setLastname(request.getLastname());
//                    user.setUsername(request.getUsername());
//                    user.setEmail(request.getEmail());
//                    user.setPassword(request.getPassword());
//                    //user.setRole(request.getRole());
//
//                    return  userRepository.save(user);
//                }).orElseThrow( ()->new userNotFoundException(Id));
//
//    }

//    @Transactional
//    @Override
//    public EditUserResponseDTO editUser(EditUserRequestDTO request) {
        //String encodedPassword = passwordEncoder.encode(request.getPassword());

//        if (requestAccessToken.getUserId() != request.getId()){
//            throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
//        }

//        User editUser = User.builder().id(request.getId()).email(request.getEmail()).firstname(request.getFirstname()).lasttname(request.getLasttname())
//                .username(request.getUsername()).password(request.getPassword())
//
//                .role(request.getRole()).build();

        //editUser.setUserRoles(Set.of(UserRole.builder().id(request.getId()).user(editUser).role(RoleEnum.STUDENT).build()));

        //User getUser = userRepository.save(editUser)
        //return EditUserResponseDTO.builder().id(getUser.getId()).email(getUser.getEmail()).userame(request.getUsername())

        //.firstname(getUser.getFirstname()).password(request.getPassword()).build();
        //}
        //}
        public void editUser(EditUserRequestDTO request) {
            String encodedPassword = passwordEncoder.encode(request.getPassword());
            Optional<User> studentOptional = userRepository.findById(request.getId());
            if (studentOptional.isEmpty()) {
                throw new InvalidUserException("STUDENT_ID_DOES_NOT_EXIST");
            }
            if(!requestAccessToken.hasRole(RoleEnum.CUSTOMER.name())) {
                if (requestAccessToken.getUserId() != request.getId()) {
                    throw new UnauthorizedDataAccessException("STUDENT_ID_NOT_FROM_LOGGED_IN_USER");
                }
            }
            User student = studentOptional.get();
            updateFields(request, student);
        }





    private void updateFields(EditUserRequestDTO request, User newUser) {
      //  student.setCountry(CountryEntity.builder().id(request.getCountryId()).build());
        newUser.setFirstname(request.getFirstname());
        newUser.setLastname(request.getLastname());
        newUser.setUsername(request.getUsername());
        newUser.setEmail(request.getEmail());
        newUser.setPassword(request.getPassword());
        userRepository.save(newUser);
    }

}