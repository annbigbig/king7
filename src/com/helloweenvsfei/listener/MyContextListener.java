package com.helloweenvsfei.listener;

import java.util.Date;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import com.helloweenvsfei.util.ApplicationConstants;

public class MyContextListener implements ServletContextListener {

	public void contextInitialized(ServletContextEvent event) {
		// 伺服器啟動時被呼叫
		ApplicationConstants.START_DATE = new Date();
	}

	public void contextDestroyed(ServletContextEvent event) {
		// 伺服器關閉時被呼叫
		ApplicationConstants.START_DATE = null;
		ApplicationConstants.MAX_ONLINE_COUNT_DATE = null;
	}
}
