package com.shinigami.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.configs.Configuration;
import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.scripts.PlayerScripts;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class ProcessingMenu extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title, licence;
	private int size;
	
	private ItemStack processItem;
	
	public ProcessingMenu(String licence){
		this.licence = licence;
		title = "Verarbeiten";
		size = 9;
		this.priceConfig = Configuration.getPriceConfig();
		createItems();
	}
	
	private void createItems() {
		
		processItem = new ItemStack(Material.HOPPER);
		ItemMeta increaseBackpackMeta = processItem.getItemMeta();
		increaseBackpackMeta.setDisplayName(Colors.WHITE + "Verarbeiten" + Colors.BLACK + "  [I]");
		ArrayList<String> increaseBackpackLore = new ArrayList<String>();
		increaseBackpackMeta.setLore(increaseBackpackLore);
		processItem.setItemMeta(increaseBackpackMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(processItem);
	}
	
	@Override
	public void open(Player p, Session session) {
		if(session.isLoggedInAsCop()){
			if(session.getProcessing().processingItem == null){
				if(session.getLicences().get(licence)){
					menu = Bukkit.createInventory(p, size, title);
					createInventory();
					p.openInventory(menu);
				}else{
					p.sendMessage(Colors.GREY + "Du hast nicht die benötigte Lizenz.");
					ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {licence});
				}
			}else{
				p.sendMessage(Colors.RED + "Du verarbeitest bereits etwas.");
			}
		}else{
			p.sendMessage(Colors.RED + "Polizisten können nicht verarbeiten.");
		}
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(i.getType() == Material.HOPPER && i.getItemMeta().getDisplayName().equals(processItem.getItemMeta().getDisplayName())){
					PlayerScripts.processingStart(p, licence, session, session.getClickedNpc().npcEntity);
				}
			}
		}
	}

}
