package zjol;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class ChangeFileName {
	public static void main(String[] args) {
		String targetDir = "C:\\Users\\ZhangShuGang\\Desktop\\targets";

		try (Stream<Path> walk = Files.walk(Paths.get("C:\\Users\\ZhangShuGang\\Desktop\\917"))) {
//			List<String> result = walk.filter(Files::isRegularFile).map(x -> x.toString()).collect(Collectors.toList());
//			result.forEach(System.out::println);

			List<Path> paths = walk.filter(Files::isRegularFile).map(x -> x).collect(Collectors.toList());

			for (Path path : paths) {
				System.out.println(path.toFile().getName());
				String originalName = path.toFile().getName();

				String targetName = originalName.substring(0, 8);
				Path target = Paths.get(targetDir + File.separator + targetName + ".apk");
				Files.copy(path, target);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
