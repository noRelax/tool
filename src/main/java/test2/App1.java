package test2;

import com.google.common.collect.Lists;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class App1 {
	public static void main(String[] args) throws IOException {

		List<Integer> notIns = Arrays.asList(111852,113474,113475,113487,113541,113570,113609,113630,113651,113673,115885,115919,115937);

		List<String> lines = 	Files.readAllLines(Paths.get("C:\\Users\\gang\\Desktop\\jars\\a.txt"));
		for(String line : lines){
			String[] array = line.split(",");
			String id = array[0];
			String title = array[1];
			if(!notIns.contains(Integer.parseInt(id))){
				// String sql = "UPDATE ugc_article SET list_title = '%s' WHERE id = %s;";
				// sql = String.format(sql,title,id);
				System.out.println(id);
			}
		}
	}
}
