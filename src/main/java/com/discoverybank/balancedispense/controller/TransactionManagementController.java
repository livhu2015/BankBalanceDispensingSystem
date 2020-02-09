package com.discoverybank.balancedispense.controller;

import com.discoverybank.balancedispense.model.dao.ClientAccount;
import com.discoverybank.balancedispense.service.TransactionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/secured")
public class TransactionManagementController {

    private Logger logger = LoggerFactory.getLogger(TransactionManagementController.class);

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
    List<ClientAccount> displayBalance(@PathVariable("clientId") int clientId) throws IOException {
        logger.info("Display balance for "+clientId+"...");
        return transactionalService.displayBalance(clientId);
    }

    /**
     * Display currency account balances with converted Rand values
     */
    @PostMapping("/convert/{currencyCode}")
    public @ResponseBody
    ClientAccount ConvertCurrencyToRand(@RequestBody ClientAccount clientAccount, @PathVariable("currencyCode") String currencyCode) {
        logger.info("Converting currency" + clientAccount.getCurrency_code() + " to ZAR...");

        return transactionalService.convertCurrencyToRand(clientAccount, currencyCode);
    }

    /**
     * Display currency account balances with converted Rand values
     */
    @PostMapping("/withdraw/{amount}")
    public @ResponseBody
    ClientAccount processCashWithdraw(@RequestBody ClientAccount clientAccount, @PathVariable("amount") BigDecimal amount) throws IOException {
        logger.info("withdraw of amount"+amount + " to ZAR...");
        return transactionalService.processCashWithdraw(clientAccount.getClient_id(), clientAccount.getAccount_number(), amount.doubleValue());
    }


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