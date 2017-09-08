
package com.casumo.bet.events.entity.player;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class RequestCoupon {
    public String type;
    public String allowOddsChange;
    public List<Long> odds = null;
    public List<Long> stakes = null;
    public List<List<List<Long>>> selection = null;
    public List<List<Long>> outcomeIds = null;
    public String betsPattern;
    public List<Boolean> eachWay = null;
    public List<Long> eachWayFraction = null;
    public List<Long> eachWayPlaceLimit = null;
}
