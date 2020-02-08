package com.discoveybank.balancedispensing.controller;

import com.discoveybank.balancedispensing.model.ClientAccount;
import com.discoveybank.balancedispensing.service.TransactionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/secured")
public class SecuredTransactionController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TransactionalService transactionalService;

    /**
     * Client is able to view all transactional accounts with the available balances
     * on each account in descending order with the highest balance displaying first
     * and the lowest balance displaying last
     * <p>
     * Client sends a request to the server with the client id and a timestamp
     *
     * @return balance
     */
    @GetMapping("/balance/{clientId}")
    public @ResponseBody
    List<ClientAccount> displayBalance(@PathVariable("clientId") int clientId) {
        logger.info("display balance...");
        return transactionalService.displayBalance(clientId);
    }

    /**
     * Display currency account balances with converted Rand values
     */
    @PostMapping("/convert/{rate}")
    public @ResponseBody
    ClientAccount ConvertCurrencyToRand(@RequestBody ClientAccount clientAccount, @PathVariable("rate") BigDecimal rate) {
        logger.info("Converting currency" + clientAccount.getCurrency_code() + " to ZAR...");

        return transactionalService.convertCurrencyToRand(clientAccount, rate);
    }

    /**
     * Display currency account balances with converted Rand values
     */
    @PostMapping("/withdraw/{amount}")
    public @ResponseBody
    ClientAccount processCashWithdraw(@RequestBody ClientAccount clientAccount, @PathVariable("amount") BigDecimal amount) {
        logger.info("withdraw of amount"+amount + " to ZAR...");
        return transactionalService.processCashWithdraw(clientAccount.getClient_id(), clientAccount.getAccount_number(), amount.doubleValue());
    }

//
//    @GetMapping("/view-clients")
//    public List<Client> viewClients() {
//        System.out.println("Find all clients...");
//        return clientRepository.findAll();
//    }
//
//    @PostMapping("/create")
//    public String createClient(@RequestBody Client client) {
//        logger.info("Inserting -> {}", client);
//        int response = clientRepository.insert(client);
//        return "respnse with id: " + response;
//    }
}