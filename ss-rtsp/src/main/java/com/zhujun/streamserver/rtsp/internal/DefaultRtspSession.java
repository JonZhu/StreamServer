/**
 * 
 */
package com.zhujun.streamserver.rtsp.internal;

import java.util.Date;

import com.zhujun.streamserver.rtsp.IRtspSession;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public class DefaultRtspSession extends AttributeAccessEntity implements IRtspSession {

	private static int SESSION = 10000000;
	
	private String id;
	
	private final Date createTime = new Date();
	private Date lastModifyTime = new Date();
	
	/**
	 * @return
	 */
	private synchronized static String createSessionId() {
		return String.valueOf(SESSION++);
	}
	
	public DefaultRtspSession() {
		id = createSessionId();
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public Date getLastModifyTime() {
		return lastModifyTime;
	}

}
