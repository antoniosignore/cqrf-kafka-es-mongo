package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class CouponRow {
    public Long eventId;
    public List<Long> eventGroupIdPath = null;
    public Boolean live;
    public Long odds;
    public Long eventStartTime;
    public Long criterionId;
    public Long outcomeId;
}
