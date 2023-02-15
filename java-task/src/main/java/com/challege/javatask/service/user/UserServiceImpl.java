package com.challege.javatask.service.user;

import com.challege.javatask.domain.User;
import com.challege.javatask.exceptions.DocumentNotFoundException;
import com.challege.javatask.exceptions.InvalidRequest;
import com.challege.javatask.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;


    @Override
    public User findByUsernameAndPassword(String username, String password) throws DocumentNotFoundException, InvalidRequest {
        if(username.equals("") || password.equals(""))
            throw new InvalidRequest();
        User user =  userRepository.findByUsernameAndPassword(username , password);
        if(user == null){
            throw new DocumentNotFoundException();
        }
        return user;
    }
}
