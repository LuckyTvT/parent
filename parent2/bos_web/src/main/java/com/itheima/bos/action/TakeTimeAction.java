package com.itheima.bos.action;

import com.itheima.bos.domain.TakeTime;
import com.itheima.bos.service.TakeTimeService;
import org.apache.struts2.convention.annotation.Action;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;


public class TakeTimeAction extends BaseAction<TakeTime> {

    @Autowired
    private TakeTimeService takeTimeService;

    public void setQ(String q) {
        this.q = q;
    }

    private String q;


    @Action("findTakeTimeAll")
    public void findTakeTimeAll() {
        System.out.println("过来的参数q："+q);
        if(q==null){
            List<TakeTime> list = takeTimeService.findTakeTimeAll();
            toJSON(list);
        }else{
            List<TakeTime> list = takeTimeService.findByQ(q);
            toJSON(list);
        }
    }



}
