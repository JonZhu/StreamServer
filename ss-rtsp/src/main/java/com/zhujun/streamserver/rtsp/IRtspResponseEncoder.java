package com.zhujun.streamserver.rtsp;

public interface IRtspResponseEncoder {

	byte[] encode(IRtspResponse response);

}
