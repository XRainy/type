import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.type.controller.HomePageController;
import com.type.mapper.user.UserDao;
import com.type.pojo.Record;
import com.type.pojo.TypeUser;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * @Author : dx
 * @Date : 2017/4/7
 * Description :
 */
public class Test {
    private static final Logger logger = LoggerFactory.getLogger(HomePageController.class);

    Random random = new Random();
    @org.junit.Test
    public void testjson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new Record());
        System.out.print(json);
    }
    @org.junit.Test
    public void testASCII(){
        for (int i=0;i<100;i++){
            char ch = (char) (random.nextInt(26)+65);
            System.out.print(""+ch);
        }
    }
    @org.junit.Test
    public void testDate(){
        DateTime dateTime = new DateTime(new Date().getTime());
        System.out.print(dateTime);
    }
    @org.junit.Test
    public void testPath(){
        String s=this.getClass().getResource("/").getPath();
        File file= new File("");
        System.out.println(s);
    }
    @org.junit.Test
    public void testPageHelper(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:spring/applicationContext-*.xml");
        UserDao userDao= applicationContext.getBean(UserDao.class);
        PageHelper.startPage(1,2);
        List<TypeUser> list = userDao.selectAll();
        for(TypeUser t:list){
            System.out.println(t);
        }
        PageInfo<TypeUser> pagInfo = new PageInfo<TypeUser>(list);
        System.out.print(pagInfo.getTotal());

    }
}
