package com.zhujun.streamserver.rtsp.internal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhujun.streamserver.rtsp.IRtspResponse;
import com.zhujun.streamserver.rtsp.IRtspResponseEncoder;
import com.zhujun.streamserver.rtsp.consts.RtspChars;

/**
 * Encode rtsp response to byte array
 * @author Jhon
 * @date 2014-8-10
 */
public class RtspResponseEncoderImpl implements IRtspResponseEncoder {

	private final static Logger LOG = LoggerFactory.getLogger(RtspResponseEncoderImpl.class);
	
	@Override
	public byte[] encode(IRtspResponse response) {
		byte[] encodeData = null;
		StringBuilder stringBuilder = new StringBuilder();
		
		// Status line
		stringBuilder.append(response.getVersion()).append(RtspChars.SPACE_STR)
					 .append(response.getStatusCode()).append(RtspChars.SPACE_STR)
					 .append(response.getReason()).append(RtspChars.CRLF_STR);
		
		// Header
		stringBuilder.append("Server: StreamServer V0.1(Author: Zhu Jun, Mail:juncke@163.com, StartDate: 2014.8)").append(RtspChars.CRLF_STR);
		String[] responseHeaders = response.getHeaders();
		if (responseHeaders.length > 0) {
			for (int i = 0; i < responseHeaders.length; i++) {
				String header = responseHeaders[i];
				String headerValue = response.getHeader(header);
				stringBuilder.append(header).append(": ");
				if (headerValue != null) {
					stringBuilder.append(headerValue);
				}
				stringBuilder.append(RtspChars.CRLF_STR);
			}
		}
		if (response.getContentLength() > 0) {
			stringBuilder.append("Content-Length: ").append(response.getContentLength()).append(RtspChars.CRLF_STR);
		}
		stringBuilder.append(RtspChars.CRLF_STR); // Header end
		
		encodeData = stringBuilder.toString().getBytes();
		
		// Body
		if (response.getContentLength() > 0) {
			byte[] temp = new byte[encodeData.length + response.getContentLength()];
			System.arraycopy(encodeData, 0, temp, 0, encodeData.length);
			System.arraycopy(response.getBody(), 0, temp, encodeData.length, response.getContentLength());
			encodeData = temp;
		}
		
		return encodeData;
	}

}
