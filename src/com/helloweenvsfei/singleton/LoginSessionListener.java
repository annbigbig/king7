package com.helloweenvsfei.singleton;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import com.helloweenvsfei.listener.PersonInfo;

public class LoginSessionListener implements HttpSessionAttributeListener {

	Log log = LogFactory.getLog(this.getClass());

	Map<String, HttpSession> map = new HashMap<String, HttpSession>();

	//增加Session屬性時被呼叫
	public void attributeAdded(HttpSessionBindingEvent event) {

		String name = event.getName();

		if (name.equals("personInfo")) {
			PersonInfo personInfo = (PersonInfo) event.getValue();
			if (map.get(personInfo.getAccount()) != null) {

				// map 中有記錄，表明該帳號在其他機器上登入過，將以前的登入失效
				HttpSession session = map.get(personInfo.getAccount());
				PersonInfo oldPersonInfo = (PersonInfo) session.getAttribute("personInfo");

				log.info("帳號" + oldPersonInfo.getAccount() + "在" + oldPersonInfo.getIp() + "已經登入，該登入將被迫下線");

				session.removeAttribute("personInfo");
				session.setAttribute("msg", "您的帳號已經在其他機器上登入，您被迫下線");
			}

			// 將Session以使用者名稱為索引，放入Map中
			map.put(personInfo.getAccount(), event.getSession());
			log.info("帳號" + personInfo.getAccount() + "在" + personInfo.getIp() + "登入");
		}
	}

	//刪除Session屬性時被呼叫
	public void attributeRemoved(HttpSessionBindingEvent event) {

		String name = event.getName();

		if (name.equals("personInfo")) {
			// 被移除的PersionInfo
			PersonInfo personInfo = (PersonInfo) event.getValue();
			map.remove(personInfo.getAccount());
			log.info("帳號" + personInfo.getAccount() + "已登出");
		}
	}

	//修改Session屬性時被呼叫
	public void attributeReplaced(HttpSessionBindingEvent event) {

		String name = event.getName();

		if (name.equals("personInfo")) {

			// 移除舊的登入資訊
			PersonInfo oldPersonInfo = (PersonInfo) event.getValue();
			map.remove(oldPersonInfo.getAccount());

			// 新的登入資訊
			PersonInfo personInfo = (PersonInfo) event.getSession().getAttribute("personInfo");

			// 也要檢查新登入的帳號是否在別的機器上登入過
			if (map.get(personInfo.getAccount()) != null) {
				// map 中有記錄，表明該帳號在其他機器上登入過，將以前的登入失效
				HttpSession session = map.get(personInfo.getAccount());
				session.removeAttribute("personInfo");
				session.setAttribute("msg", "您的帳號已經在其他機器上登入，您被迫下線");
			}
			map.put("personInfo", event.getSession());
		}

	}

}
