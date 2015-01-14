package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class Cashpoint extends Menu{

	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack withdraw1Item, withdraw5Item, withdraw10Item, withdraw50Item, withdraw100Item, withdraw250Item, withdraw500Item, withdrawAllItem, moneyItem,
				deposite1Item, deposite5Item, deposite10Item, deposite50Item, deposite100Item, deposite250Item, deposite500Item, depositeAllItem;
	
	public Cashpoint(){
		title = "Bank";
		size = 18;
		createItems();
	}

	private void createItems() {

		moneyItem = new ItemStack(Material.CHEST);
		ItemMeta withdrawMeta = moneyItem.getItemMeta();
		withdrawMeta.setDisplayName(Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		moneyItem.setItemMeta(withdrawMeta);
		
		withdraw1Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw1Meta = withdraw1Item.getItemMeta();
		withdraw1Meta.setDisplayName(Colors.DARK_RED + "1 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw1Item.setItemMeta(withdraw1Meta);
		
		withdraw5Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw5Meta = withdraw5Item.getItemMeta();
		withdraw5Meta.setDisplayName(Colors.DARK_RED + "5 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw5Item.setItemMeta(withdraw5Meta);
		
		withdraw10Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw10Meta = withdraw10Item.getItemMeta();
		withdraw10Meta.setDisplayName(Colors.DARK_RED + "10 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw10Item.setItemMeta(withdraw10Meta);
		
		withdraw50Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw50Meta = withdraw50Item.getItemMeta();
		withdraw50Meta.setDisplayName(Colors.DARK_RED + "50 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw50Item.setItemMeta(withdraw50Meta);
		
		withdraw100Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw100Meta = withdraw100Item.getItemMeta();
		withdraw100Meta.setDisplayName(Colors.DARK_RED + "100 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw100Item.setItemMeta(withdraw100Meta);
		
		withdraw250Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw250Meta = withdraw250Item.getItemMeta();
		withdraw250Meta.setDisplayName(Colors.DARK_RED + "250 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw250Item.setItemMeta(withdraw250Meta);
		
		withdraw500Item = new ItemStack(Material.RECORD_6);
		ItemMeta withdraw500Meta = withdraw500Item.getItemMeta();
		withdraw500Meta.setDisplayName(Colors.DARK_RED + "500 " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdraw500Item.setItemMeta(withdraw500Meta);
		
		withdrawAllItem = new ItemStack(Material.RECORD_6);
		ItemMeta withdrawAllMeta = withdrawAllItem.getItemMeta();
		withdrawAllMeta.setDisplayName(Colors.DARK_RED + "Alles " + Colors.RED + "Abheben" + Colors.BLACK + "  [I]");
		withdrawAllItem.setItemMeta(withdrawAllMeta);
		
		
		deposite1Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite1Meta = deposite1Item.getItemMeta();
		deposite1Meta.setDisplayName(Colors.DARK_GREEN + "1 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite1Item.setItemMeta(deposite1Meta);
		
		deposite5Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite5Meta = deposite5Item.getItemMeta();
		deposite5Meta.setDisplayName(Colors.DARK_GREEN + "5 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite5Item.setItemMeta(deposite5Meta);
		
		deposite10Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite10Meta = deposite10Item.getItemMeta();
		deposite10Meta.setDisplayName(Colors.DARK_GREEN + "10 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite10Item.setItemMeta(deposite10Meta);
		
		deposite50Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite50Meta = deposite50Item.getItemMeta();
		deposite50Meta.setDisplayName(Colors.DARK_GREEN + "50 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite50Item.setItemMeta(deposite50Meta);
		
		deposite100Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite100Meta = deposite100Item.getItemMeta();
		deposite100Meta.setDisplayName(Colors.DARK_GREEN + "100 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite100Item.setItemMeta(deposite100Meta);
		
		deposite250Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite250Meta = deposite250Item.getItemMeta();
		deposite250Meta.setDisplayName(Colors.DARK_GREEN + "250 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite250Item.setItemMeta(deposite250Meta);
		
		deposite500Item = new ItemStack(Material.RECORD_5);
		ItemMeta deposite500Meta = deposite500Item.getItemMeta();
		deposite500Meta.setDisplayName(Colors.DARK_GREEN + "500 " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		deposite500Item.setItemMeta(deposite500Meta);
		
		depositeAllItem = new ItemStack(Material.RECORD_5);
		ItemMeta depositeAllMeta = depositeAllItem.getItemMeta();
		depositeAllMeta.setDisplayName(Colors.DARK_GREEN + "Alles " + Colors.GREEN + "Einzahlen" + Colors.BLACK + "  [I]");
		depositeAllItem.setItemMeta(depositeAllMeta);
		
	}

	private void createInventory(Session session) {

		menu.addItem(withdraw1Item);
		menu.addItem(withdraw1Item);
		menu.addItem(withdraw5Item);
		menu.addItem(withdraw10Item);
		menu.addItem(withdraw50Item);
		menu.addItem(withdraw100Item);
		menu.addItem(withdraw250Item);
		menu.addItem(withdraw500Item);
		menu.addItem(withdrawAllItem);
		
		menu.addItem(deposite1Item);
		menu.addItem(deposite1Item);
		menu.addItem(deposite5Item);
		menu.addItem(deposite10Item);
		menu.addItem(deposite50Item);
		menu.addItem(deposite100Item);
		menu.addItem(deposite250Item);
		menu.addItem(deposite500Item);
		menu.addItem(depositeAllItem);
		
		ItemMeta moneyMeta = moneyItem.getItemMeta();
		moneyMeta.setDisplayName(Colors.DARK_GREEN + "Bank: " + session.getMoneyInBank());
		moneyItem.setItemMeta(moneyMeta);
		
		menu.setItem(0, new ItemStack(moneyItem));
		menu.setItem(9, new ItemStack(moneyItem));
		
	}

	@Override
	public void open(Player p, Session session) {
		if(!session.isBanksClosed()){
			menu = Bukkit.createInventory(p, size, title);
			createInventory(session);
			p.openInventory(menu);
		}
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int amount = 0;
				if(i.getType() == Material.RECORD_6){
					if(i.getItemMeta().getDisplayName().contains(Colors.RED + "Abheben")){
						if(i.getItemMeta().getDisplayName().equals(withdraw1Item.getItemMeta().getDisplayName())){
							amount = 1;
						}else if(i.getItemMeta().getDisplayName().equals(withdraw5Item.getItemMeta().getDisplayName())){
							amount = 5;
						}else if(i.getItemMeta().getDisplayName().equals(withdraw10Item.getItemMeta().getDisplayName())){
							amount = 10;
						}else if(i.getItemMeta().getDisplayName().equals(withdraw50Item.getItemMeta().getDisplayName())){
							amount = 50;
						}else if(i.getItemMeta().getDisplayName().equals(withdraw100Item.getItemMeta().getDisplayName())){
							amount = 100;
						}else if(i.getItemMeta().getDisplayName().equals(withdraw250Item.getItemMeta().getDisplayName())){
							amount = 250;
						}else if(i.getItemMeta().getDisplayName().equals(withdraw500Item.getItemMeta().getDisplayName())){
							amount = 500;
						}else if(i.getItemMeta().getDisplayName().equals(withdrawAllItem.getItemMeta().getDisplayName())){
							amount = session.getMoneyInBank();
						}
							
						if(amount <= session.getMoneyBank()){
							p.sendMessage(Colors.RED + "Du hast " + Colors.DARK_RED + amount + Colors.RED + " abgehoben.");
							session.setMoneyInBank(session.getMoneyInBank()
									- amount);
								
							session.setMoneyPocket(session.getMoneyPocket()
									+ amount);
						}
							
					}
				}else if(i.getType() == Material.RECORD_5){
					if(i.getItemMeta().getDisplayName().contains(Colors.GREEN + "Einzahlen")){
						if(i.getItemMeta().getDisplayName().equals(deposite1Item.getItemMeta().getDisplayName())){
							amount = 1;
						}else if(i.getItemMeta().getDisplayName().equals(deposite5Item.getItemMeta().getDisplayName())){
							amount = 5;
						}else if(i.getItemMeta().getDisplayName().equals(deposite10Item.getItemMeta().getDisplayName())){
							amount = 10;
						}else if(i.getItemMeta().getDisplayName().equals(deposite50Item.getItemMeta().getDisplayName())){
							amount = 50;
						}else if(i.getItemMeta().getDisplayName().equals(deposite100Item.getItemMeta().getDisplayName())){
							amount = 100;
						}else if(i.getItemMeta().getDisplayName().equals(deposite250Item.getItemMeta().getDisplayName())){
							amount = 250;
						}else if(i.getItemMeta().getDisplayName().equals(deposite500Item.getItemMeta().getDisplayName())){
							amount = 500;
						}else if(i.getItemMeta().getDisplayName().equals(depositeAllItem.getItemMeta().getDisplayName())){
							amount = session.getMoneyPocket();
						}
						
						int money = session.getMoneyPocket();
						
						if(amount <= money){
							p.sendMessage(Colors.GREEN + "Du hast " + Colors.DARK_GREEN + amount + Colors.GREEN + " eingezahlt.");
							session.setMoneyInBank(session.getMoneyInBank()
									+ amount);
							
							session.setMoneyPocket(session.getMoneyPocket()
									- amount);
						}
					}
				}
				
				ItemMeta moneyMeta = moneyItem.getItemMeta();
				moneyMeta.setDisplayName(Colors.DARK_GREEN + "Bank: " + session.getMoneyInBank());
				moneyItem.setItemMeta(moneyMeta);
				
				menu.setItem(0, new ItemStack(moneyItem));
				menu.setItem(9, new ItemStack(moneyItem));
			}
		}
	}
	
}
