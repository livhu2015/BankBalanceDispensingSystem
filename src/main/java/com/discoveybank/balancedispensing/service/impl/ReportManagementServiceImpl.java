package com.discoveybank.balancedispensing.service.impl;

import com.discoveybank.balancedispensing.mapper.ClientMapper;
import com.discoveybank.balancedispensing.mapper.ReportManagementMapper;
import com.discoveybank.balancedispensing.mapper.TransactionalAccountMapper;
import com.discoveybank.balancedispensing.model.Client;
import com.discoveybank.balancedispensing.model.ClientAccountDetail;
import com.discoveybank.balancedispensing.model.ClientAccountSummary;
import com.discoveybank.balancedispensing.model.ClientAggregate;
import com.discoveybank.balancedispensing.service.ReportManagementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

@Service("ReportManagementService")
public class ReportManagementServiceImpl implements ReportManagementService {

    @Autowired
    private ReportManagementMapper reportManagementMapper;

    @Autowired
    private ClientMapper clientMapper;

    @Autowired
    private TransactionalAccountMapper transactionalAccountMapper;

    @Override
    public List<ClientAccountSummary> generateClientAccountReport() {
        return reportManagementMapper.findAll();
    }

    /**
     * Client (Client Title + Client Name + Client Surname)
     * Loan Balance (Aggregate of all loan amounts)
     * Transactional Balance (Aggregate of all transactional accounts)
     * Net Position (Net position across all accounts)
     */
    @Override
    public ClientAggregate calculateAggregatePosition(int clientId) {

        ClientAggregate clientAggregateResponse = new ClientAggregate();
        Client client = clientMapper.findClientById(clientId);
        clientAggregateResponse.setName(client.getName());
        clientAggregateResponse.setSurname(client.getSurname());
        clientAggregateResponse.setTitle(client.getTitle());

        BigDecimal loanBalance = processingAggregatedBalance(clientId, 0);
        BigDecimal aggregatedBalance = processingAggregatedBalance(clientId, 1);
        BigDecimal netPosition = aggregatedBalance.subtract(loanBalance);
        clientAggregateResponse.setTransactionalBalance(aggregatedBalance);
        clientAggregateResponse.setLoanBalance(loanBalance);
        clientAggregateResponse.setNetPosition(netPosition);

        return clientAggregateResponse;
    }

    private BigDecimal processingAggregatedBalance(int clientId, int transactional) {
        List<ClientAccountDetail> transactionalAccounts = reportManagementMapper.findClientTransactionAccounts(clientId, transactional);

        BigDecimal balance = BigDecimal.valueOf(0);

        for (ClientAccountDetail clientAccountDetail : transactionalAccounts) {
            BigDecimal aggregatedBalance = clientAccountDetail.getDisplay_balance();
            balance = aggregatedBalance.add(balance);
        }
        return balance;
    }
}
