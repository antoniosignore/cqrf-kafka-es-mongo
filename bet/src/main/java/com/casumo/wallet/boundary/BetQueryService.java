package com.casumo.wallet.boundary;


import com.casumo.wallet.control.BetRepository;
import com.casumo.wallet.events.entity.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BetQueryService {

    final
    BetRepository betRepository;

    @Autowired
    public BetQueryService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Bet getOrder(final UUID orderId) {
        return betRepository.get(orderId);
    }

}
