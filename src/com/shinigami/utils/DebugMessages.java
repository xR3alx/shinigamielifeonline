package com.shinigami.utils;

import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.npcs.NPCManager.NPC;
import com.shinigami.sessions.Session;


public class DebugMessages {

	private static boolean debug;
	
	public static void setDebug(boolean bool){
		debug = bool;
	}
	

	public static void npcCreateMessage(NPC npc){
		if(debug){
			System.out.println("-------|  NPC created!  |-------");
			System.out.println("Name:  " + npc.getVillager().getCustomName());
			System.out.println("ID:  " + npc.id);
			System.out.println("Menu:  " + npc.menu);
			System.out.println("Location:  " + npc.npcLoc);
			System.out.println("--------------------------------");
		}
	}
	
	public static void openMenu(Session session){
		if(debug){
			System.out.println("-------|  Menu open!  |-------");
			System.out.println("Name:  " + session.getPlayerName());
			System.out.println("Menu:  " + session.getOpenMenu());
			System.out.println("--------------------------------");
		}
	}
	
	public static void clickMenu(Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(debug){
			System.out.println("---------| Menu clicked! |---------");
			System.out.println("Name:  " + session.getPlayerName());
			System.out.println("Menu:  " + session.getOpenMenu());
			System.out.println("Item:  " + i);
			System.out.println("Inventory:  " + inv);
			System.out.println("-----------------------------------");
		}
	}
}
