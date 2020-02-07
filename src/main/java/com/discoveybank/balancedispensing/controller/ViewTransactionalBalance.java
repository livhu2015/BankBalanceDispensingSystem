package com.discoveybank.balancedispensing.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.discoveybank.balancedispensing.model.Client;
import com.discoveybank.balancedispensing.model.ClientAccount;
import com.discoveybank.balancedispensing.mapper.ClientRepository;
import com.discoveybank.balancedispensing.service.AccountBalance;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class ViewTransactionalBalance {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private AccountBalance accountBalance;

    /**
     * Client is able to view all transactional accounts with the available balances
     * on each account in descending order with the highest balance displaying first
     * and the lowest balance displaying last
     * 
     * Client sends a request to the server with the client id and a timestamp
     * 
     * @return balance
     */

    @GetMapping("/balance/{clientId}")
    public @ResponseBody List<ClientAccount> displayBalance(@PathVariable("clientId") int clientId) {
        logger.info("display balance...");

        return accountBalance.displayBalance(clientId);
    }

    @GetMapping("/view-clients")
    public List<Client> viewClients() {
        System.out.println("Find all clients...");
        return clientRepository.findAll();
    }

    @PostMapping("/create")
    public String createClient(@RequestBody Client client) {
        logger.info("Inserting -> {}", client);
        int response = clientRepository.insert(client);
        return "respnse with id: " + response;
    }

    @GetMapping("ping")
    public String ping() {
        return "pong";
    }
}