package com.zhujun.streamserver.rtsp.internal;

import com.zhujun.streamserver.rtsp.IRtspSession;

/**
 * 
 * @author Jhon
 * @date 2014-8-10
 */
public interface IRtspSessionManager {

	IRtspSession get(String sessionId);
	
	void add(IRtspSession session);
	
	IRtspSession remove(String sessionId);
	
}
