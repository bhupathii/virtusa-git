package com.interest.test;

import com.interest.bean.*;
import com.interest.exception.InvalidInputException;

public class InterestCalculatorTest {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void main(String[] args) {
        System.out.println("===== INTEREST CALCULATOR TEST SUITE =====\n");
        
        testSBAccount();
        testFDAccount();
        testRDAccount();
        
        printResults();
    }

    private static void testSBAccount() {
        System.out.println("--- Testing SB Account ---");
        
        // Test 1: Normal account
        try {
            SBAccount sb1 = new SBAccount(5000, "Normal");
            double interest = sb1.calculateInterest();
            double expected = 200.0; // 5000 * 4.0 / 100
            assertTrue("SB Normal 5000", Math.abs(interest - expected) < 0.01);
        } catch (Exception e) {
            fail("SB Normal account failed: " + e.getMessage());
        }

        // Test 2: NRI account
        try {
            SBAccount sb2 = new SBAccount(10000, "NRI");
            double interest = sb2.calculateInterest();
            double expected = 600.0; // 10000 * 6.0 / 100
            assertTrue("SB NRI 10000", Math.abs(interest - expected) < 0.01);
        } catch (Exception e) {
            fail("SB NRI account failed: " + e.getMessage());
        }

        // Test 3: Case insensitive type
        try {
            SBAccount sb3 = new SBAccount(5000, "normal");
            double interest = sb3.calculateInterest();
            double expected = 200.0;
            assertTrue("SB normal (lowercase)", Math.abs(interest - expected) < 0.01);
        } catch (Exception e) {
            fail("SB lowercase type failed: " + e.getMessage());
        }

        // Test 4: Invalid amount
        try {
            SBAccount sb4 = new SBAccount(-1000, "Normal");
            fail("SB should reject negative amount");
        } catch (InvalidInputException e) {
            pass("SB negative amount validation");
        }

        // Test 5: Zero amount
        try {
            SBAccount sb5 = new SBAccount(0, "Normal");
            double interest = sb5.calculateInterest();
            assertTrue("SB zero amount", interest == 0);
        } catch (Exception e) {
            fail("SB zero amount failed: " + e.getMessage());
        }

        System.out.println();
    }

