package com.shinigami.locations;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.Location;

import com.shinigami.utils.Utils;

public class LocationsManager {

	public HashMap<String, Location> locations;
	public Properties props;
	public File locDataFile;
	
	public LocationsManager(){
		File dataDir = new File("MinecraftLife/db");
		if(!dataDir.exists()){
			dataDir.mkdirs();
		}
		
		locDataFile = new File("MinecraftLife/db/locs.data");
		
		if(!locDataFile.exists()){
			try {
				locDataFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		props = Utils.loadFile(locDataFile);
		
		locations = new HashMap<String, Location>();
		
		load();
		
	}
	
	public void load(){
		boolean loaded = false;
		while(!loaded){
			for(Object o : props.keySet()){
				if(o instanceof String){
					String s = (String) o;
					String prop = props.getProperty(s);
					String[] locs = prop.split(";");
					
					Location loc = new Location(Bukkit.getWorld(locs[0]), Double.parseDouble(locs[1]), Double.parseDouble(locs[2]), Double.parseDouble(locs[3]));

					locations.put(s, loc);
				}
			}
			loaded = true;
		}
	}
	
	public void saveAll(){
		boolean saved = false;
		while (!saved) {
			for(String s : locations.keySet()){
				Location loc = locations.get(s);
				
				props.setProperty(s, loc.getWorld().getName() + ";" + loc.getX() + ";" + loc.getY() + ";" + loc.getZ());
			}
			
			try {
				
				FileOutputStream fileOut = new FileOutputStream(locDataFile);
				props.store(fileOut, null);
				fileOut.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			saved = true;
		}
	}
	
	public boolean exist(String s){
		if(locations.containsKey(s)){
			return true;
		}
		return false;
	}
	
	public void add(Location loc, String s){
		locations.put(s, loc);
	}
	
}
