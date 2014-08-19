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
public class ClasspathResource extends AbstractResource implements IResource {

	public ClasspathResource(String name) {
		this.name = name;
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		InputStream inputStream = ClasspathResource.class.getResourceAsStream(name);
		if (inputStream == null) {
			throw new IOException("ClasspathResource don't exist, name: " + name);
		}
		return inputStream;
	}
	
	@Override
	public String toString() {
		return "ClasspathResource, name: " + name;
	}

}
