package com.shinigami.oldmenus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class PlayerMenu extends Menu{

	private Inventory menu, payMenu, playerMenu;
	private String title;
	private int size;
	
	private ItemStack moneyItem, payItem, channelItem, groupItem, licenceItem,
					pay1Item, pay5Item, pay10Item, pay50Item, pay100Item, pay250Item, pay500Item, pay1000Item, payAllItem;
	
	public PlayerMenu(){
		title = "Spielermenü";
		size = 9;
		createItems();
	}
	
	private void createItems(){
		
		moneyItem = new ItemStack(Material.CHEST);
		ItemMeta moneyMeta = moneyItem.getItemMeta();
		moneyMeta.setDisplayName(Colors.GREEN + "Geldbörse  " + Colors.BLACK + "  [I]");
		moneyItem.setItemMeta(moneyMeta);
		
		payItem = new ItemStack(Material.CHEST);
		ItemMeta payMeta = payItem.getItemMeta();
		payMeta.setDisplayName(Colors.DARK_GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		payItem.setItemMeta(payMeta);
		
		licenceItem = new ItemStack(Material.BOOK);
		
		channelItem = new ItemStack(Material.NETHER_BRICK_ITEM);
		ItemMeta channelMeta = channelItem.getItemMeta();
		channelMeta.setDisplayName(Colors.YELLOW + "Smartphone" + Colors.BLACK + "  [I]");
		channelItem.setItemMeta(channelMeta);
		
		groupItem = new ItemStack(Material.BOAT);
		ItemMeta groupMeta = groupItem.getItemMeta();
		groupMeta.setDisplayName(Colors.TURKISH + "Gruppe" + Colors.BLACK + "  [I]");
		groupItem.setItemMeta(groupMeta);
		
		pay1Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay1Meta = pay1Item.getItemMeta();
		pay1Meta.setDisplayName(Colors.DARK_GREEN + "1 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay1Item.setItemMeta(pay1Meta);
		
		pay5Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay5Meta = pay5Item.getItemMeta();
		pay5Meta.setDisplayName(Colors.DARK_GREEN + "5 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay5Item.setItemMeta(pay5Meta);
		
		pay10Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay10Meta = pay10Item.getItemMeta();
		pay10Meta.setDisplayName(Colors.DARK_GREEN + "10 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay10Item.setItemMeta(pay10Meta);
		
		pay50Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay50Meta = pay50Item.getItemMeta();
		pay50Meta.setDisplayName(Colors.DARK_GREEN + "50 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay50Item.setItemMeta(pay50Meta);
		
		pay100Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay100Meta = pay100Item.getItemMeta();
		pay100Meta.setDisplayName(Colors.DARK_GREEN + "100 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay100Item.setItemMeta(pay100Meta);
		
		pay250Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay250Meta = pay250Item.getItemMeta();
		pay250Meta.setDisplayName(Colors.DARK_GREEN + "250 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay250Item.setItemMeta(pay250Meta);
		
		pay500Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay500Meta = pay500Item.getItemMeta();
		pay500Meta.setDisplayName(Colors.DARK_GREEN + "500 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay500Item.setItemMeta(pay500Meta);
		
		pay1000Item = new ItemStack(Material.RECORD_6);
		ItemMeta pay1000Meta = pay1000Item.getItemMeta();
		pay1000Meta.setDisplayName(Colors.DARK_GREEN + "1000 " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		pay1000Item.setItemMeta(pay1000Meta);
		
		payAllItem = new ItemStack(Material.RECORD_6);
		ItemMeta payAllMeta = payAllItem.getItemMeta();
		payAllMeta.setDisplayName(Colors.DARK_GREEN + "Alles " + Colors.GREEN + "Überweisen" + Colors.BLACK + "  [I]");
		payAllItem.setItemMeta(payAllMeta);
		
	}
	
	private void createInventory(Session session, Player p) {
	
		ItemMeta moneyMeta = moneyItem.getItemMeta();
//		moneyMeta.setDisplayName(Colors.GREEN + "Geldbörse  " + session.getMoneyPocket() + Colors.BLACK + "  [I]");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Colors.DARK_GREEN + "Konto  " + session.getMoneyBank());
		moneyMeta.setLore(lore);
		moneyItem.setItemMeta(moneyMeta);
		
		ItemMeta licenceMeta = licenceItem.getItemMeta();
		licenceMeta.setDisplayName(Colors.TURKISH + Colors.UNDERLINE + "Lizenzen" + Colors.BLACK + "  [I]");
		ArrayList<String> licenceLore = new ArrayList<String>();
		for(String s : session.getLicences().keySet()){
//			licenceLore.add(Utils.changeLicences(s) + "  " + Utils.changeTruFalse(session.getLicence(s) + ""));
		}
		licenceMeta.setLore(licenceLore);
		licenceItem.setItemMeta(licenceMeta);
		
		menu.addItem(moneyItem);
		if(!session.isBanksClosed()){
			menu.addItem(payItem);
		}
		menu.addItem(licenceItem);
		menu.addItem(channelItem);
		menu.addItem(groupItem);
		
//		if(session.getMoneyPocket() >= 5){
//			payMenu.addItem(pay5Item);
//		}
//		if(session.getMoneyPocket() >= 10){
//			payMenu.addItem(pay10Item);
//		}
//		if(session.getMoneyPocket() >= 50){
//			payMenu.addItem(pay50Item);
//		}
//		if(session.getMoneyPocket() >= 100){
//			payMenu.addItem(pay100Item);
//		}
//		if(session.getMoneyPocket() >= 250){
//			payMenu.addItem(pay250Item);
//		}
//		if(session.getMoneyPocket() >= 500){
//			payMenu.addItem(pay500Item);
//		}
//		if(session.getMoneyPocket() >= 1000){
//			payMenu.addItem(pay1000Item);
//		}
		payMenu.addItem(payAllItem);
		
		ItemStack pi = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		SkullMeta skullMeta = (SkullMeta)pi.getItemMeta();
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			if(player != p){
				if(p.getLocation().getX() - player.getLocation().getX() < 15 && p.getLocation().getX() - player.getLocation().getX() > -15
						&& p.getLocation().getY() - player.getLocation().getY() < 15 && p.getLocation().getY() - player.getLocation().getY() > -15
						&& p.getLocation().getZ() - player.getLocation().getZ() < 15 && p.getLocation().getZ() - player.getLocation().getZ() > -15){
					skullMeta.setDisplayName(player.getName() + Colors.BLACK + "  [I]");
					skullMeta.setOwner(p.getName());
					ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
					playerItem.setItemMeta(skullMeta);
					playerMenu.addItem(new ItemStack(playerItem));
				}
			}
		}
		
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		payMenu = Bukkit.createInventory(p, size, title);
		playerMenu = Bukkit.createInventory(p, 54, title);
		createInventory(session, p);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(i.getType() == Material.NETHER_BRICK_ITEM && i.getItemMeta().getDisplayName().equals(channelItem.getItemMeta().getDisplayName())){
					p.closeInventory();
					ShinigamiLife.getMenuManager().open(p, session, "smartphone");
				}else if(i.getType() == Material.BOAT && i.getItemMeta().getDisplayName().equals(groupItem.getItemMeta().getDisplayName())){
					p.closeInventory();
					ShinigamiLife.getMenuManager().open(p, session, "groupmenu");
				}else if(i.getType() == Material.CHEST && i.getItemMeta().getDisplayName().equals(payItem.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(payMenu);
				}else if(i.getType() == Material.RECORD_6){
					if(i.getItemMeta().getDisplayName().contains("Überweisen")){
						if(i.getItemMeta().getDisplayName().equals(pay1Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(1);
						}else if(i.getItemMeta().getDisplayName().equals(pay5Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(5);
						}else if(i.getItemMeta().getDisplayName().equals(pay10Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(10);
						}else if(i.getItemMeta().getDisplayName().equals(pay50Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(50);
						}else if(i.getItemMeta().getDisplayName().equals(pay100Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(100);
						}else if(i.getItemMeta().getDisplayName().equals(pay250Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(250);
						}else if(i.getItemMeta().getDisplayName().equals(pay500Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(500);
						}else if(i.getItemMeta().getDisplayName().equals(pay1000Item.getItemMeta().getDisplayName())){
							session.setMoneyPayAmount(1000);
						}else if(i.getItemMeta().getDisplayName().equals(payAllItem.getItemMeta().getDisplayName())){
//							session.setMoneyPayAmount(session.getMoneyPocket());
						}
						p.closeInventory();
						p.openInventory(playerMenu);
					}
				}else if(i.getType() == Material.SKULL_ITEM){
					Player player = Bukkit.getServer().getPlayer(i.getItemMeta().getDisplayName());
					
					Session sessionT = ShinigamiLife.getSessionManager().getSession(player.getUniqueId() + "");
						
//					if(session.getMoneyPocket() >= session.getMoneyPayAmount()){
//						sessionT.setMoneyPocket(sessionT.getMoneyPocket()
//								+ session.getMoneyPayAmount());
//						session.setMoneyPocket(session.getMoneyPocket()
//								- session.getMoneyPayAmount());
//						
//						p.sendMessage(Colors.GREEN + "Du hast erfolgreich " + Colors.DARK_GREEN + session.getMoneyPayAmount() + Colors.GREEN + " an " + Colors.DARK_GREEN + player.getName() + Colors.GREEN + " überwiesen.");
//						player.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat dir " + Colors.DARK_GREEN + session.getMoneyPayAmount() + Colors.GREEN + " überwiesen!");
//						session.setMoneyPayAmount(0);
//					}else{
//						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei!");
//						session.setMoneyPayAmount(0);
//					}
					
					p.closeInventory();
				}
			}
		}
	}
	
}
