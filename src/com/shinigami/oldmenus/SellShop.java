package com.shinigami.oldmenus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
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
import com.shinigami.utils.SellInventory;
import com.shinigami.utils.Utils;

public class SellShop extends Menu{
	
	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	private ArrayList<String> itemnames;
	
	public SellShop(ArrayList<String> itemnames){
		title = "Käufer";
		size = 18;
		this.priceConfig = Configuration.getPriceConfig();
	}
	
	private void createInventory(Player p) {
		
		SellInventory inv = new SellInventory();
		inv.name = p.getUniqueId() + "";
		for(ItemStack i : p.getInventory().getContents()){
			if(i != null){
				if(i.hasItemMeta()){
					if(itemnames.contains(i.getItemMeta().getDisplayName())){
						String itemName = Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true);
						
						if(priceConfig.sellPrices.containsKey(itemName)){
							inv.items.add(i);
							
							ItemStack sellItem = new ItemStack(i);
							ItemMeta sellMeta = i.getItemMeta();
							ArrayList<String> lore = new ArrayList<String>();
							if(i.getItemMeta().getLore() != null){
								lore.addAll(i.getItemMeta().getLore());
								lore.add(" ");
							}
							lore.add(Colors.DARK_GREEN + "Verkaufspreis: " + Colors.GREEN + priceConfig.sellPrices.get(itemName.toLowerCase()));
							sellMeta.setLore(lore);
							sellItem.setItemMeta(sellMeta);
							
							menu.addItem(sellItem);
						}
					}
				}
			}
		}
		ShinigamiLife.getMenuManager().addSellInvetory(inv);
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory(p);
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
				
				SellInventory items = ShinigamiLife.getMenuManager().getSellInventory(p);
				
				ItemStack remove = null;
				for(ItemStack itemstack : items.items){
					if(itemstack.getItemMeta().getDisplayName().equals(i.getItemMeta().getDisplayName())){
						String itemName = Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true);
						
						cost = priceConfig.sellPrices.get(itemName);
						remove = itemstack;
						break;
					}
				}
				
				if(remove != null){
					if(cost != 0){
						boolean conti = false;
						for(String s : i.getItemMeta().getLore()){
							if(s.contains(Colors.DARK_GREEN + "Verkaufspreis")){
								conti = true;
								break;
							}
						}
						if(conti){
							if(event.isShiftClick() && event.isRightClick()){
								if(i.getItemMeta().getDisplayName().contains("unverarbeitet") || i.getItemMeta().getDisplayName().contains("roher")){
									if(i.getItemMeta().getDisplayName().contains("kokain") || i.getItemMeta().getDisplayName().contains("marihuana")  || i.getItemMeta().getDisplayName().contains("heroin")){
										ShinigamiLife.getEcoSystem().addUnprocessedDrug(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true), i.getAmount());
									}else{
										ShinigamiLife.getEcoSystem().addUnprocessed(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true), i.getAmount());
									}
								}else{
									if(i.getItemMeta().getDisplayName().contains("kokain") || i.getItemMeta().getDisplayName().contains("marihuana")  || i.getItemMeta().getDisplayName().contains("heroin")){
										ShinigamiLife.getEcoSystem().addProcessedDrug(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true), i.getAmount());
									}else{
										ShinigamiLife.getEcoSystem().addProcessed(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true), i.getAmount());
									}
								}
								for(int x = 0; x<p.getInventory().getContents().length; x++){
									ItemStack itemstack = p.getInventory().getContents()[x];
									if(itemstack != null){
										if(itemstack != null && itemstack.hasItemMeta()){
											if(itemstack.getItemMeta().getDisplayName().equals(i.getItemMeta().getDisplayName())){
												p.getInventory().setItem(x, null);
												break;
											}
										}
									}
								}
								menu.setItem(event.getSlot(), null);
								
//								money+=cost*i.getAmount();
								p.sendMessage(Colors.WHITE + "Du hast " + Colors.DARK_GREEN + i.getAmount() + Colors.WHITE + "x " + Colors.DARK_GREEN + i.getItemMeta().getDisplayName() + Colors.WHITE + " für " + Colors.DARK_GREEN + cost*i.getAmount() + Colors.WHITE + " vekauft.");
//								session.setMoneyPocket(money);
							}else if(event.isLeftClick()){
								if(i.getAmount() > 1){
									if(i.getItemMeta().getDisplayName().contains("unverarbeitet") || i.getItemMeta().getDisplayName().contains("roher")){
										ShinigamiLife.getEcoSystem().addUnprocessed(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true), 1);
									}else{
										ShinigamiLife.getEcoSystem().addProcessed(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true), 1);
									}
									i.setAmount(i.getAmount()-1);;
			
									for(ItemStack itemstack : p.getInventory().getContents()){
										if(itemstack != null && itemstack.hasItemMeta()){
											if(itemstack.getItemMeta().getDisplayName().equals(i.getItemMeta().getDisplayName())){
												if(itemstack.getAmount() > 1){
													itemstack.setAmount(i.getAmount());
												}else{
													p.getInventory().remove(itemstack);
												}
												break;
											}
										}
									}
									
									p.sendMessage(Colors.WHITE + "Du hast " + Colors.DARK_GREEN + i.getAmount() + Colors.WHITE + "x " + Colors.DARK_GREEN + i.getItemMeta().getDisplayName() + Colors.WHITE + " für" + Colors.DARK_GREEN + cost + Colors.WHITE + " vekauft.");
//									money+=cost;
//									session.setMoneyPocket(money);
								}
							}
						}
					}
				}
			}
		}
	}
}
