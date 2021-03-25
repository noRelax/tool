package zjol.virtual;

import java.io.File;
import java.io.FileInputStream;
import java.util.LinkedHashMap;
import java.util.List;

import zjol.virtual.VirtualAccountInfo.AccountInfo;


/**
 * 澶栭儴铏氭嫙鍙峰鐞�
 * @author sunpeng
 */
public class VirtualAccountOuterSync {
    
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
     * 璇诲彇Exel鍐呭
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
            fieldMap.put("昵称", "nickName");
            fieldMap.put("密码", "password");
            List<AccountInfo> list = ExcelUtils.excelToList(new FileInputStream(file), 0, AccountInfo.class, fieldMap, new String[] { "虚拟码" });
            virtualAccountInfo.setAccountInfo(list);
            virtualAccountInfo.setTimestamp(123456L);
            virtualAccountInfo.setSignature("a50c254e572495e3dfb68faf03539f066cc8993b2d9885b3c0eab08cbfd70379");
            virtualAccountInfo.setExistUpdate(false);
            System.out.println(1);
            System.out.println(JsonUtils.toJson(virtualAccountInfo));
        }
        return virtualAccountInfo;
    }
}
