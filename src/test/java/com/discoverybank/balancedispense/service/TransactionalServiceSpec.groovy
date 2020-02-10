package com.discoverybank.balancedispense.service;

import com.discoverybank.balancedispense.mapper.TransactionalAccountMapper;
import com.discoverybank.balancedispense.model.dao.ClientAccount
import com.discoverybank.balancedispense.model.dto.TransactionalAccount
import com.discoverybank.balancedispense.service.impl.TransactionalServiceImpl
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Transactional Management Service Test")
@Subject(TransactionalServiceImpl)
class TransactionalServiceSpec extends Specification {

    private TransactionalAccountMapper transactionalAccountMapper = Mock()

    public TransactionalServiceImpl transactionalService = new TransactionalServiceImpl(
            transactionalAccountMapper: transactionalAccountMapper,)

    def 'should be able to view all transactional accounts with the available balances' () {
        given: 'client id'
        int clientId = 1

        when: 'calling a view balance method'
        List<TransactionalAccount> clientAccounts = transactionalService.displayBalance(clientId)

        then: 'should return the list of accounts with available balance'
        1 * transactionalAccountMapper.findClientAccounts(_) >> new ArrayList<TransactionalAccount>([clientAccountResponse()])

        and: 'assert the results'
        !clientAccounts.isEmpty()
        clientAccounts.get(0).getAccountNumber().equals("some-account")
        clientAccounts.get(0).displayBalance == 20
        clientAccounts.get(0).transactional

    }

    def 'the client does not have any qualifying accounts'() {
        given: 'client id'
        int clientId = 1

        when: 'calling a view balance method'
        List<TransactionalAccount> clientAccounts = transactionalService.displayBalance(clientId)

        then: 'should not return the list of accounts'
        1 * transactionalAccountMapper.findClientAccounts(_) >> new ArrayList<ClientAccount>([])

        and: 'display the error message'
        clientAccounts.isEmpty()
    }

    def 'no transactional account accounts'() {
        given: 'client id'
        int clientId = 1

        when: 'calling a view balance method'
        List<TransactionalAccount> clientAccounts = transactionalService.displayBalance(clientId)

        then: 'should not return the list of accounts'
        1 * transactionalAccountMapper.findClientAccounts(_) >> new ArrayList<ClientAccount>([nonTransactionalAccountResponse()])

        and: 'display the error message'
        clientAccounts.isEmpty()
    }

    private TransactionalAccount clientAccountResponse() {
        TransactionalAccount account = new TransactionalAccount();
        account.setAccountNumber("some-account")
        account.setAccountName("some-name")
        account.setDisplayBalance(20)
        account.setCurrencyCode("ZAR")
        account.setTransactional(true)
        return account
    }
    private TransactionalAccount nonTransactionalAccountResponse() {
        TransactionalAccount account = new TransactionalAccount();
        account.setAccountNumber("some-account")
        account.setAccountName("some-name")
        account.setDisplayBalance(20)
        account.setCurrencyCode("ZAR")
        account.setTransactional(false)
        return account
    }
}
