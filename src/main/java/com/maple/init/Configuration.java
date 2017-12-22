package com.maple.init;

import java.io.File;
import java.net.InetAddress;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.maxmind.geoip2.DatabaseReader;
import com.maxmind.geoip2.model.CityResponse;

@ConfigurationProperties
@Component
public class Configuration {

	public static String bootstrapServer;
	public static String es_host;
	public static int es_port;
	

	public static String getEs_host() {
		return es_host;
	}

	public static void setEs_host(String es_host) {
		Configuration.es_host = es_host;
	}

	public static int getEs_port() {
		return es_port;
	}

	public static void setEs_port(int es_port) {
		Configuration.es_port = es_port;
	}

	public static String getBootstrapServer() {
		return bootstrapServer;
	}

	public static void setBootstrapServer(String bootstrapServer) {
		Configuration.bootstrapServer = bootstrapServer;
	}

}
