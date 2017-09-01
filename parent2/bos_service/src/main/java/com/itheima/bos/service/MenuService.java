package com.itheima.bos.service;

import com.itheima.bos.domain.system.Menu;

import java.util.List;

public interface MenuService {

    List<Menu> menu_findByPidIsNull();


    Menu save(Menu model);

    Menu findOne(Integer j);
}
