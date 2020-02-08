package com.discoveybank.balancedispensing.service;

import com.discoveybank.balancedispensing.model.ClientAccount;

import java.math.BigDecimal;
import java.util.List;

public interface TransactionalService {
    List<ClientAccount> displayBalance(int clientId);
    ClientAccount convertCurrencyToRand(ClientAccount clientAccount, BigDecimal rate);
    ClientAccount processCashWithdraw(int clientId, String accountNumber, double amount);
}
