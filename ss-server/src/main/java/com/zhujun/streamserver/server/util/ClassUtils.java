/**
 * 
 */
package com.zhujun.streamserver.server.util;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Jhon
 * @date 2014-8-14
 */
public class ClassUtils {

	private static Map<String, Object> singletonInstanceMap = new HashMap<String, Object>();
	
	public static Object getNewInstance(String className) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		Class clazz = Class.forName(className);
		return clazz.newInstance();
	}
	
	public static Object getSingletonInstance(String className) 
			throws ClassNotFoundException, InstantiationException, IllegalAccessException {
		synchronized (singletonInstanceMap) {
			if (singletonInstanceMap.containsKey(className)) {
				return singletonInstanceMap.get(className);
			}
			
			Object instance = getNewInstance(className);
			singletonInstanceMap.put(className, instance);
			
			return instance;
		}
	}
	
}
