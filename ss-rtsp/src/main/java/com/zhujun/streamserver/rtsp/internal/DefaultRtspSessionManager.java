/**
 * 
 */
package com.zhujun.streamserver.rtsp.internal;

import java.util.HashMap;
import java.util.Map;

import com.zhujun.streamserver.rtsp.IRtspSession;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public class DefaultRtspSessionManager implements IRtspSessionManager {

	private static IRtspSessionManager sessionManager;
	
	private Map<String, IRtspSession> sessionMap = new HashMap<String, IRtspSession>();
	
	public static IRtspSessionManager getInstance() {
		if (sessionManager == null) {
			sessionManager = new DefaultRtspSessionManager();
		}
		return sessionManager;
	}
	
	private DefaultRtspSessionManager() {
		
	}
	
	@Override
	public synchronized IRtspSession get(String sessionId) {
		return sessionMap.get(sessionId);
	}

	@Override
	public synchronized void add(IRtspSession session) {
		sessionMap.put(session.getId(), session);
	}

	@Override
	public synchronized IRtspSession remove(String sessionId) {
		return sessionMap.remove(sessionId);
	}

}
