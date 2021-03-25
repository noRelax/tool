package es;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author gang
 * @Description:
 * @date 2020/5/25 10:12
 */
public class ProdOut {
    private static ObjectMapper mapper = new ObjectMapper();
    public static void main(String[] args) throws Exception {
        String raw = new String(Files.readAllBytes(Paths.get("/Users/wusong/Downloads/1.txt")));


        BufferedWriter writer = Files.newBufferedWriter(Paths.get("/Users/wusong/Downloads/out1.txt"));

        JsonNode root = mapper.readTree(raw);
        JsonNode list = root.get("hits").get("hits");
        for(int i=0;i<list.size();i++){
            JsonNode tmp = list.get(i).get("_source");
            String id = tmp.get("id").asText();
            writer.write(id );
            //String url = tmp.get("url").asText();
            // String listTitle = tmp.get("list_title").asText();
            // writer.write(id + " , " +url+","+listTitle);
            // writer.write(id + " , " +listTitle+","+url);
            //writer.write(id +","+url);
            writer.newLine();
        }

        writer.close();
        System.out.println("ok");
    }
}
