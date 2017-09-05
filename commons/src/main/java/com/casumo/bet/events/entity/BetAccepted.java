package com.casumo.bet.events.entity;

import com.casumo.bet.events.entity.player.BetInfo;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public final class BetAccepted  implements Serializable {

    private UUID id;
    private BetInfo betInfo;


}
