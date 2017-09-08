package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class BetErrors {
    public List<BetError> betErrors = null;
}
