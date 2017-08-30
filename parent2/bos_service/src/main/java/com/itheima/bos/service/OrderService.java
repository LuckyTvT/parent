package com.itheima.bos.service;

import com.itheima.bos.domain.Area;
import com.itheima.bos.domain.Order;

import javax.jws.WebService;


@WebService
public interface OrderService {

    void save(Order order);

    void findByCityAndProvinceAndDistrict(Area area, Order order);
}
