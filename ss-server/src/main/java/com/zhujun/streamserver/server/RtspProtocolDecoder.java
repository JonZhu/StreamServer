package com.zhujun.streamserver.server;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.filter.codec.ProtocolDecoder;
import org.apache.mina.filter.codec.ProtocolDecoderOutput;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestParser;
import com.zhujun.streamserver.rtsp.RtspProvider;

/**
 * 
 * @author Jhon
 * @date 2014-8-14
 */
public class RtspProtocolDecoder implements ProtocolDecoder {

	private final static String RTSP_REQUEST_PARSER_KEY = IRtspRequestParser.class.getName();
	
	@Override
	public void decode(IoSession session, IoBuffer in, ProtocolDecoderOutput out)
			throws Exception {
		// Get parser from IO session
		IRtspRequestParser rtspRequestParser = (IRtspRequestParser)session.getAttribute(RTSP_REQUEST_PARSER_KEY);
		if (rtspRequestParser == null) {
			rtspRequestParser = RtspProvider.newRequestParser();
			session.setAttribute(RTSP_REQUEST_PARSER_KEY, rtspRequestParser);
		}
		
		// Read datas from IO
		byte[] inputDatas = new byte[in.limit()];
		in.get(inputDatas);
		
		// Append to parser
		rtspRequestParser.appendIn(inputDatas);
		while (rtspRequestParser.hasNext()) {
			IRtspRequest request = rtspRequestParser.nextRequest();
			out.write(request);
		}
	}

	@Override
	public void finishDecode(IoSession session, ProtocolDecoderOutput out)
			throws Exception {

	}

	@Override
	public void dispose(IoSession session) throws Exception {
		session.removeAttribute(RTSP_REQUEST_PARSER_KEY);
	}

}
