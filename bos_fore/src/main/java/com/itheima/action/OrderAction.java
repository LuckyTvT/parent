package com.itheima.action;

import com.itheima.bos.domain.Area;
import com.itheima.bos.domain.Order;


import com.itheima.fore_service.server.CrmService;
import com.itheima.fore_service.server.Customer;
import com.itheima.orderService.OrderService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;


public class OrderAction extends BaseAction<Order> {

    private String sendAreaInfo;
    public void setSendAreaInfo(String sendAreaInfo) {
        this.sendAreaInfo = sendAreaInfo;
    }



    @Autowired
    OrderService orderService;

    @Action("orderAction_add")
    public void orderAction_add() {

        String[] areas = sendAreaInfo.split("/");
        Area area = new Area();
        area.setProvince(areas[0]);
        area.setCity(areas[1]);
        area.setDistrict(areas[2]);
        orderService.findByCityAndProvinceAndDistrict(area, model);
        try {
            ServletActionContext.getResponse().sendRedirect("deal.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
