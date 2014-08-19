/**
 * 
 */
package com.zhujun.streamserver.ts.streaming;

import com.zhujun.streamserver.ts.ITarget;
import com.zhujun.streamserver.ts.resource.IResource;


/**
 * Streaming interface for ts
 * 
 * @author Jhon
 * @date 2014-8-19
 */
public interface ITsStreaming {

	void setResource(IResource resource);
	IResource getResource();
	
	void addTarget(ITarget target);
	void removeTarget(ITarget target);
	ITarget[] getTargets();
	
	void start();
	void puase();
	void stop();
	
}
