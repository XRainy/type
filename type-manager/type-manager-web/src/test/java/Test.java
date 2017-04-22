import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.type.controller.HomePageController;
import com.type.pojo.Record;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.util.Date;
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
}
