package com.shinigami.menus;

import java.util.ArrayList;
import java.util.List;

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
import com.shinigami.police.TicketManager;
import com.shinigami.police.TicketManager.Ticket;
import com.shinigami.scripts.PlayerScripts;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class PoliceMenu extends Menu{

	private TicketManager ticketManager;
	private Inventory menu, ticketMenu, playerMenu, ticketPayMenu;
	private String title;
	private int size;
	
	private ItemStack frezzePlayer, escortPlayer, stopEscort, writeTicket, wantedList, hideUnhidePlayer,
						payTicket, reverseTicket;
	private ArrayList<ItemStack> tickets;
	
	public PoliceMenu(){
		tickets = new ArrayList<ItemStack>();
		this.ticketManager = ShinigamiLife.getTicketManager();
		title = "Polizeimenü";
		size = 9;
		createItems();
		createTickets(ticketManager);
	}
	
	private void createItems(){
		
		writeTicket = new ItemStack(Material.BOOK_AND_QUILL);
		ItemMeta writeTicketMeta = writeTicket.getItemMeta();
		writeTicketMeta.setDisplayName(Colors.YELLOW + "Strafzettel austellen" + Colors.BLACK + "  [I]");
		writeTicket.setItemMeta(writeTicketMeta);
		
		frezzePlayer = new ItemStack(Material.TRIPWIRE_HOOK);
		ItemMeta frezzeMeta = frezzePlayer.getItemMeta();
		frezzeMeta.setDisplayName(Colors.YELLOW + "Festnehmen" + Colors.WHITE + "/" + Colors.YELLOW + "Freilassen" + Colors.BLACK + "  [I]");
		frezzePlayer.setItemMeta(frezzeMeta);
		
		wantedList = new ItemStack(Material.PAPER);
		ItemMeta wantedMeta = wantedList.getItemMeta();
		wantedMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Fahndungsliste" + Colors.BLACK + "  [I]");
		wantedMeta.setLore(ShinigamiLife.getPoliceManager().wantedList.getLore());
		wantedList.setItemMeta(wantedMeta);
		
		escortPlayer = new ItemStack(Material.LEASH);
		ItemMeta escortMeta = escortPlayer.getItemMeta();
		escortMeta.setDisplayName(Colors.YELLOW + "Spieler eskortieren" + Colors.BLACK + "  [I]");
		escortPlayer.setItemMeta(escortMeta);
		
		stopEscort = new ItemStack(Material.LEASH);
		ItemMeta stopEscortMeta = stopEscort.getItemMeta();
		stopEscortMeta.setDisplayName(Colors.YELLOW + "Spieler nicht mehr eskortieren" + Colors.BLACK + "  [I]");
		stopEscort.setItemMeta(stopEscortMeta);
		
		hideUnhidePlayer = new ItemStack(Material.NETHER_STAR);
		ItemMeta hideUnhideMeta = hideUnhidePlayer.getItemMeta();
		hideUnhideMeta.setDisplayName(Colors.YELLOW + "Eskortierter Spieler anzeigen/verbergen" + Colors.BLACK + "  [I]");
		hideUnhidePlayer.setItemMeta(hideUnhideMeta);
		
	}
	
	private void createTicketPayMenu(List<String> description, int ticketPay, Player cop){
		payTicket = new ItemStack(Material.PAPER);
		ItemMeta acceptMeta = payTicket.getItemMeta();
		acceptMeta.setDisplayName(Colors.GREEN + "bezahlen" + Colors.BLACK + "  [I]");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Colors.RED + "Strafe: " + ticketPay);
		lore.add(Colors.BLUE + "Polizist: " + cop.getName());
		for(String s : description){
			lore.add(s);
		}
		acceptMeta.setLore(lore);
		payTicket.setItemMeta(acceptMeta);
		
		reverseTicket = new ItemStack(Material.PAPER);
		ItemMeta denyMeta = reverseTicket.getItemMeta();
		denyMeta.setDisplayName(Colors.RED + "verweigern");
		denyMeta.setLore(lore);
		reverseTicket.setItemMeta(denyMeta);
		
		ticketPayMenu.addItem(new ItemStack(payTicket));
		ticketPayMenu.setItem(8, new ItemStack(reverseTicket));
	}
	
	private void createInventory(Session session, Player p) {
	
		menu.addItem(writeTicket);
		
		ItemMeta wantedMeta = wantedList.getItemMeta();
		wantedMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Fahndungsliste" + Colors.BLACK + "  [I]");
		wantedMeta.setLore(ShinigamiLife.getPoliceManager().wantedList.getLore());
		wantedList.setItemMeta(wantedMeta);
		
		menu.addItem(wantedList);
		menu.addItem(frezzePlayer);
		if(session.getEscort() == null){
			menu.addItem(escortPlayer);
		}else{
			menu.addItem(stopEscort);
			menu.addItem(hideUnhidePlayer);
		}
		
		ItemStack pi = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		SkullMeta skullMeta = (SkullMeta)pi.getItemMeta();
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			if(player != p){
				if(p.getLocation().getX() - player.getLocation().getX() < 4 && p.getLocation().getX() - player.getLocation().getX() > -4
						&& p.getLocation().getY() - player.getLocation().getY() < 4 && p.getLocation().getY() - player.getLocation().getY() > -4
						&& p.getLocation().getZ() - player.getLocation().getZ() < 4 && p.getLocation().getZ() - player.getLocation().getZ() > -4){
					skullMeta.setDisplayName(player.getName() + Colors.BLACK + "  [I]");
					skullMeta.setOwner(p.getName());
					ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
					playerItem.setItemMeta(skullMeta);
					playerMenu.addItem(new ItemStack(playerItem));
				}
			}
		}
		
		for(ItemStack i : tickets){
			ticketMenu.addItem(i);
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		ticketMenu = Bukkit.createInventory(p, 54, title);
		ticketPayMenu = Bukkit.createInventory(p, size, title);
		playerMenu = Bukkit.createInventory(p, 54, title);
		createInventory(session, p);
		p.openInventory(menu);
	}
	
	@SuppressWarnings("deprecation")
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(session.isLoggedInAsCop()){
					if(i.getType() == Material.BOOK_AND_QUILL && i.getItemMeta().getDisplayName().equals(writeTicket.getItemMeta().getDisplayName())){
						p.closeInventory();
						p.openInventory(ticketMenu);
					}else if(i.getType() == Material.TRIPWIRE_HOOK && i.getItemMeta().getDisplayName().equals(frezzePlayer.getItemMeta().getDisplayName())){
						p.closeInventory();
						session.setFrezzePlayer(true);
						p.openInventory(playerMenu);
					}else if(i.getType() == Material.LEASH){
						p.closeInventory();
						session.setEscortPlayer(true);
						p.openInventory(playerMenu);
					}else if(i.getType() == Material.NETHER_STAR){
						p.closeInventory();
						Player escort = session.getEscort();
						if(p.canSee(escort)){
							p.hidePlayer(escort);
						}else{
							p.showPlayer(escort);
						}
					}else if(i.getType() == Material.SKULL_ITEM){
						Player player = Bukkit.getServer().getPlayer(i.getItemMeta().getDisplayName());
						
						Session sessionT = ShinigamiLife.getSessionManager().getSession(player.getUniqueId() + "");
						
						if(session.isFrezzePlayer()){
							double x = p.getLocation().getX() - player.getLocation().getX();
							double y = p.getLocation().getY() - player.getLocation().getY();
							double z = p.getLocation().getZ() - player.getLocation().getZ();
							
							if(x > -3 && x < 3 && y > -3 && y < 3 && z > -3 && z < 3){
								PlayerScripts.fetterPlayerCop(p, player);
								session.setFrezzePlayer(false);
							}else{
								p.sendMessage(Colors.RED + "Der Spieler ist zu weit weg.");
							}
							
						}else if(session.isEscortPlayer()){
							double x = p.getLocation().getX() - player.getLocation().getX();
							double y = p.getLocation().getY() - player.getLocation().getY();
							double z = p.getLocation().getZ() - player.getLocation().getZ();
							
							if(x > -3 && x < 3 && y > -3 && y < 3 && z > -3 && z < 3){
								if(session.getEscort() == null){
									if(!sessionT.isEscorted()){
										p.setLeashHolder(player);
										session.setEscort(player);
										sessionT.setEscorted(true);
										session.setEscortPlayer(false);
									}else{
										session.setEscortPlayer(false);
									}
								}else{
									sessionT.setEscorted(false);
									session.setEscort(null);
									session.setEscortPlayer(false);
								}
							}else{
								p.sendMessage(Colors.RED + "Der Spieler ist zu weit weg.");
							}
						}else if(session.getTicketPay() != 0){
							Bukkit.broadcastMessage(Colors.DARK_GREY + p.getName() + Colors.GREY + " hat ein Ticket in höhe von " + Colors.DARK_GREY + session.getTicketPay() + Colors.GREY + " an " + Colors.DARK_GREY + player.getName() + Colors.GREY + " ausgestellt.");
							createTicketPayMenu(session.getTicketMsg(), session.getTicketPay(), p);
							player.closeInventory();
							player.openInventory(ticketPayMenu);
							session.setTicketMsg(null);
							session.setTicketPay(0);
						}
						
						p.closeInventory();
					}else if(i.getType() == Material.PAPER){
						if(!i.getItemMeta().getDisplayName().equals(payTicket.getItemMeta().getDisplayName()) && !i.getItemMeta().getDisplayName().equals(reverseTicket.getItemMeta().getDisplayName()))
							session.setTicketPay(Integer.parseInt(i.getItemMeta().getLore().get(0).replace(Colors.WHITE + "Strafe: ", "")));
						session.setTicketMsg(i.getItemMeta().getLore());
							
							p.closeInventory();
							p.openInventory(playerMenu);
					}
				}else{
					if(i.getItemMeta().getDisplayName().equals(payTicket.getItemMeta().getDisplayName())){
						
						Player copPlayer = Bukkit.getPlayer(i.getItemMeta().getLore().get(1).replace(Colors.DARK_TURKISH + "Polizist: ", ""));
						int ticketPay = Integer.parseInt(i.getItemMeta().getLore().get(0).replace(Colors.RED + "Strafe: ", ""));
						
						Session sessionC = ShinigamiLife.getSessionManager().getSession(copPlayer.getUniqueId() + "");
						
						if(session.getMoneyPocket() >= ticketPay){
							session.setMoneyPocket(session.getMoneyPocket()	- ticketPay);
							sessionC.setMoneyInBank(sessionC.getMoneyInBank() + ticketPay);
							
							p.sendMessage(Colors.GREEN + "Du hast die Strafe von " + Colors.DARK_GREEN + ticketPay + Colors.GREEN + " bezahlt.");
							copPlayer.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat die Strafe von " + Colors.DARK_GREEN + ticketPay + Colors.GREEN + " bezahlt.");
							
						}else if(session.getMoneyInBank() >= ticketPay){
							session.setMoneyInBank(session.getMoneyInBank() - ticketPay);
							sessionC.setMoneyInBank(sessionC.getMoneyInBank() + ticketPay);
							
							p.sendMessage(Colors.GREEN + "Du hast die Strafe von " + Colors.DARK_GREEN + ticketPay + Colors.GREEN + " bezahlt.");
							copPlayer.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat die Strafe von " + Colors.DARK_GREEN + ticketPay + Colors.GREEN +  " bezahlt.");
							
							sessionC.setTicketPay(0);
						}else{
							p.sendMessage(Colors.RED + "Du hast nicht genügend Geld auf dem Konto/in der Brieftasche.");
							copPlayer.sendMessage(Colors.RED + "Der Straftäter hat nicht genügend Geld auf dem Konto/in der Brieftasche.");
						}
						
						p.closeInventory();
						
					}else if(i.getItemMeta().getDisplayName().equals(reverseTicket.getItemMeta().getDisplayName())){
						
						Player copPlayer = Bukkit.getPlayer(i.getItemMeta().getLore().get(1).replace(Colors.DARK_TURKISH + "Polizist: ", ""));

						p.sendMessage(Colors.RED + "Du hast das Ticket nicht bezahlt!");
						copPlayer.sendMessage(Colors.RED + "Der Spieler " + Colors.DARK_RED + p.getName() + Colors.RED + " hat das Ticket nicht bezahlt!");
						
						p.closeInventory();
						
					}
				}
			}
		}
	}
	
	private void createTickets(TicketManager ticketManager){
		for(Ticket t : ticketManager.tickets){
			ItemStack ticket = new ItemStack(Material.PAPER);
			ItemMeta meta = ticket.getItemMeta();
			meta.setDisplayName(Colors.YELLOW + t.name + Colors.BLACK + "  [I]");
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Colors.RED + "Strafe: " + Colors.DARK_RED + t.pay);
			lore.add("");
			for(String s : t.description){
				lore.add(s);
			}
			meta.setLore(lore);
			ticket.setItemMeta(meta);
			
			tickets.add(ticket);
		}
	}
	
}