    private static void testFDAccount() {
        System.out.println("--- Testing FD Account ---");

        // Test range: 7-14 days, below 1 crore, regular age
        try {
            FDAccount fd1 = new FDAccount(50000, 10, 35);
            double interest = fd1.calculateInterest();
            double expected = 50000 * 4.5 / 100;
            assertTrue("FD 7-14 days, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("FD 7-14 days failed: " + e.getMessage());
        }

        // Test range: 7-14 days, below 1 crore, senior citizen (>=60)
        try {
            FDAccount fd2 = new FDAccount(50000, 12, 65);
            double interest = fd2.calculateInterest();
            double expected = 50000 * 5.0 / 100;
            assertTrue("FD 7-14 days, senior", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("FD 7-14 days senior failed: " + e.getMessage());
        }

        // Test range: 15-29 days
        try {
            FDAccount fd3 = new FDAccount(50000, 20, 35);
            double interest = fd3.calculateInterest();
            double expected = 50000 * 4.75 / 100;
            assertTrue("FD 15-29 days, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("FD 15-29 days failed: " + e.getMessage());
        }

        // Test range: 30-45 days
        try {
            FDAccount fd4 = new FDAccount(50000, 40, 35);
            double interest = fd4.calculateInterest();
            double expected = 50000 * 5.5 / 100;
            assertTrue("FD 30-45 days, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("FD 30-45 days failed: " + e.getMessage());
        }

        // Test range: 61-184 days
        try {
            FDAccount fd5 = new FDAccount(50000, 90, 35);
            double interest = fd5.calculateInterest();
            double expected = 50000 * 7.5 / 100;
            assertTrue("FD 61-184 days, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("FD 61-184 days failed: " + e.getMessage());
        }

        // Test range: 185-365 days
        try {
            FDAccount fd6 = new FDAccount(50000, 200, 35);
            double interest = fd6.calculateInterest();
            double expected = 50000 * 8.0 / 100;
            assertTrue("FD 185-365 days, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("FD 185-365 days failed: " + e.getMessage());
        }

        // Test above 1 crore
        try {
            FDAccount fd7 = new FDAccount(10000001, 90, 35);
            double interest = fd7.calculateInterest();
            double expected = 10000001 * 8.5 / 100;
            assertTrue("FD above 1 crore, 61-184 days", Math.abs(interest - expected) < 100);
        } catch (Exception e) {
            fail("FD above 1 crore failed: " + e.getMessage());
        }

        // Test invalid day
        try {
            FDAccount fd8 = new FDAccount(50000, -5, 35);
            fail("FD should reject negative days");
        } catch (InvalidInputException e) {
            pass("FD negative days validation");
        }

        // Test invalid age
        try {
            FDAccount fd9 = new FDAccount(50000, 10, -5);
            fail("FD should reject negative age");
        } catch (InvalidInputException e) {
            pass("FD negative age validation");
        }

        System.out.println();
    }

    private static void testRDAccount() {
        System.out.println("--- Testing RD Account ---");

        // Test 6 months, regular
        try {
            RDAccount rd1 = new RDAccount(1000, 6, 35);
            double interest = rd1.calculateInterest();
            double expected = 1000 * 7.5 / 100;
            assertTrue("RD 6 months, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 6 months failed: " + e.getMessage());
        }

        // Test 6 months, senior
        try {
            RDAccount rd2 = new RDAccount(1000, 6, 60);
            double interest = rd2.calculateInterest();
            double expected = 1000 * 8.0 / 100;
            assertTrue("RD 6 months, senior", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 6 months senior failed: " + e.getMessage());
        }

        // Test 9 months
        try {
            RDAccount rd3 = new RDAccount(1000, 9, 35);
            double interest = rd3.calculateInterest();
            double expected = 1000 * 7.75 / 100;
            assertTrue("RD 9 months, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 9 months failed: " + e.getMessage());
        }

        // Test 12 months
        try {
            RDAccount rd4 = new RDAccount(1000, 12, 35);
            double interest = rd4.calculateInterest();
            double expected = 1000 * 8.0 / 100;
            assertTrue("RD 12 months, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 12 months failed: " + e.getMessage());
        }

        // Test 15 months
        try {
            RDAccount rd5 = new RDAccount(1000, 15, 35);
            double interest = rd5.calculateInterest();
            double expected = 1000 * 8.25 / 100;
            assertTrue("RD 15 months, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 15 months failed: " + e.getMessage());
        }

        // Test 18 months
        try {
            RDAccount rd6 = new RDAccount(1000, 18, 35);
            double interest = rd6.calculateInterest();
            double expected = 1000 * 8.5 / 100;
            assertTrue("RD 18 months, normal", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 18 months failed: " + e.getMessage());
        }

        // Test 21 months, senior
        try {
            RDAccount rd7 = new RDAccount(1000, 21, 65);
            double interest = rd7.calculateInterest();
            double expected = 1000 * 9.25 / 100;
            assertTrue("RD 21 months, senior", Math.abs(interest - expected) < 1);
        } catch (Exception e) {
            fail("RD 21 months senior failed: " + e.getMessage());
        }

        // Test invalid amount
        try {
            RDAccount rd8 = new RDAccount(-1000, 12, 35);
            fail("RD should reject negative amount");
        } catch (InvalidInputException e) {
            pass("RD negative amount validation");
        }

        // Test invalid months
        try {
            RDAccount rd9 = new RDAccount(1000, -6, 35);
            fail("RD should reject negative months");
        } catch (InvalidInputException e) {
            pass("RD negative months validation");
        }

        // Test invalid age
        try {
            RDAccount rd10 = new RDAccount(1000, 12, -5);
            fail("RD should reject negative age");
        } catch (InvalidInputException e) {
            pass("RD negative age validation");
        }

        System.out.println();
    }

    private static void assertTrue(String testName, boolean condition) {
        if (condition) {
            pass(testName);
        } else {
            fail(testName);
        }
    }

    private static void pass(String testName) {
        System.out.println("✓ PASS: " + testName);
        testsPassed++;
    }

    private static void fail(String testName) {
        System.out.println("✗ FAIL: " + testName);
        testsFailed++;
    }

    private static void printResults() {
        System.out.println("\n===== TEST RESULTS =====");
        System.out.println("Passed: " + testsPassed);
        System.out.println("Failed: " + testsFailed);
        System.out.println("Total:  " + (testsPassed + testsFailed));
        System.out.println("Status: " + (testsFailed == 0 ? "ALL TESTS PASSED ✓" : "SOME TESTS FAILED ✗"));
    }
}
