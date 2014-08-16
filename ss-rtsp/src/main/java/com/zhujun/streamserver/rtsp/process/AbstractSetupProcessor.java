/**
 * 
 */
package com.zhujun.streamserver.rtsp.process;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessor;
import com.zhujun.streamserver.rtsp.IRtspResponse;
import com.zhujun.streamserver.rtsp.IRtspSession;
import com.zhujun.streamserver.rtsp.internal.DefaultRtspRequest;
import com.zhujun.streamserver.rtsp.internal.DefaultRtspSession;
import com.zhujun.streamserver.rtsp.internal.DefaultRtspSessionManager;

/**
 * Setup request processor
 * 
 * <p>Your business setup commond bean must extends AbstractSetupProcessor.</p>
 * 
 * <p>
 * 	First commond of rtsp session, 
 *  Create rtsp session.
 * </p>
 * 
 * @author Jhon
 * @date 2014-8-14
 */
public abstract class AbstractSetupProcessor implements IRtspRequestProcessor {

	@Override
	public void process(IRtspRequest request, IRtspResponse response) {
		IRtspSession rtspSession = createNewRtspSession();
		((DefaultRtspRequest)request).setSession(rtspSession);
		
		internalProcess(request, response);
		
		// Add rtsp session
		DefaultRtspSessionManager.getInstance().add(rtspSession);
	}

	/**
	 * @param request
	 * @param response
	 */
	protected abstract void internalProcess(IRtspRequest request, IRtspResponse response);

	/**
	 * Create session for rtsp
	 * 
	 * @return
	 */
	protected IRtspSession createNewRtspSession() {
		return new DefaultRtspSession();
	}
	
	
}
