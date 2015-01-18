package com.shinigami.menus;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;
import com.shinigami.utils.DebugMessages;
import com.shinigami.utils.SellInventory;

public class MenuManager {

	private HashMap<String, Menu> menus;
	private ArrayList<SellInventory> sellInventories;
	
	public MenuManager(){
		menus = new HashMap<String, Menu>();
		
		boolean loaded = false;
		while(!loaded){
			menus.put("account", new AccountMenu());
			
			loaded = true;
		}
	}
	
	@SuppressWarnings("null")
	public void checkMenu(Player p, Session session, Entity npc){
		String menuName = npc.getMetadata("menu").get(0).asString();
			
		if(menuName != null || !menuName.equals("")){
			Menu menu = menus.get(menuName);
			session.setOpenMenu(menuName);
			menu.open(p, session);
			
			DebugMessages.openMenu(session);
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
