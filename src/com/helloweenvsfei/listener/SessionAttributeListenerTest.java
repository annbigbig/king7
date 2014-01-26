package com.helloweenvsfei.listener;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionAttributeListenerTest implements
		HttpSessionAttributeListener {

	Log log = LogFactory.getLog(getClass());

	// 新增屬性時被呼叫
	public void attributeAdded(HttpSessionBindingEvent se) {
		//HttpSession session = se.getSession();
		String name = se.getName();
		log.info("新增session屬性" + name + ", 值為" + se.getValue());	//原作者錯誤寫法
	}

	// 移除屬性時被呼叫
	public void attributeRemoved(HttpSessionBindingEvent se) {
		//HttpSession session = se.getSession();
		String name = se.getName();
		log.info("刪除session屬性" + name + ", 值為" + se.getValue());	//原作者錯誤寫法
	}

	// 修改屬性時被呼叫
	public void attributeReplaced(HttpSessionBindingEvent se) {
		HttpSession session = se.getSession();
		String name = se.getName();
		Object oldValue = se.getValue();
		log.info("修改session屬性" + name + ", 原值" + oldValue + ", 新值"
				+ session.getAttribute(name));
	}
}
