package com.shinigami.configs;

public class Configuration {

	private static Config config;
	private static PriceConfig priceConfig;
	
	public Configuration() {
		config = new Config();
		priceConfig = new PriceConfig();
	}
	
	public static Config getConfig(){
		return config;
	}
	
	public static PriceConfig getPriceConfig(){
		return priceConfig;
	}

}
