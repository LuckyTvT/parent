package com.itheima.bos.service.impl;

import com.itheima.bos.dao.WayBillDao;
import com.itheima.bos.domain.WayBill;
import com.itheima.bos.service.WayBillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class WayBillServiceImpl implements WayBillService {


    @Autowired
    private WayBillDao wayBillDao;

    @Override
    public void save(WayBill wayBill) {
        wayBillDao.save(wayBill);
    }

    @Override
    public List<WayBill> wayBillFindAll() {
        List<WayBill> list = (List<WayBill>) wayBillDao.findAll();
        return list;
    }
}
