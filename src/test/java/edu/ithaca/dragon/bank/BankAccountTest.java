package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        assertEquals(200, bankAccount.getBalance()); //test when balance returns positive number
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        //edge cases
        bankAccount.withdraw(0);
        assertEquals(100, bankAccount.getBalance());

        bankAccount.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        //equivalence classes
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(1)); //tests to see if it throws when bal < amount
        BankAccount bankAccount2 = new BankAccount("a@b.com", 200);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-10)); //test to see if it throws when amount is negative
        bankAccount2.withdraw(100);
        assertEquals(0, bankAccount.getBalance());

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 1.234));
    }

    @Test
    void isEmailValidTest(){
        //testing prefix
        assertTrue(BankAccount.isEmailValid( "a@b.com")); // Equivalence Class: No special characters and .com domain
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com")); // Equivalence Class: Prefix w/ "."
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com")); // Equivalence Class: Prefix w/ "_"
        assertTrue(BankAccount.isEmailValid("Ross-Doane@gmail.com")); // Equivalence Class: Prefix w/ "-"
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com")); // Equivalence Class: Prefix w/ "#"
        assertFalse(BankAccount.isEmailValid(".abc@mail.com")); // Boarder Case: "." as the first char of the prefix
        assertFalse(BankAccount.isEmailValid("Ross-@gmail.com")); // Boarder Case: "-" as the last char of the prefix
        assertFalse(BankAccount.isEmailValid("Ro..@gmail.com")); // Boarder Case: ".." as the last two chars of the prefix
        
        //testing domain
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc")); // Equivalence Class: Domain w/ acceptable last portion
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com")); // Equivalence Class: Domain w/ "-"
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.org")); // Boarder Case: Domain w/ ".org"
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com")); // Boarder Case: Domain w/ ".com"
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c")); // Equivalence Class: Domain w/ unacceptable last portion
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com")); // Equivalence Class: Domain w/ "#"
        assertFalse(BankAccount.isEmailValid("abc.def@mail")); // Boarder Case: Domain w/o a last portion
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com")); //Boarder Case: Domain w/ extra "." before the last portion
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 1.234));
        
    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(0)); //Edge case: closest to negative you can get
        assertTrue(BankAccount.isAmountValid(1.23)); //Edge case: closest to more than two decimals you can get.

        assertFalse(BankAccount.isAmountValid(-1)); //Equivalence case: Negative
        assertFalse(BankAccount.isAmountValid(1.234)); //Equivalence case: More than two decimal places.        
    }

    @Test
    void depositTest(){
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.deposit(0);

        //edge case
        assertEquals(200, bankAccount.getBalance());

        //equivalence classes
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(-10)); //test to see if it throws when amount is negative
        bankAccount.deposit(200);
        assertEquals(400, bankAccount.getBalance());

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 1.234));
    }

    @Test
    void transferTest() throws InsufficientFundsException {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        BankAccount bankAccount2 = new BankAccount("a@b.com", 0);

        //edge cases
        bankAccount.transfer(bankAccount2, 200);
        assertEquals(0, bankAccount.getBalance());
        assertEquals(200, bankAccount2.getBalance());

        //equivalence classes
        assertThrows(IllegalArgumentException.class, () -> bankAccount.transfer(bankAccount2, -10)); //test to see if it throws when amount is negative
        bankAccount2.transfer(bankAccount, 100);

        assertEquals(100, bankAccount2.getBalance());
        assertEquals(100, bankAccount.getBalance());

        bankAccount2.transfer(bankAccount, 25.25);
        assertEquals(125.25, bankAccount.getBalance());
        assertEquals(74.75, bankAccount2.getBalance());

        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", -1));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("a@b.com", 1.234));
    }
}