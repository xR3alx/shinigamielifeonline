package com.shinigami.housing;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Player;

import com.shinigami.regions.RegionsManager;
import com.shinigami.regions.RegionsManager.Region;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class HousingManager {

	public File housesDir;
	public ArrayList<House> houses;
	
	public HousingManager(){
		
		housesDir = new File("MinecraftLife/db/houses");
		if(!housesDir.exists()){
			housesDir.mkdirs();
		}
		
		
		houses = new ArrayList<House>();
		
		load();
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			if(housesDir.listFiles().length != 0){
				for(File f : housesDir.listFiles()){
					
					Properties props = Utils.loadFile(f);

					House house = new House();
					
					house.name = f.getName().replace(".house", "");
					if(props.getProperty("owner") != null){
						house.owner = Bukkit.getPlayer(props.getProperty("owner"));
					}
					house.world = Bukkit.getWorld(props.getProperty("world"));
					
					double loc1X = Double.parseDouble(props.getProperty("loc1.x"));
					double loc1Z = Double.parseDouble(props.getProperty("loc1.z"));
					double loc1Y = Double.parseDouble(props.getProperty("loc1.y"));
					
					Location loc1 = new Location(house.world, loc1X, loc1Y, loc1Z);
					
					double loc2X = Double.parseDouble(props.getProperty("loc2.x"));
					double loc2Z = Double.parseDouble(props.getProperty("loc2.z"));
					double loc2Y = Double.parseDouble(props.getProperty("loc2.y"));
					
					Location loc2 = new Location(house.world, loc2X, loc2Y, loc2Z);
					
					Region r = RegionsManager.createNewRegion();
					r.loc1 = loc1;
					r.loc2 = loc2;
					house.area = r;
					
					houses.add(house);
				}
			}
			loaded = true;
		}
	}
	
	public void saveAll(){
		boolean saved = false;
		while (!saved) {
			if(housesDir.listFiles().length != 0){
				for(File f : housesDir.listFiles()){
					Properties props = Utils.loadFile(f);
					for(House house : houses){
						if(house.name.equals(f.getName().replace(".house", ""))){
							
							if(house.owner != null){
								props.setProperty("owner", "" + house.owner.getUniqueId());
							}
							
							props.setProperty("world", "" + house.world.getName());
							
							props.setProperty("loc1.x", "" + house.area.loc1.getX());
							props.setProperty("loc1.y", "" + house.area.loc1.getZ());
							props.setProperty("loc1.z", "" + house.area.loc1.getY());
							
							props.setProperty("loc2.x", "" + house.area.loc2.getX());
							props.setProperty("loc2.y", "" + house.area.loc2.getZ());
							props.setProperty("loc2.z", "" + house.area.loc2.getY());
							
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
			saved = true;
		}
	}
	
	public void buyHouse(String name, Player buyer, Session session){
		for(House house : houses){
			if(house.name.equals(name)){
				house.owner = buyer;
				session.getHouses().put(house.name, house);
				buyer.sendMessage(Colors.DARK_GREEN + "Du hast das Haus erfolgreich gekauft!");
				break;
			}
		}
	}
	
	public void sellHouse(String name, Player buyer, Session session){
		for(House house : houses){
			if(house.name.equals(name)){
				house.owner = null;
				session.getHouses().remove(house.name);
				break;
			}
		}
	}
	
	public void createHouse(String name, Player p, Session session){
		House house = new House();
		
		house.name = name;
		house.owner = null;
		house.world = p.getWorld();
		
		Region r = new Region();
		r.loc1 = session.getCmdLoc1();
		r.loc2 = session.getCmdLoc2();
		house.area = r;
		
		houses.add(house);
		
		File file = new File("MinecraftLife/db/houses/" + name + ".house");
		
		if(!file.exists()){
			try {
				file.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public class House {
		public String name;
		public World world;
		public Player owner;
		public Region area;
	}
}
