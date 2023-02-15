package com.challege.javatask.service.user;

import com.challege.javatask.domain.User;
import com.challege.javatask.exceptions.DocumentNotFoundException;
import com.challege.javatask.exceptions.InvalidRequest;

public interface UserService {
    User findByUsernameAndPassword(String username , String password) throws DocumentNotFoundException, InvalidRequest;
}
