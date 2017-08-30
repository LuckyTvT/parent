package com.itheima.bos.service;

import com.itheima.bos.domain.TakeTime;

import java.util.List;

public interface TakeTimeService {
    List<TakeTime> findTakeTimeAll();

    List<TakeTime> findByQ(String q);
}
