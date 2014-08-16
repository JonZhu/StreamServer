package com.zhujun.streamserver.rtsp;


/**
 * 
 * @author Jhon
 * @date 2014-8-9
 */
public interface IRtspRequest extends IAttributeAccess {

	IRtspSession getSession();
	
	String getMethod();
	
	String getUrl();
	
	String getVersion();
	
	/**
	 * Body content bytes length
	 * @return
	 */
	Integer getContentLength();
	
	String getHeader(String name);
	
	byte[] getBody();
	
}
