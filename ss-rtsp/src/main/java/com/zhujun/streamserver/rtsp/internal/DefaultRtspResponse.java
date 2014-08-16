/**
 * 
 */
package com.zhujun.streamserver.rtsp.internal;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.zhujun.streamserver.rtsp.IRtspResponse;
import com.zhujun.streamserver.rtsp.consts.RtspChars;

/**
 * Default implements IRtspResponse
 * 
 * @author Jhon
 * @date 2014-8-14
 * @see IRtspResponse
 */
public class DefaultRtspResponse implements IRtspResponse {

	private Integer statusCode = 200;
	private String version = RtspChars.VERSION;
	private String reason = "OK";
	
	private Map<String, String> headerMap = new HashMap<String, String>();
	
	private byte[] body;
	
	@Override
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	@Override
	public Integer getStatusCode() {
		return statusCode;
	}

	@Override
	public void setVersion(String version) {
		this.version = version;
	}

	@Override
	public String getVersion() {
		return version;
	}

	@Override
	public void setReason(String reason) {
		this.reason = reason;
	}

	@Override
	public String getReason() {
		return reason;
	}

	@Override
	public void setHeader(String name, String value) {
		headerMap.put(name, value);
	}

	@Override
	public String getHeader(String name) {
		return headerMap.get(name);
	}

	@Override
	public String[] getHeaders() {
		Set<String> nameSet = headerMap.keySet();
		if (nameSet == null) {
			return new String[0];
		}
		
		return nameSet.toArray(new String[0]);
	}

	@Override
	public void setBody(byte[] body) {
		this.body = body;
	}

	@Override
	public byte[] getBody() {
		return body;
	}

	@Override
	public int getContentLength() {
		return body == null ? 0 : body.length;
	}

}
