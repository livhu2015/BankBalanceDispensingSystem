package com.discoverybank.balancedispense.service;

import com.discoverybank.balancedispense.mapper.ClientManagementMapper;
import com.discoverybank.balancedispense.mapper.ReportManagementMapper;
import com.discoverybank.balancedispense.mapper.TransactionalAccountMapper;
import com.discoverybank.balancedispense.model.dao.ClientAccount;
import com.discoverybank.balancedispense.service.impl.ReportManagementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import spock.lang.Specification;
import spock.lang.Subject;
import spock.lang.Title;

import java.util.ArrayList;
import java.util.List;

@Title("Report Management Service Test")
@Subject(ReportManagementService.class)
public class ReportManagementServiceSpec extends Specification {

//    private ReportManagementMapper reportManagementMapper = Mock();
//    private ClientManagementMapper clientManagementMapper = Mock()
//    private TransactionalAccountMapper transactionalAccountMapper = Mock();
//
//    private ReportManagementServiceImpl reportManagementService = new ReportManagementServiceImpl(
//            reportManagementMapper: reportManagementMapper, clientManagementMapper: clientManagementMapper,
//    transactionalAccountMapper: transactionalAccountMapper
//    )
//
//    def 'should be able to view all transactional accounts with the available balances' () {
//        given: 'client id'
//        int clientId = 1
//
//        when: 'calling a view balance method'
//        List<ClientAccount> clientAccounts = transactionalService.displayBalance(clientId)
//
//        then: 'should return the list of accounts with available balance'
//        1 * transactionalAccountMapper.findClientAccounts(_) >> new ArrayList<ClientAccount>(new ClientAccount(account_number: "some-account",client_id: 1, account_type_code: "SA", currency_code: "ZAR", display_balance: 20))
//
//        and: 'assert the results'
//        clientAccounts.size() > 0
//        clientAccounts.get(0).getAccount_number().equals("some-account")
//    }

}
