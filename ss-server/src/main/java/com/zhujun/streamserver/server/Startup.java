package com.zhujun.streamserver.server;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

import org.apache.mina.filter.codec.ProtocolCodecFilter;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.zhujun.streamserver.server.config.ConfigLoader;

/**
 * Startup StreamServer
 * @author Jhon
 * @date 2014-8-9
 */
public class Startup {

	private final static Logger LOG = LoggerFactory.getLogger(Startup.class);
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		if (args.length < 1) {
			LOG.error("Please set config file path");
			System.exit(1);
		}
		
		String configFile = args[0];
		LOG.debug("Server config file: {}", configFile);
		
		// Load config
		try {
			ConfigLoader.load(configFile);
		} catch (Exception e) {
			LOG.error("Load server config error", e);
			System.exit(1);
		}
		
		// Start server
		try {
			NioSocketAcceptor socketAcceptor = new NioSocketAcceptor();
			
			socketAcceptor.getFilterChain().addLast("codec", new ProtocolCodecFilter(new RtspProtocolCodecFactory()));
			socketAcceptor.setHandler(new RtspIoHandler());
			
			SocketAddress bindSocketAddress = new InetSocketAddress(ConfigLoader.getConfig().getPort());
			socketAcceptor.bind(bindSocketAddress);
			LOG.info("Stream server startup success on port {}", ConfigLoader.getConfig().getPort());
		} catch (Exception e) {
			LOG.error("Startup server error", e);
			System.exit(1);
		}
		
		
	}

}
