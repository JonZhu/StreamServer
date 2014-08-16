/**
 * 
 */
package com.zhujun.streamserver.server.config;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

/**
 * The loader of stream server config file
 * 
 * @author Jhon
 * @date 2014-8-14
 */
public class ConfigLoader {

	private static ServerConfig serverConfig;
	
	public static void load(String cfgFile) throws Exception {
		JAXBContext jaxbContext = JAXBContext.newInstance(ServerConfig.class);
		Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
		serverConfig = (ServerConfig)unmarshaller.unmarshal(new File(cfgFile));
	}
	
	public static ServerConfig getConfig() {
		return serverConfig;
	}
	
}
