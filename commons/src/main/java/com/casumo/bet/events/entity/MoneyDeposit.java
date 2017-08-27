package com.casumo.bet.events.entity;

public class MoneyDeposit extends AbstractEvent {

    private String username;
    private Double amount;

    public MoneyDeposit() {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        MoneyDeposit that = (MoneyDeposit) o;

        if (!username.equals(that.username)) return false;
        return amount.equals(that.amount);
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + username.hashCode();
        result = 31 * result + amount.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "MoneyDeposit{" +
                "username='" + username + '\'' +
                ", amount=" + amount +
                '}';
    }
}
