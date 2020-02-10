package com.discoverybank.balancedispense.service.impl;

import com.discoverybank.balancedispense.mapper.ClientManagementMapper;
import com.discoverybank.balancedispense.mapper.ReportManagementMapper;
import com.discoverybank.balancedispense.mapper.TransactionalAccountMapper;
import com.discoverybank.balancedispense.model.dto.ClientAccountDetail;
import com.discoverybank.balancedispense.model.dto.ClientAccountSummary;
import com.discoverybank.balancedispense.model.dto.ClientAggregate;
import com.discoverybank.balancedispense.model.dto.ClientProfile;
import com.discoverybank.balancedispense.service.ReportManagementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service("ReportManagementService")
public class ReportManagementServiceImpl implements ReportManagementService {
    private Logger logger = LoggerFactory.getLogger(ReportManagementServiceImpl.class);

    @Autowired
    private ReportManagementMapper reportManagementMapper;

    @Autowired
    private ClientManagementMapper clientManagementMapper;

    @Autowired
    private TransactionalAccountMapper transactionalAccountMapper;

    @Autowired
    private ScheduledTasks scheduledTasks;

    @Override
    public List<ClientAccountSummary> generateClientAccountReport() {
        try {
            List<ClientAccountSummary> clientSummary = reportManagementMapper.findAllTransactionalAccounts();
            return clientSummary;

        } catch (Exception ex) {
            logger.error("Some thing went wrong"+ ex.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public List<ClientAggregate> calculateClientsAggregatePosition() {

        List<ClientAggregate> clientAggregates = new ArrayList<>();
        List<ClientProfile> clients = clientManagementMapper.findAllClients();

        for(ClientProfile client: clients) {
            ClientAggregate clientAggregate = calculateAggregatePosition(client.getClientId());
            clientAggregates.add(clientAggregate);
        }
        scheduledTasks.scheduleTaskUsingCronExpression();
        return clientAggregates;
    }

    private ClientAggregate calculateAggregatePosition(int clientId) {

        ClientAggregate clientAggregateResponse = new ClientAggregate();
        ClientProfile client = clientManagementMapper.findClientById(clientId);
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
            BigDecimal aggregatedBalance = BigDecimal.valueOf(clientAccountDetail.getDisplayBalance());
            balance = aggregatedBalance.add(balance);
        }
        return balance;
    }
}




