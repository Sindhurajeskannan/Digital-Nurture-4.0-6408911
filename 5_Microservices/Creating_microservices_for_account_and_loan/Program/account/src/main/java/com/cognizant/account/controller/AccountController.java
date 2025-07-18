package com.cognizant.account.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/accounts")
public class AccountController {

    @GetMapping("/{number}")
    public Account getAccountDetails(@PathVariable String number) {
        return new Account(number, "savings", 234343);
    }

    static class Account {
        private String number;
        private String type;
        private double balance;

        public Account(String number, String type, double balance) {
            this.number = number;
            this.type = type;
            this.balance = balance;
        }

        public String getNumber() { return number; }
        public String getType() { return type; }
        public double getBalance() { return balance; }

        public void setNumber(String number) { this.number = number; }
        public void setType(String type) { this.type = type; }
        public void setBalance(double balance) { this.balance = balance; }
    }
}
