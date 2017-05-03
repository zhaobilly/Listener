package com.cs.listener.test;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class SessionAttributeListenerTest implements
		HttpSessionAttributeListener {

	Log log = LogFactory.getLog(getClass());

	// �������
	public void attributeAdded(HttpSessionBindingEvent se) {
		HttpSession session = se.getSession();
		String name = se.getName();
		log.info("新建session属性" + name + ",值为" + se.getValue());
	}

	// ɾ������
	public void attributeRemoved(HttpSessionBindingEvent se) {
		HttpSession session = se.getSession();
		String name = se.getName();
		log.info("删除session属性" + name + ",值为" + se.getValue());
	}

	// �޸�����
	public void attributeReplaced(HttpSessionBindingEvent se) {
		HttpSession session = se.getSession();
		String name = se.getName();
		Object oldValue = se.getValue();
		log.info("修改session属性" + name + ", 原值" + oldValue + ", 新值"
				+ session.getAttribute(name));
	}
}
