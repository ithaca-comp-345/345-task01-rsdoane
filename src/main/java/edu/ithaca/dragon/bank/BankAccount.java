package edu.ithaca.dragon.bank;
import java.util.regex.Matcher;

public class BankAccount {

    private String email;
    private double balance;

    /**
     * @throws IllegalArgumentException if email is invalid
     */
    public BankAccount(String email, double startingBalance){
        if (isEmailValid(email)){
            this.email = email;
            this.balance = startingBalance;
        }
        else {
            throw new IllegalArgumentException("Email address: " + email + " is invalid, cannot create account");
        }
    }

    public double getBalance(){
        return balance;
    }

    public String getEmail(){
        return email;
    }

    /**
     * @post reduces the balance by amount if amount is non-negative and smaller than balance
     */
    public void withdraw (double amount) throws InsufficientFundsException{
        if (amount <= balance){
            balance -= amount;
        }
        else if(amount < 0){
            throw new InsufficientFundsException("Cannot subtract negative money.");
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }


    public static boolean isEmailValid(String email){
         if(!(email.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"))){
            return false;
        }
        else if (email.indexOf('@') == -1){
            return false;
        }
        else if (email.lastIndexOf('.') == (email.indexOf('@') + 1)){
            return false;
        }
        else if (email.lastIndexOf('.') < (email.lastIndexOf('@'))){
            return false;
        }
        else if ((email.length() - 1) == email.lastIndexOf('.')){
            return false;
        }
        else if(email.charAt(email.lastIndexOf('@') - 1) == '-' || email.charAt(email.lastIndexOf('@') - 1) == '_' || email.charAt(email.lastIndexOf('@') - 1) == '.'){
            return false;
        }
        else if(email.charAt(0) == '.' || email.charAt(0) == '-' || email.charAt(0) == '_'){
            return false;
        }
        else if(email.charAt(email.lastIndexOf('.') - 1) == '.' || email.charAt(email.indexOf('.') + 1) == '.'){
            return false;
        }
        else if(email.charAt(email.lastIndexOf('.') - 1) == '-' || email.charAt(email.indexOf('.') + 1) == '-'){
            return false;
        }
        else if(email.charAt(email.lastIndexOf('.') - 1) == '_' || email.charAt(email.indexOf('.') + 1) == '_'){
            return false;
        }
        else if(!doubleDash(email)){
            return false;
        }
        else if(!doubleUnderscore(email)){
            return false;
        }
        else if(email.charAt(email.lastIndexOf('.') + 1) == 't'){
            return false;
        }
        else {
            return true;
        }
    }
    public static boolean doubleDash(String email){
        if(email.indexOf('-') != -1){
            if(email.charAt(email.lastIndexOf('-') - 1) == '-' || email.charAt(email.indexOf('-') + 1) == '-'){
                return false;
            }
            else if(email.charAt(email.lastIndexOf('-') - 1) == '.' || email.charAt(email.indexOf('-') + 1) == '.'){
                return false;
            }
            else if(email.charAt(email.lastIndexOf('-') - 1) == '_' || email.charAt(email.indexOf('-') + 1) == '_'){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }
    public static boolean doubleUnderscore(String email){
        if(email.indexOf('_') != -1){
            if(email.charAt(email.lastIndexOf('_') - 1) == '_' || email.charAt(email.indexOf('_') + 1) == '_'){
                return false;
            }
            else if(email.charAt(email.lastIndexOf('_') - 1) == '-' || email.charAt(email.indexOf('_') + 1) == '-'){
                return false;
            }
            else if(email.charAt(email.lastIndexOf('_') - 1) == '.' || email.charAt(email.indexOf('_') + 1) == '.'){
                return false;
            }
            else{
                return true;
            }
        }
        else{
            return true;
        }
    }
}
