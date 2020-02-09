package com.discoverybank.balancedispense.service;

import com.discoverybank.balancedispense.model.dao.ClientAccount;

import java.io.IOException;
import java.util.List;

public interface TransactionalService {
    List<ClientAccount> displayBalance(int clientId) throws IOException;
    ClientAccount convertCurrencyToRand(ClientAccount clientAccount, String currencyCode);
    ClientAccount processCashWithdraw(int clientId, String accountNumber, double amount) throws IOException;
}
