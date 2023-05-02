package com.example.creditcard;

import org.junit.Test;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CreditCardValidatorTest {

    @Test
    public void testVisaCard() throws InvalidCreditCardException {
        String number = "4111111111111111";
        String expiryDateStr = "12/25";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "123";
        CreditCard card = new VisaCard(number, expiryDate, cvv);
        assertTrue(card.isValid());
        assertEquals("Visa", card.getType());
    }

    @Test
    public void testMasterCard() throws InvalidCreditCardException {
        String number = "5105105105105100";
        String expiryDateStr = "05/24";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "456";
        CreditCard card = new MasterCard(number, expiryDate, cvv);
        assertTrue(card.isValid());
        assertEquals("MasterCard", card.getType());
    }

    @Test(expected = InvalidCreditCardException.class)
    public void testInvalidCardNumber() throws InvalidCreditCardException {
        String number = "1234567890123";
        String expiryDateStr = "01/23";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "789";
        CreditCard card = new DiscoveryCard(number, expiryDate, cvv);
    }

    @Test(expected = InvalidCreditCardException.class)
    public void testExpiredCard() throws InvalidCreditCardException {
        String number = "6011111111111117";
        String expiryDateStr = "01/20";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "234";
        CreditCard card = new DiscoveryCard(number, expiryDate, cvv);
    }
    @Test
    public void testValidVisaCard() throws InvalidCreditCardException {
        String number = "4012888888881881";
        String expiryDateStr = "12/25";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "123";
        CreditCard card = new VisaCard(number, expiryDate, cvv);
        assertTrue(card.isValid());
    }
    @Test
    public void testValidMasterCard() throws InvalidCreditCardException {
        String number = "5555555555554444";
        String expiryDateStr = "12/25";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "123";
        CreditCard card = new MasterCard(number, expiryDate, cvv);
        assertTrue(card.isValid());
    }
    @Test
    public void testValidDiscoveryCard() throws InvalidCreditCardException {
        String number = "6011111111111117";
        String expiryDateStr = "12/25";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "123";
        CreditCard card = new DiscoveryCard(number, expiryDate, cvv);
        assertTrue(card.isValid());
    }
    @Test(expected = InvalidCreditCardException.class)
    public void testExpiryDateInPast() throws InvalidCreditCardException {
        String number = "4111111111111111";
        String expiryDateStr = "01/20";
        LocalDate expiryDate = LocalDate.parse(expiryDateStr + "/01", DateTimeFormatter.ofPattern("MM/yy/dd"));
        String cvv = "123";
        CreditCard card = new VisaCard(number, expiryDate, cvv);
    }
  
    





}
