package com.discoveybank.balancedispensing.mapper;

import java.util.List;

import com.discoveybank.balancedispensing.model.Client;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface ClientMapper {

    @Select("SELECT * FROM client")
    public List<Client> findAll();
//    Error attempting to get column 'title' from result set.
//    Cause: java.sql.SQLDataException: Cannot determine value type from string 'Mr'
    ;
    /**
     * @param clientId
     * @return
     */
    @Select("SELECT title as title, name as name, surname as surname FROM client WHERE client_id=#{client_id}")
    public Client findClientById(int clientId);

    @Delete("DELETE FROM client WHERE client_id = #{client_id}")
    public int deleteById(int id);

    @Insert("INSERT INTO client(client_id, title, name, surname, dob, client_sub_type_code) " +
        " VALUES (#{client_id}, #{title}, #{name}, #{surname}, #{dob}, #{client_sub_type_code})")
    public int insert(Client client);

    @Update("Update client set name=#{name}, " +
        " surname=#{surname}, dob=#{dob} where client_id=#{client_id}")
    public int update(Client client);

}