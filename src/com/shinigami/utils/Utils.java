package com.shinigami.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;

import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;

public class Utils {

	public static String changeDisplayname(String itemName, boolean toLowerCase){
		String newString = itemName.replace("§4", "")
								.replace("§c", "")
								.replace("§e", "")
								.replace("§6", "")
								.replace("§2", "")
								.replace("§a", "")
								.replace("§b", "")
								.replace("§3", "")
								.replace("§1", "")
								.replace("§9", "")
								.replace("§d", "")
								.replace("§5", "")
								.replace("§f", "")
								.replace("§7", "")
								.replace("§8", "")
								.replace("§0", "")
								.replace("§r", "")
								.replace("\u00A74", "")
								.replace("\u00A7c", "")
								.replace("\u00A7e", "")
								.replace("\u00A76", "")
								.replace("\u00A72", "")
								.replace("\u00A7a", "")
								.replace("\u00A7b", "")
								.replace("\u00A73", "")
								.replace("\u00A71", "")
								.replace("\u00A79", "")
								.replace("\u00A7d", "")
								.replace("\u00A75", "")
								.replace("\u00A7f", "")
								.replace("\u00A77", "")
								.replace("\u00A78", "")
								.replace("\u00A70", "")
								.replace(" ", "_");
		if(toLowerCase){
			newString = newString.toLowerCase();
		}
		return newString;
	}
	
	public static String changeNumbers(String s){
		return s.replace("1", "")
				.replace("2", "")
				.replace("3", "")
				.replace("4", "")
				.replace("5", "")
				.replace("6", "")
				.replace("7", "")
				.replace("8", "")
				.replace("9", "");
	}
	
	public static String changeLicences(String s){
		return s.replace("ridinglicence", "Reitlizenz")
				.replace("boatlicence", "Bootslizenz")
				.replace("weaponlicence", "Waffenlizenz")
				.replace("rebellicence", "Rebellentraining")
				.replace("ironmininglicence", "Eisenlizenz")
				.replace("diamondmininglicence", "Diamantenlizenz")
				.replace("redstonemininglicence", "Rotsteinlizenz")
				.replace("coalmininglicence", "Kohlelizenz")
				.replace("smaragdmininglicence", "Smaragdlizenz")
				.replace("goldmininglicence", "Goldlizenz")
				.replace("fishinglicence", "Fischerlizenz")
				.replace("huntinglicence", "Jagdschein")
				.replace("courierlicence", "Kurierlizenz")
				.replace("woodcutterlicence", "Holzlizenz")
				.replace("saltmininglicence", "Salzlizenz");
	}
	
	public static double getLivingEntityHealth(LivingEntity p){
		double health = 0;
		try {
			health = (double)p.getClass().getMethod("getHealth", double.class).invoke(p);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return health;
	}
	
	public static double getLivingEntityMaxHealth(LivingEntity p){
		double health = 0;
		try {
			health = (double)p.getClass().getMethod("getMaxHealth", double.class).invoke(p);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
		return health;
	}
	
	public static String changeTruFalse(String s){
		return s.replace("true", "Ja").replace("false", "Nein");
	}
	
	public static String changeSpaces(String itemName, boolean toLowerCase){
		String newString = itemName.replace("_", " ");
		if(toLowerCase){
			newString = newString.toLowerCase();
		}
		return newString;
	}
	
	public static Properties loadFile(File f){
		Properties props = new Properties();
		
		try {
			FileInputStream fileIn = new FileInputStream(f);
			props.load(fileIn);
			fileIn.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return props;
	}
	
}
