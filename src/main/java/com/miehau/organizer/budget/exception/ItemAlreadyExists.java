package com.miehau.organizer.budget.exception;

public class ItemAlreadyExists extends Exception {
    public ItemAlreadyExists() {
        super();
    }

    public ItemAlreadyExists(String message) {
        super(message);
    }

}
