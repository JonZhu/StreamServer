/**
 * 
 */
package com.zhujun.streamserver.server.config;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * @author Jhon
 * @date 2014-8-14
 */
@XmlRootElement(name = "StreamServer")
@XmlAccessorType(XmlAccessType.FIELD)
public class ServerConfig {

	@XmlElement(name = "Port", required = true)
	private Integer port;
	
	@XmlElement(name = "Request", required = true)
	private RequestConfig requestConfig;

	public Integer getPort() {
		return port;
	}

	public void setPort(Integer port) {
		this.port = port;
	}

	public RequestConfig getRequestConfig() {
		return requestConfig;
	}

	public void setRequestConfig(RequestConfig requestConfig) {
		this.requestConfig = requestConfig;
	}
	
}
