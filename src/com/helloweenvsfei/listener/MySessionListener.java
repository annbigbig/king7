package com.helloweenvsfei.listener;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.helloweenvsfei.util.ApplicationConstants;

public class MySessionListener implements HttpSessionListener,
		HttpSessionAttributeListener {

	public void sessionCreated(HttpSessionEvent sessionEvent) {

		HttpSession session = sessionEvent.getSession();

		// 儲存 session
		ApplicationConstants.SESSION_MAP.put(session.getId(), session);
		// 總存取人數++
		ApplicationConstants.TOTAL_HISTORY_COUNT++;

		// 如果目前線上人數超過歷史記錄
		if (ApplicationConstants.SESSION_MAP.size() > ApplicationConstants.MAX_ONLINE_COUNT) {
			ApplicationConstants.MAX_ONLINE_COUNT = ApplicationConstants.SESSION_MAP.size();
			ApplicationConstants.MAX_ONLINE_COUNT_DATE = new Date();
		}
	}

	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		// 即將被銷毀的 session
		HttpSession session = sessionEvent.getSession();
		// 移除session 記錄
		ApplicationConstants.SESSION_MAP.remove(session.getId());
	}

	public void attributeAdded(HttpSessionBindingEvent event) {

		if (event.getName().equals("personInfo")) {

			// �ثe�n�J�ϥΪ̼�++
			ApplicationConstants.CURRENT_LOGIN_COUNT++;
			HttpSession session = event.getSession();

			// �M��ӱb�����S���b��L�����W�n�J
			for (HttpSession sess : ApplicationConstants.SESSION_MAP.values()) {

				// �p�G�ӱb���w�g�b��L�����W�n�J�A�h�H�e���n�J����
				if (event.getValue().equals(sess.getAttribute("personInfo"))
						&& session.getId() != sess.getId()) {
					sess.invalidate();
				}
			}
		}
	}

	public void attributeRemoved(HttpSessionBindingEvent event) {

		// ��P �ثe�n�J�ϥΪ̼�--
		if (event.getName().equals("personInfo")) {
			ApplicationConstants.CURRENT_LOGIN_COUNT--;
		}
	}

	public void attributeReplaced(HttpSessionBindingEvent event) {

		// ���s�n�J
		if (event.getName().equals("personInfo")) {
			HttpSession session = event.getSession();
			for (HttpSession sess : ApplicationConstants.SESSION_MAP.values()) {
				// �p�G�s�b���b��L�����W�n�J�L�A�h�H�e�n�J����
				if (event.getValue().equals(sess.getAttribute("personInfo"))
						&& session.getId() != sess.getId()) {
					sess.invalidate();
				}
			}
		}
	}

}
