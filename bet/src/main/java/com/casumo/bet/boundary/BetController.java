package com.casumo.bet.boundary;

import com.casumo.bet.events.entity.BetInfo;
import com.casumo.bet.events.entity.Bet;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.UUID;

@RestController
public class BetController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BetController.class);

    final BetCommandService commandService;
    final BetQueryService queryService;


    @Autowired
    public BetController(BetCommandService commandService, BetQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @RequestMapping(value = "/bets", method = RequestMethod.POST)
    public ResponseEntity placeBet(BetInfo betInfo, HttpServletRequest request) {

        log.debug("BetController.placeBet");

        if (betInfo == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


        final UUID betId = UUID.randomUUID();
        commandService.placeBet(new BetInfo(betId));

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/bets/{id}").build()
                        .expand(betId).toUri();

        System.out.println("location = " + location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/bets/{id}", method = RequestMethod.GET)
    public Bet getBet(@PathVariable("id") UUID orderId)
            throws ChangeSetPersister.NotFoundException {

        final Bet order = queryService.getOrder(orderId);

        if (order == null)
            throw new ChangeSetPersister.NotFoundException();

        return order;
    }

}
