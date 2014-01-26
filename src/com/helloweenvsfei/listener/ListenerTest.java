package com.helloweenvsfei.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ListenerTest implements HttpSessionListener,
		ServletContextListener, ServletRequestListener {

	Log log = LogFactory.getLog(getClass());

	// 建立 session
	public void sessionCreated(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		log.info("新建立一個session, ID為: " + session.getId());
	}

	// 銷毀 session
	public void sessionDestroyed(HttpSessionEvent se) {
		HttpSession session = se.getSession();
		log.info("銷毀session, ID為: " + session.getId());
	}

	// 載入 context
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		log.info("即將啟動" + servletContext.getContextPath());
	}

	// 銷毀 context
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext servletContext = sce.getServletContext();
		log.info("即將關閉" + servletContext.getContextPath());
	}

	// 建立 request
	public void requestInitialized(ServletRequestEvent sre) {

		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();

		String uri = request.getRequestURI();
		uri = request.getQueryString() == null ? uri : (uri + "?" + request
				.getQueryString());

		request.setAttribute("dateCreated", System.currentTimeMillis());

		log.info("IP " + request.getRemoteAddr() + " 請求 " + uri);
	}

	// 銷毀 request
	public void requestDestroyed(ServletRequestEvent sre) {

		HttpServletRequest request = (HttpServletRequest) sre
				.getServletRequest();

		long time = System.currentTimeMillis()
				- (Long) request.getAttribute("dateCreated");

		log.info(request.getRemoteAddr() + "請求處理結束, 用時" + time + "毫秒. ");
	}

}
