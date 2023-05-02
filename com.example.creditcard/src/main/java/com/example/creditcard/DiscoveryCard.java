package com.example.creditcard;

import java.time.LocalDate;

public class DiscoveryCard extends CreditCard {
	public DiscoveryCard(String number, LocalDate expiryDate, String cvv) throws InvalidCreditCardException {
        super(number, expiryDate, cvv);
        if (!number.startsWith("6")) {
            throw new InvalidCreditCardException("Invalid Discovery card number");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidCreditCardException("Card has expired");
        }
    }

    @Override
    public boolean isValid() {
        if (this.getNumber().length() != 16 || !this.getNumber().startsWith("6")) {
            return false;
        }

        LocalDate today = LocalDate.now();
        if (this.getExpiryDate().isBefore(today)) {
            return false;
        }

        if (this.getCvv().length() != 3 || !this.getCvv().matches("\\d+")) {
            return false;
        }

        if (!isLuhnValid(getNumber())) {
            return false;
        }

        return true;
    }

    @Override
    public String getType() {
        return "Discover";
    }
}

