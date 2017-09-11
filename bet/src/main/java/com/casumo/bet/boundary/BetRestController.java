package com.casumo.bet.boundary;

import com.casumo.bet.events.entity.Bet;
import com.casumo.bet.events.entity.BetPlaced;
import com.casumo.bet.events.entity.player.BetInfo;
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
import java.util.Map;
import java.util.UUID;

@RestController
public class BetRestController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(BetRestController.class);

    final BetCommandService commandService;
    final BetQueryService queryService;


    @Autowired
    public BetRestController(BetCommandService commandService, BetQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }


    @RequestMapping(value = "/bets", method = RequestMethod.GET)
    public Map<UUID, Bet> getBets()
            throws ChangeSetPersister.NotFoundException {

        Map<UUID, Bet> bets = queryService.getBets();
        if (bets == null)
            throw new ChangeSetPersister.NotFoundException();

        return bets;
    }

    @RequestMapping(value = "/bets", method = RequestMethod.POST)
    public ResponseEntity placeBet(@RequestBody BetPlaced betInfo, HttpServletRequest request) {

        log.debug("BetController.placeBet");

        if (betInfo == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        final UUID betId = UUID.randomUUID();
        betInfo.setId(betId);

        commandService.placeBet(betInfo);

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/bets/{id}").build()
                        .expand(betId).toUri();

        System.out.println("location = " + location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/bets/{id}", method = RequestMethod.GET)
    public Bet getBet(@PathVariable("id") UUID betId)
            throws ChangeSetPersister.NotFoundException {

        final Bet bet = queryService.getBet(betId);

        if (bet == null)
            throw new ChangeSetPersister.NotFoundException();

        return bet;
    }

}
