package com.interest.bean;
import com.interest.exception.InvalidInputException;
public class SBAccount extends Account {
    private String accountType;
    public SBAccount(double amount, String accountType) throws InvalidInputException {
        if (amount < 0)
            throw new InvalidInputException("Invalid amount. Please enter non-negative values.");
        this.amount = amount;
        this.accountType = accountType;
    }

    @Override
    public double calculateInterest() {
        if (accountType.equalsIgnoreCase("Normal")) {
            interestRate = 4.0;
        } else if (accountType.equalsIgnoreCase("NRI")) {
            interestRate = 6.0;
        }
        return (amount * interestRate) / 100;
    }
}
