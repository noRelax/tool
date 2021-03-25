package utils;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * 订阅数、稿件数转换类
 *
 * @author zhangshugang
 */
public class NumberConvertUtils {
    private static final int TEN_THOUSANDS = 10_000;// 一万
    private static final int HUNDRED_THOUSANDS = 100_000;//十万
    private static final int HUNDRED_MILLIONS = 100_000_000;// 一亿

    // decimalformatter不是线程安全的，因此使用threadlocal变量
    private static ThreadLocal<DecimalFormat> FORMATTER = ThreadLocal.withInitial(() -> new DecimalFormat("#.0"));

    /**
     * 把精确的数字转换成大概的数字，用于App端显示
     *
     * @param number
     * @return
     */
    public static String convert(Integer number) {
        if (Objects.isNull(number) || number <= 0) {
            return "";
        }

        if (number < TEN_THOUSANDS) {
            return String.valueOf(number);
        }

        Integer unit = number < HUNDRED_MILLIONS ? TEN_THOUSANDS : HUNDRED_MILLIONS;
        String unitSymbol = number < HUNDRED_MILLIONS ? "万" : "亿";

        int beforePoint = number / unit;
        int afterPoint = (number % unit) / (unit / 10);

        String plusSymbol = "";// 是否显示加号
        if (number - (beforePoint * unit + afterPoint * (unit / 10)) > 0) {
            plusSymbol = "+";
        }

        if (Objects.equals(0, afterPoint)) {
            return beforePoint + unitSymbol + plusSymbol;
        } else {
            return beforePoint + "." + afterPoint + unitSymbol + plusSymbol;
        }
    }
    
	/**
	 * 直播稿件观看人数放大公式
	 * 观看人数为1~99999，直接显示
	 * 观看人数为100000~99999999，显示“10万~9999.9万”，精确到小数点后一位，例：1207000显示“120.7万”，1207001显示“120.7万”
	 * 观看人数大于99999999，显示为1.x亿，规则同上
	 * @param number
	 * @return
	 */
	public static String convert4LiveViewNum(Integer number) {
		if (Objects.isNull(number) || number <= 0) {
			return "";
		}

		if (number < HUNDRED_THOUSANDS) {
			return String.valueOf(number);
		}

		Integer unit = number < HUNDRED_MILLIONS ? TEN_THOUSANDS : HUNDRED_MILLIONS;
		String unitSymbol = number < HUNDRED_MILLIONS ? "万" : "亿";
		int beforePoint = number / unit;
		int afterPoint = (number % unit) / (unit / 10);
		
		if (Objects.equals(0, afterPoint)) {
			return beforePoint + unitSymbol;
		} else {
			return beforePoint + "." + afterPoint + unitSymbol;
		}
	
	}
	
	/**
	 * 活动报名人数转换方法
	 * 1-9999人直接显示，大于9999显示数值+单位（万）
	 * 精确到小数点后一位
	 * @param number 实际数字
	 * @return
	 */
	public static String convert4ActivityRegisterCount(Integer number) {
		if (Objects.isNull(number) || number <= 0) {
			return "";
		}
		if (number < TEN_THOUSANDS) {
			return String.valueOf(number);
		}

		Integer unit = number < HUNDRED_MILLIONS ? TEN_THOUSANDS : HUNDRED_MILLIONS;
		String unitSymbol = number < HUNDRED_MILLIONS ? "万" : "亿";
		int beforePoint = number / unit;
		int afterPoint = (number % unit) / (unit / 10);
		
		if (Objects.equals(0, afterPoint)) {
			return beforePoint + unitSymbol;
		} else {
			return beforePoint + "." + afterPoint + unitSymbol;
		}
	}

	/**
	 * 处理点赞数 点赞数为0则不显示数字
	 * • 点赞数为1~9999，直接显示具体数字
	 * • 点赞数为10000~99999999显示为：1万~9999.9万，例：109000将显示为10.9万，109001将显示为10.9万
	 * • 点赞数大于99999999显示为1.x亿
	 * @param number 需要转化的数字
	 * @return 转化字符串
	 */
	public static String convertLikeCount(Integer number) {
		if (Objects.isNull(number) || number <= 0) {
			return "";
		}

		if (number < TEN_THOUSANDS) {
			return String.valueOf(number);
		}

		Integer unit = number < HUNDRED_MILLIONS ? TEN_THOUSANDS : HUNDRED_MILLIONS;
		String unitSymbol = number < HUNDRED_MILLIONS ? "万" : "亿";

		int beforePoint = number / unit;
		int afterPoint = (number % unit) / (unit / 10);

		if (Objects.equals(0, afterPoint)) {
			return beforePoint + unitSymbol;
		} else {
			return beforePoint + "." + afterPoint + unitSymbol;
		}
	}

    public static String convertDate(LocalDateTime datetime) {
        long timestamp = TimeUtils.getTimestamp(datetime);
        long current = System.currentTimeMillis();
        int delta = (int) (current - timestamp) / 1000;
        if (delta <= 5) {
            return "刚刚";
        } else if (delta <= 60) {
            return delta + "秒前";
        } else if (delta <= 60 * 60) {
            return delta / 60 + "分钟前";
        } else if (delta <= 24 * 60 * 60) {
            return delta / (60 * 60) + "小时前";
        } else if (delta <= 3 * 24 * 60 * 60) {
            return delta / (24 * 60 * 60) + "天前";
        } else {
            return TimeUtils.format(datetime, TimeUtils.DATE_FORMATTER);
        }
    }
    
	/**
	 * 打榜人气值转换 人气值的数字显示规则：
	 * 0-9999显示具体数字
	 * 大于99999999显示为1.x亿
	 * 其余按照10.万+显示
	 * 
	 * @param number 原生值
	 * @return
	 */
	public static String convert4RankHitCount(Integer number) {
		// 特殊处理，小于10000不返回给客户端
        if (Objects.isNull(number) || number < TEN_THOUSANDS) {
            return "";
        }

        Integer unit = number < HUNDRED_MILLIONS ? TEN_THOUSANDS : HUNDRED_MILLIONS;
        String unitSymbol = number < HUNDRED_MILLIONS ? "万" : "亿";

        int beforePoint = number / unit;
        int afterPoint = (number % unit) / (unit / 10);

        String plusSymbol = "";// 是否显示加号
        if (number - (beforePoint * unit + afterPoint * (unit / 10)) > 0) {
            plusSymbol = "+";
        }

        if (Objects.equals(0, afterPoint)) {
            return beforePoint + unitSymbol + plusSymbol;
        } else {
            return beforePoint + "." + afterPoint + unitSymbol + plusSymbol;
        }
	}

}
