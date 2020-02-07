package com.discoveybank.balancedispensing.mapper;

import java.util.List;

import com.discoveybank.balancedispensing.model.ClientAccount;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientAccountMapper {
    /**
     * @param client_id
     * @return
     */
    @Select("SELECT account_number as account_number, client_id as client_id, account_type_code as account_type_code, currency_code as currency_code, display_balance as display_balance FROM client_account WHERE client_id = #{client_id}")
    public List<ClientAccount> findById(int client_id);

    @Select("SELECT * FROM demo.client_account")
    public List<ClientAccount> findAll();

}