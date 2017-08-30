package com.itheima.bos.dao;


import com.itheima.bos.domain.Standard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface StandardDao extends JpaRepository<Standard,Integer>{

//    @Query(value = " from Standard where name=?")
    @Query(value="select * from T_Standard where C_NAME=?",nativeQuery = true)
    List<Standard> fingByss(String s);

    List<Standard> findByNameLike(String s);
}
