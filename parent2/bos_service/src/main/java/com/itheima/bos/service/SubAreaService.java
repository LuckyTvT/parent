package com.itheima.bos.service;

import com.itheima.bos.domain.SubArea;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface SubAreaService {

    void save(SubArea subArea);

    Page<SubArea> findSubAreaByPage(Pageable pageable);

    List<SubArea> findByFixedAreaIsNull();

    SubArea findById(String j);
}
