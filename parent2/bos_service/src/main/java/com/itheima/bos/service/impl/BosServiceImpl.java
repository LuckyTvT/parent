package com.itheima.bos.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.itheima.bos.dao.CourierDao;
import com.itheima.bos.dao.StandardDao;
import com.itheima.bos.domain.Courier;
import com.itheima.bos.domain.Standard;
import com.itheima.bos.service.BosService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect;

@Service
@Transactional
public class BosServiceImpl implements BosService {

    @Autowired
    StandardDao standardDao;

    @Autowired
    CourierDao courierDao;


    @Override
    public void StandardSave(Standard standard) {
        standardDao.save(standard);
    }

    @Override
    public Page findStandardByPage(Pageable pageable) {

        return standardDao.findAll(pageable);
    }

    @Override
    public String findCourierByPage(Pageable pageable) {
        Page<Courier> page = courierDao.findAll(pageable);
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",page.getTotalElements());
        map.put("rows",page.getContent());
//        JsonConfig jsonConfig = new JsonConfig();
//        jsonConfig.setExcludes(new String[]{"fixedAreas"});
//        String json = JSONObject.fromObject(map, jsonConfig).toString();
        String json = JSON.toJSONString(map, new PropertyFilter(){
            @Override
            public boolean apply(Object object, String name, Object value) {
                if(name.equalsIgnoreCase("fixedAreas")){
                    //false表示last字段将被排除在外
                    return false;
                }
                return true;
            }
//         打开循环引用检测，防止在封装引用类型数据时，数据变成这个样子{"$ref":"$.rows[0].standard"}
//         感觉可能在第一次查到引用类型数据后
        },DisableCircularReferenceDetect);
        System.out.println(json);
        return json;
    }

    @Override
    public List<Standard> findStandardAll() {
        List<Standard> list = (List<Standard>) standardDao.findAll();

        return list;
    }

    @RequiresPermissions("save")
    @Override
    public void courierSave(Courier courier) {
        courier.setDeltag('0');
        courierDao.save(courier);
    }

    @RequiresPermissions("delete")
    @Override
    public void delCourier(String ds) {

        String[] split = ds.split(",");
        for (String s : split) {
            int i = Integer.parseInt(s);
            courierDao.del(i);
        }

    }

    @RequiresPermissions("delete")
    @Override
    public void reNewCourier(String ds) {

        String[] split = ds.split(",");
        for (String s : split) {
            int i = Integer.parseInt(s);
            courierDao.reNew(i);
        }

    }


    @Override
    public Page findByCondition(Specification<Courier> specification, Pageable pageable) {
        return courierDao.findAll(specification,pageable);
    }


    @Override
    public List<Courier> findByDeltag() {
        List<Courier> list = (List<Courier>) courierDao.findByDeltag();
        return list;
    }


    @Override
    public List<Standard> findByQ(String q) {
        List<Standard> list = standardDao.findByNameLike("%"+q+"%");
        return list;
    }

    @RequiresPermissions("update")
    @Override
    public void edit() {

    }


}
