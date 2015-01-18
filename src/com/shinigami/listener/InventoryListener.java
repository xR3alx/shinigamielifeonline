package com.shinigami.listener;

import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class InventoryListener implements Listener{

	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClick(InventoryClickEvent event) {
		Player p = (Player) event.getWhoClicked();
		Inventory inv = p.getInventory();
		ItemStack i = event.getCurrentItem();
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(event.getInventory().getType() == InventoryType.PLAYER && event.getInventory().getType() != InventoryType.CREATIVE){
			if(session.getProcessing() != null){
				event.setCancelled(true);
			}
		}
		
		if(!session.getOpenMenu().equals("")){
			if(i != null && i.getType() != Material.AIR){
				if(i.hasItemMeta()){
					if(i.getItemMeta().getDisplayName().contains(Colors.BLACK + "  .")){
						ShinigamiLife.getMenuManager().checkEvent(p, inv, i, event, session);
						p.updateInventory();
					}
				}
			}
		}
		
		if(i != null){
			if(i.getType() == Material.getMaterial(52)){
				event.getView().setCursor(null);
				event.setCancelled(true);
			}else if(i.getType() == Material.SADDLE){
				event.getView().setCursor(null);
				event.setCancelled(true);
			}else if(i.getType() == Material.IRON_BARDING){
				event.getView().setCursor(null);
				event.setCancelled(true);
			}else if(i.getType() == Material.DIAMOND_BARDING){
				event.getView().setCursor(null);
				event.setCancelled(true);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onInventoryClose(InventoryCloseEvent event) {
		if(event.getInventory().getHolder() instanceof Player){
			Player p = (Player) event.getInventory().getHolder();
			Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
			
			if(event.getInventory().getTitle().equals("Käufer")){
				ShinigamiLife.getMenuManager().closeInventory(p);
			}else if(event.getInventory().getTitle().equals("Spawn")){
				session.setSpawned(true);
			}else if(event.getInventory().getTitle().equals("Seite")){
				session.setJoined(true);
//				session.setSide("civ");
			}
			
			session.setOpenMenu("");
		}
	}
	
}
