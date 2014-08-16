package com.zhujun.streamserver.rtsp;

/**
 * 
 * @author Jhon
 * @date 2014-8-9
 */
public interface IRtspResponse {

	void setStatusCode(Integer statusCode);
	Integer getStatusCode();
	
	void setVersion(String version);
	String getVersion();
	
	void setReason(String reason);
	String getReason();
	
	void setHeader(String name, String value);
	String getHeader(String name);
	String[] getHeaders();
	
	void setBody(byte[] body);
	byte[] getBody();
	int getContentLength();
	
}
