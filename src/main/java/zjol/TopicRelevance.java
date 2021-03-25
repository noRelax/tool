package zjol;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.web.client.RestTemplate;

public class TopicRelevance {
	private static RestTemplate restTemplate = new RestTemplate();

	public static void main(String[] args) throws IOException, InterruptedException {
		List<String> articleIds = Files
				.readAllLines(Paths.get("C:\\Users\\gang\\Desktop\\jars\\ids.txt"));
		System.out.println(articleIds.size());

		for (String articleId : articleIds) {
			String url = "https://app-gw.zjol.com.cn/openapi/tools/topiclabel_relevance?token=MOSMD345msofOSM&id="
					+ articleId;
			System.out.println("请求地址:" + url);
			String ret = restTemplate.getForObject(url, String.class);
			System.out.println("返回数据:" + ret);
			
			Thread.sleep(100L);
		}

	}
}
