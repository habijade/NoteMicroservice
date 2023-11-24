package com.nnk.springboot.exceptions;

public class NoteNotFoundException extends Exception {
    public NoteNotFoundException(String message) {
        super(message);
    }
}
