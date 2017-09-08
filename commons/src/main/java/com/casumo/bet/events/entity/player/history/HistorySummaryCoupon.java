
package com.casumo.bet.events.entity.player.history;

import java.util.List;

public class HistorySummaryCoupon {

    public Long id;
    public String placedDate;
    public Long stake;
    public Long euroStake;
    public String currency;
    public Long requestedStake;
    public Long potentialPayout;
    public Long nrOfSettledBetOutcomes;
    public Long channelId;
    public Boolean live;
    public Long odds;
    public Long playedOdds;
    public Long singleBetId;
    public List<Long> distinctBetStatuses = null;
    public List<Outcome> outcomes = null;
    public String name;
    public List<Bet> bets = null;
    public Long betStatus;
    public String betsPattern;

}
