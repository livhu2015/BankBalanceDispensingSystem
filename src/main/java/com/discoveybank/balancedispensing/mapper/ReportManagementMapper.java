package com.discoveybank.balancedispensing.mapper;

import com.discoveybank.balancedispensing.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportManagementMapper {

    @Select("SELECT name as name, surname as surname, account_number as account_number, display_balance as display_balance, description as description FROM client INNER JOIN client_account on client_account.client_id=client.client_id join account_type on account_type.account_type_code=client_account.account_type_code ORDER BY display_balance DESC")
    public List<ClientAccountSummary> findAll();

    @Select("SELECT title as title, name as name, surname as surname, account_type_code as account_type_code, display_balance as display_balance, account_type as account_type " +
            "FROM client INNER JOIN client_account ON client_account.client_id=client.client_id")
    public List<ClientAggregate> findClientSummary();

    @Select("SELECT title as title, name as name, surname as surname, account_number as account_number, display_balance as display_balance, description as description, transactional as transactional FROM client INNER JOIN client_account on client_account.client_id=client.client_id join account_type on account_type.account_type_code=client_account.account_type_code WHERE transactional=0")
    public List<ClientAccountDetail> findClientTransactionalAccount();

    @Select("SELECT title as title, name as name, surname as surname, account_number as account_number, display_balance as display_balance, description as description, transactional as transactional FROM client INNER JOIN client_account on client_account.client_id=client.client_id join account_type on account_type.account_type_code=client_account.account_type_code WHERE transactional=1")
    public List<ClientAccountDetail> findClientLoanAccounts();

    @Select("SELECT * FROM demo.client_account " +
            "INNER JOIN account_type " +
            "ON account_type.account_type_code=client_account.account_type_code " +
            "WHERE transactional=#{transactional} " +
            "AND client_id=#{clientId}")

    public List<ClientAccountDetail> findClientTransactionAccounts(int clientId, int transactional);

//    @Select("SELECT title as title, name as name, surname as surname FROM client WHERE client_id = #{client_id}")
//    public Client findClientById(int clientId);


}
