package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.SkullType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class MenuAccount extends Menu{

	private Inventory menu;
	private ItemStack glassPaneItem, account1Item, account2Item, account3Item;
	
	private final String title = "Character ";
	
	public MenuAccount() {
		createItems();
	}
	
	private void createItems(){
		glassPaneItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
		Utils.changeItemMeta(glassPaneItem, Colors.BLACK + "  .", new String[] {});
	}
	
	private void createInventory(Player p, Session session){
		if(session.getAccCount() == 3){
			if(session.getAcc1Profession().equals("civ")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else if(session.getAcc1Profession().equals("cop")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else if(session.getAcc1Profession().equals("reb")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else{
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
				Utils.changeItemMeta(account1Item, "No Account N°1" + Colors.BLACK + "  .", new String[] {});
			}
			
			if(session.getAcc2Profession().equals("civ")){
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				Utils.changeItemMeta(account2Item, "Character name: " + session.getAcc2CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc2Money(), "Profession: " + session.getAcc2Profession()});
			}else if(session.getAcc2Profession().equals("cop")){
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
				Utils.changeItemMeta(account2Item, "Character name: " + session.getAcc2CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc2Money(), "Profession: " + session.getAcc2Profession()});
			}else if(session.getAcc2Profession().equals("reb")){
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
				Utils.changeItemMeta(account2Item, "Character name: " + session.getAcc2CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc2Money(), "Profession: " + session.getAcc2Profession()});
			}else{
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
				Utils.changeItemMeta(account2Item, "No Account N°2" + Colors.BLACK + "  .", new String[] {});
			}
			
			if(session.getAcc3Profession().equals("civ")){
				account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				Utils.changeItemMeta(account3Item, "Character name: " + session.getAcc3CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc3Money(), "Profession: " + session.getAcc3Profession()});
			}else if(session.getAcc3Profession().equals("cop")){
				account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
				Utils.changeItemMeta(account3Item, "Character name: " + session.getAcc3CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc3Money(), "Profession: " + session.getAcc3Profession()});
			}else if(session.getAcc3Profession().equals("reb")){
				account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
				Utils.changeItemMeta(account3Item, "Character name: " + session.getAcc3CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc3Money(), "Profession: " + session.getAcc3Profession()});
			}else{
				account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
				Utils.changeItemMeta(account3Item, "No Account N°3" + Colors.BLACK + "  .", new String[] {});
			}
		}else if(session.getAccCount() == 2){
			if(session.getAcc1Profession().equals("civ")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else if(session.getAcc1Profession().equals("cop")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else if(session.getAcc1Profession().equals("reb")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else{
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
				Utils.changeItemMeta(account1Item, "No Account N°1" + Colors.BLACK + "  .", new String[] {});
			}
			
			if(session.getAcc2Profession().equals("civ")){
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				Utils.changeItemMeta(account2Item, "Character name: " + session.getAcc2CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc2Money(), "Profession: " + session.getAcc2Profession()});
			}else if(session.getAcc2Profession().equals("cop")){
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
				Utils.changeItemMeta(account2Item, "Character name: " + session.getAcc2CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc2Money(), "Profession: " + session.getAcc2Profession()});
			}else if(session.getAcc2Profession().equals("reb")){
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
				Utils.changeItemMeta(account2Item, "Character name: " + session.getAcc2CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc2Money(), "Profession: " + session.getAcc2Profession()});
			}else{
				account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
				Utils.changeItemMeta(account2Item, "No Account N°2" + Colors.BLACK + "  .", new String[] {});
			}
			
			account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
			Utils.changeItemMeta(account3Item, "No Account N°3" + Colors.BLACK + "  .", new String[] {});
		}else if(session.getAccCount() == 1){
			if(session.getAcc1Profession().equals("civ")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else if(session.getAcc1Profession().equals("cop")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.SKELETON.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else if(session.getAcc1Profession().equals("reb")){
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.ZOMBIE.ordinal());
				Utils.changeItemMeta(account1Item, "Character name: " + session.getAcc1CharName() + Colors.BLACK + "  .", new String[] {"Money: " + session.getAcc1Money(), "Profession: " + session.getAcc1Profession()});
			}else{
				account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
				Utils.changeItemMeta(account1Item, "No Account N°3" + Colors.BLACK + "  .", new String[] {});
			}
			
			account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
			Utils.changeItemMeta(account2Item, "No Account N°2" + Colors.BLACK + "  .", null);
			account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
			Utils.changeItemMeta(account3Item, "No Account N°3" + Colors.BLACK + "  .", null);
		}else{
			account1Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
			Utils.changeItemMeta(account1Item, "No Account N°1" + Colors.BLACK + "  .", null);
			account2Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
			Utils.changeItemMeta(account2Item, "No Account N°2" + Colors.BLACK + "  .", null);
			account3Item = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.WITHER.ordinal());
			Utils.changeItemMeta(account3Item, "No Account N°3" + Colors.BLACK + "  .", null);
		}
		
		menu.addItem(glassPaneItem);
		menu.addItem(account1Item);
		menu.addItem(account2Item);
		menu.addItem(account3Item);
		menu.setItem(4, new ItemStack(glassPaneItem));
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, InventoryType.HOPPER, title);
		createInventory(p, session);
		p.openInventory(menu);
	}

	@Override
	public void event(Player p, Inventory inv, ItemStack i,
			InventoryClickEvent event, Session session) {
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(!i.getItemMeta().getDisplayName().contains("No Account")){
					if(i.getItemMeta().getDisplayName().equals(account1Item.getItemMeta().getDisplayName())){
						session.setAccNumLoggedIn(1);
						session.setProfession(session.getAcc1Profession());
						session.setCharname(session.getAcc1CharName());
						ShinigamiLife.getSessionManager().loadData(p, 1);
					}else if(i.getItemMeta().getDisplayName().equals(account2Item.getItemMeta().getDisplayName())){
						session.setAccNumLoggedIn(2);
						session.setProfession(session.getAcc2Profession());
						session.setCharname(session.getAcc2CharName());
						ShinigamiLife.getSessionManager().loadData(p, 2);
					}else if(i.getItemMeta().getDisplayName().equals(account3Item.getItemMeta().getDisplayName())){
						session.setAccNumLoggedIn(3);
						session.setProfession(session.getAcc3Profession());
						session.setCharname(session.getAcc3CharName());
						ShinigamiLife.getSessionManager().loadData(p, 3);
					}
					p.closeInventory();
				}else{
					p.sendMessage("Register new character with: /regchar <character name>");
				}
			}
		}
	}
}
