package com.discoveybank.balancedispensing.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.discoveybank.balancedispensing.model.ClientAccount;
import com.discoveybank.balancedispensing.mapper.ClientAccountMapper;
import com.discoveybank.balancedispensing.service.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("AccountBalance")
public class AccountBalanceImpl implements AccountBalance {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ClientAccountMapper clientAccountMapper;
 
    @Override
    public List<ClientAccount> displayBalance( int clientId) {
        List<ClientAccount> clientAccounts = clientAccountMapper.findById(clientId);

        if(clientAccounts.isEmpty()) {
            return new ArrayList<ClientAccount>();
        }

        logger.info("AccountNumber:::"+ clientAccounts.get(0).getAccount_number());

        return clientAccounts;
    }

}