package zjol;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import utils.TimeUtils;

public class TopicClassInit {
	private static ObjectMapper mapper = new ObjectMapper();

	public static void main(String[] args) throws Exception {
		Long sortNum = System.currentTimeMillis();
		String userName = "59a94dfcddf6b041182a9f56";
		String date = "2020-03-11 00:00:00";

		StringBuilder sql = new StringBuilder(
				"INSERT INTO `topic_class`(`id`, `name`, `sort_number`, `created_at`, `created_by`, `updated_at`, `updated_by`) VALUES ");

		String json = new String(Files.readAllBytes(Paths.get("C:\\Users\\gang\\Desktop\\jars\\1.json")),"utf-8");
		JsonNode rootNode = mapper.readTree(json);
		JsonNode datas = rootNode.get("data");
		for (int i = 0; i < datas.size(); i++) {
			JsonNode data = datas.get(i);
			sql.append(String.format("(%s, '%s', %s, '%s', '%s', '%s', '%s')", data.get("tag_id").asText(),
					data.get("tag_name").asText(), sortNum, date, userName, date, userName));
			sortNum += 10;
			if (i != datas.size() - 1) {
				sql.append(",");
			}
		}

		System.out.println(sql);
	}
}
