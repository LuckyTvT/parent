package com.itheima.bos.action;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.PropertyFilter;
import com.itheima.bos.domain.Courier;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import org.apache.poi.ss.formula.functions.T;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

import static com.alibaba.fastjson.serializer.SerializerFeature.DisableCircularReferenceDetect;

@Controller
@Namespace("/")
@ParentPackage("struts-default")
@Scope("prototype")
public class BaseAction<T> extends ActionSupport implements ModelDriven<T> {

    protected T model;

//    为了创建model对象，需要一个无参构造
    public BaseAction() {
//        this.getClass()这里的this 谁调用该构造方法 这个this就是谁
//        获取父类的参数化类型
        Type type = this.getClass().getGenericSuperclass();
//        使用Type的子类  ParameterizedType 所以需要强转
        ParameterizedType ptype = (ParameterizedType) type;
//        获取真实参数，就是BaseAction<T> 中的泛型，该方法返回的是一个数组，因为可以传入多个泛型
        Type[] actType = ptype.getActualTypeArguments();
        Class<T> clazz = (Class<T>)actType[0];
        try {
//            使用字节码文件创建对象，因为使用模型驱动需要将类对象创建出来。这样就可以使用模型驱动了。
            model = clazz.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    @Override
    public T getModel() {
        return model;
    }

    protected int page;
    protected int rows;
    public void setPage(int page) {
        this.page = page;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }


//    抽取分页代码，主要抽取转换成JSON部分的代码，传入一个map和一个需要排除的字段组成的数组
    public void javaToJSON(Page page,final String... strings) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("total",page.getTotalElements());
        map.put("rows",page.getContent());
        toJSON(map,strings);
    }

//    抽取封装json的代码
    public void toJSON(Object objcect,final String... strings){
        String s = JSON.toJSONString(objcect, new PropertyFilter() {
            @Override
            public boolean apply(Object object, String name, Object value) {
                for (int i = 0; i < strings.length; i++) {
                    if (name.equalsIgnoreCase(strings[i])) {
                        return false;
                    }
                }
                return true;
            }
//            打开循环引用检测
        }, DisableCircularReferenceDetect);
        try {
            HttpServletResponse response = ServletActionContext.getResponse();
            response.setContentType("application/json;charset=utf-8");
            System.out.println(s);
            response.getWriter().write(s);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


//    抽取ajax返回信息
    public void ajaxReturn() {
//        封装map中success:true    message:"操作成功/失败"
//        然后将封装好的map返回。
    }







}
