package com.itheima.bos.dao;

import com.itheima.bos.domain.SubArea;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubAreaDao extends JpaRepository<SubArea,String> {


    List<SubArea> findByFixedAreaIsNull();
}
