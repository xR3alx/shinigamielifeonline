package com.shinigami.menus;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.sessions.Session;
import com.shinigami.utils.DebugMessages;
import com.shinigami.utils.SellInventory;

public class MenuManager {

	private HashMap<String, Menu> menus;
	private ArrayList<SellInventory> sellInventories;
	
	public MenuManager(){
		sellInventories = new ArrayList<SellInventory>();
		menus = new HashMap<String, Menu>();
		
		boolean loaded = false;
		while(!loaded){
			menus.put("menu_account", new MenuAccount());
			
			menus.put("shop_clothing_tab1", new ShopClothing(1));
			menus.put("shop_clothing_tab2", new ShopClothing(2));
			
			loaded = true;
		}
	}
	
	@SuppressWarnings("null")
	public void checkMenu(Player p, Session session, String string){
		if(!string.equals("")){
			if(menus.containsKey(string)){
				Menu menu = menus.get(string);
				session.setOpenMenu(string);
				menu.open(p, session);
				
				DebugMessages.openMenu(session);
			}
		}
	}
	
	public void checkEvent(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(session.getOpenMenu() != null || !session.getOpenMenu().equals("")){
			Menu menu = menus.get(session.getOpenMenu());
			menu.event(p, inv, i, event, session);
			
			DebugMessages.clickMenu(inv, i, event, session);
		}
	}
	
	public void open(Player p, Session session, String name){
		Menu menu = menus.get(name);
		menu.open(p, session);
		session.setOpenMenu(name);
	}
	
	public Menu get(String name){
		return menus.get(name);
	}
	
	public HashMap<String, Menu> getMenus(){
		return menus;
	}
	
	
	
	public void addSellInvetory(SellInventory sellInventory){
		sellInventories.add(sellInventory);
	}
	
	public SellInventory getSellInventory(Player p){
		SellInventory inv = null;
		for(SellInventory s : sellInventories){
			if(s.name.equals(p.getUniqueId() + "")){
				inv = s;
				break;
			}
		}
		return inv;
	}
	
	public void closeInventory(Player p){
		for(SellInventory s : sellInventories){
			if(s.name.equals(p.getUniqueId() + "")){
				sellInventories.remove(s);
				break;
			}
		}
	}
}
