package com.casumo.wallet.boundary;

import com.casumo.wallet.control.WalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class WalletQueryService {

    final
    WalletRepository walletRepository;

    @Autowired
    public WalletQueryService(WalletRepository walletRepository) {
        this.walletRepository = walletRepository;
    }

    public Map<String, Double> getBalances() {

        return walletRepository.getBalances();

    }

}
