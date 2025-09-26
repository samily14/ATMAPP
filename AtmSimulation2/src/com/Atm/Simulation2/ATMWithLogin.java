package com.Atm.Simulation2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ATMWithLogin {

    private static double balance = 10000.0; // User's account balance
    private static final int correctPIN = 2004; // Hardcoded PIN for demo

    public static void main(String[] args) {
        // Set default UI fonts for a modern look
        UIManager.put("Button.font", new Font("Segoe UI", Font.BOLD, 14));
        UIManager.put("Label.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("TextField.font", new Font("Segoe UI", Font.PLAIN, 14));
        UIManager.put("PasswordField.font", new Font("Segoe UI", Font.PLAIN, 14));

        showLoginScreen();
    }

    public static void showLoginScreen() {
        JFrame loginFrame = new JFrame("\uD83D\uDD10 ATM Login");
        loginFrame.setSize(350, 180);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setLayout(new GridLayout(3, 1, 10, 10));
        loginFrame.getContentPane().setBackground(new Color(240, 248, 255));

        JPanel panel = new JPanel(new FlowLayout());
        panel.setBackground(new Color(240, 248, 255));

        JLabel label = new JLabel("Enter 4-digit PIN:");
        JPasswordField pinField = new JPasswordField(10);
        JButton loginButton = new JButton("Login");
        loginButton.setBackground(new Color(30, 144, 255));
        loginButton.setForeground(Color.GREEN);

        panel.add(label);
        panel.add(pinField);
        panel.add(loginButton);

        loginFrame.add(new JLabel("Welcome to Secure ATM", SwingConstants.CENTER));
        loginFrame.add(panel);

        // Check the PIN when login button is pressed
        loginButton.addActionListener(e -> {
            String pinText = new String(pinField.getPassword());
            if (pinText.equals(String.valueOf(correctPIN))) {
                loginFrame.dispose();
                showATMWindow();
            } else {
                JOptionPane.showMessageDialog(loginFrame, "Incorrect PIN. Try again.");
                pinField.setText("");
            }
        });

        loginFrame.setLocationRelativeTo(null);
        loginFrame.setVisible(true);
    }

    public static void showATMWindow() {
        JFrame atmFrame = new JFrame("\uD83C\uDFE6 Simple ATM");
        atmFrame.setSize(350, 350);
        atmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        atmFrame.setLayout(new GridLayout(6, 1, 10, 10));
        atmFrame.getContentPane().setBackground(new Color(245, 255, 250));

        JLabel label = new JLabel("Select an operation:", SwingConstants.CENTER);

        JButton checkBalance = new JButton("\uD83D\uDCB0 Check Balance");
        JButton deposit = new JButton("\u2795 Deposit");
        JButton withdraw = new JButton("\u2796 Withdraw");
        JButton exit = new JButton("\uD83D\uDEAA Exit");

        Color btnColor = new Color(50, 205, 50);
        Font btnFont = new Font("Segoe UI", Font.BOLD, 19);

        for (JButton btn : new JButton[]{checkBalance, deposit, withdraw, exit}) {
            btn.setBackground(btnColor);
            btn.setForeground(Color.GREEN);
            btn.setFont(btnFont);
        }

        checkBalance.addActionListener(e ->
                JOptionPane.showMessageDialog(atmFrame, "Your balance: ₹" + balance));

        deposit.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(atmFrame, "Enter amount to deposit:");
            if (input != null && !input.isEmpty()) {
                double amount = Double.parseDouble(input);
                balance += amount;
                JOptionPane.showMessageDialog(atmFrame, "₹" + amount +
                        " deposited.\nNew balance: ₹" + balance);
            }
        });
        
        withdraw.addActionListener(e -> {
            String input = JOptionPane.showInputDialog(atmFrame, "Enter amount to withdraw:");
            if (input != null && !input.isEmpty()) {
                double amount = Double.parseDouble(input);
                if (amount <= balance) {
                    balance -= amount;
                    JOptionPane.showMessageDialog(atmFrame, "Withdrawn: ₹" + amount +
                            "\nRemaining: ₹" + balance);
                } else {
                    JOptionPane.showMessageDialog(atmFrame, "Insufficient balance!");
                }
            }
        });

        exit.addActionListener(e -> System.exit(0));

        atmFrame.add(label);
        atmFrame.add(checkBalance);
        atmFrame.add(deposit);
        atmFrame.add(withdraw);
        atmFrame.add(exit);

        atmFrame.setLocationRelativeTo(null);
        atmFrame.setVisible(true);
    }
}
