package test2;

import java.util.*;


class Solution {
    public static void main(String[] args) {
       String  ret = new Solution1().changeFormatNumber("12");
        System.out.println(ret);
    }
    /**
     * 将输入的十进制数字转换为对应的二进制字符串和十六进制字符串
     * @param number string字符串 十进制数字字符串
     * @return string字符串
     */
    static Map<Integer, List<Integer>> retMap = new HashMap<>();
    static Map<Integer, String> map = new HashMap<>();
    public String changeFormatNumber (String number) {
        // write code here
        int theInput = Integer.parseInt(number);//要输入的数字

        int max = 2;
        for (int i = 0; i < 16; i++) {
            max = max * 2;
        }
        theInput = theInput > 0 ? theInput : max + theInput;

        StringBuffer ret = new StringBuffer();
        map.put(10, "A");
        map.put(11, "B");
        map.put(12, "C");
        map.put(13, "D");
        map.put(14, "E");
        map.put(15, "F");

        retMap.put(2, new ArrayList<>());
        retMap.put(16, new ArrayList<>());
        trans(theInput, 2);
        trans16(theInput);
        for (int i = retMap.get(2).size() - 1; i < 16; i++) {
            retMap.get(2).add(0);
        }


        for (int i = retMap.get(16).size() - 1; i < 4; i++) {
            retMap.get(16).add(0);
        }
        retMap.put(16, retMap.get(16).subList(0, 4));

        for (int i = retMap.get(2).size() - 1; i >= 0; i--) {
            // System.out.println(retMap.get(2).get(i));
            ret.append(retMap.get(2).get(i));
        }
        ret.append(",");
        for (int i = retMap.get(16).size() - 1; i >= 0; i--) {
            int tmp = retMap.get(16).get(i);
            if (tmp > 10) {
                // System.out.println(map.get(tmp));
                ret.append(map.get(tmp));
            } else {
                ret.append(tmp);
                // System.out.println(tmp);
            }
        }
        System.out.println(ret);

        return ret.toString();
    }

    public static void trans(int num, int format) {
        if (num <= 0) {
            return;
        }

        int yu = num % format;
        retMap.get(format).add(yu);
        int shang = num / format;
        trans(shang, format);
    }

    public static void trans16(int num) {
        if (num <= 0) {
            return;
        }

        int yu = num % 16;
        retMap.get(16).add(yu);
        int shang = num / 16;
        trans(shang, 16);
    }
}