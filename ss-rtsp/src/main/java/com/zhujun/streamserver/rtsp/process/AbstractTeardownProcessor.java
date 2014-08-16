/**
 * 
 */
package com.zhujun.streamserver.rtsp.process;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessor;
import com.zhujun.streamserver.rtsp.IRtspResponse;
import com.zhujun.streamserver.rtsp.internal.DefaultRtspSessionManager;

/**
 * The last commond in rtsp session
 * 
 * <p>
 *  Remove session from manager
 * </p>
 * 
 * @author Jhon
 * @date 2014-8-16
 */
public abstract class AbstractTeardownProcessor implements IRtspRequestProcessor {

	@Override
	public void process(IRtspRequest request, IRtspResponse response) {
		internalProcess(request, response);
		
		if (request.getSession() != null) {
			// Remove session
			DefaultRtspSessionManager.getInstance().remove(request.getSession().getId());
		}
		
	}

	/**
	 * @param request
	 * @param response
	 */
	protected abstract void internalProcess(IRtspRequest request, IRtspResponse response);

}
