package com.casumo.bet.events.entity;

public class MoneyWithdraw extends AbstractEvent {

    private String username;
    private Double amount;

    public MoneyWithdraw() {
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getUsername() {
        return username;
    }

    public Double getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return "MoneyWithdraw{" +
                "username='" + username + '\'' +
                ", amount=" + amount +
                '}';
    }
}
