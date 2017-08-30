package com.itheima.bos.service.impl;

import com.itheima.bos.dao.*;
import com.itheima.bos.domain.*;
import com.itheima.bos.service.OrderService;
import com.itheima.bos.service.server.CrmService;
import com.itheima.bos.service.server.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Autowired
    private CrmService crmService;
    @Autowired
    private FixedAreaDao fixedAreaDao;
    @Autowired
    private SubAreaDao subAreaDao;
    @Autowired
    private AreaDao areaDao;
    @Autowired
    private WorkBillDao workBillDao;

    @Override
    public void save(Order order) {
//        保存订单之前，需要为订单分配快递小哥
//        自动分配快递小哥需要通过一些算法
//        算法：通过获得的发货地址来查询Customer表是否有完全匹配的，
//        如果有，就获得该对象的定区ID，然后通过定区ID来查询负责该定区的快递员
//        如果没有，就需要通过Area表进行关键字/辅助关键字查询。来获得该定区ID
//        然后再关联快递员。
        String sendAddress = order.getSendAddress();
        Customer customer = crmService.findByAddress(sendAddress);
        orderDao.save(order);
//        如果能查询到对应的客户信息 就通过该客户的定区id来查询快递员。
        if(customer!=null){
            String fixedAreaId = customer.getFixedAreaId();
            FixedArea fixedArea = fixedAreaDao.findOne(fixedAreaId);
            Set<Courier> couriers = fixedArea.getCouriers();
            for (Courier courier : couriers) {
//                通过一些条件筛选，如接收能力，上班时间，距离等获得最合适的快递员创建工单
                WorkBill workBill = new WorkBill();
                workBill.setType("新单");
                workBill.setBuildtime(new Date());
                workBill.setAttachbilltimes(0);
                workBill.setCourier(courier);
                workBill.setOrder(order);
                workBill.setPickstate("待取件");
                workBillDao.save(workBill);
//                给快递员发送短信提示到XX地点取件
//                SmsUtils.sendSmsByWebService(courier.getTelephone(),"到XX取件");
                break;
            }
        }else{
            System.out.println(5);
//            如果从Customer中查询不到对应的地区
//            就要从Area表中使用关键字匹配
            List<SubArea> list = (List<SubArea>) subAreaDao.findAll();
            for (SubArea subArea : list) {
                if(sendAddress.contains(subArea.getAssistKeyWords())||sendAddress.contains(subArea.getKeyWords())){
                    System.out.println(subArea.getId());
                    FixedArea fixedArea = subArea.getFixedArea();
                    Set<Courier> couriers = fixedArea.getCouriers();
                    for (Courier courier : couriers) {
                        WorkBill workBill = new WorkBill();
                        workBill.setType("new");
                        workBill.setBuildtime(new Date());
//                        追单次数
                        workBill.setAttachbilltimes(0);
                        workBill.setCourier(courier);
                        workBill.setOrder(order);
                        workBill.setPickstate("待取件");
                        workBillDao.save(workBill);
//                        给快递员发送短信提示到XX地点取件
//                        SmsUtils.sendSmsByWebService(courier.getTelephone(),"到XX取件");
                        break;
                    }
                    break;
                }
            }
        }
    }

    @Override
    public void findByCityAndProvinceAndDistrict(Area area, Order order) {
        Area area1 = areaDao.findByCityAndProvinceAndDistrict(area.getCity(), area.getProvince(),area.getDistrict());
        order.setSendArea(area1);
        orderDao.save(order);
        save(order);
    }
}
