package com.itheima.bos.dao;

import com.itheima.bos.domain.Area;
import com.itheima.bos.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderDao extends JpaRepository<Order,Integer> {



}
