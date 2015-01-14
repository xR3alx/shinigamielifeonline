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

import com.shinigami.configs.Configuration;
import com.shinigami.configs.PriceConfig;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class ToolShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack hoeItem, pickaxeItem, axeItem;
	
	public ToolShop(){
		title = "Werkzeugladen";
		size = 9;
		this.priceConfig = Configuration.getPriceConfig();
		createItems();
	}
	
	private void createItems() {
		
		hoeItem = new ItemStack(Material.IRON_HOE);
		ItemMeta hoeMeta = hoeItem.getItemMeta();
		hoeMeta.setDisplayName(Colors.WHITE + "Hacke" + Colors.BLACK + "  [I]");
		ArrayList<String> hoeLore = new ArrayList<String>();
		hoeLore.add(Colors.YELLOW + "Benötigt für Drogen, Äpfel und Melonen");
		hoeLore.add("");
		hoeLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("hoe"));
		hoeMeta.setLore(hoeLore);
		hoeItem.setItemMeta(hoeMeta);
		
		pickaxeItem = new ItemStack(Material.IRON_PICKAXE);
		ItemMeta pickaxeMeta = pickaxeItem.getItemMeta();
		pickaxeMeta.setDisplayName(Colors.WHITE + "Spitzhacke" + Colors.BLACK + "  [I]");
		ArrayList<String> pickaxeLore = new ArrayList<String>();
		pickaxeLore.add(Colors.YELLOW + "Benötigt für alle Erze und Salz");
		pickaxeLore.add("");
		pickaxeLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("pickaxe"));
		pickaxeMeta.setLore(pickaxeLore);
		pickaxeItem.setItemMeta(pickaxeMeta);
		
		axeItem = new ItemStack(Material.IRON_AXE);
		ItemMeta axeMeta = axeItem.getItemMeta();
		axeMeta.setDisplayName(Colors.WHITE + "Axt" + Colors.BLACK + "  [I]");
		ArrayList<String> axeLore = new ArrayList<String>();
		axeLore.add(Colors.YELLOW + "Benötigt für den Wald");
		axeLore.add("");
		axeLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("axe"));
		axeMeta.setLore(axeLore);
		axeItem.setItemMeta(axeMeta);
		
	}
	
	private void createInventory() {
		
		menu.addItem(hoeItem);
		menu.addItem(pickaxeItem);
		menu.addItem(axeItem);
	}
	
	@Override
	public void open(Player p, Session session) {
		if(!session.isLoggedInAsCop()){
			menu = Bukkit.createInventory(p, size, title);
			createInventory();
			p.openInventory(menu);
		}else{
			p.sendMessage(Colors.RED + "Polizisten können hier nicht einkaufen.");
		}
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.IRON_HOE && i.getItemMeta().getDisplayName().equals(hoeItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("hoe");
					boughtItem = new ItemStack(hoeItem);
				}else if(i.getType() == Material.IRON_PICKAXE && i.getItemMeta().getDisplayName().equals(pickaxeItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("pickaxe");
					boughtItem = new ItemStack(pickaxeItem);
				}else if(i.getType() == Material.IRON_AXE && i.getItemMeta().getDisplayName().equals(axeItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("axe");
					boughtItem = new ItemStack(axeItem);
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
						List<String> lore = meta.getLore();
						lore.remove(1);
						lore.remove(1);
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
