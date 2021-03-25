package zjol.virtual;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.List;

import zjol.virtual.VirtualAccountInfo.AccountInfo;


/**
 * 虚拟账号处理
 *  //String[][] as = ExcelOperateUtils.getData(new File(filePath), 0);
     //for (int i = 0; i < as.length-1; i++) {
     //    System.out.println("" + as[i][6]+"=="+as[i][7]);
     //}
 * @author sunpeng
 * 
 * 接口地址：https://app-gw.zjol.com.cn/openapi/account_sync/save
 */
public class VirtualAccountSync {
    
    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        try {
            VirtualAccountInfo virtualAccountInfo = readFromExcel("C:\\Users\\gang\\Desktop\\jars\\virtual\\test.xls");
            virtualAccountInfo.getTimestamp();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    /**
     * 读取Exel内容
     * @param filePath
     * @return
     * @throws Exception
     */
    public static VirtualAccountInfo readFromExcel(String filePath) throws Exception {
        VirtualAccountInfo virtualAccountInfo = new VirtualAccountInfo();
        File file = new File(filePath);
        if (file.exists()) {
            LinkedHashMap<String, String> fieldMap = new LinkedHashMap<String, String>();
            fieldMap.put("虚拟码", "phoneNumber");
            fieldMap.put("邀请码", "refCode");
            fieldMap.put("昵称", "nickName");
            List<AccountInfo> list = ExcelUtils.excelToList(new FileInputStream(file), 0, AccountInfo.class, fieldMap, new String[] { "虚拟码" });
            virtualAccountInfo.setAccountInfo(list);
            virtualAccountInfo.setTimestamp(123456L);
            virtualAccountInfo.setSignature("a50c254e572495e3dfb68faf03539f066cc8993b2d9885b3c0eab08cbfd70379");
            virtualAccountInfo.setExistUpdate(true);
            virtualAccountInfo.setImageUrl("https://app-stc.zjol.com.cn/assets/cs_portrait/20191031/1572506924497_5dba8d2cb297c7000114a35e.png");
            System.out.println(1);
            System.out.println(JsonUtils.toJson(virtualAccountInfo));
        }
        return virtualAccountInfo;
    }
}
