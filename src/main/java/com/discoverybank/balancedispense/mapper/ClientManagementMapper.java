package com.discoverybank.balancedispense.mapper;

import java.util.List;

import com.discoverybank.balancedispense.model.dao.Client;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface ClientManagementMapper {

    @Select("SELECT * FROM client")
    public List<Client> findAllClients();

    @Select("SELECT title as title, name as name, surname as surname " +
            "FROM client " +
            "WHERE client_id=#{client_id}")
    public Client findClientById(int clientId);

//    @Delete("DELETE FROM client WHERE client_id = #{client_id}")
//    public int deleteById(int id);
//
//    @Insert("INSERT INTO client(client_id, title, name, surname, dob, client_sub_type_code) " +
//        " VALUES (#{client_id}, #{title}, #{name}, #{surname}, #{dob}, #{client_sub_type_code})")
//    public int insert(Client client);
//
//    @Update("Update client set name=#{name}, " +
//        " surname=#{surname}, dob=#{dob} where client_id=#{client_id}")
//    public int update(Client client);

}