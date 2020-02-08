package com.discoveybank.balancedispensing.service;

import com.discoveybank.balancedispensing.model.ClientAccountSummary;
import com.discoveybank.balancedispensing.model.ClientAggregate;

import java.util.List;

public interface ReportManagementService {
    List<ClientAccountSummary> generateClientAccountReport();
    ClientAggregate calculateAggregatePosition(int clientId);
}
