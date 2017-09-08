
package com.casumo.bet.events.entity.player.history;

import java.util.List;

public class HistoryOutcome {

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
    public Long line;
    public Boolean live;
    public Long payoutOdds;
    public Long playedOdds;
    public Long playedOddsBoosted;
    public Long oddsBoosted;
    public Long status;
    public String voidReason;
    public Long voidType;
    public Boolean rule4Applied;
    public Long rule4Odds;
    public String extraInfo;
    public String homeName;
    public String awayName;
    public String scorerCriterion;
    public String eachWayTerms;
    public Result result;

    public String h2hParticipants;
    public MultiPosition multiPosition;


    public Boolean startingPrice;
    public Boolean cashOutProhibited;
    public Boolean bestOddsGuaranteed;

}
