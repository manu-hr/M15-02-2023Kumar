package com.challege.javatask.controller;

import com.challege.javatask.domain.User;
import com.challege.javatask.dto.UserDto;
import com.challege.javatask.exceptions.DocumentNotFoundException;
import com.challege.javatask.exceptions.InvalidRequest;
import com.challege.javatask.service.token.SecurityTokenGenerator;
import com.challege.javatask.service.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/service/v1")
@CrossOrigin
public class UserController {

    private UserService userService;
    private SecurityTokenGenerator securityTokenGenerator;

    @Autowired
    public UserController(UserService userService , SecurityTokenGenerator securityTokenGenerator)
    {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/token")
    public ResponseEntity<?> generateToken(@RequestBody UserDto user) throws DocumentNotFoundException, InvalidRequest {
        Map<String, String> map = null;
        User userObj = userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
        if (userObj.getUsername().equals(user.getUsername())) {
            map = securityTokenGenerator.generateToken(userObj);
        }
        return new ResponseEntity<>(map, HttpStatus.OK);
    }

}
