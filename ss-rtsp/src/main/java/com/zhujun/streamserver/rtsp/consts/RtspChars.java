package com.zhujun.streamserver.rtsp.consts;

/**
 * Constant chars for rtsp protocol
 * 
 * @author Jhon
 * @date 2014-8-10
 */
public interface RtspChars {

	String CRLF_STR = "\r\n";
	byte[] CRLF_BYTES = CRLF_STR.getBytes();
	
	String SPACE_STR = " ";
	byte[] SPACE_BYTES = SPACE_STR.getBytes();
	
	String VERSION = "RTSP/1.1";
	
	/**
	 * The name of session id in rtsp header
	 */
	String SESSION_ID_HEADER_NAME = "Session";
	
}
