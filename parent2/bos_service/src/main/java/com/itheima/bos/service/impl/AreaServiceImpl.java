package com.itheima.bos.service.impl;

import com.itheima.bos.dao.AreaDao;
import com.itheima.bos.domain.Area;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.service.server.CrmService;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.itheima.bos.utils.PinYin4jUtils;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class AreaServiceImpl implements AreaService {

    @Autowired
    AreaDao areaDao;


    @Override
    public void save(File areaFile) {
        try {
//            需要导入OcUpload包
            FileInputStream in = new FileInputStream(areaFile);
            HSSFWorkbook book = new HSSFWorkbook(in);
            HSSFSheet sheet = book.getSheetAt(0);
            ArrayList<Area> list = new ArrayList<Area>();
            Area area = null;
//            因为这里sheet.getLastRowNum 获取到的是最后一条数据的索引，
//            而最后一条数据也需要存储，所以则for循环中i 要小于等于。
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                HSSFRow row = sheet.getRow(i);
                String id = row.getCell(0).getStringCellValue();
                String province = row.getCell(1).getStringCellValue();
                String city = row.getCell(2).getStringCellValue();
                String district = row.getCell(3).getStringCellValue();
                String postcode = row.getCell(4).getStringCellValue();
                area = new Area();
                area.setId(id);
                area.setProvince(province);
                area.setCity(city);
                area.setDistrict(district);
                area.setPostcode(postcode);
                area.setCitycode(PinYin4jUtils.hanziToPinyin(city,""));
                String[] headByString = PinYin4jUtils.getHeadByString(province + city + district);
                String head = StringUtils.join(headByString);
                area.setShortcode(head);
                list.add(area);
            }
//            因为如果每次存入一个对象，则xls中有多少条数据就开启 关闭多少次事务，
//            所以将全部对象装入一个list，只需要开启一次事务即可。
            areaDao.save(list);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public Page<Area> findAreaByPage(Pageable pageable) {
        Page<Area> page = areaDao.findAll(pageable);
        return page;

    }

    @Override
    public List<Area> findArea() {
        List<Area> list = (List<Area>) areaDao.findAll();
        return list;
    }

    @Override
    public List<Area> findByQ(String q) {
        List<Area> list = areaDao.findByCitycodeLikeOrShortcodeLike("%"+q.toLowerCase()+"%","%"+q.toUpperCase()+"%");
        return list;
    }

    @Test
    public void te(){
        try {
            FileInputStream in = new FileInputStream(new File("d:\\区域导入测试数据.xls"));
            HSSFWorkbook book = new HSSFWorkbook(in);
            HSSFSheet sheet = book.getSheetAt(0);
            HSSFRow row = sheet.getRow(0);
            HSSFCell cell = row.getCell(0);
            System.out.println(cell);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
