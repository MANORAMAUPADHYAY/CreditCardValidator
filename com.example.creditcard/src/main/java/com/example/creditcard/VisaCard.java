package com.example.creditcard;

import java.time.LocalDate;

public class VisaCard extends CreditCard {
	public VisaCard(String number, LocalDate expiryDate, String cvv) throws InvalidCreditCardException {
        super(number, expiryDate, cvv);
        if (!number.startsWith("4")) {
            throw new InvalidCreditCardException("Invalid Discovery card number");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidCreditCardException("Card has expired");
        }
    }

    @Override
    public boolean isValid() {
        // Check if the card number is valid using Luhn algorithm
        if (!isLuhnValid(getNumber())) {
            return false;
        }

        // Check if the card type is valid (starts with '4')
        if (!getNumber().startsWith("4")) {
            return false;
        }

        // Check if the expiry date is valid (not expired)
        LocalDate today = LocalDate.now();
        if (today.isAfter(getExpiryDate())) {
            return false;
        }

        // Check if the CVV is valid (3 or 4 digits)
        String cvv = getCvv();
        if (cvv == null || cvv.length() < 3 || cvv.length() > 4 || !cvv.matches("\\d+")) {
            return false;
        }

        // If all checks pass, the card is valid
        return true;
    }

    @Override
    public String getType() {
        return "Visa";
    }
}
