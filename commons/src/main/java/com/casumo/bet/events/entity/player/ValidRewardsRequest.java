package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ValidRewardsRequest {
    public List<CouponRow> couponRows = null;
}
