package com.example.springboot.services;

import com.example.springboot.dtos.UserRecordDto;
import com.example.springboot.models.UserModel;
import com.example.springboot.repositories.UserRepository;
import jakarta.persistence.EntityExistsException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;


    public UserModel createUser(UserRecordDto userRecordDto){
        Optional<UserModel> optionalUserModel = userRepository.findUserByEmail(userRecordDto.email());

        if(optionalUserModel.isPresent()){
            throw new EntityExistsException();
        }

        UserModel userModel = new UserModel();
        BeanUtils.copyProperties(userRecordDto,userModel);

        String encryptedPassword = new BCryptPasswordEncoder().encode(userRecordDto.password());
        userModel.setPassword(encryptedPassword);

        return userRepository.save(userModel);
    }
}
