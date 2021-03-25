package utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class StringUnicodeTest {

	/**
	 *斜线u后面跟的是16进制数字
	 * @param args
	 * @throws UnsupportedEncodingException
	 */
	public static void main(String[] args) throws UnsupportedEncodingException {
		String s = unicodeToString("\\uD83D\\uDE14");
		String str = URLEncoder.encode(s, "utf-8").replaceAll("%", "");
		System.out.println(str);
	}

	/**
	 * 字符串转unicode
	 * 
	 * @param str
	 * @return
	 */
	public static String stringToUnicode(String str) {
		StringBuffer sb = new StringBuffer();
		char[] c = str.toCharArray();
		for (int i = 0; i < c.length; i++) {
			sb.append("\\u" + Integer.toHexString(c[i]));
		}
		return sb.toString();
	}

	/**
	 * unicode转字符串
	 * 
	 * @param unicode
	 * @return
	 */
	public static String unicodeToString(String unicode) {
		StringBuffer sb = new StringBuffer();
		String[] hex = unicode.split("\\\\u");
		for (int i = 1; i < hex.length; i++) {
			int index = Integer.parseInt(hex[i], 16);
			sb.append((char) index);
		}
		return sb.toString();
	}
}
