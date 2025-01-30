package com.cims.computerinventorymanagmentsystem.exceptions;

public class AssetNotFoundException extends RuntimeException {
    public AssetNotFoundException(String message) {
        super(message);
    }
}
