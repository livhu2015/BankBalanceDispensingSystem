package com.discoveybank.balancedispensing.service.impl;

import com.discoveybank.balancedispensing.mapper.ClientManagementMapper;
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
import java.util.ArrayList;
import java.util.List;

@Service("ReportManagementService")
public class ReportManagementServiceImpl implements ReportManagementService {

    @Autowired
    private ReportManagementMapper reportManagementMapper;

    @Autowired
    private ClientManagementMapper clientManagementMapper;

    @Autowired
    private TransactionalAccountMapper transactionalAccountMapper;

    @Override
    public List<ClientAccountSummary> generateClientAccountReport() {
        return reportManagementMapper.findAllTransactionalAccounts();
    }

    @Override
    public List<ClientAggregate> calculateClientsAggregatePosition() {

        List<ClientAggregate> clientAggregates = new ArrayList<>();
        List<Client> clients = clientManagementMapper.findAllClients();

        for(Client client: clients) {
            ClientAggregate clientAggregate = calculateAggregatePosition(client.getClientId());
            clientAggregates.add(clientAggregate);
        }
        return clientAggregates;
    }

    private ClientAggregate calculateAggregatePosition(int clientId) {

        ClientAggregate clientAggregateResponse = new ClientAggregate();
        Client client = clientManagementMapper.findClientById(clientId);
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
