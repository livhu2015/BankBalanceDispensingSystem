package com.discoverybank.balancedispense.service

import com.discoverybank.balancedispense.mapper.ClientManagementMapper
import com.discoverybank.balancedispense.mapper.ReportManagementMapper
import com.discoverybank.balancedispense.mapper.TransactionalAccountMapper
import com.discoverybank.balancedispense.model.dao.Client
import com.discoverybank.balancedispense.model.dto.ClientAccountDetail
import com.discoverybank.balancedispense.model.dto.ClientAccountSummary
import com.discoverybank.balancedispense.model.dto.ClientAggregate
import com.discoverybank.balancedispense.model.dto.ClientProfile
import com.discoverybank.balancedispense.model.dto.TransactionalAccount
import com.discoverybank.balancedispense.service.impl.ReportManagementServiceImpl
import com.discoverybank.balancedispense.service.impl.TransactionalServiceImpl
import spock.lang.Specification
import spock.lang.Subject
import spock.lang.Title

@Title("Report Management Service Test")
@Subject(ReportManagementServiceImpl)
class ReportManagementServiceSpec extends Specification {

    private ReportManagementMapper reportManagementMapper = Mock()
    private ClientManagementMapper clientManagementMapper = Mock()
    private TransactionalAccountMapper transactionalAccountMapper = Mock()

    public ReportManagementServiceImpl reportManagementService = new ReportManagementServiceImpl(reportManagementMapper:reportManagementMapper,
            clientManagementMapper: clientManagementMapper, transactionalAccountMapper: transactionalAccountMapper)

    def 'should generated that displays a list of all clients along with the account details of the client’s account with the highest balance' () {

        when: 'calling a generate client account report'
        List<ClientAccountSummary> accountSummaries = reportManagementService.generateClientAccountReport()

        then: 'should return the list of accounts summary with available balance'
        1 * reportManagementMapper.findAllTransactionalAccounts() >> new ArrayList<ClientAccountSummary>([clientAccountSummary()])

        and: 'assert the results'
        !accountSummaries.isEmpty()
        accountSummaries.get(0).displayBalance==50
        accountSummaries.size()==1
    }

    def 'should not displays a list of all clients' () {

        when: 'calling a generate client account report'
        List<ClientAccountSummary> accountSummaries = reportManagementService.generateClientAccountReport()

        then: 'should return the list of accounts summary with available balance'
        1 * reportManagementMapper.findAllTransactionalAccounts() >> new ArrayList<ClientAccountSummary>([])

        and: 'assert the results'
        accountSummaries.isEmpty()
    }

    def 'generate report of client accounts summary should throw an exception' () {

        when: 'calling a generate client account report'
        List<ClientAccountSummary> accountSummaries = reportManagementService.generateClientAccountReport()

        then: 'should return the list of accounts summary with available balance'
        1 * reportManagementMapper.findAllTransactionalAccounts() >> new Exception("could not read the database")

        and: 'assert the results'
        accountSummaries.isEmpty()
    }

    def 'calculate clients aggregated position' () {

        when: 'calling a generate client account report'
        List<ClientAggregate> accountSummaries = reportManagementService.calculateClientsAggregatePosition()

        then: 'should return the list of accounts summary with available balance'
        1 * clientManagementMapper.findAllClients() >> new ArrayList<>([clientOne(), clientTwo()])
        2 * clientManagementMapper.findClientById(_) >> clientOne()
        2 * reportManagementMapper.findClientTransactionAccounts(_, 1) >> new ArrayList<ClientAccountDetail>()
        2 * reportManagementMapper.findClientTransactionAccounts(_, 0) >> new ArrayList<ClientAccountDetail>()

        and: 'assert the results'
        !accountSummaries.isEmpty()
        accountSummaries.size()==2
    }
    private ClientProfile clientOne() {
        ClientProfile client = new ClientProfile()
        client.setSurname("Green")
        client.setTitle("Mr")
        client.setName("Livhu")
        client
    }
    private ClientProfile clientTwo() {
        ClientProfile client = new ClientProfile()
        client.setSurname("Blue")
        client.setTitle("Miss")
        client.setName("Lavhe")
        client
    }
    private ClientAggregate clientAggregate() {
        ClientAccountSummary accountSummary = new ClientAccountSummary()
        accountSummary.setAccountNumber("some-account")
        accountSummary.setDisplayBalance(50)
        accountSummary.setDescription("description")
        accountSummary.setName("name")
        accountSummary.setSurname("surname")
    }

    private ClientAccountSummary clientAccountSummary() {
        ClientAccountSummary accountSummary = new ClientAccountSummary()
        accountSummary.setAccountNumber("some-account")
        accountSummary.setDisplayBalance(50)
        accountSummary.setDescription("description")
        accountSummary.setName("name")
        accountSummary.setSurname("surname")
    }
}
