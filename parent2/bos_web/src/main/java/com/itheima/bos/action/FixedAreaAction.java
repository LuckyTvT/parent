package com.itheima.bos.action;

import com.itheima.bos.domain.SubArea;
import com.itheima.bos.service.server.CrmService;
import com.itheima.bos.service.server.Customer;
import com.itheima.bos.domain.FixedArea;
import com.itheima.bos.service.FixedAreaService;
import com.itheima.bos.service.SubAreaService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;


import java.util.List;


public class FixedAreaAction extends BaseAction<FixedArea> {

    @Autowired
    FixedAreaService fixedAreaService;
    @Autowired
    CrmService crmService;
    @Autowired
    SubAreaService subAreaService;

    @Action("findFixedAreaByPage")
    public void findFixAreaByPage() {
        Pageable pageable = new PageRequest(page - 1, rows);
        Page<FixedArea> byPage = fixedAreaService.findFixedAreaByPage(pageable);
        javaToJSON(byPage,"subareas","couriers");
    }


    public void setSubAreaIds(String subAreaIds) {
        this.subAreaIds = subAreaIds;
    }

    private String subAreaIds;

    @Action("fixedArea_save")
    public void fixedArea_save() {
        fixedAreaService.fixedArea_save(model,subAreaIds);

    }

    @Action("fixedAreaIdIsNull")
    public void fixedAreaIdIsNull(){
        List<Customer> list = crmService.findByFixedAreaIdIsNull();
        toJSON(list);
    }

    public void setFixedAreaId(String fixedAreaId) {
        this.fixedAreaId = fixedAreaId;
    }

    private String fixedAreaId;

    @Action("fixedAreaId")
    public void fixedAreaId(){
        List<Customer> list = crmService.findByFixedAreaId(fixedAreaId);
        toJSON(list);
    }

    public void setIds(String ids) {
        this.ids = ids;
    }
    private String ids;

    @Action("associate")
    public void associate(){
        crmService.associate(model.getId(),ids);
    }


    public void setSelects(String selects) {
        this.selects = selects;
    }

    private String selects;

    @Action("noassociate")
    public void noassociate() {
        crmService.noassociate(selects);
    }


    private int takeTimeId;
    private int courierId;

    public void setTakeTimeId(int takeTimeId) {
        this.takeTimeId = takeTimeId;
    }

    public void setCourierId(int courierId) {
        this.courierId = courierId;
    }

    @Action("associateCourier")
    public void associateCourier() {
        fixedAreaService.associateCourier(model.getId(),takeTimeId,courierId);
    }

}
