package com.grapplermodule1.GrapplerEnhancement.customxception;

public class UserNotFoundException extends  RuntimeException{
    public UserNotFoundException(String message)
    {
         super(message);
    }
}
