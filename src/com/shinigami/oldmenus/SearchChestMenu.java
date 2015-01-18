package com.shinigami.oldmenus;

import org.bukkit.Bukkit;
import org.bukkit.block.Chest;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.configs.Configuration;
import com.shinigami.configs.PriceConfig;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class SearchChestMenu extends Menu{

	private PriceConfig priceConfig;
	private Inventory impoundInventory;
	private String title;
	private int size;
	
	
	public SearchChestMenu(){
		this.priceConfig = Configuration.getPriceConfig();
		title = "Durchsuchen (Kiste)";
		size = 54;
	}

	
	private void createInventory(Session session) {
		for(ItemStack i : session.getSearchedChest().getBlockInventory().getContents()){
			if(i != null){
				if(i.hasItemMeta()){
					if(!i.getItemMeta().getDisplayName().contains("Spielermenü") && !i.getItemMeta().getDisplayName().contains("Polizeimenü")){
						impoundInventory.addItem(i);
					}
				}
			}
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		impoundInventory = Bukkit.createInventory(p, size, title);
		createInventory(session);
		p.openInventory(impoundInventory);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			
			int impoundedMoney = 0;
			
			String itemName = Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true);
			if(priceConfig.sellPrices.containsKey(itemName)){
				impoundedMoney = priceConfig.sellPrices.get(itemName) * i.getAmount();
				impoundedMoney = impoundedMoney * 15 / 100;
				p.sendMessage(Colors.GREY + "Du hast " + Colors.WHITE + i.getItemMeta().getDisplayName() + Colors.GREY + " x" + Colors.WHITE + i.getAmount() + Colors.GREY + " beschlagnahmt und " + Colors.WHITE + impoundedMoney + Colors.GREY + " als Belohnung bekommen.");
			}
			
			impoundInventory.remove(i);
			Chest c = session.getSearchedChest();
			c.getBlockInventory().remove(i);
		}
	}
	
}
