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
import com.shinigami.police.TicketManager.Ticket;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class SmartphoneMenu extends Menu{

	private Inventory menu, fastAlertMenu, slowAlertMenu1, slowAlertMenu2, slowAlertMenu3;
	private String title;
	private int size;
	
	private ItemStack localChannel, groupChannel, helpChannel, globalChannel, sendFastAlert, sendSlowAlert,
						alertPriority1, alertPriority2, alertPriority3, alertPriority4, alertPriority5, alertPriority6,
						alertWeaponNone, alertWeaponPistole, alertWeaponPumpgun, alertWeaponGun;
	private ArrayList<ItemStack> tickets;
	
	public SmartphoneMenu(){
		tickets = new ArrayList<ItemStack>();
		title = "Smartphone";
		size = 9;
		createItems();
		createTickets();
	}
	
	private void createItems(){
		
		localChannel = new ItemStack(Material.PAPER);
		ItemMeta localMeta = localChannel.getItemMeta();
		localMeta.setDisplayName(Colors.YELLOW + "Lokaler Chat" + Colors.BLACK + "  [I]");
		localChannel.setItemMeta(localMeta);
		
		helpChannel = new ItemStack(Material.PAPER);
		ItemMeta helpMeta = helpChannel.getItemMeta();
		helpMeta.setDisplayName(Colors.YELLOW + "Hilfe Chat" + Colors.BLACK + "  [I]");
		helpChannel.setItemMeta(helpMeta);
		
		groupChannel = new ItemStack(Material.PAPER);
		ItemMeta groupMeta = groupChannel.getItemMeta();
		groupMeta.setDisplayName(Colors.YELLOW + "Gruppen Chat" + Colors.BLACK + "  [I]");
		groupChannel.setItemMeta(groupMeta);
		
		globalChannel = new ItemStack(Material.PAPER);
		ItemMeta globalMeta = globalChannel.getItemMeta();
		globalMeta.setDisplayName(Colors.YELLOW + "Globaler Chat" + Colors.BLACK + "  [I]");
		globalChannel.setItemMeta(globalMeta);
		
		sendFastAlert = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta sendFastAlertMeta = sendFastAlert.getItemMeta();
		sendFastAlertMeta.setDisplayName(Colors.RED + "schneller Notruf" + Colors.BLACK + "  [I]");
		sendFastAlert.setItemMeta(sendFastAlertMeta);
		
		sendSlowAlert = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta sendSlowAlertMeta = sendSlowAlert.getItemMeta();
		sendSlowAlertMeta.setDisplayName(Colors.RED + "Notruf" + Colors.BLACK + "  [I]");
		sendSlowAlert.setItemMeta(sendSlowAlertMeta);
		
		
		alertPriority1 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta alertPriority1Meta = alertPriority1.getItemMeta();
		alertPriority1Meta.setDisplayName(Colors.RED + "Priorität: " + Colors.DARK_RED + "1" + Colors.BLACK + "  [I]");
		alertPriority1.setItemMeta(alertPriority1Meta);
		
		alertPriority2 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta alertPriority2Meta = alertPriority2.getItemMeta();
		alertPriority2Meta.setDisplayName(Colors.RED + "Priorität: " + Colors.DARK_RED + "2" + Colors.BLACK + "  [I]");
		alertPriority2.setItemMeta(alertPriority2Meta);
		
		alertPriority3 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta alertPriority3Meta = alertPriority3.getItemMeta();
		alertPriority3Meta.setDisplayName(Colors.RED + "Priorität: " + Colors.DARK_RED + "3" + Colors.BLACK + "  [I]");
		alertPriority3.setItemMeta(alertPriority3Meta);
		
		alertPriority4 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta alertPriority4Meta = alertPriority4.getItemMeta();
		alertPriority4Meta.setDisplayName(Colors.RED + "Priorität: " + Colors.DARK_RED + "4" + Colors.BLACK + "  [I]");
		alertPriority4.setItemMeta(alertPriority4Meta);
		
		alertPriority5 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta alertPriority5Meta = alertPriority5.getItemMeta();
		alertPriority5Meta.setDisplayName(Colors.RED + "Priorität: " + Colors.DARK_RED + "5" + Colors.BLACK + "  [I]");
		alertPriority5.setItemMeta(alertPriority5Meta);
		
		alertPriority6 = new ItemStack(Material.REDSTONE_TORCH_ON);
		ItemMeta alertPriority6Meta = alertPriority6.getItemMeta();
		alertPriority6Meta.setDisplayName(Colors.RED + "Priorität: " + Colors.DARK_RED + "6" + Colors.BLACK + "  [I]");
		alertPriority6.setItemMeta(alertPriority6Meta);
		
		alertWeaponNone = new ItemStack(Material.GLASS);
		ItemMeta alertWeaponNoneMeta = alertWeaponNone.getItemMeta();
		alertWeaponNoneMeta.setDisplayName(Colors.YELLOW + "Keine Waffen" + Colors.BLACK + "  [I]");
		alertWeaponNone.setItemMeta(alertWeaponNoneMeta);
		
		alertWeaponPistole = new ItemStack(Material.STONE_HOE);
		ItemMeta alertWeaponPistoleMeta = alertWeaponPistole.getItemMeta();
		alertWeaponPistoleMeta.setDisplayName(Colors.YELLOW + "Pistole(n)" + Colors.BLACK + "  [I]");
		alertWeaponPistole.setItemMeta(alertWeaponPistoleMeta);
		
		alertWeaponPumpgun = new ItemStack(Material.STONE_AXE);
		ItemMeta alertWeaponPumpgunMeta = alertWeaponPumpgun.getItemMeta();
		alertWeaponPumpgunMeta.setDisplayName(Colors.YELLOW + "Schrotflinte(n)" + Colors.BLACK + "  [I]");
		alertWeaponPumpgun.setItemMeta(alertWeaponPumpgunMeta);
		
		alertWeaponGun = new ItemStack(Material.STONE_SPADE);
		ItemMeta alertWeaponGunMeta = alertWeaponGun.getItemMeta();
		alertWeaponGunMeta.setDisplayName(Colors.YELLOW + "Maschinengewehr(e)" + Colors.BLACK + "  [I]");
		alertWeaponGun.setItemMeta(alertWeaponGunMeta);
		
	}
	
	private void createInventory(Session session, Player p) {
	
		menu.addItem(localChannel);
		if(session.getGroup() != null){
			menu.addItem(groupChannel);
		}
		menu.addItem(globalChannel);
		menu.addItem(helpChannel);
		menu.setItem(7, sendSlowAlert);
		menu.setItem(8, sendFastAlert);
		
		fastAlertMenu.addItem(alertPriority1);
		fastAlertMenu.addItem(alertPriority2);
		fastAlertMenu.addItem(alertPriority3);
		fastAlertMenu.addItem(alertPriority4);
		fastAlertMenu.addItem(alertPriority5);
		fastAlertMenu.addItem(alertPriority6);
		
		ItemStack pi = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		SkullMeta skullMeta = (SkullMeta)pi.getItemMeta();
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			if(player != p){
				if(p.getLocation().getX() - player.getLocation().getX() < 30 && p.getLocation().getX() - player.getLocation().getX() > -30
						&& p.getLocation().getY() - player.getLocation().getY() < 30 && p.getLocation().getY() - player.getLocation().getY() > -30
						&& p.getLocation().getZ() - player.getLocation().getZ() < 30 && p.getLocation().getZ() - player.getLocation().getZ() > -30){
					skullMeta.setDisplayName(player.getName() + Colors.BLACK + "  [I]");
					skullMeta.setOwner(p.getName());
					ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
					playerItem.setItemMeta(skullMeta);
					slowAlertMenu2.addItem(new ItemStack(playerItem));
				}
			}
		}
		
		for(ItemStack i : tickets){
			slowAlertMenu1.addItem(i);
		}
		
		slowAlertMenu3.addItem(alertWeaponNone);
		slowAlertMenu3.addItem(alertWeaponPistole);
		slowAlertMenu3.addItem(alertWeaponPumpgun);
		slowAlertMenu3.addItem(alertWeaponGun);
		
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		fastAlertMenu = Bukkit.createInventory(p, size, title);
		slowAlertMenu1 = Bukkit.createInventory(p, 54, title);
		slowAlertMenu2 = Bukkit.createInventory(p, 54, title);
		slowAlertMenu3 = Bukkit.createInventory(p, size, title);
		createInventory(session, p);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){

				if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(localChannel.getItemMeta().getDisplayName())){
					session.setChannel("L");
					p.sendMessage(Colors.GREY + "Du bist in den Lokalen Chat gewechselt.");
					p.closeInventory();
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(helpChannel.getItemMeta().getDisplayName())){
					session.setChannel("H");
					p.sendMessage(Colors.GREY + "Du bist in den Hilfe Chat gewechselt.");
					p.closeInventory();
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(groupChannel.getItemMeta().getDisplayName())){
					session.setChannel(session.getGroup().id + "");
					p.sendMessage(Colors.GREY + "Du bist in den Gruppen Chat gewechselt.");
					p.closeInventory();
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(globalChannel.getItemMeta().getDisplayName())){
					session.setChannel("G");
					p.sendMessage(Colors.GREY + "Du bist in den Globalen Chat gewechselt.");
					p.closeInventory();
				}else if(i.getType() == Material.REDSTONE_BLOCK && i.getItemMeta().getDisplayName().equals(sendFastAlert.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(fastAlertMenu);
				}else if(i.getType() == Material.LAPIS_BLOCK && i.getItemMeta().getDisplayName().equals(sendSlowAlert.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(slowAlertMenu1);
				}else if(i.getType() == Material.REDSTONE_TORCH_ON){
					int priority = Integer.parseInt(i.getItemMeta().getDisplayName().replace(Colors.RED + "Priorität: " + Colors.DARK_RED, ""));
					
					ShinigamiLife.getPoliceManager().fastAlert(p, priority);
					p.closeInventory();
				}else if(i.getType() == Material.BOOK){

					session.setAlertMsg(i.getItemMeta().getDisplayName());
					
					p.closeInventory();
					p.openInventory(slowAlertMenu2);
				}else if(i.getType() == Material.SKULL_ITEM){

					session.setAlertPlayer(Bukkit.getPlayer(i.getItemMeta().getDisplayName()));
					
					p.closeInventory();
					p.openInventory(slowAlertMenu3);
				}else if(i.getType() == Material.GLASS){
					session.setAlertWeapons(i.getItemMeta().getDisplayName());
					p.closeInventory();
				}else if(i.getType() == Material.STONE_HOE || i.getType() == Material.STONE_SPADE || i.getType() == Material.STONE_AXE){
					session.setAlertWeapons(i.getItemMeta().getDisplayName());
					p.closeInventory();
				}
			}
		}
	}
	
	private void createTickets(){
		for(Ticket t : ShinigamiLife.getTicketManager().tickets){
			ItemStack ticket = new ItemStack(Material.BOOK);
			ItemMeta meta = ticket.getItemMeta();
			meta.setDisplayName(Colors.YELLOW + t.name);
			ArrayList<String> lore = new ArrayList<String>();
			for(String s : t.description){
				lore.add(s);
			}
			meta.setLore(lore);
			ticket.setItemMeta(meta);
			
			tickets.add(ticket);
		}
	}
	
}
