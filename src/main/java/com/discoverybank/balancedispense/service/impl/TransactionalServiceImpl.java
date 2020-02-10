package com.discoverybank.balancedispense.service.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import com.discoverybank.balancedispense.mapper.TransactionalAccountMapper;
import com.discoverybank.balancedispense.model.dao.ClientAccount;
import com.discoverybank.balancedispense.model.dao.CurrencyConversionRate;
import com.discoverybank.balancedispense.model.dto.CurrencyAccountBalance;
import com.discoverybank.balancedispense.model.dto.CurrencyConversion;
import com.discoverybank.balancedispense.model.dto.TransactionalAccount;
import com.discoverybank.balancedispense.service.MessageConsumer;
import com.discoverybank.balancedispense.service.MessageProducer;
import com.discoverybank.balancedispense.service.TransactionalService;

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
        CurrencyAccountBalance currencyAccountBalance = new CurrencyAccountBalance();

        try {
            List<CurrencyAccountBalance> foreignAccounts = transactionalAccountMapper.findForeignCurrencyAccounts(clientId);

            for (CurrencyAccountBalance foreignAccount: foreignAccounts) {

                CurrencyConversion currencyConversion = transactionalAccountMapper.findCurrencyConversionRate(foreignAccount.getCurrencyCode());
                double currentBalance = foreignAccount.getDisplayBalance();
                String accountNumber = foreignAccount.getAccountNumber();

                currencyAccountBalance.setConvertedAmount(currentBalance * currencyConversion.getRate());
                currencyAccountBalance.setDisplayBalance(currentBalance);
                currencyAccountBalance.setAccountNumber(accountNumber);
                currencyAccountBalance.setConversionRate(currencyConversion.getRate());
                currencyAccountBalance.setCurrencyCode(currencyConversion.getCode());

                convertedAccountResponse.add(currencyAccountBalance);
            }
        } catch (Exception e) {
            logger.error("No account found: ", e.getMessage());
            return new ArrayList<>();
        }
        return convertedAccountResponse;
    }

    @Override
    public ClientAccount processCashWithdraw(int clientId, String accountNumber, double amount) throws IOException {
        return null;
    }
//
//    //Todo unit testing
//    @Override
//    public TransactionalAccount processCashWithdraw(int clientId, String accountNumber, double amount) throws IOException {
//
//        ClientAccount clientAccountResponse = new ClientAccount();
//
//        List<TransactionalAccount>  currentAccountBalances = displayBalance(clientId);
//        for (TransactionalAccount clientAccount: currentAccountBalances) {
//            double currentAccountBalance = clientAccount.getDisplay_balance().doubleValue();
//
//            if (clientAccount.getAccount_number().equals(accountNumber)) {
//
//                if (currentAccountBalance > amount) {
//                    currentAccountBalance = currentAccountBalance - amount;
//                    clientAccountResponse.setDisplay_balance(BigDecimal.valueOf(currentAccountBalance));
//                    //Update the client account
//                    clientAccountResponse = transactionalAccountMapper.updateAccountBalance(clientAccountResponse);
//
//                }else {
//                    // insufficient funds
//                }
//                clientAccountResponse.setCurrency_code(clientAccount.getCurrency_code());
//                clientAccountResponse.setClient_id(clientAccount.getClient_id());
//                clientAccountResponse.setAccount_type_code(clientAccount.getAccount_type_code());
//                clientAccountResponse.setAccount_number(clientAccount.getAccount_number());
//
//            } else {
//                //Todo handle the errors
//                //invalid account
//            }
//        }
//        return clientAccountResponse;
//    }

}