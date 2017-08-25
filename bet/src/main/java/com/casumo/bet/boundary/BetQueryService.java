package com.casumo.bet.boundary;


import com.casumo.bet.control.BetsRepository;
import com.casumo.bet.events.entity.Bet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class BetQueryService {

    final
    BetsRepository betsRepository;

    @Autowired
    public BetQueryService(BetsRepository betsRepository) {
        this.betsRepository = betsRepository;
    }

    public Bet getOrder(final UUID orderId) {
        return betsRepository.get(orderId);
    }

}
