package com.discoveybank.balancedispensing.controller;


import com.discoveybank.balancedispensing.model.ClientAccount;
import com.discoveybank.balancedispensing.model.ClientAccountSummary;
import com.discoveybank.balancedispensing.model.ClientAggregate;
import com.discoveybank.balancedispensing.service.ReportManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportManagementController {
    /**
     * A report needs to be generated that displays a list of all clients along with the account details of the
     * client’s account with the highest balance
     *
     *
     */
    private Logger logger = LoggerFactory.getLogger(ReportManagementController.class);

    @Autowired
    private ReportManagementService reportManagementService;

    @GetMapping("/client-dashboard")
    public @ResponseBody
    List<ClientAccountSummary> generateClientAccountReport() {
        logger.info("reporting...");
        return reportManagementService.generateClientAccountReport();
    }

    /**
     * Calculate aggregate financial position per client
     * @return
     */
     @GetMapping("/financial-positions")
    public @ResponseBody
     ClientAggregate calculateAggregatePosition(int clientId) {
        logger.info("reporting...");

         /**
          * Client (Client Title + Client Name + Client Surname)
          * Loan Balance (Aggregate of all loan amounts)
          * Transactional Balance (Aggregate of all transactional accounts)
          * Net Position (Net position across all accounts)
          */
         return reportManagementService.calculateAggregatePosition(clientId);
    }



}
