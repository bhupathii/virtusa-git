package com.interest.bean;

import com.interest.exception.InvalidInputException;

public class FDAccount extends Account {
    private int noOfDays;
    private int ageOfACHolder;

    public FDAccount(double amount, int noOfDays, int age) throws InvalidInputException {
        if (amount < 0)
            throw new InvalidInputException("Invalid amount. Please enter non-negative values.");

        if (noOfDays < 0)
            throw new InvalidInputException("Invalid Number of days. Please enter non-negative values.");

        if (age < 0)
            throw new InvalidInputException("Invalid Age. Please enter non-negative values.");

        this.amount = amount;
        this.noOfDays = noOfDays;
        this.ageOfACHolder = age;
    }

    @Override
    public double calculateInterest() {
        if (amount < 10000000) {
            if (noOfDays >= 7 && noOfDays <= 14)
                interestRate = (ageOfACHolder >= 60) ? 5.0 : 4.5;

            else if (noOfDays >= 15 && noOfDays <= 29)
                interestRate = (ageOfACHolder >= 60) ? 5.25 : 4.75;

            else if (noOfDays >= 30 && noOfDays <= 45)
                interestRate = (ageOfACHolder >= 60) ? 6.0 : 5.5;

            else if (noOfDays >= 46 && noOfDays <= 60)
                interestRate = (ageOfACHolder >= 60) ? 7.5 : 7.0;

            else if (noOfDays >= 61 && noOfDays <= 184)
                interestRate = (ageOfACHolder >= 60) ? 8.0 : 7.5;

            else if (noOfDays >= 185 && noOfDays <= 365)
                interestRate = (ageOfACHolder >= 60) ? 8.5 : 8.0;
        } else {
            if (noOfDays >= 7 && noOfDays <= 14)
                interestRate = 6.5;

            else if (noOfDays >= 15 && noOfDays <= 29)
                interestRate = 6.75;

            else if (noOfDays >= 30 && noOfDays <= 45)
                interestRate = 6.75;

            else if (noOfDays >= 46 && noOfDays <= 60)
                interestRate = 8.0;

            else if (noOfDays >= 61 && noOfDays <= 184)
                interestRate = 8.5;

            else if (noOfDays >= 185 && noOfDays <= 365)
                interestRate = 10.0;
        }

        return (amount * interestRate) / 100;
    }
}
