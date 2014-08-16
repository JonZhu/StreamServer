/**
 * 
 */
package com.zhujun.streamserver.rtsp.internal;

import java.util.regex.Pattern;

import org.junit.Test;

/**
 * @author Jhon
 * @date 2014-8-13
 */
public class RtspRequestParserImplTest {
	@Test
	public void testHeaderPattern() {
		String regex = "^(DESCRIBE|OPTIONS|SETUP|PLAY|RECORD|PAUSE|TEARDOWN|ANNOUNCE|GET_PARAMETER|SET_PARAMETER|REDIRECT) ([^ ]+) (RTSP/\\d+(\\.\\d+)?)$";
		String requestLine = "OPTIONS rtsp://192.168.20.136:5000/xxx666 RTSP/1.0";
		System.out.println(Pattern.matches(regex, requestLine));
		
	}
	
}
