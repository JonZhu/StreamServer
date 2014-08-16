package com.zhujun.streamserver.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolEncoder;
import org.apache.mina.filter.codec.ProtocolEncoderOutput;

import com.zhujun.streamserver.rtsp.IRtspResponse;
import com.zhujun.streamserver.rtsp.IRtspResponseEncoder;
import com.zhujun.streamserver.rtsp.RtspProvider;

/**
 * 
 * @author Jhon
 * @date 2014-8-14
 */
public class RtspProtocolEncoder implements ProtocolEncoder {

	private IRtspResponseEncoder rtspResponseEncoder = RtspProvider.newResponseEncoder();
	
	@Override
	public void encode(IoSession session, Object message,
			ProtocolEncoderOutput out) throws Exception {
		if (message instanceof IRtspResponse) {
			byte[] encodeBytes = rtspResponseEncoder.encode((IRtspResponse)message);
			IoBuffer ioBuffer = IoBuffer.allocate(encodeBytes.length);
			ioBuffer.put(encodeBytes);
			ioBuffer.flip();
			out.write(ioBuffer);
		}
	}

	@Override
	public void dispose(IoSession session) throws Exception {
		rtspResponseEncoder = null;
	}

}
