package com.itheima.bos.service;

import com.itheima.bos.domain.Courier;
import com.itheima.bos.domain.Standard;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;


public interface BosService {

    void StandardSave(Standard standard);

    Page findStandardByPage(Pageable pageable);

    String findCourierByPage(Pageable pageable);

    List<Standard> findStandardAll();

    void courierSave(Courier courier);

    void delCourier(String ds);

    void reNewCourier(String ds);

    Page findByCondition(Specification<Courier> specification, Pageable pageable);

    List<Courier> findByDeltag();

    List<Standard> findByQ(String q);

    void edit();
}
