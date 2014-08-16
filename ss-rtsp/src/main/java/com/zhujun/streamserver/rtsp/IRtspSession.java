package com.zhujun.streamserver.rtsp;

import java.util.Date;

/**
 * @author Jhon
 * @date 2014-8-10
 */
public interface IRtspSession extends IAttributeAccess {

	String getId();
	
	void setAttribute(String name, Object value);
	
	Object getAttribute(String name);
	
	Date getCreateTime();
	
	Date getLastModifyTime();
	
}
