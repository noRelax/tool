package hutool.dfa;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.dfa.WordTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

/**
 * dfa查找
 *
 * @author wusong
 * @create 2021-03-18 10:14
 **/
public class DfaTest {
    WordTree tree = new WordTree();

    @Before
    public void init() {
        tree.addWord("杭州");
        tree.addWord("今天");
        tree.addWord("昨天");
        tree.addWord("前天");
        tree.addWord("天气");
    }

    /**
     * 情况一：标准匹配，匹配到最短关键词，并跳过已经匹配的关键词
     */
    @Test
    public void test1() {
        //正文
        String text = "天猫精灵，给我来一个昨天杭州的天气";
        // 匹配到【大】，就不再继续匹配了，因此【大土豆】不匹配
        // 匹配到【刚出锅】，就跳过这三个字了，因此【出锅】不匹配（由于刚首先被匹配，因此长的被匹配，最短匹配只针对第一个字相同选最短）
        List<String> matchAll = tree.matchAll(text, -1, false, false);
        System.out.println(CollUtil.join(matchAll, ","));
    }
}
