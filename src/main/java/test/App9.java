package test;

import java.util.ArrayList;
import java.util.List;

import utils.JedisUtil;
import utils.JsonUtils;

public class App9 {
	public static void main(String[] args) {
		List<ContactsDTO> contactsDTOs = new ArrayList<>();
		for (int i = 0; i < 1000_000; i++) {
			ContactsDTO contactsDTO = new ContactsDTO();
			contactsDTO.setName("name" + i);
			contactsDTO.setMobile("mobile" + i);
			contactsDTO.setSortNumber(new Long(i));

			contactsDTOs.add(contactsDTO);
		}

		String ret = JsonUtils.toJson(contactsDTOs);
		System.out.println(ret);
		JedisUtil.set("myc", ret);
	}

}
