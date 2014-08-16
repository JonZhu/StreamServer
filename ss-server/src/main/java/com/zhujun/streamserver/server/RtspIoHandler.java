package com.zhujun.streamserver.server;

import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IoSession;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessor;
import com.zhujun.streamserver.rtsp.IRtspRequestProcessorFactory;
import com.zhujun.streamserver.rtsp.IRtspResponse;
import com.zhujun.streamserver.rtsp.RtspProvider;
import com.zhujun.streamserver.rtsp.consts.RtspChars;
import com.zhujun.streamserver.server.config.ConfigLoader;
import com.zhujun.streamserver.server.util.ClassUtils;

public class RtspIoHandler extends IoHandlerAdapter {

	
	@Override
	public void messageReceived(IoSession session, Object message)
			throws Exception {
		if (message instanceof IRtspRequest) {
			IRtspRequest rtspRequest = (IRtspRequest)message;
			IRtspResponse rtspResponse = RtspProvider.newResponse(); // Create response
			beforeProcess(rtspRequest, rtspResponse); // Do before process
			IRtspRequestProcessor requestProcessor = getRequestProcessor(rtspRequest);
			if (requestProcessor != null) {
				try {
					requestProcessor.process(rtspRequest, rtspResponse);
					// Process sucess
					rtspResponse.setStatusCode(200);
					rtspResponse.setReason("OK");
				} catch (Exception e) {
					// Process error
					rtspResponse.setStatusCode(500);
					rtspResponse.setReason("Server error");
				}
			} else {
				// No processor for the request
				rtspResponse.setStatusCode(500);
				rtspResponse.setReason("Not found processor fo request: " + rtspRequest.getMethod());
			}
			session.write(rtspResponse);
		}
	}

	/**
	 * Do before process
	 * 
	 * @param rtspRequest
	 * @param rtspResponse
	 */
	private void beforeProcess(IRtspRequest rtspRequest,
			IRtspResponse rtspResponse) {
		String cseq = "Cseq";
		if (rtspRequest.getHeader(cseq) != null) {
			rtspResponse.setHeader(cseq, rtspRequest.getHeader(cseq));
		}
		
		if (rtspRequest.getSession() != null) {
			rtspResponse.setHeader(RtspChars.SESSION_ID_HEADER_NAME, rtspRequest.getSession().getId());
		}
		
	}

	/**
	 * @param rtspRequest
	 * @return
	 */
	private IRtspRequestProcessor getRequestProcessor(IRtspRequest rtspRequest) throws Exception {
		IRtspRequestProcessorFactory processorFactory = (IRtspRequestProcessorFactory)
				ClassUtils.getSingletonInstance(ConfigLoader.getConfig().getRequestConfig().getProcessorFactory());
		return processorFactory.getProcessor(rtspRequest);
	}
	
}
