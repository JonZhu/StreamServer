/**
 * 
 */
package com.zhujun.streamserver.rtsp;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public interface IRtspRequestProcessorFactory {

	IRtspRequestProcessor getProcessor(IRtspRequest request);
	
}
