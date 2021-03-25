package zjrd;

import java.io.File;
import java.time.LocalDateTime;

import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;

/**
 * 上传文件到人大oss
 *
 * @author ZhangShuGang
 */
public class UploadFile {

    private static OSS ossClient;

    private static String ossBucket;

    public static void main(String[] args) {

        String endpoint = "http://oss-cn-hangzhou-zwynet-d01-a.internet.cloud.zj.gov.cn";
        String bucket = "xwkhd";
        String key = "ZoOYuhzHdtUMWO5H";
        String secret = "9omNQKoqU7v01IUAjQAPsrKuSa56vH";

        ossClient = new OSSClientBuilder().build(endpoint, key, secret);
        ossBucket = bucket;

        File file = new File("C:\\Users\\gang\\Desktop\\jars\\zjrd.apk");
        String fileName = "assets/downloads/zjrd.apk";
//		String fileName = getFileKey("png");
        ossClient.putObject(ossBucket, fileName, file);
        System.out.println(getAccessUrl(fileName));
    }

    private static String getFileKey(String suffix) {
        LocalDateTime now = LocalDateTime.now();
        long timestamp = zjrd.TimeUtils.getTimestamp(now);
        String objectId = zjrd.UidUtils.generateObjectId();
        return String.format("assets/%s%02d%02d/%s_%s.%s", now.getYear(), now.getMonthValue(), now.getDayOfMonth(),
                timestamp, objectId, suffix);
    }

    private static String getAccessUrl(String key) {
        return "http://oss-cn-hangzhou-zwynet-d01-a.internet.cloud.zj.gov.cn" + "/" + ossBucket + "/" + key;
    }
}
