package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ResponseCoupon {
    public Long status;
    public Long couponId;
    public String couponUri;
    public Long delayBeforeAcceptingBet;

    public RequestCoupon requestCoupon;
    public BetErrors betErrors;
}
