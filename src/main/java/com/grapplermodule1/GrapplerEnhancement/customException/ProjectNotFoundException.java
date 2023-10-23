package com.grapplermodule1.GrapplerEnhancement.customException;

public class ProjectNotFoundException extends RuntimeException{
    public ProjectNotFoundException(String message)
    {
        super(message);
    }
}
