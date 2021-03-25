package hutool.clone;

import cn.hutool.core.util.ObjectUtil;
import lombok.Data;

import java.io.Serializable;

/**
 * @author wusong
 * @create 2021-03-18 09:34
 **/
@Data
public class Pig implements Serializable {
    private static final long serialVersionUID = -35227353761236963L;
    private String name;
    private Integer age;

    public static void main(String[] args) {
        Pig pig = new Pig();
        Pig pig1 = ObjectUtil.cloneIfPossible(pig);
        System.out.println(pig.equals(pig1));
    }
}
