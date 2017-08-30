package com.itheima.bos.dao;

import com.itheima.bos.domain.Area;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AreaDao extends JpaRepository<Area,String>{

    List<Area> findByCitycodeLikeOrShortcodeLike(String s, String s1);
    Area findByCityAndProvinceAndDistrict(String area, String area1, String area2);
}
