package com.itheima.bos.service.impl;

import com.itheima.bos.dao.CourierDao;
import com.itheima.bos.dao.FixedAreaDao;
import com.itheima.bos.dao.SubAreaDao;
import com.itheima.bos.dao.TakeTimeDao;
import com.itheima.bos.domain.Courier;
import com.itheima.bos.domain.FixedArea;
import com.itheima.bos.domain.SubArea;
import com.itheima.bos.domain.TakeTime;
import com.itheima.bos.service.FixedAreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FixedAreaServiceImpl implements FixedAreaService {

    @Autowired
    FixedAreaDao fixedAreaDao;
    @Autowired
    SubAreaDao subAreaDao;
    @Autowired
    CourierDao courierDao;
    @Autowired
    TakeTimeDao takeTimeDao;

    @Override
    public Page<FixedArea> findFixedAreaByPage(Pageable pageable) {
        Page<FixedArea> page = fixedAreaDao.findAll(pageable);
        return page;
    }

    @Override
    public void fixedArea_save(FixedArea model, String subAreaIds) {
        String[] strings = subAreaIds.split(",");
//        保存对象后返回一个持久太的该对象
        model = fixedAreaDao.save(model);
        for (int i = 0; i < strings.length; i++) {
            SubArea subArea = subAreaDao.findOne(strings[i]);
//            设置该持久态对象的属性，会自动更新数据库关联的内容。
            subArea.setFixedArea(model);
        }
    }

    @Override
    public void associateCourier(String id, int takeTimeId, int courierId) {
        FixedArea fixedArea = fixedAreaDao.findOne(id);
        Courier courier = courierDao.findOne(courierId);
        TakeTime takeTime = takeTimeDao.findOne(takeTimeId);
        courier.setTakeTime(takeTime);
        fixedArea.getCouriers().add(courier);

    }


}
