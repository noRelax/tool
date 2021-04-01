package main.java.hutool.crypto;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SmUtil;
import cn.hutool.crypto.asymmetric.KeyType;
import cn.hutool.crypto.asymmetric.SM2;
import cn.hutool.crypto.symmetric.SymmetricCrypto;
import org.junit.Test;

/**
 * 加密算法
 *
 * @author wusong
 **/
public class CryptoTest {
    /**
     * 非对称加密
     *
     * @param args
     */
    public static void main(String[] args) {
        String text = "我是一段测试aaaa";
        SM2 sm2 = SmUtil.sm2();
        // 公钥加密，私钥解密
        String encryptStr = sm2.encryptBcd(text, KeyType.PublicKey);
        String decryptStr = StrUtil.utf8Str(sm2.decryptFromBcd(encryptStr, KeyType.PrivateKey));
        Console.log("加密字符串:{}，\n解密字符串:{}", encryptStr, decryptStr);
    }

    /**
     * 对称加密
     */
    @Test
    public void test2() {
        String content = "我是一段测试aaaa";
        SymmetricCrypto sm4 = SmUtil.sm4();
        String encryptHex = sm4.encryptHex(content);
        String decryptStr = sm4.decryptStr(encryptHex, CharsetUtil.CHARSET_UTF_8);
        Console.log("加密字符串:{}，\n解密字符串:{}", encryptHex, decryptStr);
    }

}
