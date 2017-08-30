package com.itheima.bos.action;

import com.itheima.bos.domain.WayBill;
import com.itheima.bos.service.WayBillService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;

public class WayBillAction extends BaseAction<WayBill> {

    @Autowired
    private WayBillService wayBillService;


    @Action("saveWayBill")
    public void saveWayBill(){
        wayBillService.save(model);
        HashMap<String, Object> map = new HashMap<>();
        map.put("success",true);
        toJSON(map);
    }

    @Action("wayBillFindAll")
    public void wayBillFindAll(){
        List<WayBill> list = wayBillService.wayBillFindAll();
        toJSON(list);
    }



}
