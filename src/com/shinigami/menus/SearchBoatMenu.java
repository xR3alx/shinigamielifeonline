package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class SearchBoatMenu extends Menu{

	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack ownerItem, stolenItem, lockedItem, impoundItems, impoundItem;
	
	public SearchBoatMenu(){
		title = "Durchsuchen (Boot)";
		size = 9;
		createItems();
	}
	
	private void createItems() {

		ownerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		stolenItem = new ItemStack(Material.TRIPWIRE_HOOK);
		lockedItem = new ItemStack(Material.LEASH);

		impoundItem = new ItemStack(Material.CHEST);
		ItemMeta impoundMeta = impoundItem.getItemMeta();
		impoundMeta.setDisplayName("Beschlagnahmen" + Colors.BLACK + "  [I]");
		impoundItem.setItemMeta(impoundMeta);
		
	}
	
	private void createInventory(Session session) {
		
		Boat b = session.getSearchedBoat();
		
		ItemMeta ownerMeta = ownerItem.getItemMeta();
		ownerMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Besitzer: " + Colors.RESET + b.getMetadata("owner").get(0).asString() + Colors.BLACK + "  [I]");
		ownerItem.setItemMeta(ownerMeta);
		
		ItemMeta stolenMeta = stolenItem.getItemMeta();
		stolenMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Gestohlen: " + Colors.RESET + Utils.changeTruFalse(b.getMetadata("stolen").get(0).asString()) + Colors.BLACK + "  [I]");
		stolenItem.setItemMeta(stolenMeta);
		
		ItemMeta lockedMeta = lockedItem.getItemMeta();
		lockedMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Abgeschlossen: " + Colors.RESET + Utils.changeTruFalse(b.getMetadata("locked").get(0).asString()) + Colors.BLACK + "  [I]");
		lockedItem.setItemMeta(lockedMeta);
		
		menu.addItem(ownerItem);
		menu.addItem(stolenItem);
		menu.addItem(lockedItem);
		menu.addItem(impoundItem);
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory(session);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				
				if(i.getItemMeta().getDisplayName().equals(impoundItem.getItemMeta().getDisplayName())){
					Boat b = session.getSearchedBoat();
					Player owner = Bukkit.getPlayer(b.getMetadata("owner").get(0).asString());
					
					Session sessionT = ShinigamiLife.getSessionManager().getSession(owner.getUniqueId() + "");
					
					sessionT.getGarageWater().garageContent.put("Boot", true);
					b.remove();
					
					p.sendMessage(Colors.GREY + "Du hast das Boot beschlagnahmt.");
					session.setSearchedBoat(null);
					p.closeInventory();
				}
			}
		}
	}
	
}
