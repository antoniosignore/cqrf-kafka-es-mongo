package com.casumo.bet.events.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@NoArgsConstructor
public final class BetDelivered implements Serializable {

    private UUID orderId;
}
