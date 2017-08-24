package com.casumo.bet.boundary;

import com.casumo.bet.control.WalletStorage;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Map;

public class WalletQueryService {

    @Autowired
    WalletStorage walletStorage;

    public Map<String, Double> getBalances() {

        return walletStorage.getBalances();

    }

}
