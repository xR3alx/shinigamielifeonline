package com.shinigami.oldmenus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.configs.Configuration;
import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class SearchHorseMenu extends Menu{
	
	private PriceConfig priceConfig;
	private Inventory menu, impoundInventory;
	private String title;
	private int size;
	
	private ItemStack ownerItem, stolenItem, lockedItem, impoundItems, impoundItem;
	
	public SearchHorseMenu(){
		this.priceConfig = Configuration.getPriceConfig();
		title = "Durchsuchen (Pferd)";
		size = 9;
		createItems();
	}
	
	private void createItems() {

		ownerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		stolenItem = new ItemStack(Material.TRIPWIRE_HOOK);
		lockedItem = new ItemStack(Material.LEASH);
		
		impoundItems = new ItemStack(Material.CHEST);
		ItemMeta impoundItemsMeta = impoundItems.getItemMeta();
		impoundItemsMeta.setDisplayName("Items im Inventar beschlagnahmen" + Colors.BLACK + "  [I]");
		impoundItems.setItemMeta(impoundItemsMeta);

		impoundItem = new ItemStack(Material.CHEST);
		ItemMeta impoundMeta = impoundItem.getItemMeta();
		impoundMeta.setDisplayName("Beschlagnahmen" + Colors.BLACK + "  [I]");
		impoundItem.setItemMeta(impoundMeta);
		
	}
	
	private void createInventory(Session session) {
		
		Horse h = session.getSearchedHorse();
		
		ItemMeta ownerMeta = ownerItem.getItemMeta();
		ownerMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Besitzer: " + Colors.RESET + h.getMetadata("owner").get(0).asString() + Colors.BLACK + "  [I]");
		ownerItem.setItemMeta(ownerMeta);
		
		ItemMeta stolenMeta = stolenItem.getItemMeta();
		stolenMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Gestohlen: " + Colors.RESET + Utils.changeTruFalse(h.getMetadata("stolen").get(0).asString()) + Colors.BLACK + "  [I]");
		stolenItem.setItemMeta(stolenMeta);
		
		ItemMeta lockedMeta = lockedItem.getItemMeta();
		lockedMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Abgeschlossen: " + Colors.RESET + Utils.changeTruFalse(h.getMetadata("locked").get(0).asString()) + Colors.BLACK + "  [I]");
		lockedItem.setItemMeta(lockedMeta);
		
		menu.addItem(ownerItem);
		menu.addItem(stolenItem);
		menu.addItem(lockedItem);
		menu.addItem(impoundItems);
		menu.addItem(impoundItem);
		
		for(ItemStack i : h.getInventory().getContents()){
			if(i != null){
				if(i.getType() != Material.SADDLE && i.getType() != Material.IRON_BARDING && i.getType() != Material.DIAMOND_BARDING){
					if(i.hasItemMeta()){
						if(!i.getItemMeta().getDisplayName().contains("Spielermenü") && !i.getItemMeta().getDisplayName().contains("Polizeimenü")){
							impoundInventory.addItem(i);
						}
					}
				}
			}
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		impoundInventory = Bukkit.createInventory(p, 36, "Items beschlagnahmen (Pferd)");
		createInventory(session);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				
				if(i.getItemMeta().getDisplayName().equals(impoundItems.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(impoundInventory);
				}else if(i.getItemMeta().getDisplayName().equals(impoundItem.getItemMeta().getDisplayName())){
					Horse h = session.getSearchedHorse();
					Player owner = (Player) h.getOwner();
					
					Session sessionT = ShinigamiLife.getSessionManager().getSession(owner.getUniqueId() + "");
					
					sessionT.getGarageLand().garageContent.put(Utils.changeDisplayname(h.getInventory().getSaddle().getItemMeta().getDisplayName(), false), true);
					h.remove();
					
					p.sendMessage(Colors.GREY + "Du hast das Pferd beschlagnahmt.");
					session.setSearchedHorse(null);
					p.closeInventory();
				}else{
					int impoundedMoney = 0;
					
					String itemName = Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true);
					if(priceConfig.sellPrices.containsKey(itemName)){
						impoundedMoney = priceConfig.sellPrices.get(itemName) * i.getAmount();
						impoundedMoney = impoundedMoney * 15 / 100;
						p.sendMessage(Colors.GREY + "Du hast " + Colors.WHITE + i.getItemMeta().getDisplayName() + Colors.GREY + " x" + Colors.WHITE + i.getAmount() + Colors.GREY + " beschlagnahmt und " + Colors.WHITE + impoundedMoney + Colors.GREY + " als Belohnung bekommen.");
					}
					
					impoundInventory.remove(i);
					Horse h = session.getSearchedHorse();
					h.getInventory().remove(i);
				}
			}
		}
	}

}
