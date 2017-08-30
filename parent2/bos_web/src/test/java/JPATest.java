import com.itheima.bos.dao.StandardDao;
import com.itheima.bos.domain.Standard;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class JPATest {

    @Autowired
    private StandardDao dao;


    @Test
    public void testAdd(){
        Standard standard = new Standard();
//		standard.setId(id);
        standard.setMaxLength(100);
        standard.setMaxWeight(100);
        standard.setMinLength(11);
        standard.setMinWeight(11);
        standard.setName("11-100");
        standard.setOperatingCompany("顺义分公司");
        standard.setOperatingTime(new Date());
        standard.setOperator("admin");
        dao.save(standard);
    }

    @Test
    public void findByName(){
        List<Standard> list = (List<Standard>) dao.findAll();
        for (Standard s : list) {
            System.out.println(s);
        }
    }

    @Test
    public void ss(){
        List<Standard> ss = dao.fingByss("11-100");
        for (Standard s : ss) {
            System.out.println(s);
        }
    }



}
