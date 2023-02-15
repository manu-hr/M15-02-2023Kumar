package com.challege.javatask.service.token;

import com.challege.javatask.domain.User;

import java.util.Map;

public interface SecurityTokenGenerator {

  Map<String,String> generateToken(User user);
}
