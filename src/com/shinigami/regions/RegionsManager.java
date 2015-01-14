package com.shinigami.regions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.shinigami.sessions.Session;
import com.shinigami.utils.Utils;

public class RegionsManager {

	public File regionsDir;
	public ArrayList<Region> regions;
	
	public RegionsManager(){
		
		regionsDir = new File("MinecraftLife/db/regions");
		if(!regionsDir.exists()){
			regionsDir.mkdirs();
		}
		
		
		regions = new ArrayList<Region>();
		
		load();
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			if(regionsDir.listFiles().length != 0){
				for(File f : regionsDir.listFiles()){
					
					Properties props = Utils.loadFile(f);

					Region region = new Region();
					
					region.world = Bukkit.getWorld(props.getProperty("world"));
					
					double loc1X = Double.parseDouble(props.getProperty("loc1.x"));
					double loc1Z = Double.parseDouble(props.getProperty("loc1.z"));
					double loc1Y = Double.parseDouble(props.getProperty("loc1.y"));
					
					Location loc1 = new Location(region.world, loc1X, loc1Y, loc1Z);
					
					double loc2X = Double.parseDouble(props.getProperty("loc2.x"));
					double loc2Z = Double.parseDouble(props.getProperty("loc2.z"));
					double loc2Y = Double.parseDouble(props.getProperty("loc2.y"));
					
					Location loc2 = new Location(region.world, loc2X, loc2Y, loc2Z);
					
					region.loc1 = loc1;
					region.loc2 = loc2;
					region.name = f.getName().replace(".region", "");
					
					regions.add(region);
					
					
				}
			}
			loaded = true;
		}
	}
	
	public void saveAll(){
		boolean saved = false;
		while (!saved) {
			if(regionsDir.listFiles().length != 0){
				for(File f : regionsDir.listFiles()){
					Properties props = Utils.loadFile(f);
					for(Region region : regions){
						if(region.name != null){
							if(region.name.equals(f.getName().replace(".region", ""))){
								
								props.setProperty("world", "" + region.world.getName());
								
								props.setProperty("loc1.x", "" + region.loc1.getX());
								props.setProperty("loc1.y", "" + region.loc1.getZ());
								props.setProperty("loc1.z", "" + region.loc1.getY());
								
								props.setProperty("loc2.x", "" + region.loc2.getX());
								props.setProperty("loc2.y", "" + region.loc2.getZ());
								props.setProperty("loc2.z", "" + region.loc2.getY());
								
								try {
									
									FileOutputStream fileOut = new FileOutputStream(f);
									props.store(fileOut, null);
									fileOut.close();
									
								} catch (IOException e) {
									e.printStackTrace();
								}
							}
						}
					}
				}
			}
			saved = true;
		}
	}
	
	public Region getRegion(String name){
		for(Region region : regions){
			if(region.name.equals(name)){
				return region;
			}
		}
		
		return null;
	}
	
	public void createRegion(String name, Player p, Session session){
		
		Region r = new Region();
		r.loc1 = session.getCmdLoc1();
		r.loc2 = session.getCmdLoc2();
		r.world = p.getWorld();
		r.name = name;
		
		regions.add(r);
		
		File file = new File("MinecraftLife/db/regions/" + name + ".region");
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static Region createNewRegion(){
		return new Region();
	}
	
	public static class Region {
		public String name;
		public Location loc1, loc2;
		public World world;

		public boolean intersectsRegion(Location ploc){
			
			if(ploc.getWorld() == world){
				if(loc1.getX() <= ploc.getX() && ploc.getX() <= loc2.getX()){
					if(loc1.getZ() >= ploc.getZ() && ploc.getZ() >= loc2.getZ()){
						return true;
					}
				}
				
				if(loc1.getX() >= ploc.getX() && ploc.getX() >= loc2.getX()){
					if(loc1.getZ() <= ploc.getZ() && ploc.getZ() <= loc2.getZ()){
						return true;
					}
				}
			}
			
			return false;
		}
	}
	
}
