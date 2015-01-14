package com.shinigami.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class HouseShop extends Menu{
	
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack buyItem, sellItem;
	
	public HouseShop(){
		title = "Hauskauf";
		size = 9;
		createItems();
	}
	
	private void createItems() {
		
		buyItem = new ItemStack(Material.PAPER);
		ItemMeta houseMeta = buyItem.getItemMeta();
		houseMeta.setDisplayName(Colors.GREEN + "Haus kaufen" + Colors.BLACK + "  [I]");
		buyItem.setItemMeta(houseMeta);
		
		sellItem = new ItemStack(Material.PAPER);
		ItemMeta sellMeta = sellItem.getItemMeta();
		sellMeta.setDisplayName(Colors.RED + "Haus verkaufen" + Colors.BLACK + "  [I]");
		sellItem.setItemMeta(sellMeta);
		
	}
	
	private void createInventory(boolean owner, Player p) {
		Block bTarget = p.getTargetBlock(null, 50);
		int price = 0;
		
		if(bTarget.getType() == Material.SIGN_POST || bTarget.getType() == Material.WALL_SIGN){
			Sign s = (Sign) bTarget.getState();
			if(s.getLine(2) != null){
				price = Integer.parseInt(s.getLine(2));
			}else{
				p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte kontaktier einen Admin!");
			}
		}
		
		if(!owner){
			ItemMeta houseMeta = buyItem.getItemMeta();
			List<String> houseLore = new ArrayList<String>();
			houseLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + price);
			houseMeta.setLore(houseLore);
			buyItem.setItemMeta(houseMeta);
			
			menu.addItem(buyItem);
		}else{
			ItemMeta sellMeta = sellItem.getItemMeta();
			List<String> sellLore = new ArrayList<String>();
			sellLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + (price / 2));
			sellMeta.setLore(sellLore);
			sellItem.setItemMeta(sellMeta);
			
			menu.addItem(sellItem);
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		if(!session.isLoggedInAsCop()){
			menu = Bukkit.createInventory(p, size, title);
	
			Block b = p.getTargetBlock(null, 50);
			Sign s = (Sign) b.getState();
			if(s.getLine(0).equals("[Hauskauf]")){
				if(!s.getLine(1).equals("")){
					if(!s.getLine(2).equals("")){
						if(s.getLine(3).equals("")){
							createInventory(false, p);
						}else if(s.getLine(3).equals(p.getName())){
							createInventory(true, p);
						}else{
							p.sendMessage("§cDiese Haus §4gehört§c bereits jemandem!");
						}
					}
				}
			}
			p.openInventory(menu);
		}else{
			p.sendMessage(Colors.RED + "Polizisten können in diesem Laden nicht einkaufen.");
		}
	}
	
	@SuppressWarnings("unused")
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
				Block bTarget = p.getTargetBlock(null, 50);
				String housename = "";
				int price = 0;
				boolean bought = false;
				
				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(buyItem.getItemMeta().getDisplayName())){
					bought = true;

					if(bTarget.getType() == Material.SIGN_POST || bTarget.getType() == Material.WALL_SIGN){
						Sign s = (Sign) bTarget.getState();
						
						if(s.getLine(1) != null){
							housename = s.getLine(1);
						}else{
							p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte kontaktier einen Admin!");
						}
						
						if(s.getLine(2) != null){
							price = Integer.parseInt(s.getLine(2));
						}else{
							p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte kontaktier einen Admin!");
						}
					}else{
						p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte guck das Schild an, welches du angeklickt hast!");
						p.closeInventory();
					}
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(sellItem.getItemMeta().getDisplayName())){

					if(bTarget.getType() == Material.SIGN_POST || bTarget.getType() == Material.WALL_SIGN){
						Sign s = (Sign) bTarget.getState();
						
						if(s.getLine(1) != null){
							housename = s.getLine(1);
						}else{
							p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte kontaktier einen Admin!");
						}
						
						if(s.getLine(2) != null){
							price = Integer.parseInt(s.getLine(2)) / 2;
						}else{
							p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte kontaktier einen Admin!");
						}
					}else{
						p.sendMessage(Colors.DARK_RED + "Es ist ein Fehler aufgetreten. " + Colors.RED + "Bitte guck das Schild an, welches du angeklickt hast!");
						p.closeInventory();
					}
				}
				
				if(money >= cost){
					money-=cost;

					session.setMoneyPocket(money);
					
					if(housename != null){
						if(price != 0){
							if(bought){
								Sign s = (Sign) bTarget.getState();
								s.setLine(3, p.getName());
								s.update();
								p.sendMessage(Colors.DARK_GREEN + "Gratulation zum Hauskauf! " + Colors.GREEN + "Du bist nun der Glückliche besitzer von " + Colors.DARK_GREEN + housename);
								p.sendMessage(Colors.GREY + "Vor dem verkaufen, entferne alle von dir platzierten Blöcke. Nach dem verkaufen ist dies nicht mehr möglich!");
								ShinigamiLife.getHouseManager().buyHouse(housename, p, session);
								p.closeInventory();
							}else{
								Sign s = (Sign) bTarget.getState();
								s.setLine(3, "");
								s.update();
								p.sendMessage(Colors.RED + "Du hast das Haus " + Colors.DARK_RED + "verkauft" + Colors.RED + ".");
								ShinigamiLife.getHouseManager().sellHouse(housename, p, session);
								p.closeInventory();
							}
						}
					}
					
					if(boughtItem != null){
						inv.addItem(boughtItem);
					}
				}else{
					p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
				}
			}
		}
	}
}
