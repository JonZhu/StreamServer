/**
 * 
 */
package com.zhujun.streamserver.server;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessor;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessorFactory;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public class RtspRequestProcessorFactory implements
		IRtspRequestProcessorFactory {

	@Override
	public IRtspRequestProcessor getProcessor(IRtspRequest request) {
		
		return null;
	}

}
