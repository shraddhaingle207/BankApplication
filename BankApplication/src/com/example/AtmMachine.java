package com.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AtmMachine {
	 private double accountBalance; // Current account balance
	    private String pin; // User PIN
	    private List<String> transactionHistory; // List to store transaction history

	    // Constructor to initialize the ATM with a balance and a PIN
	    public AtmMachine(double initialBalance, String initialPin) {
	        this.accountBalance = initialBalance;
	        this.pin = initialPin;
	        this.transactionHistory = new ArrayList<>();
	    }

	    // Main method to run the ATM machine
	    public static void main(String[] args) {
	        AtmMachine atm = new AtmMachine(5000.00, "1234"); // Initialize ATM with a balance of 1000.00 and a PIN of "1234"
	        atm.runATM(); // Start the ATM
	    }

	    // Method to run the ATM interface
	    public void runATM() {
	        Scanner scanner = new Scanner(System.in);
	        System.out.println("Welcome to the ATM!");
	        
	        // Prompt user to enter PIN
	        System.out.print("Please enter your PIN: ");
	        String enteredPin = scanner.nextLine();
	        
	        // Validate PIN
	        if (!validatePin(enteredPin)) {
	            System.out.println("Incorrect PIN. Exiting...");
	            return;
	        }
	        
	        boolean exit = false;
	        while (!exit) {
	            // Display ATM menu
	            System.out.println("\nATM Menu:");
	            System.out.println("1. Account Balance Inquiry");
	            System.out.println("2. Cash Withdrawal");
	            System.out.println("3. Cash Deposit");
	            System.out.println("4. Change PIN");
	            System.out.println("5. Transaction History");
	            System.out.println("6. Exit");
	            System.out.print("Select an option (1-6): ");
	            
	            int choice = scanner.nextInt();
	            scanner.nextLine(); // Consume newline
	            
	            switch (choice) {
	                case 1:
	                    accountBalanceInquiry();
	                    break;
	                case 2:
	                    cashWithdrawal(scanner);
	                    break;
	                case 3:
	                    cashDeposit(scanner);
	                    break;
	                case 4:
	                    changePin(scanner);
	                    break;
	                case 5:
	                    displayTransactionHistory();
	                    break;
	                case 6:
	                    exit = true;
	                    System.out.println("Thank you for using the ATM. Goodbye!");
	                    break;
	                default:
	                    System.out.println("Invalid option. Please try again.");
	            }
	        }
	        
	        scanner.close(); // Close the scanner resource
	    }

	    // Method to validate the entered PIN
	    private boolean validatePin(String enteredPin) {
	        return this.pin.equals(enteredPin);
	    }

	    // Method to perform account balance inquiry
	    private void accountBalanceInquiry() {
	        System.out.printf("Your current balance is: $%.2f%n", accountBalance);
	    }

	    // Method to perform cash withdrawal
	    private void cashWithdrawal(Scanner scanner) {
	        System.out.print("Enter amount to withdraw: $");
	        double amount = scanner.nextDouble();
	        
	        // Check if the withdrawal amount is valid
	        if (amount <= 0) {
	            System.out.println("Withdrawal amount must be greater than zero.");
	            return;
	        }
	        
	        // Check if there are sufficient funds
	        if (amount > accountBalance) {
	            System.out.println("Insufficient funds.");
	            return;
	        }
	        
	        // Deduct amount and record transaction
	        accountBalance -= amount;
	        transactionHistory.add("Withdrew: $" + amount);
	        System.out.printf("Successfully withdrew: $%.2f%n", amount);
	    }

	    // Method to perform cash deposit
	    private void cashDeposit(Scanner scanner) {
	        System.out.print("Enter amount to deposit: $");
	        double amount = scanner.nextDouble();
	        
	        // Check if the deposit amount is valid
	        if (amount <= 0) {
	            System.out.println("Deposit amount must be greater than zero.");
	            return;
	        }
	        
	        // Add amount and record transaction
	        accountBalance += amount;
	        transactionHistory.add("Deposited: $" + amount);
	        System.out.printf("Successfully deposited: $%.2f%n", amount);
	    }

	    // Method to change the user's PIN
	    private void changePin(Scanner scanner) {
	        System.out.print("Enter new PIN: ");
	        String newPin = scanner.nextLine();
	        
	        // Simple validation for new PIN (must be 4 digits)
	        if (newPin.length() != 4 || !newPin.matches("\\d{4}")) {
	            System.out.println("Invalid PIN. It must be 4 digits.");
	            return;
	        }
	        
	        // Change the PIN and confirm the change
	        this.pin = newPin;
	        System.out.println("PIN changed successfully.");
	    }

	    // Method to display transaction history
	    private void displayTransactionHistory() {
	        System.out.println("Transaction History:");
	        if (transactionHistory.isEmpty()) {
	            System.out.println("No transactions yet.");
	        } else {
	            for (String transaction : transactionHistory) {
	                System.out.println(transaction);
	            }
	        }
	    }
	}

