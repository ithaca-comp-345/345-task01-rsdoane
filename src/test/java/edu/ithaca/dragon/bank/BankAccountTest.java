package edu.ithaca.dragon.bank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance());
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance());
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
    }

    @Test
    void isEmailValidTest(){
        //testing prefix
        assertTrue(BankAccount.isEmailValid( "a@b.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc_def@mail.com"));
        assertTrue(BankAccount.isEmailValid("Ross-Doane@gmail.com"));
        assertFalse( BankAccount.isEmailValid("abc#def@mail.com"));
        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("Ross-@gmail.com"));
        assertFalse(BankAccount.isEmailValid("Ro..@gmail.com"));
        
        //testing domain
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.cc"));
        assertTrue(BankAccount.isEmailValid( "abc.def@mail-archive.com"));
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid( "abc.def@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail#archive.com"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance());
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
    }

}