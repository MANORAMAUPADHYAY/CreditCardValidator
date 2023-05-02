package com.example.creditcard;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class CreditCardValidator {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        boolean exit = false;

        while (!exit) {
            try {
                System.out.print("Enter credit card number: ");
                String number = scanner.nextLine();

                if (number.length() < 13) {
                    throw new InvalidCreditCardException("Enter at least 13 digits for credit card number");
                }

                String expiryDateStr = "";
                while (expiryDateStr.isEmpty()) {
                    System.out.print("Enter expiry date (MM/yy): ");
                    expiryDateStr = scanner.nextLine();
                }

                // Parse the expiry date string into a LocalDate object
                LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));

                // Check if expiry date is in the future
                if (expiryDate.isBefore(LocalDate.now())) {
                    throw new InvalidCreditCardException("Card has expired");
                }

                String cvv = "";
                while (cvv.isEmpty()) {
                    System.out.print("Enter CVV code: ");
                    cvv = scanner.nextLine();
                }

                CreditCard card;
                if (number.startsWith("4")) {
                    card = new VisaCard(number, expiryDate, cvv);
                } else if (number.startsWith("5")) {
                    card = new MasterCard(number, expiryDate, cvv);
                } else if (number.startsWith("6")) {
                    card = new DiscoveryCard(number, expiryDate, cvv);
                } else {
                    throw new InvalidCreditCardException("Invalid card number");
                }

                if (!card.isValid()) {
                    throw new InvalidCreditCardException("Invalid card");
                }

                System.out.println("Card type: " + card.getType());
                System.out.println("Card is valid");

                System.out.print("Do you want to check another card? (Y/N): ");
                String input = scanner.nextLine().toUpperCase();
                if (input.equals("N")) {
                    exit = true;
                }
            } catch (InvalidCreditCardException e) {
                System.out.println("Error: " + e.getMessage());
            }
        }
    }
}
