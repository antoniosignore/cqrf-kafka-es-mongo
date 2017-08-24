package com.casumo.bet.boundary;

import com.casumo.bet.events.entity.BetInfo;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.UUID;

@RestController
public class WalletController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletController.class);

    WalletCommandService commandService;
    WalletQueryService queryService;

    @Autowired
    public WalletController(WalletCommandService commandService, WalletQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @RequestMapping(value = "/wallets/{username}", method = RequestMethod.GET)
    public Double getBalance(@PathVariable("username") String username)
            throws ChangeSetPersister.NotFoundException {

        final Double order = queryService.getBalances().get(username);

        if (order == null)
            throw new ChangeSetPersister.NotFoundException();

        return order;
    }


    @RequestMapping(value = "/wallets", method = RequestMethod.POST)
    public ResponseEntity placeFunds(BetInfo betInfo, HttpServletRequest request) {

        log.debug("BetController.placeBet");

        if (betInfo == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();


        final UUID betId = UUID.randomUUID();
        commandService.addFunds(betInfo.getUsername(), new BetInfo(betId));

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/bets/{id}").build()
                        .expand(betId).toUri();

        System.out.println("location = " + location);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }

    @POST
    public void storeBeans(JsonObject object) {
        final String beanOrigin = object.getString("beanOrigin", null);
        final int amount = object.getInt("amount", 0);

        if (beanOrigin == null || amount == 0)
            throw new BadRequestException();

        commandService.addFunds(beanOrigin, amount);
    }

}
