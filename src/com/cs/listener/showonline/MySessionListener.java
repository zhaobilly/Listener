package com.cs.listener.showonline;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

/**
 * Application Lifecycle Listener implementation class MySessionListener
 *
 */
public class MySessionListener implements HttpSessionListener, HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public MySessionListener() {
  
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent arg0) {
    	if(arg0.getName().equals("personInfo")){
    		ApplicationConstants.CURRENT_LOGIN_COUNT--;
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent arg0) {
    	if(arg0.getName().equals("personInfo")){
    		ApplicationConstants.CURRENT_LOGIN_COUNT++;
    		HttpSession session=arg0.getSession();
    		for(HttpSession sess:ApplicationConstants.SESSION_MAP.values()){
    			if(arg0.getValue().equals("personInfo")&&session.getId()!=sess.getId()){
    				sess.invalidate();
    			}
    		}
    	}
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent arg0) {
    	if(arg0.getName().equals("personInfo")){
    		HttpSession session=arg0.getSession();
    		for(HttpSession sess:ApplicationConstants.SESSION_MAP.values()){
    			if(arg0.getValue().equals("personInfo")&&session.getId()!=sess.getId()){
    				sess.invalidate();
    			}
    		}
    	}
    }

	/**
     * @see HttpSessionListener#sessionCreated(HttpSessionEvent)
     */
    public void sessionCreated(HttpSessionEvent arg0) {
      	HttpSession session= arg0.getSession();
      	ApplicationConstants.SESSION_MAP.put(session.getId(), session);
      	ApplicationConstants.TOTAL_HISTORY_COUNT++;
      	if(ApplicationConstants.SESSION_MAP.size()>ApplicationConstants.MAX_ONLINE_COUNT){
      		ApplicationConstants.MAX_ONLINE_COUNT=ApplicationConstants.SESSION_MAP.size();
      		ApplicationConstants.MAX_ONLINE_COUNT_DATE=new Date();
      	}
    }

	/**
     * @see HttpSessionListener#sessionDestroyed(HttpSessionEvent)
     */
    public void sessionDestroyed(HttpSessionEvent arg0) {
    	HttpSession session=arg0.getSession();
    	ApplicationConstants.SESSION_MAP.remove(session.getId());
    }
	
}
