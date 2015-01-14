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
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;

public class FishingShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack fishingrodItem;
	
	public FishingShop(){
		title = "Fischerladen";
		size = 9;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		fishingrodItem = new ItemStack(Material.FISHING_ROD);
		ItemMeta fishingrodItemMeta = fishingrodItem.getItemMeta();
		fishingrodItemMeta.setDisplayName(Colors.WHITE + "Angel" + Colors.BLACK + "  [I]");
		List<String> fishingrodItemLore = new ArrayList<String>();
		fishingrodItemLore.add(" ");
		fishingrodItemLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("fishingrod"));
		fishingrodItemMeta.setLore(fishingrodItemLore);
		fishingrodItem.setItemMeta(fishingrodItemMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(fishingrodItem);
	}
	
	@Override
	public void open(Player p, Session session) {
		if(session.getLicence(Licence.FISHINGLICENCE)){
			menu = Bukkit.createInventory(p, size, title);
			createInventory();
			p.openInventory(menu);
		}else{
			p.sendMessage(Colors.GREY + "Du hast nicht die benötigte Lizenz.");
			ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {Licence.FISHINGLICENCE});
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

				if(i.getItemMeta().getDisplayName().equals(fishingrodItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("fishingrod");
					boughtItem = new ItemStack(fishingrodItem);
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
