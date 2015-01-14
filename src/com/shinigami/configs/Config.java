package com.shinigami.configs;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

public class Config {
	private Properties props;
	private File configFile;
	
	private HashMap<String, String> configs;
	
	public Config(){
		File dataDir = new File("MinecraftLife/config");
		if(!dataDir.exists()){
			dataDir.mkdirs();
		}
		
		configFile = new File("MinecraftLife/config/config.yml");
		props = new Properties();
		
		try {
			
			if(!configFile.exists()){
				configFile.createNewFile();
			}
			
			FileInputStream fileIn = new FileInputStream(configFile);
			props.load(fileIn);
			fileIn.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		configs = new HashMap<String, String>();
		
		load();
	}
	
	public String getEntry(String s){
		return configs.get(s);
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			for(Object s : props.keySet()){
				configs.put(s.toString(), props.getProperty(s.toString()));
			}
			loaded = true;
		}
	}
}
