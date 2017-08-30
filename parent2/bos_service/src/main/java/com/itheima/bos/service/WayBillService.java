package com.itheima.bos.service;

import com.itheima.bos.domain.WayBill;

import java.util.List;

public interface WayBillService {
    void save(WayBill wayBill);

    List<WayBill> wayBillFindAll();
}
