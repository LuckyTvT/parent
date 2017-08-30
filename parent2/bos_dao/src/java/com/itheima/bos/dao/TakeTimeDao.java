package com.itheima.bos.dao;

import com.itheima.bos.domain.TakeTime;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TakeTimeDao extends JpaRepository<TakeTime,Integer> {

    List<TakeTime> findByNameLike(String s);
}
