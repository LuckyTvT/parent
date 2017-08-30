package com.itheima.bos.service.impl;

import com.itheima.bos.dao.TakeTimeDao;
import com.itheima.bos.domain.TakeTime;
import com.itheima.bos.service.TakeTimeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class TakeTimeServiceImpl implements TakeTimeService {

    @Autowired
    private TakeTimeDao takeTimeDao;

    @Override
    public List<TakeTime> findTakeTimeAll() {
        List<TakeTime> list = (List<TakeTime>) takeTimeDao.findAll();
        return list;
    }

    @Override
    public List<TakeTime> findByQ(String q) {
        List<TakeTime> list = takeTimeDao.findByNameLike("%"+q+"%");
        return list;
    }
}
