package com.discoveybank.balancedispensing.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.discoveybank.balancedispensing.model.ClientAccount;
import com.discoveybank.balancedispensing.mapper.TransactionalAccountMapper;

import com.discoveybank.balancedispensing.service.TransactionalService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TransactionalService")
public class TransactionalServiceImpl implements TransactionalService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private static final double ZAR_RATE = 1L;
    private static final double USD_RATE = 15.02;
    private static final double AUD_RATE = 2L;
    private static final String AUD = "AUD";
    private static final String USD = "USD";
    private static final String ZAR = "ZAR";

    private final TransactionalAccountMapper transactionalAccountMapper;

    @Autowired
    public TransactionalServiceImpl(TransactionalAccountMapper transactionalAccountMapper) {
        this.transactionalAccountMapper = transactionalAccountMapper;
    }

    //Todo unit testing
    @Override
    public List<ClientAccount> displayBalance( int clientId) {
        List<ClientAccount> clientAccounts = transactionalAccountMapper.findById(clientId);

        if(clientAccounts.isEmpty()) {
            return new ArrayList<ClientAccount>();
        }

        logger.info("AccountNumber:::"+ clientAccounts.get(0).getAccount_number());

        return clientAccounts;
    }

    //Todo unit testing
    @Override
    public ClientAccount convertCurrencyToRand(ClientAccount clientAccount, BigDecimal rate) {
        ClientAccount convertedAccount = new ClientAccount();

        String clientAccountNumber = clientAccount.getAccount_number();
        BigDecimal currentBalance = clientAccount.getDisplay_balance();

        convertedAccount.setAccount_number(clientAccountNumber);
        convertedAccount.setAccount_type_code(clientAccount.getAccount_type_code());
        convertedAccount.setDisplay_balance(currentBalance.multiply(rate));
        convertedAccount.setClient_id(clientAccount.getClient_id());
        convertedAccount.setCurrency_code(clientAccount.getCurrency_code());

        return convertedAccount;
    }

    //Todo unit testing
    @Override
    public ClientAccount processCashWithdraw(int clientId, String accountNumber, double amount) {

        ClientAccount clientAccountResponse = new ClientAccount();
        List<ClientAccount>  currentAccountBalances = displayBalance(clientId);
        for (ClientAccount clientAccount: currentAccountBalances) {
            double currentAccountBalance = clientAccount.getDisplay_balance().doubleValue();

            if (clientAccount.getAccount_number().equals(accountNumber)) {

                if (currentAccountBalance > amount) {
                    currentAccountBalance = currentAccountBalance - amount;
                    clientAccountResponse.setDisplay_balance(BigDecimal.valueOf(currentAccountBalance));
                    //Update the client account
                    clientAccountResponse = transactionalAccountMapper.updateAccountBalance(clientAccountResponse);

                }else {
                    // insufficient funds
                }
                clientAccountResponse.setCurrency_code(clientAccount.getCurrency_code());
                clientAccountResponse.setClient_id(clientAccount.getClient_id());
                clientAccountResponse.setAccount_type_code(clientAccount.getAccount_type_code());
                clientAccountResponse.setAccount_number(clientAccount.getAccount_number());

            } else {
                //Todo handle the errors
                //invalid account
            }
        }
        return clientAccountResponse;
    }

}