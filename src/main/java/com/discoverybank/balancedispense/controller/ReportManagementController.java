package com.discoverybank.balancedispense.controller;


import com.discoverybank.balancedispense.model.dto.ClientAccountSummary;
import com.discoverybank.balancedispense.service.ReportManagementService;
import com.discoverybank.balancedispense.model.dto.ClientAggregate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportManagementController {

    private Logger logger = LoggerFactory.getLogger(ReportManagementController.class);

    @Autowired
    private ReportManagementService reportManagementService;

    @GetMapping("/transactional-accounts")
    public @ResponseBody
    List<ClientAccountSummary> generateClientAccountReport() {
        logger.info("Reporting on the transactional account per client with the highest balance...");
        return reportManagementService.generateClientAccountReport();
    }

    /**
     * Calculate aggregate financial position per client
     * @return
     */
     @GetMapping("/financial-positions")
     public @ResponseBody List<ClientAggregate> calculateAggregatePosition() {
        logger.info("reporting on the aggregate financial position per client...");
         return reportManagementService.calculateClientsAggregatePosition();
    }

}
