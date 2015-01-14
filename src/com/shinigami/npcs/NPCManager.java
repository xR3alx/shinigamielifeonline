package com.shinigami.npcs;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.utils.DebugMessages;
import com.shinigami.utils.Utils;

public class NPCManager {

	private File npcsDir;
	private ArrayList<NPC> npcs;
	
	public NPCManager(){
		
		npcsDir = new File("MinecraftLife/db/npcs");
		if(!npcsDir.exists()){
			npcsDir.mkdirs();
		}
		
		npcs = new ArrayList<NPC>();
		
		load();
	}
	
	private void load(){
		boolean loaded = false;
		if(npcsDir.listFiles().length != 0){
			while(!loaded){
				for(File f : npcsDir.listFiles()){
					Properties props = Utils.loadFile(f);
	
					NPC npc = new NPC();
					World world = Bukkit.getWorld(props.getProperty("world"));
					Location loc = new Location(world, Double.parseDouble(props.getProperty("x")), Double.parseDouble(props.getProperty("y")), Double.parseDouble(props.getProperty("z")));
					world.loadChunk(loc.getChunk());
					Villager vil = (Villager) world.spawnEntity(loc, EntityType.VILLAGER);
					npc.menu = props.getProperty("menu");
					
					vil.setCustomName(props.getProperty("name"));
					vil.setCustomNameVisible(true);
					vil.setAdult();
					vil.setBreed(false);
					vil.setCanPickupItems(false);
					vil.setRemoveWhenFarAway(false);
					vil.setMetadata("menu", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), npc.menu));
					
					npc.npcEntity = vil;
					npc.npcLoc = loc;
	
					npcs.add(npc);
					
					npc.id = npcs.size();
					vil.setMetadata("id", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), npc.id));
				}
				loaded = true;
			}
		}
	}
	
	public void saveAll(){
		boolean saved = false;
		while (!saved) {
			if(npcsDir.listFiles().length != 0){
				for(File f : npcsDir.listFiles()){
					Properties props = Utils.loadFile(f);
					for(NPC npc : npcs){
						if(npc.id == Integer.parseInt(f.getName().replace(".npc", ""))){
							
							props.setProperty("name", npc.getVillager().getCustomName());
							props.setProperty("menu", npc.menu);
							props.setProperty("world", npc.npcLoc.getWorld().getName());
							props.setProperty("x", npc.npcLoc.getBlockX() + "");
							props.setProperty("z", npc.npcLoc.getBlockZ() + "");
							props.setProperty("y", npc.npcLoc.getBlockY() + "");
							
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
	
	public void update(){
		for(NPC npc : npcs){
			npc.npcEntity.teleport(npc.npcLoc);
			
			if(npc.robbed){
				npc.robbedTimer++;
				if(npc.robbedTimer >= 3600){
					npc.robbed = false;
				}
			}
			
			npc.getVillager().addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 99999, -30));
			npc.getVillager().addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 99999, -30));
		}
	}
	
	public void addNPC(String name, String menu, Player p){
		
		NPC npc = new NPC();
		
		Villager vil = (Villager) p.getWorld().spawnEntity(p.getLocation(), EntityType.VILLAGER);
		
		vil.setCustomName(Utils.changeNumbers(name));
		vil.setCustomNameVisible(true);
		vil.setAdult();
		vil.setBreed(false);
		vil.setCanPickupItems(false);
		vil.setRemoveWhenFarAway(false);
		
		vil.setMetadata("menu", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), menu));
		
		npc.menu = menu;
		npc.npcEntity = vil;
		npc.npcLoc = p.getLocation();
		
		npcs.add(npc);
		
		npc.id = npcs.size();
		vil.setMetadata("id", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), npc.id));
		
		File file = new File("MinecraftLife/db/npcs/" + npcs.size() + ".npc");
		
		DebugMessages.npcCreateMessage(npc);
		
		try {
			file.createNewFile();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public NPC getByName(String name){
		
		for(NPC npc : npcs){
			if(npc.getVillager().getCustomName().equals(name)){
				return npc;
			}
		}
		
		return null;
	}
	
	public NPC getByID(int id){
		return npcs.get((id-1));
	}
	
	public NPC getByLoc(Location loc){
		
		for(NPC npc : npcs){
			if(npc.npcEntity.getLocation().getBlockX() == loc.getBlockX()
					&& npc.npcEntity.getLocation().getBlockY() == loc.getBlockY()
					&& npc.npcEntity.getLocation().getBlockZ() == loc.getBlockZ()){
				return npc;
			}
		}
		
		return null;
	}
	
	public NPC getByNameAndLoc(String name, Location loc){
		
		if(getByName(name).equals(getByLoc(loc))){
			return getByLoc(loc);
		}
		
		return null;
	}
	
	public ArrayList<NPC> getNPCs(){
		return npcs;
	}
	
	public class NPC {
		public Entity npcEntity;
		public int money, robbedTimer, id;
		public boolean robbed;
		public String menu;
		public Location npcLoc;
		
		public Villager getVillager(){
			return (Villager) npcEntity;
		}
	}
	
}
