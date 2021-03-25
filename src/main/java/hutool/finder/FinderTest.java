package main.java.hutool.finder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.util.List;

/**
 * 爬虫测试
 *
 * @author wusong
 * @create 2021-03-25 14:35
 **/
public class FinderTest {
    /**
     * 爬虫测试
     */
    @Test
    public void finderTest() {
        String listContent = HttpUtil.get("https://zj.zjol.com.cn/news.html?id=1638755");
        List<String> list = ReUtil.findAll("<p style=\"text-align: left;\">(.*?)</p>", listContent, 1);
        CollUtil.forEach(list, new CollUtil.Consumer<String>() {
            @Override
            public void accept(String value, int index) {
                Console.log("{}", value);
            }
        });

    }
}
