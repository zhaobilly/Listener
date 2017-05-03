package com.cs.listener.test;

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
import org.apache.log4j.PropertyConfigurator;

/**
 * Application Lifecycle Listener implementation class ListenerTest
 *
 */
public class ListenerTest implements HttpSessionListener,ServletContextListener,ServletRequestListener {

    /**
     * Default constructor. 
     */
	Log log=LogFactory.getLog(getClass());
    public ListenerTest() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent se) {
    	HttpSession session=se.getSession();
    	log.info("新创建了一个session，ID为："+session.getId());
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent se) {
    	HttpSession session=se.getSession();
    	log.info("销毁了一个session，ID为："+session.getId());
    }

	@Override
	public void requestDestroyed(ServletRequestEvent se) {
		HttpServletRequest request=(HttpServletRequest)se.getServletRequest();
		long time=System.currentTimeMillis()-(Long)request.getAttribute("dateCreated");
		log.info(request.getRemoteAddr()+"请求处理结束，用时"+time+"毫秒.");
	}

	@Override
	public void requestInitialized(ServletRequestEvent se) {
		HttpServletRequest request=(HttpServletRequest) se.getServletRequest();
		String uri=request.getRequestURI();
		uri=request.getQueryString()==null?uri:(uri+"?"+request.getQueryString());
		request.setAttribute("dateCreated", System.currentTimeMillis());
		log.info("IP"+request.getRemoteAddr()+"请求"+uri);
	}

	@Override
	public void contextDestroyed(ServletContextEvent se) {
		ServletContext servletContext=se.getServletContext();
		log.info("即将关闭"+servletContext.getContextPath());
	}

	@Override
	public void contextInitialized(ServletContextEvent se) {
		ServletContext servletContext=se.getServletContext();
		log.info("即将启动"+servletContext.getContextPath());
	}
	
}
