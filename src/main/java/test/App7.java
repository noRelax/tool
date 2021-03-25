package test;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class App7 {
	// ----###----###----
	public static void main(String[] args) {
		List<Item> items = new ArrayList();
		String msg = "你好#话题1#杭州#话题2#宁波";
		Pattern pattern = Pattern.compile("#[^#]+#");
		Matcher matcher = pattern.matcher(msg);
		int lastEnd = 0;
		int end = msg.length();
		while (matcher.find()) {
			int topicStart = matcher.start();
			int topicEnd = matcher.end();

			if (topicStart > lastEnd) {
				items.add(new Item(false, msg.substring(lastEnd, topicStart)));
			}
			items.add(new Item(true, msg.substring(topicStart, topicEnd)));
			lastEnd = topicEnd;
		}

		if (lastEnd < msg.length()) {
			items.add(new Item(false, msg.substring(lastEnd, msg.length())));
		}

		System.out.println(items);
	}

	public static class Item {
		@Override
		public String toString() {
			return "Item [content=" + content + ", flag=" + flag + "]";
		}

		public Item(boolean flag, String content) {
			this.content = content;
			this.flag = flag;
		}

		private String content;
		private boolean flag;

		public String getContent() {
			return content;
		}

		public void setContent(String content) {
			this.content = content;
		}

		public boolean isFlag() {
			return flag;
		}

		public void setFlag(boolean flag) {
			this.flag = flag;
		}

	}
}
