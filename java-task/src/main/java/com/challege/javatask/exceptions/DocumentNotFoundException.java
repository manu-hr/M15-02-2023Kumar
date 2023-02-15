package com.challege.javatask.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND , reason = "No Result")
public class DocumentNotFoundException extends Exception{ }
