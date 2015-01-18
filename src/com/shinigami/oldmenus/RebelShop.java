package com.shinigami.oldmenus;

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
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;

public class RebelShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack pruegelstockItem, fesselItem;
	
	public RebelShop(){
		title = "Rebellenladen";
		size = 18;
		this.priceConfig = Configuration.getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		pruegelstockItem = new ItemStack(Material.STICK);
		ItemMeta pruegelstockMeta = pruegelstockItem.getItemMeta();
		pruegelstockMeta.setDisplayName(Colors.DARK_RED + "Pruegelstock" + Colors.BLACK + "  [I]");
		List<String> pruegelstockLore = new ArrayList<String>();
		pruegelstockLore.add(Colors.RED + "Rechtsklick auf einen");
		pruegelstockLore.add(Colors.RED + "Spieler um ihn");
		pruegelstockLore.add(Colors.RED + "auszurauben.");
		pruegelstockLore.add(" ");
		pruegelstockLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("pruegelstock"));
		pruegelstockMeta.setLore(pruegelstockLore);
		pruegelstockItem.setItemMeta(pruegelstockMeta);
		
		fesselItem = new ItemStack(Material.LEASH);
		ItemMeta fesselMeta = fesselItem.getItemMeta();
		fesselMeta.setDisplayName(Colors.DARK_RED + "Fessel" + Colors.BLACK + "  [I]");
		List<String> fesselLore = new ArrayList<String>();
		fesselLore.add(Colors.RED + "Rechtsklick auf einen");
		fesselLore.add(Colors.RED + "Spieler um ihn");
		fesselLore.add(Colors.RED + "zu fesseln.");
		fesselLore.add(" ");
		fesselLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("fessel"));
		fesselMeta.setLore(fesselLore);
		fesselItem.setItemMeta(fesselMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(pruegelstockItem);
		menu.addItem(fesselItem);
	}
	
	@Override
	public void open(Player p, Session session) {
//		if(session.getLicence(Licence.REBELLICENCE)){
//			menu = Bukkit.createInventory(p, size, title);
//			createInventory();
//			p.openInventory(menu);
//		}else{
//			p.sendMessage(Colors.GREY + "Du hast nicht die benötigte Lizenz.");
//			ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {Licence.REBELLICENCE});
//		}
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.STICK && i.getItemMeta().getDisplayName().equals(pruegelstockItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("pruegelstock");

					boughtItem = new ItemStack(pruegelstockItem);
				}else if(i.getType() == Material.LEASH && i.getItemMeta().getDisplayName().equals(fesselItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("fessel");

					boughtItem = new ItemStack(fesselItem);
				}
				
//				if(boughtItem != null){
//					if(money >= cost){
//						money-=cost;
//	
//						session.setMoneyPocket(money);
//						if(session.getClickedNpc() != null){
//							session.getClickedNpc().money+=cost;
//						}
//						
//						ItemMeta boughtItemMeta = boughtItem.getItemMeta();
//						boughtItemMeta.setDisplayName(boughtItemMeta.getDisplayName().replace(Colors.BLACK + "  [I]", ""));
//						boughtItemMeta.getLore().remove(boughtItemMeta.getLore().size()-1);
//						boughtItemMeta.getLore().remove(boughtItemMeta.getLore().size()-1);
//						
//						inv.addItem(boughtItem);
//					}else{
//						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
//					}
//				}
			}
		}
	}
}
