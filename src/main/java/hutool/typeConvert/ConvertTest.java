package hutool.typeConvert;

import cn.hutool.core.convert.Convert;
import org.junit.Test;

/**
 * @author wusong
 * @create 2021-03-18 09:56
 **/
public class ConvertTest {
    @Test
    public void toStr() {
        int a = 1;
        //aStr为"1"
        String aStr = Convert.toStr(a);
        long[] b = {1, 2, 3, 4, 5};
        //bStr为："[1, 2, 3, 4, 5]"
        String bStr = Convert.toStr(b);
        System.out.println("aStr：" + aStr + "\nbStr:" + bStr);

    }
}
