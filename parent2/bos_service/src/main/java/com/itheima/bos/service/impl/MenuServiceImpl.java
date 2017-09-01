package com.itheima.bos.service.impl;

import com.itheima.bos.dao.MenuDao;
import com.itheima.bos.domain.system.Menu;
import com.itheima.bos.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    MenuDao menuDao;

    @Override
    public List<Menu> menu_findByPidIsNull() {
        return menuDao.findByParentMenuIsNull();
    }

    @Override
    public Menu save(Menu model) {
        Menu menu = menuDao.save(model);
        return menu;
    }

    @Override
    public Menu findOne(Integer j) {
        return menuDao.findOne(j);
    }


}
