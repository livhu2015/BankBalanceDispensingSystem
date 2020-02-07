package com.discoveybank.balancedispensing.service;

import java.util.List;

import com.discoveybank.balancedispensing.model.ClientAccount;

public interface AccountBalance {
    public List<ClientAccount> displayBalance( int clientId);
    }