package main.java.hutool.excel;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.ListUtil;
import cn.hutool.core.io.file.FileReader;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.core.lang.Console;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.sax.handler.RowHandler;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.Set;

/**
 * @author wusong
 * @create 2021-03-18 11:34
 **/
public class ExcelOpt {
    public static final Set<String> videoIds = CollUtil.newHashSet();
    public static final Set<String> tmVideoIdsFromDb = CollUtil.newHashSet();


    @Before
    public void initTmVideoIds() {

        //天目数据库查询的视频id
        ExcelUtil.readBySax("/浙报传媒/天目新闻/修改视频域名/迁移的videoIds.xlsx", -1, new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowList) {
                if (rowIndex > 0) {
                    tmVideoIdsFromDb.add(StrUtil.toString(rowList.get(0)));
                }

            }
        });


        //运维迁移的视频id
        ExcelUtil.readBySax("/浙报传媒/天目新闻/修改视频域名/运维.xlsx", -1, new RowHandler() {
            @Override
            public void handle(int sheetIndex, long rowIndex, List<Object> rowList) {
                if (rowIndex > 0 && tmVideoIdsFromDb.contains(StrUtil.toString(rowList.get(2)))) {
                    videoIds.add(StrUtil.toString(rowList.get(2)));
                }
            }
        });
    }

    @Test
    public void bigDataExport() {
        FileWriter writer = new FileWriter("/浙报传媒/天目新闻/修改视频域名/article_video.sql");
        String sql = "update article_video set url=replace(url,'https://mc-public.zjol.com.cn','https://tmvf.zjol.com.cn') where video_id={};\n";
        Console.log("{}", CollUtil.join(tmVideoIdsFromDb, ","));
        CollUtil.forEach(tmVideoIdsFromDb, (value, index) -> writer.append(StrUtil.format(sql, value)));

    }

    @Test
    public void exportRedisRefreshCode() {
        List<String> articleIds = CollUtil.newArrayList();
        ExcelUtil.readBySax("/浙报传媒/天目新闻/修改视频域名/查询稿件id列表.xlsx", -1, (sheetIndex, rowIndex, rowList) -> {
            if (rowIndex > 0) {
                articleIds.add(StrUtil.toString(rowList.get(0)));
            }
        });
        FileWriter writer = new FileWriter("/浙报传媒/天目新闻/修改视频域名/刷新稿件缓存脚本");
        String refreshCode = "del kv_article_detail:{} LOCK_kv_article_detail:{}\n";
        CollUtil.forEach(articleIds, (value, index) -> writer.append(StrUtil.format(refreshCode, value, value)));
    }

    @Test
    public void listVideoIds() {
        Console.log("{}", CollUtil.join(videoIds, ","));
    }

}
