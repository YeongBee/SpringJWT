package com.example.springjwt.service;

import com.example.springjwt.entity.UserEntity;
import com.example.springjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final UserRepository userRepository;

    public Boolean login(String username, String password){

        UserEntity user = userRepository.findByUsername(username);

        if(user != null){
            return true;
        }

        return false;
    }

}
