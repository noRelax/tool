package main.java.hutool.file;

import cn.hutool.core.io.resource.ClassPathResource;
import cn.hutool.core.lang.Console;
import org.junit.Test;

import java.io.IOException;
import java.util.Properties;

/**
 * @author wusong
 * @create 2021-05-12 15:07
 **/
public class ResourceUtilTest {
    @Test
    public void classResourceTest() throws IOException {
        ClassPathResource resource = new ClassPathResource("test.properties");
        Properties properties = new Properties();
        properties.load(resource.getStream());
        Console.log("Properties: {}", properties);
    }
}
