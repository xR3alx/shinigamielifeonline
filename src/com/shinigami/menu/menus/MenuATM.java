package com.shinigami.menu.menus;

import org.bukkit.Bukkit;
import org.bukkit.DyeColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.menu.Menu;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class MenuATM extends Menu{

	private Inventory menu;
	private ItemStack currentMoney, closeATM, glassPaneItem,
					deposit5, deposit10, deposit50, deposit100, deposit1000, deposit5000,
					withdraw5, withdraw10, withdraw50, withdraw100, withdraw1000, withdraw5000;
	
	private final String title = "                    ATM";
	private final int size = 36;
	
	public MenuATM() {
		createItems();
	}
	
	private void createItems(){
		glassPaneItem = new ItemStack(Material.STAINED_GLASS_PANE, 1, DyeColor.GRAY.getData());
		Utils.changeItemMeta(glassPaneItem, Colors.BLACK + "  .", new String[] {});
		
		currentMoney = new ItemStack(Material.YELLOW_FLOWER);
		closeATM = new ItemStack(Material.BARRIER);
		Utils.changeItemMeta(closeATM, Colors.RESET + "Close ATM" + Colors.BLACK + "  .", null);

		deposit5 = new ItemStack(Material.IRON_INGOT);
		Utils.changeItemMeta(deposit5, Colors.RESET + "Deposit $5" + Colors.BLACK + "  .", null);
		deposit10 = new ItemStack(Material.IRON_BLOCK);
		Utils.changeItemMeta(deposit10, Colors.RESET + "Deposit $10" + Colors.BLACK + "  .", null);
		deposit50 = new ItemStack(Material.GOLD_INGOT);
		Utils.changeItemMeta(deposit50, Colors.RESET + "Deposit $50" + Colors.BLACK + "  .", null);
		deposit100 = new ItemStack(Material.GOLD_BLOCK);
		Utils.changeItemMeta(deposit100, Colors.RESET + "Deposit $100" + Colors.BLACK + "  .", null);
		deposit1000 = new ItemStack(Material.DIAMOND);
		Utils.changeItemMeta(deposit1000, Colors.RESET + "Deposit $1000" + Colors.BLACK + "  .", null);
		deposit5000 = new ItemStack(Material.DIAMOND_BLOCK);
		Utils.changeItemMeta(deposit5000, Colors.RESET + "Deposit $5000" + Colors.BLACK + "  .", null);
		
		withdraw5 = new ItemStack(Material.IRON_INGOT);
		withdraw5.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		Utils.changeItemMeta(withdraw5, Colors.RESET + "Withdraw $5" + Colors.BLACK + "  .", null);
		withdraw10 = new ItemStack(Material.IRON_BLOCK);
		withdraw10.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		Utils.changeItemMeta(withdraw10, Colors.RESET + "Withdraw $10" + Colors.BLACK + "  .", null);
		withdraw50 = new ItemStack(Material.GOLD_INGOT);
		withdraw50.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		Utils.changeItemMeta(withdraw50, Colors.RESET + "Withdraw $50" + Colors.BLACK + "  .", null);
		withdraw100 = new ItemStack(Material.GOLD_BLOCK);
		withdraw100.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		Utils.changeItemMeta(withdraw100, Colors.RESET + "Withdraw $100" + Colors.BLACK + "  .", null);
		withdraw1000 = new ItemStack(Material.DIAMOND);
		withdraw1000.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		Utils.changeItemMeta(withdraw1000, Colors.RESET + "Withdraw $1000" + Colors.BLACK + "  .", null);
		withdraw5000 = new ItemStack(Material.DIAMOND_BLOCK);
		withdraw5000.addUnsafeEnchantment(Enchantment.DURABILITY, 1);
		Utils.changeItemMeta(withdraw5000, Colors.RESET + "Withdraw $5000" + Colors.BLACK + "  .", null);
	}
	
	private void createInventory(Player p, Session session){
		Utils.changeItemMeta(currentMoney, Colors.RESET + Colors.UNDERLINE + "Current Money statistics for account " + Colors.BOLD + session.getCharname() + Colors.RESET + Colors.BLACK + "  .", new String[] {"Bankaccount: " + session.getMoneyBank(), "Pocketmoney: " + session.getMoneyInPocket()});
		
		menu.addItem(currentMoney);
		menu.setItem(1, new ItemStack(glassPaneItem));
		menu.setItem(2, new ItemStack(glassPaneItem));
		menu.setItem(3, new ItemStack(glassPaneItem));
		menu.setItem(4, new ItemStack(glassPaneItem));
		menu.setItem(5, new ItemStack(glassPaneItem));
		menu.setItem(6, new ItemStack(glassPaneItem));
		menu.setItem(7, new ItemStack(glassPaneItem));
		menu.setItem(8, new ItemStack(glassPaneItem));
		menu.setItem(9, new ItemStack(glassPaneItem));
		menu.setItem(10, new ItemStack(glassPaneItem));
		menu.addItem(deposit5);
		menu.addItem(deposit10);
		menu.addItem(deposit50);
		menu.addItem(deposit100);
		menu.addItem(deposit1000);
		menu.addItem(deposit5000);
		menu.setItem(17, new ItemStack(glassPaneItem));
		menu.setItem(18, new ItemStack(glassPaneItem));
		menu.setItem(19, new ItemStack(glassPaneItem));
		menu.addItem(withdraw5);
		menu.addItem(withdraw10);
		menu.addItem(withdraw50);
		menu.addItem(withdraw100);
		menu.addItem(withdraw1000);
		menu.addItem(withdraw5000);
		menu.setItem(26, new ItemStack(glassPaneItem));
		menu.addItem(closeATM);
		menu.setItem(28, new ItemStack(glassPaneItem));
		menu.setItem(29, new ItemStack(glassPaneItem));
		menu.setItem(30, new ItemStack(glassPaneItem));
		menu.setItem(31, new ItemStack(glassPaneItem));
		menu.setItem(32, new ItemStack(glassPaneItem));
		menu.setItem(33, new ItemStack(glassPaneItem));
		menu.setItem(34, new ItemStack(glassPaneItem));
		menu.setItem(35, new ItemStack(glassPaneItem));
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
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
				if(i.getType() == Material.BARRIER){
					p.closeInventory();
				}else if(i.getType() != Material.STAINED_GLASS_PANE && i.getType() != Material.YELLOW_FLOWER){
					boolean deposit = false;
					int amount = Integer.parseInt(i.getItemMeta().getDisplayName().split(" ")[1].replace(Colors.BLACK, "").replace("$", ""));
					
					if(i.getItemMeta().getDisplayName().contains("Deposit")){
						deposit = true;
					}
					
					if(deposit){
						if(session.getMoneyInPocket() >= amount){
							session.setMoneyInPocket(session.getMoneyInPocket()-amount);
							session.setMoneyBank(session.getMoneyBank()+amount);
							Utils.changeItemMeta(event.getInventory().getItem(0), Colors.RESET + Colors.UNDERLINE + "Current Money statistics for account " + Colors.BOLD + session.getCharname() + Colors.RESET + Colors.BLACK + "  .", new String[] {"Bankaccount: " + session.getMoneyBank(), "Pocketmoney: " + session.getMoneyInPocket()});
							p.sendMessage("You have deposit $" + amount + " to your bankaccount.");
						}else{
							p.sendMessage(Colors.RED + "You don't have that amount of money in your pocket!");
						}
					}else{
						if(session.getMoneyBank() >= amount){
							session.setMoneyBank(session.getMoneyBank()-amount);
							session.setMoneyInPocket(session.getMoneyInPocket()+amount);
							Utils.changeItemMeta(event.getInventory().getItem(0), Colors.RESET + Colors.UNDERLINE + "Current Money statistics for account " + Colors.BOLD + session.getCharname() + Colors.RESET + Colors.BLACK + "  .", new String[] {"Bankaccount: " + session.getMoneyBank(), "Pocketmoney: " + session.getMoneyInPocket()});
							p.sendMessage("You have withdraw $" + amount + " from your bankaccount.");
						}else{
							p.sendMessage(Colors.RED + "You don't have that amount of money in your bankaccount!");
						}
					}
				}
			}
		}
	}
}
