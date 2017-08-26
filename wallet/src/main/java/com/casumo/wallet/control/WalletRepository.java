package com.casumo.wallet.control;

import com.casumo.wallet.events.entity.MoneyWithdraw;
import com.casumo.wallet.events.entity.MoneyDeposit;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Component
public class WalletRepository {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletRepository.class);

    private Map<String, Double> balances = new ConcurrentHashMap<>();

    public Map<String, Double> getBalances() {
        return Collections.unmodifiableMap(balances);
    }

    public Double getRemainingAmount(final String username) {
        return balances.getOrDefault(username, 0D);
    }

    @Async
    @EventListener
    public void apply(MoneyDeposit moneyDeposit) {
        Double currentValue = balances.get(moneyDeposit.getUsername());
        balances.put(moneyDeposit.getUsername(), currentValue + moneyDeposit.getAmount());
    }

    @Async
    @EventListener
    public void apply(MoneyWithdraw withdraw) {
        log.debug("WalletStorage.apply");
        Double currentValue = balances.get(withdraw.getUsername());
        balances.put(withdraw.getUsername(), currentValue - withdraw.getAmount());
    }

}
