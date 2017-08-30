package com.itheima.bos.dao;

import com.itheima.bos.domain.Courier;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CourierDao extends JpaRepository<Courier,Integer>,JpaSpecificationExecutor<Courier> {

    @Query("update Courier set deltag = '1' where id = ?")
    @Modifying
    void del(int s);

    @Query("update Courier set deltag = '0' where id = ?")
    @Modifying
    void reNew(int i);


    @Query("from Courier where deltag != '1'")
    List<Courier> findByDeltag();
}
