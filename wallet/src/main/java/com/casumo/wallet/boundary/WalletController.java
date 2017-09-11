package com.casumo.wallet.boundary;

import com.casumo.bet.events.entity.wallet.MoneyDeposit;
import com.casumo.bet.events.entity.wallet.MoneyWithdraw;
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

@RestController
public class WalletController {

    private static final org.slf4j.Logger log = LoggerFactory.getLogger(WalletController.class);

    private WalletCommandService commandService;
    private WalletQueryService queryService;

    @Autowired
    public WalletController(WalletCommandService commandService, WalletQueryService queryService) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    @RequestMapping(value = "/wallets", method = RequestMethod.GET)
    public Map<String, Double> getBalances()
            throws ChangeSetPersister.NotFoundException {
        final Map<String, Double> balances = queryService.getBalances();
        if (balances == null)
            throw new ChangeSetPersister.NotFoundException();
        return balances;
    }

    @RequestMapping(value = "/wallets/{username}", method = RequestMethod.GET)
    public Double getBalance(@PathVariable("username") String username)
            throws ChangeSetPersister.NotFoundException {

        addUserIfNotExists(username);

        final Double balance = queryService.getBalances().get(username);

        if (balance == null)
            throw new ChangeSetPersister.NotFoundException();
        return balance;
    }

    @RequestMapping(value = "/wallets/deposit", method = RequestMethod.POST)
    public ResponseEntity addFunds(@RequestBody MoneyDeposit deposit, HttpServletRequest request) {

        if (deposit == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        addUserIfNotExists(deposit.getUsername());

        commandService.addFunds(deposit.getUsername(), deposit.getAmount());

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/wallets/{userrname}").build()
                        .expand(deposit.getUsername()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }


    @RequestMapping(value = "/wallets/withdraw", method = RequestMethod.POST)
    public ResponseEntity withdrawFunds(@RequestBody MoneyWithdraw withdraw, HttpServletRequest request) {

        if (withdraw == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();

        addUserIfNotExists(withdraw.getUsername());

        commandService.withdrawnFunds(withdraw.getUsername(), withdraw.getAmount());

        URI location =
                ServletUriComponentsBuilder.fromServletMapping(request).path("/wallets/{username}").build()
                        .expand(withdraw.getUsername()).toUri();

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(location);
        return new ResponseEntity(headers, HttpStatus.ACCEPTED);
    }

    private void addUserIfNotExists(String username) {
        Map<String, Double> balances = queryService.getBalances();
        if (balances.get(username) == null)
            balances.put(username, 0D);
    }


}
