
package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
public class BetInfo {
    private UUID id;
    private RequestCoupon requestCoupon;
    public ValidRewardsRequest validRewardsRequest;
}
