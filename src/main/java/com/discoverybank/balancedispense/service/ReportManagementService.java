package com.discoverybank.balancedispense.service;

import com.discoverybank.balancedispense.model.dto.ClientAccountSummary;
import com.discoverybank.balancedispense.model.dto.ClientAggregate;

import java.util.List;

public interface ReportManagementService {
    List<ClientAccountSummary> generateClientAccountReport();
    List<ClientAggregate> calculateClientsAggregatePosition();
}
