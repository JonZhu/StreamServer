/**
 * 
 */
package com.zhujun.streamserver.rtsp;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public interface IAttributeAccess {

	void setAttribute(String name, Object value);
	Object getAttribute(String name);
}
