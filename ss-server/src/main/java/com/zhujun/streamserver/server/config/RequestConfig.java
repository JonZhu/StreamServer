/**
 * 
 */
package com.zhujun.streamserver.server.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;

/**
 * @author Jhon
 * @date 2014-8-14
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class RequestConfig {

	@XmlElement(name = "ProcessorFactory")
	private String processorFactory;

	public String getProcessorFactory() {
		return processorFactory;
	}

	public void setProcessorFactory(String processorFactory) {
		this.processorFactory = processorFactory;
	}
	
}
