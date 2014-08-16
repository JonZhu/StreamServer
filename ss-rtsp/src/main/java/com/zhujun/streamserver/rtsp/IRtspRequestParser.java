package com.zhujun.streamserver.rtsp;

/**
 * Parse rtsp request from append datas
 * @author Jhon
 *
 */
public interface IRtspRequestParser {

	/**
	 * Append data
	 * @param data
	 */
	void appendIn(byte[] data);

	/**
	 * 
	 * @return
	 */
	boolean hasNext();

	/**
	 * Return request and remove from the queue of parser
	 * @return
	 */
	IRtspRequest nextRequest();

}
