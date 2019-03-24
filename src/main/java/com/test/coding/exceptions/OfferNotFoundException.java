package com.test.coding.exceptions;

public class OfferNotFoundException extends Exception {
    String message;

    public OfferNotFoundException(String no_offer_found) {
        super(no_offer_found);
        this.message = no_offer_found;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
