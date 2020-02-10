package com.discoverybank.balancedispense.mapper;

import java.util.List;

import com.discoverybank.balancedispense.model.dao.ClientAccount;

import com.discoverybank.balancedispense.model.dao.CurrencyConversionRate;
import com.discoverybank.balancedispense.model.dto.CurrencyAccountBalance;
import com.discoverybank.balancedispense.model.dto.TransactionalAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransactionalAccountMapper {

    @Select("\n" +
        "SELECT account_number as accountNumber, currency_code as currencyCode,\n" +
        "display_balance as displayBalance, description as accountName, transactional as transactional\n" +
        "FROM client_account\n" +
        "INNER JOIN account_type\n" +
        "ON account_type.account_type_code=client_account.account_type_code\n" +
        "WHERE client_id=#{clientId}\n" +
        "ORDER BY display_balance DESC\n")
    List<TransactionalAccount> findClientAccounts(int clientId);

    @Update("Update client_account set display_balance=#{display_balance} where client_id=#{client_id}")
    ClientAccount updateAccountBalance(ClientAccount updatedClientAccount);

    @Select("SELECT account_number as accountNumber, currency_code as currencyCode, \n" +
            "display_balance as displayBalance\n" +
            "FROM client_account \n" +
            "INNER JOIN account_type\n" +
            "ON account_type.account_type_code=client_account.account_type_code\n" +
            "WHERE currency_code != \"ZAR\" and client_id=#{clientId}\n" +
            "ORDER BY display_balance DESC")
    List<CurrencyAccountBalance> findForeignCurrencyAccounts(int clientId);

    @Select("SELECT * FROM currency_conversion_rate\n" +
            "WHERE currency_code=#{currencyCode}")
    CurrencyConversionRate findCurrencyConversionRate(String z);
}