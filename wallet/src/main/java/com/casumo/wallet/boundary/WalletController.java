package com.casumo.wallet.boundary;

import com.casumo.wallet.events.entity.MoneyDeposit;
import com.casumo.wallet.events.entity.MoneyWithdraw;
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

    @RequestMapping(value = "/wallets/deposit", method = RequestMethod.POST)
    public ResponseEntity addFunds(MoneyDeposit deposit, HttpServletRequest request) {

        log.debug("BetController.addFunds");

        if (deposit == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        commandService.addFunds(deposit.getUsername(), deposit.getAmount());

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/wallets/{userrname}").build()
                        .expand(deposit.getUsername()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/wallets/withdraw", method = RequestMethod.POST)
    public ResponseEntity withdrawFunds(MoneyWithdraw withdraw, HttpServletRequest request) {

        log.debug("BetController.addFunds");

        if (withdraw == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        commandService.withdrawnFunds(withdraw.getUsername(), withdraw.getAmount());

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/wallets/{username}").build()
                        .expand(withdraw.getUsername()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }


}
