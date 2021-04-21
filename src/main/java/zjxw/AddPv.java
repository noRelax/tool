package main.java.zjxw;

import cn.hutool.core.lang.Console;
import cn.hutool.core.thread.ConcurrencyTester;
import cn.hutool.core.thread.ThreadUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * 增加稿件阅读数
 *
 * @author ZhangShuGang
 */
public class AddPv {
    private static final RestTemplate restTemplate = new RestTemplate();

    public static void main(String[] args) {
        String url = "https://zj.zjol.com.cn/video.html?id=1628995";
        int times = 10;

//		for (int i = 0; i < times; i++) {
//			String ret = restTemplate.getForObject(url, String.class);
//			System.out.println(i);
//		}
        MultiValueMap<String, Object> paramsMap = new LinkedMultiValueMap<>();
        paramsMap.add("device_id", "kkdgp6l2_181_wwww");
        paramsMap.add("client_session_id", "111111111111111111111111");

        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<Map<String, Object>> mapHttpEntity = new HttpEntity(paramsMap, httpHeaders);
        int threadNum = 10;
		/*for (int j = 0; j < 10; j++) {
			new Thread(() -> {
				for (int i = 0; i < times / threadNum; i++) {
					String ret = restTemplate.postForObject(url,mapHttpEntity, String.class);
					System.out.println(ret+">>>>>>>"+Thread.currentThread().getName() + "---" + i);
				}

			}).start();
		}*/
        ConcurrencyTester concurrencyTester = ThreadUtil.concurrencyTest(10, () -> {
            for (int i = 0; i < times / threadNum; i++) {
                String ret = restTemplate.postForObject(url, mapHttpEntity, String.class);
                System.out.println(ret + ">>>>>>>" + Thread.currentThread().getName() + "---" + i);
            }
        });
        Console.log("耗时：{}ms", concurrencyTester.getInterval());
    }

}
