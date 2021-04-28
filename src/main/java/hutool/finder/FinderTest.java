package main.java.hutool.finder;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.ReUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import org.junit.Test;

import java.nio.charset.Charset;
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

    @Test
    public void testContent() {
        String content = FileUtil.readString("/Users/wusong/zbcm-workspace/tool/src/main/java/hutool/doc.txt", Charset.defaultCharset());
        content = HtmlUtils.removeHtmlTag(content);
        String result = getContentForSearch(content, "红色根脉", 50);
        Console.log("处理前的内容：{},\n处理后的内容：{},\n长度：{}", content, result, result.length());
    }

    public String getContentForSearch(String content, String keyword, Integer num) {
        if (content.contains(keyword)) {
            if (keyword.length() > num) {
                return keyword.substring(0, num);
            }
            int index = content.indexOf(keyword);
            String preSubString = StrUtil.subBefore(content, keyword, false);
            int length = (num - keyword.length()) / 2;
            String lastSubString = StrUtil.subWithLength(content, index, length + keyword.length());
            preSubString = preSubString.substring(preSubString.length() > num - lastSubString.length() ? preSubString.length() - num + lastSubString.length() : 0);
            return StrUtil.removeAllLineBreaks(preSubString + lastSubString);
        }
        return null;
    }
}
