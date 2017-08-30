package com.itheima.bos.action;

import com.itheima.bos.domain.Courier;
import com.itheima.bos.domain.Standard;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;


import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;


@Controller
@Namespace("/")
@Scope("prototype")
@ParentPackage("struts-default")
public class FindAction extends ActionSupport implements ModelDriven<Courier> {

    private Courier courier = new Courier();

    @Override
    public Courier getModel() {
        return courier;
    }

    public void setPage(int page) {
        this.page = page;
    }

    private int page;

    public void setRows(int rows) {
        this.rows = rows;
    }

    private int rows;

    @Action("pageQuery")
    public void pageQuery() {
        Pageable pageable = new PageRequest(page-1,rows);
        final String courierNum = courier.getCourierNum();
        final String company = courier.getCompany();
        final String type = courier.getType();
        final Standard standard = courier.getStandard();


        Specification<Courier> specification = new Specification<Courier>() {
            @Override
//          root相当于查询对象的主体（一个对象），cb相当于detachedCritieria中的Restriction
            public Predicate toPredicate(Root<Courier> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate predicate = null;

                List<Predicate> list = new ArrayList<>();
                if(StringUtils.isNotBlank(courierNum)){
                    Predicate p1 = cb.equal(root.get("courierNum").as(String.class), courierNum);
                    list.add(p1);
                }
                if(StringUtils.isNotBlank(company)){
                    Predicate p2 = cb.equal(root.get(type).as(String.class),type);
                    list.add(p2);
                }
//                多表查询  相对应表中存放引用类型数据时，需要判断2次是否为空
//                第一次判断对象是否为空，在判断需要的属性是否为空
                if(standard!=null && StringUtils.isNotBlank(standard.getName())){
//                    先关联查询   将引用类型数据封装到join类中（这是一个map）
                    Join<Object, Object> join = root.join("standard");
//                    正常封装条件，获取属性  是从join这个map中获取。
                    cb.like(join.get("name").as(String.class),standard.getName());
                }

//                如果查询条件没有填写，直接返回空
                if(list.size()==0){
                    return null;
                }

//                最后要把list转换成Predicate对象
//                先把list转成数组，然后调用cb对象的and方法 传入数组即可获得predicate对象
                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                Predicate pre = cb.and(predicates);

                return pre;
            }
        };
    }




}















