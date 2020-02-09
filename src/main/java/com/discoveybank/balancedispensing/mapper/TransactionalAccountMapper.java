package com.discoveybank.balancedispensing.mapper;

import java.util.List;

import com.discoveybank.balancedispensing.model.ClientAccount;

import com.discoveybank.balancedispensing.model.CurrencyConversionRate;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface TransactionalAccountMapper {
    /**
     * @param clientId
     * @return
     */


    @Select("\n" +
        "SELECT account_number as account_number, client_id as client_id, currency_code as currency_code,\n" +
        "display_balance as display_balance, description as account_type_code\n" +
        "FROM client_account\n" +
        "INNER JOIN account_type\n" +
        "ON account_type.account_type_code=client_account.account_type_code\n" +
        "WHERE client_id=#{clientId}\n" +
        "ORDER BY display_balance DESC\n")
    public List<ClientAccount> findClientAccounts(int clientId);

    @Update("Update client_account set display_balance=#{display_balance} where client_id=#{client_id}")
    ClientAccount updateAccountBalance(ClientAccount updatedClientAccount);

    @Select("SELECT * FROM currency_conversion_rate\n" +
            "WHERE currency_code=#{currencyCode}")
    CurrencyConversionRate findCurrencyConversionRate(String currencyCode);
}