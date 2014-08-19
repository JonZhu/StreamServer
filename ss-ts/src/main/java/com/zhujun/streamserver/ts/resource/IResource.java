/**
 * 
 */
package com.zhujun.streamserver.ts.resource;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jhon
 * @date 2014-8-19
 */
public interface IResource {

	/**
	 * The name of the resource
	 */
	String getName();
	
	/**
	 * A new input stream for the resource
	 */
	InputStream getInputStream() throws IOException;
	
}
