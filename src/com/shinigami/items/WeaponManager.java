package com.shinigami.items;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import com.shinigami.utils.Utils;

public class WeaponManager {

	public File itemDir;
	
	public ArrayList<Weapon> sniper, mps, pumpguns, guns, mgs, throwers, pistols;
	
	public WeaponManager(){
		
		itemDir = new File("MinecraftLife/weapons");
		if(!itemDir.exists()){
			itemDir.mkdirs();
		}
		
		sniper = new ArrayList<Weapon>();
		mps = new ArrayList<Weapon>();
		pumpguns = new ArrayList<Weapon>();
		guns = new ArrayList<Weapon>();
		pistols = new ArrayList<Weapon>();
		throwers = new ArrayList<Weapon>();
		mgs = new ArrayList<Weapon>();
		
		load();
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			if(itemDir.listFiles().length != 0){
				for(File f : itemDir.listFiles()){
					
					Properties props = Utils.loadFile(f);
					
					Weapon item = new Weapon();

					item.name = f.getName().replace(".item", "");
					item.ammo = props.getProperty("ammo");
					item.ammoCost = Integer.parseInt(props.getProperty("ammo_cost"));
					item.ammoDisplayname = props.getProperty("ammo_displayname");
					item.projectile = props.getProperty("projectile");
					item.shootRate = Float.parseFloat(props.getProperty("firerate"));
					item.damage = Float.parseFloat(props.getProperty("damage"));
					item.recoil = Float.parseFloat(props.getProperty("recoil"));
					item.type = props.getProperty("type");
					
					if(props.getProperty("type").equals("pistol")){
						pistols.add(item);
					}else if(props.getProperty("type").equals("gun")){
						guns.add(item);
					}else if(props.getProperty("type").equals("sniper")){
						sniper.add(item);
					}else if(props.getProperty("type").equals("pumpgun")){
						pumpguns.add(item);
					}else if(props.getProperty("type").equals("mp")){
						mps.add(item);
					}else if(props.getProperty("type").equals("thrower")){
						throwers.add(item);
					}else if(props.getProperty("type").equals("mg")){
						mgs.add(item);
					}
				}
			}
			loaded = true;
		}
	}
	
	public class Weapon {
		public String name, type;
		public String ammo, ammoDisplayname, projectile;
		public int ammoCost;
		public float shootRate, damage, recoil;
	}
}
