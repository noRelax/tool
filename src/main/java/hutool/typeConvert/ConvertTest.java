package hutool.typeConvert;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Console;
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

    @Test
    public void digitToChinese() {
        double a = 999967556.32;
        //结果为："陆万柒仟伍佰伍拾陆元叁角贰分"
        String digitUppercase = Convert.digitToChinese(a);
        Console.log("{}", digitUppercase);
    }
}
