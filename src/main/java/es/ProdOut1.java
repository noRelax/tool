package es;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * @author gang
 * @Description:
 * @date 2020/6/2 9:07
 */
public class ProdOut1 {
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = Files.newBufferedWriter(Paths.get("C:\\Users\\gang\\Desktop\\qianren\\out1.txt"));

        List<String> lines = Files.readAllLines(Paths.get("C:\\Users\\gang\\Desktop\\qianren\\1.txt"));

        for(int i=0;i<lines.size();i++){
            String line = lines.get(i);
            // System.out.println(line);
            if(line.contains("_source")){
                String listTitle =lines.get(i+1).replace("\"list_title\" : ","").replace(",","");
                String id = lines.get(i+2).replace("\"id\" : ","").replace(",","");
                String url = lines.get(i+3).replace("\"url\" : ","").replace(",","");
                i = i + 4;
                // System.out.println(listTitle);
                writer.write(id + " , " +url+","+listTitle);
                writer.newLine();
            }

        }

        writer.close();
        System.out.println("ok");
    }
}
