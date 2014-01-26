package com.helloweenvsfei.listener;

import java.io.Serializable;
import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionActivationListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import javax.servlet.http.HttpSessionEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PersonInfo implements HttpSessionActivationListener,
		HttpSessionBindingListener, Serializable {

	private static final long serialVersionUID = -4780592776386225973L;

	Log log = LogFactory.getLog(getClass());

	//原來就有的屬性
	private String name;
	private Date dateCreated;
	//
	
	//我後來加上的
	//
	private String account;
	private String ip;
	private Date loginDate;
	//

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getDateCreated() {
		return dateCreated;
	}

	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	// 從硬碟恢復後被呼叫
	public void sessionDidActivate(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		log.info(this + "已經成功從硬碟中載入sessionId: " + session.getId());
	}

	// 即將被鈍化到硬碟時被呼叫
	public void sessionWillPassivate(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		log.info(this + "即將儲存到硬碟sessionId: " + session.getId());
	}

	// 被放進session前被呼叫
	public void valueBound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		String name = event.getName();
		log.info(this + "被綁定到 session \"" + session.getId() + "\"的" + name
				+ "屬性上");

		// 記錄放到 session 中的時間
		this.setDateCreated(new Date());
	}

	// 從session中移除後被呼叫
	public void valueUnbound(HttpSessionBindingEvent event) {
		HttpSession session = event.getSession();
		String name = event.getName();
		log.info(this + "被從session \"" + session.getId() + "\"的" + name
				+ "屬性上移除");
	}

	@Override
	public String toString() {
		return "PersonInfo(" + name + ")";
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

	
}
