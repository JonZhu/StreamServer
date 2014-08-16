/**
 * 
 */
package com.zhujun.streamserver.rtsp.internal;

import java.util.HashMap;
import java.util.Map;

import com.zhujun.streamserver.rtsp.IAttributeAccess;

/**
 * Implement IAttributeAccess
 * 
 * @see IAttributeAccess
 * 
 * @author Jhon
 * @date 2014-8-14
 */
public class AttributeAccessEntity implements IAttributeAccess {

	private Map<String, Object> attributeMap = new HashMap<String, Object>();
	
	@Override
	public void setAttribute(String name, Object value) {
		attributeMap.put(name, value);
	}

	@Override
	public Object getAttribute(String name) {
		return attributeMap.get(name);
	}

}
