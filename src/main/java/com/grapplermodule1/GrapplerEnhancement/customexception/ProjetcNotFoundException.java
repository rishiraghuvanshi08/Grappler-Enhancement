package com.grapplermodule1.GrapplerEnhancement.customexception;

public class ProjetcNotFoundException extends RuntimeException{
    public ProjetcNotFoundException(String message) {
        super(message);
    }
}
