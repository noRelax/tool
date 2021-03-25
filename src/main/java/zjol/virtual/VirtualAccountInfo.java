package zjol.virtual;

import java.util.List;

/**
 * 推荐词同步实体
 * @author sunpeng
 */
public class VirtualAccountInfo {

    private Long timestamp; // 时间戳
    private String signature; // 签名
    private Boolean existUpdate; //存在则更新指定昵称和指定邀请码
    private String imageUrl;//头像地址不为空，则设为账户的默认头像
    private List<AccountInfo> accountInfo; //账号信息

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
        this.timestamp = timestamp;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(String signature) {
        this.signature = signature;
    }
    
    public Boolean getExistUpdate() {
        return existUpdate;
    }

    public void setExistUpdate(Boolean existUpdate) {
        this.existUpdate = existUpdate;
    }
    
    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public List<AccountInfo> getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(List<AccountInfo> accountInfo) {
        this.accountInfo = accountInfo;
    }

    /**
     * 账户信息
     * @author sunpeng
     */
    public static class AccountInfo {

        private String phoneNumber; //手机号
        private String refCode;//邀请码
        private String nickName;//昵称
        private String password; //密码
        
        public AccountInfo() {
            
        }
        
        public AccountInfo(String phoneNumber, String refCode, String nickName, String password) {
            this.phoneNumber = phoneNumber;
            this.refCode = refCode;
            this.nickName = nickName;
            this.password = password;
        }
        public String getPhoneNumber() {
            return phoneNumber;
        }
        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }
        public String getRefCode() {
            return refCode;
        }
        public void setRefCode(String refCode) {
            this.refCode = refCode;
        }
        public String getNickName() {
            return nickName;
        }
        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }
    }
}
