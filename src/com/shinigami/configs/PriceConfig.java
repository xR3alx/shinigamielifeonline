package com.shinigami.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class PriceConfig {
	public Properties props;
	public File priceConfigFile;
	
	public HashMap<String, Integer> buyPrices, sellPrices;
	
	public PriceConfig(){
		
		File dataDir = new File("MinecraftLife/config");
		if(!dataDir.exists()){
			dataDir.mkdirs();
		}
		
		priceConfigFile = new File("MinecraftLife/config/prices.data");
		props = new Properties();
		
		try {
			
			if(!priceConfigFile.exists()){
				priceConfigFile.createNewFile();
			}
			
			FileInputStream fileIn = new FileInputStream(priceConfigFile);
			props.load(fileIn);
			fileIn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		buyPrices = new HashMap<String, Integer>();
		sellPrices = new HashMap<String, Integer>();
		
		load();
	}
	
	public int getBuyPrice(String s){
		return buyPrices.get(s);
	}
	
	public int getSellPrice(String s){
		return sellPrices.get(s);
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			for(Object o : props.keySet()){
				String s = (String) o;
				if(s.contains("buy.")){
					buyPrices.put(s.replace("buy.", ""), Integer.parseInt(props.getProperty(s)));
				}
				if(s.contains("sell.")){
					sellPrices.put(s.replace("sell.", ""), Integer.parseInt(props.getProperty(s)));
				}
			}
			loaded = true;
		}
	}
}
