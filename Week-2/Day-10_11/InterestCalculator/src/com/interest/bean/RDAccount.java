package com.interest.bean;

import com.interest.exception.InvalidInputException;

public class RDAccount extends Account {
    private int noOfMonths;
    private int age;

    public RDAccount(double monthlyAmount, int months, int age) throws InvalidInputException {
        if (monthlyAmount < 0)
            throw new InvalidInputException("Invalid monthly amount. Please enter non-negative values.");

        if (months < 0)
            throw new InvalidInputException("Invalid months. Please enter non-negative values.");

        if (months != 6 && months != 9 && months != 12 && months != 15 && months != 18 && months != 21)
            throw new InvalidInputException("Invalid months. Please enter 6, 9, 12, 15, 18, or 21 months.");

        if (age < 0)
            throw new InvalidInputException("Invalid age. Please enter non-negative values.");

        this.amount = monthlyAmount;
        this.noOfMonths = months;
        this.age = age;
    }

    @Override
    public double calculateInterest() {
        switch (noOfMonths) {
            case 6:
                interestRate = (age >= 60) ? 8.0 : 7.5;
                break;
            case 9:
                interestRate = (age >= 60) ? 8.25 : 7.75;
                break;
            case 12:
                interestRate = (age >= 60) ? 8.5 : 8.0;
                break;
            case 15:
                interestRate = (age >= 60) ? 8.75 : 8.25;
                break;
            case 18:
                interestRate = (age >= 60) ? 9.0 : 8.5;
                break;
            case 21:
                interestRate = (age >= 60) ? 9.25 : 8.75;
                break;
            default:
                interestRate = 0;
        }

        return (amount * interestRate) / 100;
    }
}
