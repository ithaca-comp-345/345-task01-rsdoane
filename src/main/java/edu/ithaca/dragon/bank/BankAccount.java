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
        if(!isAmountValid(startingBalance)){
            throw new IllegalArgumentException("Amount cannot be negative or over two decimals.");
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
        if(!isAmountValid(amount)){
            throw new IllegalArgumentException("Amount cannot be negative or over two decimals");
        }
        else if (amount <= balance && amount >= 0){
            balance -= amount;
        }
        else {
            throw new InsufficientFundsException("Not enough money");
        }
    }

    public static boolean isAmountValid(double amount){
        if(amount < 0){
            return false;
        }
        String amtStr = ""; 
        amtStr = String.valueOf(amount);
        int i = amtStr.lastIndexOf('.');
        if(i != -1 && amtStr.substring(i + 1).length() == 3) {
         return false;
            }
        else{
            return true;
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
        else if(email.length() -1 == (email.lastIndexOf('.') + 1)) {
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
