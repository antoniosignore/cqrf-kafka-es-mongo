
package com.casumo.bet.events.entity.player.history;

import java.util.List;

public class Outcome {

    public Long id;
    public List<Long> outcomeId = null;
    public String betLabel;
    public String eventName;
    public Long betOfferId;
    public String boType;
    public Long boTypeId;
    public String criterion;
    public List<EventGroup> eventGroups = null;
    public Long eventId;
    public String eventStartDate;
    public Boolean live;
    public Long payoutOdds;
    public Long playedOdds;
    public Long status;
    public Boolean rule4Applied;
    public String homeName;
    public String awayName;
    public Result result;
    public Boolean startingPrice;
    public Boolean cashOutProhibited;
    public Boolean bestOddsGuaranteed;

}
