package com.casumo.bet.events.entity;

import java.io.Serializable;
import java.util.UUID;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public final class BmcBetFinished implements Serializable {

    private UUID uuid;

}
