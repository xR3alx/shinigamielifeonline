package com.shinigami.oldmenus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Boat;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class BoatShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title, type;
	private int size;
	
	private ItemStack boatItem, speedboatItem;
	
	public BoatShop(String type){
		title = "Bootsladen";
		this.type = type;
		size = 9;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		boatItem = new ItemStack(Material.BOAT);
		ItemMeta boatItemMeta = boatItem.getItemMeta();
		boatItemMeta.setDisplayName("Boot" + Colors.BLACK + "  [I]");
		List<String> boatItemLore = new ArrayList<String>();
		boatItemLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("boat"));
		boatItemMeta.setLore(boatItemLore);
		boatItem.setItemMeta(boatItemMeta);
		
		
		speedboatItem = new ItemStack(Material.BOAT);
		ItemMeta speedboatItemMeta = speedboatItem.getItemMeta();
		speedboatItemMeta.setDisplayName("Schnellboot" + Colors.BLACK + "  [I]");
		List<String> speedboatItemLore = new ArrayList<String>();
		speedboatItemLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("speedboat"));
		speedboatItemMeta.setLore(speedboatItemLore);
		speedboatItem.setItemMeta(speedboatItemMeta);
		
	}
	
	private void createInventory() {
		
		menu.addItem(boatItem);
		
		if(!type.equals("civ")){
			menu.addItem(speedboatItem);
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory();
		p.openInventory(menu);
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.BOAT && i.getItemMeta().getDisplayName().equals(boatItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("boat");
				}else if(i.getType() == Material.BOAT && i.getItemMeta().getDisplayName().equals(speedboatItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("speedboat");
				}
				
				
//				if(money >= cost){
//					money-=cost;
//					
//					session.setMoneyPocket(money);
//					if(session.getClickedNpc() != null){
//						session.getClickedNpc().money+=cost;
//					}
//					
//					spawnBoat(i, p, session);
//				}else{
//					p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
//				}
			}
		}
	}
	
	public void spawnBoat(ItemStack i, Player p, Session session){
		
		Location loc = p.getLocation();
		Boat b = (Boat) p.getWorld().spawnEntity(loc, EntityType.BOAT);
		b.setMetadata("owner", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), p.getName()));
		b.setMetadata("locked", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "true"));
		b.setMetadata("stolen", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "false"));
		
		if(i.getItemMeta().getDisplayName().equals(speedboatItem.getItemMeta().getDisplayName())){
			b.setMetadata("speedboat", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "true"));
		}else{
			b.setMetadata("speedboat", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "false"));
		}
		
		p.getInventory().remove(i);
		session.getBoats().add(b);
	}
	
}
