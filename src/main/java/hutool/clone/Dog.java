package hutool.clone;

import cn.hutool.core.clone.CloneSupport;

/**
 * 狗狗类，用于继承CloneSupport类
 *
 * @author Looly
 */
public class Dog extends CloneSupport<Dog> {
    private String name = "wangwang";
    private int age = 3;
}
