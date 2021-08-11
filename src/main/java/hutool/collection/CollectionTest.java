package main.java.hutool.collection;

import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.json.JSONUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

/**
 * @author wusong
 * @create 2021-07-20 14:51
 **/
public class CollectionTest {
    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    static class ArticleTop {
        private Long articleId;
        private String listTitle;
    }

    public static void main(String[] args) {
        List<ArticleTop> articleTops = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            ArticleTop articleTop = new ArticleTop(Long.valueOf(i), "标题i:" + i);
            articleTops.add(articleTop);
        }
        ArticleTop articleTop = CollectionUtil.findOneByField(articleTops, "articleId", 300L);
        Console.log(JSONUtil.toJsonStr(articleTop));
    }

}
