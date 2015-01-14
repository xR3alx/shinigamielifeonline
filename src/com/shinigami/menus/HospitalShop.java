package com.shinigami.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class HospitalShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack medKitItem;
	
	public HospitalShop(){
		title = "Apotheke";
		size = 9;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		medKitItem = new ItemStack(Material.NAME_TAG);
		ItemMeta medKitMeta = medKitItem.getItemMeta();
		medKitMeta.setDisplayName(Colors.WHITE + "Arzttasche" + Colors.BLACK + "  [I]");
		List<String> medKitLore = new ArrayList<String>();
		medKitLore.add(" ");
		medKitLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + priceConfig.buyPrices.get("medkit"));
		medKitMeta.setLore(medKitLore);
		medKitItem.setItemMeta(medKitMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(medKitItem);
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory();
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
				
				int money = session.getMoneyPocket();

				if(i.getItemMeta().getDisplayName().equals(medKitItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("medkit");
					boughtItem = new ItemStack(medKitItem);
				}
				
				if(boughtItem != null){
					if(money >= cost){
						money-=cost;
	
						session.setMoneyPocket(money);
						if(session.getClickedNpc() != null){
							session.getClickedNpc().money+=cost;
						}
						
						ItemMeta meta = boughtItem.getItemMeta();
						meta.setDisplayName(meta.getDisplayName().replace(Colors.BLACK + "  [I]", ""));
						meta.setLore(null);
						boughtItem.setItemMeta(meta);
						
						inv.addItem(boughtItem);
					}else{
						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
					}
				}
			}
		}
	}
	
}
