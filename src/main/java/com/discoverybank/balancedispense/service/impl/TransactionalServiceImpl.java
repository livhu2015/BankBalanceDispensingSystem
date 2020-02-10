package com.discoverybank.balancedispense.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.discoverybank.balancedispense.mapper.TransactionalAccountMapper;
import com.discoverybank.balancedispense.model.dto.*;
import com.discoverybank.balancedispense.service.TransactionalService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("TransactionalService")
public class TransactionalServiceImpl implements TransactionalService {
    private Logger logger = LoggerFactory.getLogger(TransactionalServiceImpl.class);

    private static final double ZAR_RATE = 1L;
    private static final double USD_RATE = 15.02;
    private static final double AUD_RATE = 2L;
    private static final String AUD = "AUD";
    private static final String USD = "USD";
    private static final String ZAR = "ZAR";

    @Autowired
    private TransactionalAccountMapper transactionalAccountMapper;

    @Override
    public List<TransactionalAccount> displayBalance(int clientId) {
        List<TransactionalAccount> transactionalAccountsResponse = new ArrayList<>();

        try {
            List<TransactionalAccount> transactionalAccounts = transactionalAccountMapper.findClientAccounts(clientId);
            for (TransactionalAccount transactionalAccount: transactionalAccounts) {
                if (transactionalAccount.isTransactional()) {
                    transactionalAccountsResponse.add(transactionalAccount);
                }
            }
        } catch (Exception ex) {
            logger.error("No account found:", ex.getMessage());
            return new ArrayList<>();
        }

        return transactionalAccountsResponse;
    }

    @Override
    public List<CurrencyAccountBalance> convertForeignCurrencyToRand(int clientId) {

        List<CurrencyAccountBalance> convertedAccountResponse = new ArrayList<>();
        CurrencyAccountBalance currencyAccountBalance = null;

        try {
            List<CurrencyAccountBalance> foreignAccounts = transactionalAccountMapper.findForeignCurrencyAccounts(clientId);
            CurrencyConversion currencyConversion;
            for (CurrencyAccountBalance foreignAccount: foreignAccounts) {
                currencyAccountBalance = new CurrencyAccountBalance();

                currencyConversion = transactionalAccountMapper.findCurrencyConversionRate(foreignAccount.getCurrencyCode());
                double currentBalance = foreignAccount.getCurrencyBalance();
                String accountNumber = foreignAccount.getAccountNumber();

                currencyAccountBalance.setRandAmount(currentBalance * currencyConversion.getRate());
                currencyAccountBalance.setCurrencyBalance(currentBalance);
                currencyAccountBalance.setAccountNumber(accountNumber);
                currencyAccountBalance.setConversionRate(currencyConversion.getRate());
                currencyAccountBalance.setCurrencyCode(currencyConversion.getCurrencyCode());

                convertedAccountResponse.add(currencyAccountBalance);

            }

            return convertedAccountResponse;
        } catch (Exception e) {
            logger.error("No account found: "+ e.getMessage());
            return new ArrayList<>();
        }
    }

    //Todo unit testing
    @Override
    public ClientAccountDetail processCashWithdraw(int clientId, String accountNumber, double amount) {
        ClientAccountDetail clientAccountResponse = new ClientAccountDetail();

        List<TransactionalAccount>  currentAccountBalances = displayBalance(clientId);
        for (TransactionalAccount clientAccount: currentAccountBalances) {
            double currentAccountBalance = clientAccount.getDisplayBalance();

            if (clientAccount.getAccountNumber().equals(accountNumber)) {

                if (currentAccountBalance > amount) {
                    currentAccountBalance = currentAccountBalance - amount;
                    clientAccountResponse.setDisplayBalance(currentAccountBalance);
                    //Update the client account
                    clientAccountResponse = transactionalAccountMapper.updateAccountBalance(clientAccountResponse);

                }else {
                    // insufficient funds
                }
                clientAccountResponse.setCurrencyCode(clientAccount.getCurrencyCode());
                clientAccountResponse.setAccountTypeCode(clientAccount.getAccountNumber());

            } else {
                //Todo handle the errors
                //invalid account
            }
        }
        return clientAccountResponse;
    }
}
