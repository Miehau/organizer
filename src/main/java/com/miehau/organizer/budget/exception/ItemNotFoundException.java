package com.miehau.organizer.budget.exception;

public class ItemNotFoundException extends Exception {
    public ItemNotFoundException() {
        super();
    }

    public ItemNotFoundException(String message) {
        super(message);
    }
}
