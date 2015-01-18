package com.shinigami.oldmenus;

import java.util.ArrayList;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import com.shinigami.configs.PriceConfig;
import com.shinigami.groups.GroupManager.Group;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class GroupMenu extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu, groupList, groupInvite, inviteMenu, groupMembers;
	private String title;
	private int size;
	
	private ItemStack createGroupItem, groupListItem, groupInviteItem, groupMembersItem, leaveGroup, acceptInvite, denyInvite;
	
	public GroupMenu(){
		title = "Gruppen";
		size = 9;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems(){
		
		createGroupItem = new ItemStack(Material.PAPER);
		ItemMeta createGroupMeta = createGroupItem.getItemMeta();
		createGroupMeta.setDisplayName(Colors.YELLOW + "Gruppe erstellen" + Colors.BLACK + "  [I]");
		createGroupItem.setItemMeta(createGroupMeta);
		
		groupListItem = new ItemStack(Material.ENDER_CHEST);
		ItemMeta createListMeta = groupListItem.getItemMeta();
		createListMeta.setDisplayName(Colors.YELLOW + "Gruppen" + Colors.BLACK + "  [I]");
		groupListItem.setItemMeta(createListMeta);
		
		groupInviteItem = new ItemStack(Material.BOOK);
		ItemMeta groupInviteMeta = groupInviteItem.getItemMeta();
		groupInviteMeta.setDisplayName(Colors.YELLOW + "Spieler einladen" + Colors.BLACK + "  [I]");
		groupInviteItem.setItemMeta(groupInviteMeta);
		
		groupMembersItem = new ItemStack(Material.CHEST);
		ItemMeta groupMembersMeta = groupMembersItem.getItemMeta();
		groupMembersMeta.setDisplayName(Colors.YELLOW + "Spieler eigene Gruppe" + Colors.BLACK + "  [I]");
		groupMembersItem.setItemMeta(groupMembersMeta);
		
		leaveGroup = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta leaveGroupMeta = leaveGroup.getItemMeta();
		leaveGroupMeta.setDisplayName(Colors.DARK_RED + "Gruppe verlassen" + Colors.BLACK + "  [I]");
		leaveGroup.setItemMeta(leaveGroupMeta);
		
	}
	
	private void createInviteMenu(String groupName){
		acceptInvite = new ItemStack(Material.PAPER);
		ItemMeta acceptMeta = acceptInvite.getItemMeta();
		acceptMeta.setDisplayName(Colors.DARK_GREEN + "Annehmen" + Colors.BLACK + "  [I]");
		ArrayList<String> lore = new ArrayList<String>();
		lore.add(Colors.GREY + "Gruppe: " + groupName);
		acceptMeta.setLore(lore);
		acceptInvite.setItemMeta(acceptMeta);
		
		denyInvite = new ItemStack(Material.PAPER);
		ItemMeta denyMeta = denyInvite.getItemMeta();
		denyMeta.setDisplayName(Colors.DARK_RED + "Ablehnen" + Colors.BLACK + "  [I]");
		denyMeta.setLore(lore);
		denyInvite.setItemMeta(denyMeta);
		
		inviteMenu.addItem(new ItemStack(acceptInvite));
		inviteMenu.addItem(new ItemStack(denyInvite));
	}
	
	private void createInventory(Session session, Player p) {
	
		if(session.getGroup() == null){
			menu.addItem(createGroupItem);
			menu.addItem(groupListItem);
		}else{
			menu.addItem(groupMembersItem);
			if(session.getGroup().leader == p){
				if(session.getGroup().members.size() < Integer.parseInt(ShinigamiLife.getConfiguration().getConfig().getEntry("group_max_members"))){
					menu.addItem(groupInviteItem);
				}
			}
			menu.addItem(groupListItem);
			menu.setItem(8, leaveGroup);
		}
		
		ItemStack pi = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		SkullMeta skullMeta = (SkullMeta)pi.getItemMeta();
		
		for(Player player : Bukkit.getServer().getOnlinePlayers()){
			Session session1 = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
			
			if(session1.getGroup() == null){
				skullMeta.setDisplayName(player.getName());
				skullMeta.setOwner(p.getName());
				ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				playerItem.setItemMeta(skullMeta);
				groupInvite.addItem(new ItemStack(playerItem));
			}else if(session.getGroup() != null){
				if(session1.getGroup().id == session.getGroup().id){
					skullMeta.setDisplayName(player.getName());
					skullMeta.setOwner(p.getName());
					ItemStack playerItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
					playerItem.setItemMeta(skullMeta);
					groupMembers.addItem(new ItemStack(playerItem));
				}
			}
		}
		
		ItemStack gi = new ItemStack(Material.BOAT);
		ItemMeta groupMeta = gi.getItemMeta();
		
		for(Group g : ShinigamiLife.getGroupManager().groups){
			groupMeta.setDisplayName("ID  " + g.id);
			ArrayList<String> lore = new ArrayList<String>();
			lore.add(Colors.DARK_YELLOW + "____" + Colors.YELLOW + "Mitglieder" + Colors.DARK_YELLOW + "____");
			for(Player player : g.members){
				lore.add(Colors.WHITE + player.getName());
			}
			groupMeta.setLore(lore);
			ItemStack groupItem = new ItemStack(Material.BOAT);
			groupItem.setItemMeta(groupMeta);
			
			groupList.addItem(new ItemStack(groupItem));
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		inviteMenu = Bukkit.createInventory(p, size, title);
		groupMembers= Bukkit.createInventory(p, 54, title);
		groupList = Bukkit.createInventory(p, 54, title);
		groupInvite = Bukkit.createInventory(p, 54, title);
		createInventory(session, p);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){

				// MAIN MENU
				if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(createGroupItem.getItemMeta().getDisplayName())){
					if(session.getMoneyInPocket() >= priceConfig.buyPrices.get("create_group")){
						Group group = ShinigamiLife.getGroupManager().createGroup(p);
						p.sendMessage(Colors.GREEN + "Du hast eine Gruppe erstellt.");
						session.setGroup(group);
						p.closeInventory();
					}else{
						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei. Dir fehlen " + (session.getMoneyInPocket() - priceConfig.buyPrices.get("create_gang")) + ".");
					}
				}else if(i.getType() == Material.ENDER_CHEST && i.getItemMeta().getDisplayName().equals(groupListItem.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(groupList);
				}else if(i.getType() == Material.BOOK && i.getItemMeta().getDisplayName().equals(groupInviteItem.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(groupInvite);
				}else if(i.getType() == Material.CHEST && i.getItemMeta().getDisplayName().equals(groupMembersItem.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(groupMembers);
				}else if(i.getType() == Material.REDSTONE_BLOCK && i.getItemMeta().getDisplayName().equals(leaveGroup.getItemMeta().getDisplayName())){
					p.closeInventory();

					int id = session.getGroup().id;
					for(Group g : ShinigamiLife.getGroupManager().groups){
						if(g.id == id){
							g.members.remove(p);
							session.setGroup(null);
							
							for(Player groupPlayer : g.members){
								groupPlayer.sendMessage(Colors.DARK_RED + p.getName() + Colors.RED + " hat die Gruppe verlassen.");
							}
							
							if(g.leader == p){
								if(g.members.size()-1 > 0){
									g.leader = g.members.get(new Random().nextInt(g.members.size()-1));
								}else{
									ShinigamiLife.getGroupManager().groups.remove(g);
								}
							}
							
//							if(session.getChannel().equals(id)){
//								SmartphoneMenu smartphone = (SmartphoneMenu) ShinigamiLife.getMenuManager().get("smartphone");
//								smartphone.open(p, session);
//							}
							
							break;
						}
					}
					
					p.sendMessage(Colors.RED + "Du hast die Gruppe " + Colors.DARK_RED + id + Colors.RED + " verlassen.");
				}

				// SEND INVITE
				Player player = Bukkit.getServer().getPlayer(i.getItemMeta().getDisplayName());
				if(player != null){
					createInviteMenu(session.getGroup().id + "");
					player.closeInventory();
					player.openInventory(inviteMenu);
				
					p.closeInventory();
					p.sendMessage(Colors.GREEN + "Du hast erfolgreich an " + Colors.DARK_GREEN + i.getItemMeta().getDisplayName() + Colors.GREEN + " eine Gruppeneinladung gesendet.");
				}
				
				// INVITE MENU
				if(acceptInvite != null){
					if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(acceptInvite.getItemMeta().getDisplayName())){
						int id = Integer.parseInt(i.getItemMeta().getLore().get(0).replace(Colors.GREY + "Gruppe: ", ""));
						p.sendMessage(Colors.GREEN + "Du hast die Einladung der Gruppe " + Colors.DARK_GREEN + id + Colors.GREEN + " angenommen.");
						p.closeInventory();
	
						for(Group g : ShinigamiLife.getGroupManager().groups){
							if(g.id == id){
								g.members.add(p);
								session.setGroup(g);
								
								for(Player groupPlayer : g.members){
									groupPlayer.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " ist der Gruppe beigetreten.");
								}
								
								break;
							}
						}
						
						Player leader = null;
						for(Group g : ShinigamiLife.getGroupManager().groups){
							if(g.id == id){
								leader = g.leader;
							}
						}
						
						leader.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat die Einladung angenommen.");
					
					}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(denyInvite.getItemMeta().getDisplayName())){
						int id = Integer.parseInt(i.getItemMeta().getLore().get(0).replace(Colors.GREY + "Gruppe: ", ""));
						p.sendMessage(Colors.RED + "Du hast die Einladung der Gruppe " + Colors.DARK_RED + id + Colors.RED + " abgelehnt");
						p.closeInventory();
	
						Player leader = null;
						for(Group g : ShinigamiLife.getGroupManager().groups){
							if(g.id == id){
								leader = g.leader;
							}
						}
						
						leader.sendMessage(Colors.DARK_RED + p.getName() + Colors.RED + " hat die Einladung abgelehnt.");
					}
				}
			}
		}
	}
	
}
