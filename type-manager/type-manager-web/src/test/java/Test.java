import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.type.pojo.Record;

import java.util.Random;

/**
 * @Author : dx
 * @Date : 2017/4/7
 * Description :
 */
public class Test {
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
}
