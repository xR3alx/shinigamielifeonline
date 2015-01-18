package com.shinigami.oldmenus;

import java.util.ArrayList;

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

public class BackpackShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack increaseBackpackItem;
	
	public BackpackShop(){
		title = "Rucksackladen";
		size = 9;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {
		
		increaseBackpackItem = new ItemStack(Material.HOPPER);
		ItemMeta increaseBackpackMeta = increaseBackpackItem.getItemMeta();
		increaseBackpackMeta.setDisplayName(Colors.WHITE + "Rucksackerweiterung" + Colors.BLACK + "  [I]");
		ArrayList<String> increaseBackpackLore = new ArrayList<String>();
		increaseBackpackLore.add(Colors.YELLOW + "Fügt 9 Plätze zum Inventar hinzu");
		increaseBackpackLore.add("");
		increaseBackpackLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("backpackIncrease"));
		increaseBackpackMeta.setLore(increaseBackpackLore);
		increaseBackpackItem.setItemMeta(increaseBackpackMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(increaseBackpackItem);
	}
	
	@Override
	public void open(Player p, Session session) {
//		if(session.isLoggedInAsCop()){
//			if(session.getInvSizeC() < 27){
//				menu = Bukkit.createInventory(p, size, title);
//				createInventory();
//				p.openInventory(menu);
//			}else{
//				p.sendMessage(Colors.RED + "Du kannst deinen Rucksack nicht mehr erweitern.");
//			}
//		}else{
//			if(session.getInvSize() < 27){
//				menu = Bukkit.createInventory(p, size, title);
//				createInventory();
//				p.openInventory(menu);
//			}else{
//				p.sendMessage(Colors.RED + "Du kannst deinen Rucksack nicht mehr erweitern.");
//			}
//		}
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				int slots = session.getInvSize();
//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.HOPPER && i.getItemMeta().getDisplayName().equals(increaseBackpackItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("backpackIncrease");
					slots+=9;
				}
				
//				if(slots != session.getInvSize()){
//					if(money >= cost){
//						money-=cost;
//	
//						session.setMoneyPocket(money);
//						if(session.getClickedNpc() != null){
//							session.getClickedNpc().money+=cost;
//						}
//						
//						session.setInvSize(slots);
//						ShinigamiLife.getSessionManager().changeInv(session, p);
//						
//						if(slots == 27){
//							p.closeInventory();
//						}
//					}else{
//						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
//					}
//				}
			}
		}
	}

}
