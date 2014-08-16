/**
 * 
 */
package com.zhujun.streamserver.rtsp;

/**
 * Process rtsp request and return response
 * 
 * @author Jhon
 * @date 2014-8-14
 */
public interface IRtspRequestProcessor {

	/**
	 * Process rtsp request
	 * @param request
	 * @param response
	 */
	void process(IRtspRequest request, IRtspResponse response);
	
}
