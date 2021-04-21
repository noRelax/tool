package main.java.hutool.tailer;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.Tailer;

/**
 * @author wusong
 * @create 2021-04-19 18:07
 **/
public class tailerTest {
    public static void main(String[] args) {
        Tailer tailer = new Tailer(FileUtil.file("/Users/wusong/zbcm-workspace/tool/src/main/java/hutool/doc.txt"), Tailer.CONSOLE_HANDLER, 2);
        tailer.start();
    }

}
