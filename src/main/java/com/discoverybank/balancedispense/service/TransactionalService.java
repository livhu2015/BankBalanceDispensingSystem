package com.discoverybank.balancedispense.service;

import com.discoverybank.balancedispense.model.dao.ClientAccount;
import com.discoverybank.balancedispense.model.dto.ClientAccountDetail;
import com.discoverybank.balancedispense.model.dto.CurrencyAccountBalance;
import com.discoverybank.balancedispense.model.dto.TransactionalAccount;

import java.io.IOException;
import java.util.List;

public interface TransactionalService {
    List<TransactionalAccount> displayBalance(int clientId) throws IOException;
    List<CurrencyAccountBalance> convertForeignCurrencyToRand(int clientId);
    ClientAccountDetail processCashWithdraw(int clientId, String accountNumber, double amount) throws IOException;
}
