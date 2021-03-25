package utils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileUtils {
	public static String readAll(String fileName) {
		try {
			Path path = Paths.get(fileName);
			byte[] bytes = Files.readAllBytes(path);
			String s = new String(bytes, "UTF-8");
			return s;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;

	}
}
