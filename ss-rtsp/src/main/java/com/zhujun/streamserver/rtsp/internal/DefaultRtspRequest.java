package com.zhujun.streamserver.rtsp.internal;

import java.util.HashMap;
import java.util.Map;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspSession;

/**
 * 
 * @author Jhon
 * @date 2014-8-13
 */
public class DefaultRtspRequest extends AttributeAccessEntity implements IRtspRequest {
	
	private IRtspSession session;
	private String method;
	private String url;
	private String version;
	
	private Integer contentLength;
	private byte[] body;
	
	private Map<String, String> headerMap = new HashMap<String, String>();
	
	public IRtspSession getSession() {
		return session;
	}
	
	public String getMethod() {
		return method;
	}
	
	public void setMethod(String method) {
		this.method = method;
	}

	public void setSession(IRtspSession session) {
		this.session = session;
	}


	public String getUrl() {
		return url;
	}


	public void setUrl(String url) {
		this.url = url;
	}


	public String getVersion() {
		return version;
	}


	public void setVersion(String version) {
		this.version = version;
	}

	public Integer getContentLength() {
		return contentLength;
	}

	public void setContentLength(Integer contentLength) {
		this.contentLength = contentLength;
	}

	public byte[] getBody() {
		return body;
	}

	public void setBody(byte[] body) {
		this.body = body;
	}
	
	
	public String getHeader(String name) {
		return headerMap.get(name);
	}

	/**
	 * @param name
	 * @param value
	 */
	public void setHeader(String name, String value) {
		headerMap.put(name, value);
	}
	
}
