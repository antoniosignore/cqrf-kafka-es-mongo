package com.casumo.bet.control;


import com.casumo.bet.events.entity.MoneyBalance;
import com.casumo.bet.events.entity.MoneyDeposit;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;


@Component
public class WalletStorage {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletStorage.class);

    private Map<String, Double> balances = new ConcurrentHashMap<>();

    public Map<String, Double> getBalances() {
        return Collections.unmodifiableMap(balances);
    }

    public Double getRemainingAmount(final String username) {
        return balances.getOrDefault(username, 0D);
    }

    @EventListener
    public void apply(MoneyDeposit moneyDeposit) {

        Double currentValue = balances.get(moneyDeposit.getUsername());
        balances.put(moneyDeposit.getUsername(), currentValue + moneyDeposit.getAmount());

    }

    @EventListener
    public void apply(MoneyBalance beansFetched) {

        log.debug("WalletStorage.apply");
        log.debug("beansFetched = " + beansFetched);

        balances.merge(beansFetched.getUsername(), 0D, (i1, i2) -> i1 - 1);
    }

}
