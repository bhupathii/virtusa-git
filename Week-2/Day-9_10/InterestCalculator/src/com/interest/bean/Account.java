package com.interest.bean;

public abstract class Account {
    protected double amount;
    protected double interestRate;

    public abstract double calculateInterest();
}
