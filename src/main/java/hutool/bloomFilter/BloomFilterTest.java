package main.java.hutool.bloomFilter;

import cn.hutool.bloomfilter.BitMapBloomFilter;
import cn.hutool.bloomfilter.BloomFilterUtil;
import cn.hutool.core.math.MathUtil;
import org.junit.Test;

/**
 * @author wusong
 * @create 2021-04-01 16:34
 **/
public class BloomFilterTest {
    @Test
    public void test1() {
        BitMapBloomFilter bitMap = BloomFilterUtil.createBitMap((int) Math.scalb(new Float("10"), 100));
        for (int i = 0; i < 1000; i++) {
            bitMap.add(i + "");
        }
        System.out.println(bitMap.contains("50"));
    }
}
