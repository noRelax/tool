package main.java.hutool.qrCode;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.hutool.extra.qrcode.QrConfig;
import org.junit.Test;

import java.awt.*;
import java.io.File;

/**
 * 二维码测试
 *
 * @author wusong
 * @create 2021-03-25 14:20
 **/
public class QrCodeTest {
    @Test
    public void test1() {
        QrConfig config = new QrConfig(200, 200);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 设置前景色，既二维码颜色（青色）
        config.setForeColor(Color.DARK_GRAY.getRGB());
        // 设置背景色（灰色）
        config.setBackColor(Color.pink.getRGB());
        QrCodeUtil.generate("https://zj.zjol.com.cn", config, FileUtil.file("/Users/wusong/Downloads/picture/二维码.jpg"));
    }

}
