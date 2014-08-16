/**
 * 
 */
package com.zhujun.streamserver.server;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessor;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessorFactory;
import com.zhujun.streamserver.rtsp.consts.RtspMethods;
import com.zhujun.streamserver.server.process.DescribeProcessor;
import com.zhujun.streamserver.server.process.GetParameterProcessor;
import com.zhujun.streamserver.server.process.OptionsProcessor;
import com.zhujun.streamserver.server.process.PauseProcessor;
import com.zhujun.streamserver.server.process.PlayProcessor;
import com.zhujun.streamserver.server.process.SetParameterProcessor;
import com.zhujun.streamserver.server.process.SetupProcessor;
import com.zhujun.streamserver.server.process.TeardownProcessor;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public class RtspRequestProcessorFactory implements
		IRtspRequestProcessorFactory {

	@Override
	public IRtspRequestProcessor getProcessor(IRtspRequest request) {
		if (RtspMethods.SETUP.equals(request.getMethod())) {
			return new SetupProcessor();
		}
		
		if (RtspMethods.PLAY.equals(request.getMethod())) {
			return new PlayProcessor();
		}
		
		if (RtspMethods.PAUSE.equals(request.getMethod())) {
			return new PauseProcessor();
		}
		
		if (RtspMethods.TEARDOWN.equals(request.getMethod())) {
			return new TeardownProcessor();
		}
		
		if (RtspMethods.SET_PARAMETER.equals(request.getMethod())) {
			return new SetParameterProcessor();
		}
		
		if (RtspMethods.GET_PARAMETER.equals(request.getMethod())) {
			return new GetParameterProcessor();
		}
		
		if (RtspMethods.DESCRIBE.equals(request.getMethod())) {
			return new DescribeProcessor();
		}
		
		if (RtspMethods.OPTIONS.equals(request.getMethod())) {
			return new OptionsProcessor();
		}
		
		return null;
	}

}
