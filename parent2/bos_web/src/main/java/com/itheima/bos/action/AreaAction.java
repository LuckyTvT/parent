package com.itheima.bos.action;

import com.itheima.bos.domain.Area;
import com.itheima.bos.service.AreaService;
import com.itheima.bos.service.server.CrmService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import java.io.File;
import java.util.List;


public class AreaAction extends BaseAction<Area> {

    private File areaFile;
    public void setAreaFile(File areaFile) {
        this.areaFile = areaFile;
    }
    public File getAreaFile() {
        return areaFile;
    }

    @Autowired
    AreaService areaService;


    @Action("importArea")
    public void importArea(){
        areaService.save(areaFile);
    }

    @Action("findAreaByPage")
    public void findAreaByPage(){
        Pageable pageable = new PageRequest(page-1,rows);
        Page<Area> areaPage = areaService.findAreaByPage(pageable);
        javaToJSON(areaPage, "subareas");
    }

    public void setQ(String q) {
        this.q = q;
    }
    private String q;

    @Action("findArea")
    public void findArea() {
        System.out.println("传过来的参数q:"+q);
        if(q==null) {
            List<Area> list = areaService.findArea();
            toJSON(list,"subareas");
        }else{
            List<Area> list = areaService.findByQ(q);
            toJSON(list,"subareas");
        }
    }


}



