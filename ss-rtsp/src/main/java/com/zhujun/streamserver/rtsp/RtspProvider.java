package com.zhujun.streamserver.rtsp;

import com.zhujun.streamserver.rtsp.internal.DefaultRtspResponse;
import com.zhujun.streamserver.rtsp.internal.RtspRequestParserImpl;
import com.zhujun.streamserver.rtsp.internal.RtspResponseEncoderImpl;

/**
 * Provider rtsp service by static method
 * @author Jhon
 *
 */
public class RtspProvider {

	public static IRtspRequestParser newRequestParser() {
		return new RtspRequestParserImpl();
	}
	
	public static IRtspResponseEncoder newResponseEncoder() {
		return new RtspResponseEncoderImpl();
	}
	
	public static IRtspResponse newResponse() {
		return new DefaultRtspResponse();
	}
	
}
