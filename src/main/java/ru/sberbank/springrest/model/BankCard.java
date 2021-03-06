package ru.sberbank.springrest.model;

import javax.persistence.*;

@Table(name = "bank_cards")
@Entity
public class BankCard {

    @Column(name = "bank_account_number")
    private String bankAccountNumber;
    @Id
    @Column(name = "number_card")
    private int numberCard;
    @Column(name = "balance")
    private double balance;


    public String getBankAccountNumber() {
        return bankAccountNumber;
    }

    public void setBankAccountNumber(String bankAccountNumber) {
        this.bankAccountNumber = bankAccountNumber;
    }

    public int getNumberCard() {
        return numberCard;
    }

    public void setNumberCard(int numberCard) {
        this.numberCard = numberCard;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
