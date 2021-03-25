package test2;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

public class Encoding {
	public static void main(String[] args) {
		String s = "a你好";
		System.out.println(Arrays.toString(s.getBytes()));

	}

	public static String getEncoding(String str) {

		String encoding = "UTF-8";

		try {

			if (str.equals(new String(str.getBytes(), encoding))) {

				return encoding;
			}

		} catch (UnsupportedEncodingException e) {

			// TODO Auto-generated catch block e.printStackTrace();

		}

		encoding = "GBK";

		try {

			if (str.equals(new String(str.getBytes(), encoding))) {

				return encoding;
			}
		} catch (UnsupportedEncodingException e) {

			// TODO Auto-generated catch block e.printStackTrace();

		}

		encoding = "ISO-8859-1";

		try {

			if (str.equals(new String(str.getBytes(), encoding))) {

				return encoding;

			}

		} catch (UnsupportedEncodingException e) {

			// TODO Auto-generated catch block e.printStackTrace();

		}

		encoding = "GB2312";

		try {

			if (str.equals(new String(str.getBytes(), encoding))) {

				return encoding;

			}

		} catch (UnsupportedEncodingException e) {

			// TODO Auto-generated catch block e.printStackTrace();

		}

		return null;

	}

}