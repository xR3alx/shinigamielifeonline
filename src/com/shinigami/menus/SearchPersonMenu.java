package com.shinigami.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.SkullType;
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
import com.shinigami.utils.Utils;

public class SearchPersonMenu extends Menu{
	
	private PriceConfig priceConfig;
	private Inventory menu, impoundInventory;
	private String title;
	private int size;
	
	private ItemStack personItem, alcoholItem, drugsInBloodItem, licenceItem, impoundItems, wantedItem;
	
	public SearchPersonMenu(){
		this.priceConfig = Configuration.getPriceConfig();
		title = "Durchsuchen (Person)";
		size = 9;
		createItems();
	}
	
	private void createItems() {

		personItem = new ItemStack(Material.SKULL_ITEM, 1, (short)SkullType.PLAYER.ordinal());
		alcoholItem = new ItemStack(Material.POTION);
		drugsInBloodItem = new ItemStack(Material.SUGAR);
		licenceItem = new ItemStack(Material.BOOK);
		
		wantedItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta wantedItemMeta = wantedItem.getItemMeta();
		wantedItemMeta.setDisplayName(Colors.RED + "Wird gesucht!" + Colors.BLACK + "  [I]");
		wantedItem.setItemMeta(wantedItemMeta);
		
		impoundItems = new ItemStack(Material.CHEST);
		ItemMeta impoundItemsMeta = impoundItems.getItemMeta();
		impoundItemsMeta.setDisplayName("Items im Inventar beschlagnahmen" + Colors.BLACK + "  [I]");
		impoundItems.setItemMeta(impoundItemsMeta);
		
	}
	
	private void createInventory(Session session, Player p) {
		
		Session sessionT = ShinigamiLife.getSessionManager().getSession(session.getSearchedPlayer().getUniqueId() + "");
		
		ItemMeta personMeta = personItem.getItemMeta();
		personMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Personenname: " + Colors.RESET + session.getSearchedPlayer().getName() + Colors.BLACK + "  [I]");
		personItem.setItemMeta(personMeta);
		
		ItemMeta alcoholMeta = alcoholItem.getItemMeta();
		alcoholMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Promile: " + Colors.RESET + sessionT.getAlcoholPromile() + Colors.BLACK + "  [I]");
		alcoholItem.setItemMeta(alcoholMeta);
		
		ItemMeta drugMeta = drugsInBloodItem.getItemMeta();
		drugMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Drogen im Blut: " + Colors.RESET + sessionT.getDrugsInBlood() + Colors.BLACK + "  [I]");
		drugsInBloodItem.setItemMeta(drugMeta);
		
		ItemMeta licenceMeta = licenceItem.getItemMeta();
		licenceMeta.setDisplayName(Colors.YELLOW + Colors.UNDERLINE + "Lizenzen");
		ArrayList<String> licenceLore = new ArrayList<String>();
		for(String s : sessionT.getLicences().keySet()){
			licenceLore.add(Utils.changeLicences(s) + "  " + Utils.changeTruFalse(sessionT.getLicences().get(s).toString()));
		}
		licenceMeta.setLore(licenceLore);
		licenceItem.setItemMeta(licenceMeta);
		
		menu.addItem(personItem);
		menu.addItem(alcoholItem);
		menu.addItem(drugsInBloodItem);
		menu.addItem(licenceItem);
		menu.addItem(impoundItems);
		
		if(ShinigamiLife.getPoliceManager().wantedList.isOnList(p)){
			menu.addItem(wantedItem);
		}
		
		for(ItemStack i : session.getSearchedPlayer().getInventory().getContents()){
			if(i != null){
				if(i.hasItemMeta()){
					if(!i.getItemMeta().getDisplayName().contains("Spielermenü") && !i.getItemMeta().getDisplayName().contains("Polizeimenü")){
						impoundInventory.addItem(i);
					}
				}
			}
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		impoundInventory = Bukkit.createInventory(p, 54, "Items beschlagnahmen (Person)");
		createInventory(session, p);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(i.getItemMeta().getDisplayName().equals(impoundItems.getItemMeta().getDisplayName())){
					p.closeInventory();
					p.openInventory(impoundInventory);
				}else{
					int impoundedMoney = 0;
					
					String itemName = Utils.changeDisplayname(i.getItemMeta().getDisplayName(), true);
					if(priceConfig.sellPrices.containsKey(itemName)){
						impoundedMoney = priceConfig.sellPrices.get(itemName) * i.getAmount();
						impoundedMoney = impoundedMoney * 15 / 100;
						p.sendMessage(Colors.GREY + "Du hast " + Colors.WHITE + i.getItemMeta().getDisplayName() + Colors.GREY + " x" + Colors.WHITE + i.getAmount() + Colors.GREY + " beschlagnahmt und " + Colors.WHITE + impoundedMoney + Colors.GREY + " als Belohnung bekommen.");
					}
					
					impoundInventory.remove(i);
					Player holder = session.getSearchedPlayer();
					holder.getInventory().remove(i);
					holder.sendMessage(Colors.WHITE + p.getName() + Colors.GREY + " hat " + Colors.WHITE + i.getItemMeta().getDisplayName() + Colors.GREY + " x" + Colors.WHITE + i.getAmount() + Colors.GREY + " beschlagnahmt.");
				}
			}
		}
	}
}
