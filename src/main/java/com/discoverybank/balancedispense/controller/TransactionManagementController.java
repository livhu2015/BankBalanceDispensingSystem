package com.discoverybank.balancedispense.controller;

import com.discoverybank.balancedispense.model.dao.ClientAccount;
import com.discoverybank.balancedispense.model.dto.ClientAccountDetail;
import com.discoverybank.balancedispense.model.dto.CurrencyAccountBalance;
import com.discoverybank.balancedispense.model.dto.TransactionalAccount;
import com.discoverybank.balancedispense.service.TransactionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/transact")
public class TransactionManagementController {

    private Logger logger = LoggerFactory.getLogger(TransactionManagementController.class);

    @Autowired
    private TransactionalService transactionalService;

    @GetMapping("/balance/{clientId}")
    public @ResponseBody
    List<TransactionalAccount> displayBalance(@PathVariable("clientId") int clientId) throws IOException {

        logger.info("Display balance for "+clientId+"...");

        return transactionalService.displayBalance(clientId);
    }

    @PostMapping("/foreign-exchange/{clientId}")
    public @ResponseBody
    List<CurrencyAccountBalance> ConvertCurrencyToRand(@PathVariable("clientId") int clientId) {

        logger.info("Converting currency of client id:: " + clientId + " to ZAR...");

        return transactionalService.convertForeignCurrencyToRand(clientId);
    }

    @PostMapping("/withdraw/{amount}")
    public @ResponseBody
    ClientAccountDetail processCashWithdraw(@RequestBody ClientAccount clientAccount, @PathVariable("amount") double amount) throws IOException {
        return transactionalService.processCashWithdraw(clientAccount.getClient_id(), clientAccount.getAccount_number(), amount);
    }

}
