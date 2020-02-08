package com.discoveybank.balancedispensing.service;

import com.discoveybank.balancedispensing.model.ClientAccount;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.List;

public interface TransactionalService {
    List<ClientAccount> displayBalance(int clientId) throws IOException;
    ClientAccount convertCurrencyToRand(ClientAccount clientAccount, BigDecimal rate);
    ClientAccount processCashWithdraw(int clientId, String accountNumber, double amount) throws IOException;
}
