package com.itheima.bos.service;

import com.itheima.bos.domain.FixedArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface FixedAreaService {
    Page<FixedArea> findFixedAreaByPage(Pageable pageable);

    void fixedArea_save(FixedArea model,String subAreaIds);

    void associateCourier(String id, int takeTimeId, int courierId);
}
