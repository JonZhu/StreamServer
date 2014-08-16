package com.zhujun.streamserver.rtsp.internal;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhujun.streamserver.rtsp.IRtspRequest;
import com.zhujun.streamserver.rtsp.IRtspRequestParser;
import com.zhujun.streamserver.rtsp.consts.RtspChars;
import com.zhujun.streamserver.rtsp.consts.RtspMethods;

/**
 * Default implement for rtsp request parser
 * @author Jhon
 * @date 2014-8-10
 */
public class RtspRequestParserImpl implements IRtspRequestParser {

	private final static Logger LOG = LoggerFactory.getLogger(RtspRequestParserImpl.class);
	
	private final static int PHASE_UNSTART = 0;
	private final static int PHASE_HEADER = 1;
	private final static int PHASE_BODY = 2;
	
	private static Pattern REQUEST_LINE_PATTERN;
	static {
		initRequestLinePattern();
	}
	
	/**
	 * Current status for parsing on request
	 */
	private int requestParseStatus = PHASE_UNSTART;
	
	private Queue<IRtspRequest> requestQueue = new ConcurrentLinkedQueue<IRtspRequest>();
	
	/**
	 * The request that is be parsed now
	 */
	private DefaultRtspRequest currentRequest;
	
	private byte[] inputDatas;
	
	/**
	 * The point of unparsed data in inputDatas property
	 */
	private int unParsedPoint = 0;
	
	
	private static void initRequestLinePattern() {
		Field[] methodFields = RtspMethods.class.getFields();
		StringBuilder regex = new StringBuilder("^(");
		// Method
		try {
			for (int i = 0; i < methodFields.length; i++) {
				Field methodField = methodFields[i];
				Object methodName = methodField.get(RtspMethods.class);
				regex.append(methodName);
				if (i != methodFields.length - 1) {
					regex.append("|");
				}
			}
		} catch (Exception e) {
			LOG.error("Build request list regex error", e);
		}
		regex.append(") ");
		
		// Url
		regex.append("([^ ]+) ");
		
		// Version
		regex.append("(RTSP/\\d+(\\.\\d+)?)");
		
		regex.append("$");
		
		REQUEST_LINE_PATTERN = Pattern.compile(regex.toString());
	}
	
	
	@Override
	public void appendIn(byte[] data) {
		if (inputDatas == null) {
			inputDatas = data;
		} else {
			byte[] temp = new byte[inputDatas.length + data.length];
			System.arraycopy(inputDatas, 0, temp, 0, inputDatas.length);
			System.arraycopy(data, 0, temp, inputDatas.length, data.length);
			inputDatas = temp;
		}
		
		parseRequest();
		
		clearInputDatas();
	}
	
	/**
	 * 
	 */
	private void clearInputDatas() {
		if (inputDatas != null && unParsedPoint > 0) {
			inputDatas = Arrays.copyOfRange(inputDatas, unParsedPoint, inputDatas.length);
		}
		
		unParsedPoint = 0;
		
	}

	private void parseRequest() {
		
		while (true) {
			if (requestParseStatus == PHASE_UNSTART) {
				String line = readLine();
				if (line == null) {
					break;
				}
				
				if (!isRequestLine(line)) {
					continue;
				}
				
				startNewRequest(line);
				requestParseStatus = PHASE_HEADER;
			} else if (requestParseStatus == PHASE_HEADER) {
				String line = readLine();
				if (line == null) {
					break;
				}
				
				if ("".equals(line)) {
					// Parse header end
					if (currentRequest.getContentLength() != null && currentRequest.getContentLength() > 0) {
						// Has body, Entry body parsing
						requestParseStatus = PHASE_BODY;
					} else {
						// No body, Current request parsing end
						endRequest();
					}
				} else {
					addHeader(line);
				}
			} else if (requestParseStatus == PHASE_BODY) {
				byte[] bodyContentBytes = readBytes(currentRequest.getContentLength());
				if (bodyContentBytes == null) {
					break;
				}
				
				currentRequest.setBody(bodyContentBytes);
				endRequest();
			}
		}
		
		
	}


	/**
	 * Start parse a new request
	 * @param requestLine
	 */
	private void startNewRequest(String requestLine) {
		String[] fields = requestLine.split(" ");
		currentRequest = new DefaultRtspRequest();
		currentRequest.setMethod(fields[0]);
		currentRequest.setUrl(fields[1]);
		currentRequest.setVersion(fields[2]);
	}
	
	/**
	 * @param headerLine
	 */
	private void addHeader(String headerLine) {
		String[] headerFields = headerLine.split(":( )?", 2);
		currentRequest.setHeader(headerFields[0], headerFields.length > 1 ? headerFields[1] : "");
	}
	
	/**
	 * End a request parsing
	 */
	private void endRequest() {
		// Append session to request
		if (!RtspMethods.SETUP.equals(currentRequest.getMethod())) {
			String sessionId = currentRequest.getHeader(RtspChars.SESSION_ID_HEADER_NAME);
			if (sessionId != null) {
				IRtspSessionManager sessionManager = DefaultRtspSessionManager.getInstance();
				currentRequest.setSession(sessionManager.get(sessionId));
			}
		}
		
		// Add queue
		requestQueue.offer(currentRequest);
		
		// Reset status
		currentRequest = null;
		requestParseStatus = PHASE_UNSTART;
	}
	

	private boolean isRequestLine(String line) {
		return REQUEST_LINE_PATTERN.matcher(line).matches();
	}

	/**
	 * Read line from inputDatas and set unParsedPoint
	 * @return line text or null if no data readed
	 */
	private String readLine() {
		int crlfIndex = findBytesIndex(inputDatas, RtspChars.CRLF_BYTES, unParsedPoint);
		if (crlfIndex == -1) {
			return null;
		}
		
		String lineStr = new String(Arrays.copyOfRange(inputDatas, unParsedPoint, crlfIndex));
		LOG.debug("Line: {}", lineStr);
		unParsedPoint = crlfIndex + RtspChars.CRLF_BYTES.length;
		
		return lineStr;
	}
	
	/**
	 * Read bytes from inputDatas and set unParsedPoint
	 * 
	 * @param length
	 * @return byte[length] or null if there is no enough bytes
	 */
	private byte[] readBytes(Integer length) {
		if (unParsedPoint + length > inputDatas.length) {
			return null;
		}
		
		byte[] readBytes = Arrays.copyOfRange(inputDatas, unParsedPoint, unParsedPoint + length);
		unParsedPoint += length;
		
		return readBytes;
	}
	

	/**
	 * Find target bytes index in datas bytes
	 * @param datas
	 * @param target
	 * @param start
	 * @return
	 */
	private int findBytesIndex(byte[] datas, byte[] target, int start) {
		for (int i = start; i < datas.length - target.length + 1; i++) {
			int matchCount = 0;
			for (int j = 0; j < target.length; j++) {
				if (datas[i + j] == target[j]) {
					matchCount++;
				} else {
					// Any one byte not matched
					break;
				}
			}
			
			if (matchCount == target.length) {
				// All bytes matched
				return i;
			}
			
		}
		
		return -1;
	}

	@Override
	public boolean hasNext() {
		return !requestQueue.isEmpty();
	}

	@Override
	public IRtspRequest nextRequest() {
		return requestQueue.poll();
	}
	
	
}
