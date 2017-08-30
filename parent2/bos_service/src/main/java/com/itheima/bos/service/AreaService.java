package com.itheima.bos.service;

import com.itheima.bos.domain.Area;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.jws.WebService;
import java.io.File;
import java.util.List;

@WebService
public interface AreaService {
    void save(File areaFile);

    Page<Area> findAreaByPage(Pageable pageable);

    List<Area> findArea();

    List<Area> findByQ(String q);


}
