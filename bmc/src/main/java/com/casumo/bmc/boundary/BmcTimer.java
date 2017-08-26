package com.casumo.bmc.boundary;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BmcTimer {

    private static final Logger log = LoggerFactory.getLogger(BmcCommandService.class);

    final BmcCommandService bmcCommandService;

    @Autowired
    public BmcTimer(BmcCommandService bmcCommandService) {
        this.bmcCommandService = bmcCommandService;
    }

    @Scheduled(fixedRate = 5000)
    void checkBets() {
        bmcCommandService.checkBets();
    }

    @Scheduled(fixedRate = 5000)
    void checkDelivery() {
        bmcCommandService.checkBetDelivery();
    }

}
