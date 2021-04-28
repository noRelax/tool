package main.java.hutool.json;

import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import org.junit.Test;

/**
 * @author wusong
 * @create 2021-04-21 14:13
 **/
public class JsonTest {
    @Test
    public void test1() {
        String s = JSONUtil.formatJsonStr("{ \"data\": 0 }");
        Console.log(s);
    }
}
