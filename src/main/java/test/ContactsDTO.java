package test;

import java.io.Serializable;

/**
 * 通讯录信息DTO
 * 
 * @author ZhangShuGang
 *
 */
public class ContactsDTO implements Serializable {
	private static final long serialVersionUID = -1384985559690489246L;
	private String name;// 通讯录上的姓名
	private String mobile;// 手机号
	private Long sortNumber;//排序值
	
	public ContactsDTO() {
	}

	public ContactsDTO(String name, String mobile) {
		this.name = name;
		this.mobile = mobile;
	}
	
	public ContactsDTO(String name, String mobile, Long sortNumber) {
		this.name = name;
		this.mobile = mobile;
		this.sortNumber = sortNumber;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Long getSortNumber() {
		return sortNumber;
	}

	public void setSortNumber(Long sortNumber) {
		this.sortNumber = sortNumber;
	}

	@Override
	public String toString() {
		return "ContactsDTO [name=" + name + ", mobile=" + mobile + "]";
	}
	
}
