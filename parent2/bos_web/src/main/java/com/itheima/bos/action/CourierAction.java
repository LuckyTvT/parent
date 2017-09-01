package com.itheima.bos.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.itheima.bos.domain.Courier;
import com.itheima.bos.domain.Standard;
import com.itheima.bos.service.BosService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.openxmlformats.schemas.spreadsheetml.x2006.main.STSourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import sun.java2d.pipe.SpanIterator;

import javax.persistence.criteria.*;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.SchemaOutputResolver;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect;

public class CourierAction extends BaseAction<Courier> {

    @Autowired
    BosService service;

    HttpServletResponse response = ServletActionContext.getResponse();

    private String ds;
    public void setDs(String ds) {
        this.ds = ds;
    }


    @Action("courierSave")
    public void courierSave() {
        Standard standard = model.getStandard();
        service.courierSave(model);
    }

    @Action("findByDeltag")
    public void findByDeltag(){
        List<Courier> list = service.findByDeltag();
        toJSON(list,"fixedAreas");
    }

//    条件分页查询
    @Action("findByCondition")
    public void findByCondition() {
        Pageable pageable = new PageRequest(page-1,rows);
        Specification<Courier> specification = new Specification<Courier>() {
            @Override
//            这是springdatajpa组合查询的条件装配方法
//            root：查询对象的主体
//            query：组装条件
//            cb：组装条件，相当于restriction
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                ArrayList<Predicate> list = new ArrayList<>();
                if(StringUtils.isNotBlank(model.getCourierNum())){
                    Predicate p1 = cb.equal(root.get("courierNum").as(Integer.class), model.getCourierNum());
                    list.add(p1);
                }
//                多表查询
                if(model.getStandard()!=null && StringUtils.isNotBlank(model.getStandard().getName())){
//                    先关联standard表
                    Join<Object, Object> join = root.join("standard");
                    Predicate p2 = cb.like(join.get("name").as(String.class), "%" + model.getStandard().getName() + "%");
                    list.add(p2);
                }
                if(StringUtils.isNotBlank(model.getCompany())){
                    Predicate p3 = cb.like(root.get("company").as(String.class), "%" + model.getCompany() + "%");
                    list.add(p3);
                }
                if(StringUtils.isNotBlank(model.getType())){
                    Predicate p4 = cb.like(root.get("type").as(String.class), "%" + model.getType() + "%");
                    list.add(p4);
                }
                if(list.size()==0){
                    return null;
                }
//                将集合转成数组，然后
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return cb.and(predicates);
            }
        };


        Page page1 = service.findByCondition(specification, pageable);
        javaToJSON(page1, "fixedAreas");

    }


    @Action("delCourier")
    public void delCourier() {
        HashMap<String, Object> map = new HashMap<>();
        try{
            service.delCourier(ds);
            map.put("success",true);
            toJSON(map);
        }catch (UnauthorizedException e){
            map.put("success",false);
            map.put("message","权限不足");
            toJSON(map);
        }catch(Exception e1){
            map.put("success",false);
            map.put("message","操作失败");
            toJSON(map);
        }
    }

    @Action("reNewCourier")
    public void reNewCourier() {
        service.reNewCourier(ds);
    }


    @Action("edit")
    public void edit(){
        HashMap<String, Object> map = new HashMap<>();
        try{
            service.edit();
            map.put("success",true);
            toJSON(map);
        }catch (UnauthorizedException e){
            map.put("success",false);
            map.put("message","权限不足");
            toJSON(map);
        }catch(Exception e1){
            map.put("success",false);
            map.put("message","操作失败");
            toJSON(map);
        }

    }


}
