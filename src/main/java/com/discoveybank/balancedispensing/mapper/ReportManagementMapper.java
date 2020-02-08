package com.discoveybank.balancedispensing.mapper;

import com.discoveybank.balancedispensing.model.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ReportManagementMapper {

    @Select("SELECT name as name, surname as surname, account_number as account_number, display_balance as display_balance, description as description \n" +
            "FROM client INNER JOIN client_account on client_account.client_id=client.client_id \n" +
            "JOIN account_type on account_type.account_type_code=client_account.account_type_code \n" +
            "WHERE transactional=1\n" +
            "ORDER BY display_balance DESC")
    public List<ClientAccountSummary> findAllTransactionalAccounts();

    @Select("SELECT * FROM demo.client_account " +
            "INNER JOIN account_type " +
            "ON account_type.account_type_code=client_account.account_type_code " +
            "WHERE transactional=#{transactional} " +
            "AND client_id=#{clientId}")

    public List<ClientAccountDetail> findClientTransactionAccounts(int clientId, int transactional);
}
