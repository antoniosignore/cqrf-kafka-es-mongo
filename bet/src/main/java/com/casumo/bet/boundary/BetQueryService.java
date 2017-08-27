package com.casumo.bet.boundary;


import com.casumo.bet.events.entity.Bet;
import com.casumo.bet.repository.BetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.UUID;

@Component
public class BetQueryService {

    final
    BetRepository betRepository;

    @Autowired
    public BetQueryService(BetRepository betRepository) {
        this.betRepository = betRepository;
    }

    public Bet getBet(final UUID orderId) {
        return betRepository.get(orderId);
    }

    public Map<UUID, Bet> getBets() {
        return betRepository.getAcceptedBets();
    }
}
