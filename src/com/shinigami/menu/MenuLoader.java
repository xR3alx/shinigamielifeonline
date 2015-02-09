package com.shinigami.menu;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;

import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class MenuLoader {

	public File itemDir;
	
	public MenuLoader(){
		
		itemDir = new File("MinecraftLife/db/menus");
		if(!itemDir.exists()){
			itemDir.mkdirs();
		}
	}
	
	public void load(HashMap<String, Menu> menus){
		boolean loaded = false;
		while(!loaded){
			if(itemDir.listFiles().length != 0){
				for(File f : itemDir.listFiles()){
					Properties props = Utils.loadFile(f);
					
					InventoryType invType = null;
					int size = 0;
					String title = "";
					
					if(props.containsKey("inventorytype")){
						invType = InventoryType.valueOf(props.getProperty("inventorytype"));
					}else{
						if(props.containsKey("size")){
							size = Integer.parseInt(props.getProperty("size"));
						}
					}
					if(props.containsKey("title")){
						title = props.getProperty("title");
					}
					
					ArrayList<MenuSlotInformation> slotInformations = new ArrayList<MenuSlotInformation>();
					
					for(int i = 0; i<size; i++){
						if(props.containsKey(i+".material")){
							ItemStack itemStack = new ItemStack(Material.getMaterial(props.getProperty(i+".material", "BARRIER").split(":")[0]), 1, (short) (Integer.parseInt(props.getProperty(i+".material", "BARRIER").split(":")[1])));
							String displayname = props.getProperty(i+".displayname", Colors.RED + "Not found!");
							String[] loreWithoutPrice = props.getProperty(i+".lore", "Not°Found°!").split(";");
							String[] enchantments = props.getProperty(i+".enchantments", "").split(";");
							Color color = Color.fromRGB(Integer.parseInt(props.getProperty(i+".colorR", "255")), Integer.parseInt(props.getProperty(i+".colorG", "255")), Integer.parseInt(props.getProperty(i+".colorB", "255")));
							boolean closeInventoryAfterClick = Boolean.parseBoolean(props.getProperty(i+".closeInventoryAfterClick")),
									addItemToInventory = Boolean.parseBoolean(props.getProperty(i+".addItemToInventory"));
							int removeMoney = Integer.parseInt(props.getProperty(i+".removeMoney", "0")),
								addMoney = Integer.parseInt(props.getProperty(i+".addMoney", "0")),
								slot = i,
								durabilityInPercent = Integer.parseInt(props.getProperty(i+".durabilityInPercent", "100"));
							String changeMenuTo = props.getProperty(i+".changeMenuTo", ""),
								removeMoneyPriceConfig = props.getProperty(i+".removeMoneyPriceConfig", ""),
								addMoneyPriceConfig = props.getProperty(i+".addMoneyPriceConfig", "");
							
							MenuSlotInformation slotInformation = new MenuSlotInformation(itemStack, displayname, loreWithoutPrice, enchantments, color, closeInventoryAfterClick, addItemToInventory, removeMoney, addMoney, slot, durabilityInPercent, changeMenuTo, removeMoneyPriceConfig, addMoneyPriceConfig);
							slotInformations.add(slotInformation);
						}
					}
					
					menus.put(f.getName().replace(".txt", ""), new MenuFromFile(invType, size, title, slotInformations));
				}
			}
			loaded = true;
		}
	}
	
}
