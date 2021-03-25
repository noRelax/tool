package es;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

/**
 * @author gang
 * @Description:
 * @date 2020/5/13 18:12
 */
public class RetTest {
    private static ObjectMapper mapper = new ObjectMapper();

    public static void main(String[] args) throws IOException {
//        List<Token> analyse = getAn();
        List<Token> inEs = getIn("content.child");

        System.out.println("es存储:");
        inEs.forEach(x->{
            System.out.println(x);
        });

//        System.out.println("分词测试:");
//        analyse.forEach(x->{
//            System.out.println(x);
//        });

//        for(int i=0;i<analyse.size();i++){
//            Token in = inEs.get(i);
//            Token an = analyse.get(i);
//            if(!Objects.equals(in,an)){
//                System.out.println("ines"+in);
//                System.out.println("analyse"+an);
//                System.out.println("--------");
//            }
//        }


    }

    static List<Token> getIn(String fieldName) throws IOException {
        List<Token> ret = new ArrayList<>();
        String s = new String(Files.readAllBytes(Paths.get("C:\\Users\\gang\\Desktop\\es\\ines.txt")));

        JsonNode root = mapper.readTree(s);
        JsonNode terms = root.get("term_vectors").get(fieldName).get("terms");
        Iterator<String> it =  terms.fieldNames();
        while(it.hasNext()){
            String key = it.next();
            JsonNode tokens = terms.get(key).get("tokens");
            for(int i=0;i<tokens.size();i++){
                Token token = new Token();
                token.setPos(tokens.get(i).get("position").asInt());
                token.setContent(key);
                ret.add(token);
            }

        }

        Collections.sort(ret,(x,y)->{
            return x.getPos().compareTo(y.getPos());
        });
        ret.forEach(x->{
            // System.out.println(x);
        });
        return ret;
    }

    static List<Token> getAn()  throws IOException{
        List<Token> ret = new ArrayList<>();
        String s = new String(Files.readAllBytes(Paths.get("C:\\Users\\gang\\Desktop\\jars\\an.txt")));
        JsonNode root = mapper.readTree(s);
        JsonNode tokens = root.get("tokens");
        for (int i = 0; i < tokens.size(); i++) {
            JsonNode token = tokens.get(i);
            Token retToken = new Token();
            retToken.setContent(token.get("token").asText());
            retToken.setPos(token.get("position").asInt());
            ret.add(retToken);

        }

        Collections.sort(ret, (x, y) -> {
            return x.getPos().compareTo(y.getPos());
        });
        ret.forEach(x -> {
            // System.out.println(x);
        });

        return ret;
    }
}
