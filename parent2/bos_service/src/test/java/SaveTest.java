import com.itheima.bos.dao.FixedAreaDao;
import com.itheima.bos.domain.FixedArea;
import com.itheima.bos.service.FixedAreaService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:applicationContext.xml")
public class SaveTest {

    @Autowired
    FixedAreaDao fixedAreaDao;

    @Test
    @Transactional
    public void t(){
        FixedArea o = fixedAreaDao.findOne("DQ004");
        System.out.println(o.getSubareas().size());
    }



}
