package com.example.creditcard;

import java.time.LocalDate;

public class MasterCard extends CreditCard {
	public MasterCard(String number, LocalDate expiryDate, String cvv) throws InvalidCreditCardException {
        super(number, expiryDate, cvv);
        if (!number.startsWith("5")) {
            throw new InvalidCreditCardException("Invalid Discovery card number");
        }
        if (expiryDate.isBefore(LocalDate.now())) {
            throw new InvalidCreditCardException("Card has expired");
        }
    }

    @Override
    public boolean isValid() {
        // Check that the card number starts with 5.
        if (!getNumber().startsWith("5")) {
            return false;
        }

        // Check that the card number is 16 digits long.
        if (getNumber().length() != 16) {
            return false;
        }

        // Perform the Luhn algorithm to validate the card number.
        if (!isLuhnValid(getNumber())) {
            return false;
        }

        // Check that the expiry date is not in the past.
        LocalDate now = LocalDate.now();
        LocalDate expiryDate = getExpiryDate().withDayOfMonth(getExpiryDate().lengthOfMonth());
        if (expiryDate.isBefore(now)) {
            return false;
        }

        return true;
    }

    @Override
    public String getType() {
        return "MasterCard";
    }
}
