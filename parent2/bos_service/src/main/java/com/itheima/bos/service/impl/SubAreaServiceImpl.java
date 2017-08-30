package com.itheima.bos.service.impl;

import com.itheima.bos.dao.SubAreaDao;
import com.itheima.bos.domain.SubArea;
import com.itheima.bos.service.SubAreaService;
import com.itheima.bos.service.server.CrmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


@Service
@Transactional
public class SubAreaServiceImpl implements SubAreaService {

    @Autowired
    SubAreaDao subAreaDao ;


    @Override
    public Page<SubArea> findSubAreaByPage(Pageable pageable) {
        Page<SubArea> page = subAreaDao.findAll(pageable);
        return page;
    }

    @Override
    public List<SubArea> findByFixedAreaIsNull() {
        return subAreaDao.findByFixedAreaIsNull();
    }

    @Override
    public SubArea findById(String j) {
        return subAreaDao.findOne(j);
    }


    @Override
    public void save(SubArea subArea) {
//        因为id为String类型无法设置自增，所以要手动添加id
//        判断id是否存在，如果存在就是修改操作，如果不存在就手动设置id
        if(subArea.getId()==null) {
            subArea.setId(UUID.randomUUID().toString());
        }
        System.out.println("保存了");
        subAreaDao.save(subArea);
    }


}
