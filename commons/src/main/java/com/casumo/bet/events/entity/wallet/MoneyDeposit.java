package com.casumo.bet.events.entity.wallet;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyDeposit {

    private String username;
    private Double amount;

}
