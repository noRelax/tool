package main.java.hutool.id;

import cn.hutool.core.lang.Console;
import cn.hutool.core.lang.ObjectId;
import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;
import org.junit.Test;

/**
 * id生成
 *
 * @author wusong
 * @create 2021-05-12 16:01
 **/
public class IdGenerate {
    @Test
    public void idTest() {
        String randomUUID = IdUtil.randomUUID();
        Console.log("randomUUID:{}", randomUUID);
        String simpleUUID = IdUtil.simpleUUID();
        Console.log(simpleUUID);
        String objectId = IdUtil.objectId();
        Console.log(objectId);
        Snowflake snowflake = IdUtil.getSnowflake(1, 1);
        long id = snowflake.nextId();
        Console.log(id);

    }
}
