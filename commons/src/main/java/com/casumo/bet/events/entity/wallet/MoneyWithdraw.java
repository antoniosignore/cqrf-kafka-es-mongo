package com.casumo.bet.events.entity.wallet;


import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class MoneyWithdraw {

    private String username;
    private Double amount;

}
