/**
 * 
 */
package com.zhujun.streamserver.ts.resource;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @author Jhon
 * @date 2014-8-19
 */
public class FileSystemResource extends AbstractResource implements IResource {

	private File file;
	
	public FileSystemResource(String fileName) {
		this(new File(fileName));
	}
	
	public FileSystemResource(File file) {
		this.file = file;
		this.name = file.getAbsolutePath();
	}
	
	@Override
	public InputStream getInputStream() throws IOException {
		return new FileInputStream(file);
	}

}
