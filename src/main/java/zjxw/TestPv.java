package zjxw;

import utils.NumberConvertUtils;

public class TestPv {
	public static void main(String[] args) {
		int raw = 155 + 10 + 1048;// 当前真实阅读量
		System.out.println("目前放大后阅读量:" + NumberConvertUtils.convert(enlargePv(raw, 600)));

		int target = 3000000;// 目标数字
		getDiff(raw, target);
	}

	public static Integer enlargePv(Integer value, Integer rand) {
		int v;
		if (value < 10000) {
			v = Double.valueOf(Math.pow((value + rand) * 10, 1.1)).intValue();
		} else {
			v = Double.valueOf(316227 + (value + rand) * 1.2).intValue();
		}
		return v;
	}

	public static void getDiff(int currentRaw, int target) {
		for (int delta = 1;; delta++) {
			if (enlargePv(currentRaw + delta, 600) >= target) {
				System.out.println("放大到" + NumberConvertUtils.convert(target) + "要调用接口的次数:" + delta);
				break;
			}
		}

	}
}
