package com.shinigami.oldmenus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class ClothingShop extends Menu{

	private PriceConfig priceConfig;
	private String title;
	private Inventory menu, helmetMenu, chestplateMenu, legginsMenu, bootsMenu;
	private int size;
	
	private ItemStack redHelmet, redChestplate, redLeggins, redBoots,
					yellowHelmet, yellowChestplate, yellowLeggins, yellowBoots,
					orangeHelmet, orangeChestplate, orangeLeggins, orangeBoots,
					silverHelmet, silverChestplate, silverLeggins, silverBoots,
					whiteHelmet, whiteChestplate, whiteLeggins, whiteBoots,
					goldHelmet, goldChestplate, goldLeggins, goldBoots,
					helmetMenuItem, chestplateMenuItem, legginsMenuItem, bootsMenuItem, backItem;
	
	public ClothingShop(){
		title = "Kleidungsladen";
		size = 9;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}

	private void createItems() {
		redArmor();
		yellowArmor();
		orangeArmor();
		silverArmor();
		whiteArmor();
		goldArmor();
		menuItems();
	}

	private void createInventory() {
		
		menu.addItem(helmetMenuItem);
		menu.addItem(chestplateMenuItem);
		menu.addItem(legginsMenuItem);
		menu.addItem(bootsMenuItem);
		
		helmetMenu.addItem(redHelmet);
		helmetMenu.addItem(yellowHelmet);
		helmetMenu.addItem(orangeHelmet);
		helmetMenu.addItem(silverHelmet);
		helmetMenu.addItem(whiteHelmet);
		helmetMenu.addItem(goldHelmet);
		helmetMenu.addItem(backItem);
		
		chestplateMenu.addItem(redChestplate);
		chestplateMenu.addItem(yellowChestplate);
		chestplateMenu.addItem(orangeChestplate);
		chestplateMenu.addItem(silverChestplate);
		chestplateMenu.addItem(whiteChestplate);
		chestplateMenu.addItem(goldChestplate);
		chestplateMenu.addItem(backItem);
		
		legginsMenu.addItem(redLeggins);
		legginsMenu.addItem(yellowLeggins);
		legginsMenu.addItem(orangeLeggins);
		legginsMenu.addItem(silverLeggins);
		legginsMenu.addItem(whiteLeggins);
		legginsMenu.addItem(goldLeggins);
		legginsMenu.addItem(backItem);
		
		bootsMenu.addItem(redBoots);
		bootsMenu.addItem(yellowBoots);
		bootsMenu.addItem(orangeBoots);
		bootsMenu.addItem(silverBoots);
		bootsMenu.addItem(whiteBoots);
		bootsMenu.addItem(goldBoots);
		bootsMenu.addItem(backItem);
	}

	@Override
	public void open(Player p, Session session) {
//		if(!session.isLoggedInAsCop()){
//			menu = Bukkit.createInventory(p, 9, title);
//			helmetMenu = Bukkit.createInventory(p, size, title);
//			chestplateMenu = Bukkit.createInventory(p, size, title);
//			legginsMenu = Bukkit.createInventory(p, size, title);
//			bootsMenu = Bukkit.createInventory(p, size, title);
//			createInventory();
//			p.openInventory(menu);
//		}else{
//			p.sendMessage(Colors.RED + "Polizisten können hier nicht einkaufen.");
//		}
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.ARROW){
					p.setItemOnCursor(null);
					p.closeInventory();
					p.openInventory(menu);
					session.setOpenMenu("clothingshop");
				}else if(i.getType() == Material.CHAINMAIL_HELMET){
					p.setItemOnCursor(null);
					p.closeInventory();
					p.openInventory(helmetMenu);
					session.setOpenMenu("clothingshop");
				}else if(i.getType() == Material.CHAINMAIL_CHESTPLATE){
					p.setItemOnCursor(null);
					p.closeInventory();
					p.openInventory(chestplateMenu);
					session.setOpenMenu("clothingshop");
				}else if(i.getType() == Material.CHAINMAIL_LEGGINGS){
					p.setItemOnCursor(null);
					p.closeInventory();
					p.openInventory(legginsMenu);
					session.setOpenMenu("clothingshop");
				}else if(i.getType() == Material.CHAINMAIL_BOOTS){
					p.setItemOnCursor(null);
					p.closeInventory();
					p.openInventory(bootsMenu);
					session.setOpenMenu("clothingshop");
				}
				
				if(i.getType() == Material.LEATHER_BOOTS){
					if(i.getItemMeta().getDisplayName().equals(redBoots.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("red_leatherclothing");
						boughtItem = new ItemStack(redBoots);
					}else if(i.getItemMeta().getDisplayName().equals(yellowBoots.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("yellow_leatherclothing");
						boughtItem = new ItemStack(yellowBoots);
					}else if(i.getItemMeta().getDisplayName().equals(orangeBoots.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("orange_leatherclothing");
						boughtItem = new ItemStack(orangeBoots);
					}else if(i.getItemMeta().getDisplayName().equals(silverBoots.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("silver_leatherclothing");
						boughtItem = new ItemStack(silverBoots);
					}else if(i.getItemMeta().getDisplayName().equals(whiteBoots.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("white_leatherclothing");
						boughtItem = new ItemStack(whiteBoots);
					}
				}else if(i.getType() == Material.GOLD_BOOTS){
					cost = priceConfig.buyPrices.get("gold_clothing");
					boughtItem = new ItemStack(goldBoots);
					
				}else if(i.getType() == Material.LEATHER_LEGGINGS){
					if(i.getItemMeta().getDisplayName().equals(redLeggins.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("red_leatherclothing");
						boughtItem = new ItemStack(redLeggins);
					}else if(i.getItemMeta().getDisplayName().equals(yellowLeggins.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("yellow_leatherclothing");
						boughtItem = new ItemStack(yellowLeggins);
					}else if(i.getItemMeta().getDisplayName().equals(orangeLeggins.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("orange_leatherclothing");
						boughtItem = new ItemStack(orangeLeggins);
					}else if(i.getItemMeta().getDisplayName().equals(silverLeggins.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("silver_leatherclothing");
						boughtItem = new ItemStack(silverLeggins);
					}else if(i.getItemMeta().getDisplayName().equals(whiteLeggins.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("white_leatherclothing");
						boughtItem = new ItemStack(whiteLeggins);
					}
				}else if(i.getType() == Material.GOLD_LEGGINGS){
					cost = priceConfig.buyPrices.get("gold_clothing");
					boughtItem = new ItemStack(goldLeggins);
					
				}else if(i.getType() == Material.LEATHER_CHESTPLATE){
					if(i.getItemMeta().getDisplayName().equals(redChestplate.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("red_leatherclothing");
						boughtItem = new ItemStack(redChestplate);
					}else if(i.getItemMeta().getDisplayName().equals(yellowChestplate.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("yellow_leatherclothing");
						boughtItem = new ItemStack(yellowChestplate);
					}else if(i.getItemMeta().getDisplayName().equals(orangeChestplate.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("orange_leatherclothing");
						boughtItem = new ItemStack(orangeChestplate);
					}else if(i.getItemMeta().getDisplayName().equals(silverChestplate.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("silver_leatherclothing");
						boughtItem = new ItemStack(silverChestplate);
					}else if(i.getItemMeta().getDisplayName().equals(whiteChestplate.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("white_leatherclothing");
						boughtItem = new ItemStack(whiteChestplate);
					}
				}else if(i.getType() == Material.GOLD_CHESTPLATE){
					cost = priceConfig.buyPrices.get("gold_clothing");
					boughtItem = new ItemStack(goldChestplate);
					
				}else if(i.getType() == Material.LEATHER_HELMET){
					if(i.getItemMeta().getDisplayName().equals(redHelmet.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("red_leatherclothing");
						boughtItem = new ItemStack(redHelmet);
					}else if(i.getItemMeta().getDisplayName().equals(yellowHelmet.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("yellow_leatherclothing");
						boughtItem = new ItemStack(yellowHelmet);
					}else if(i.getItemMeta().getDisplayName().equals(orangeHelmet.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("orange_leatherclothing");
						boughtItem = new ItemStack(orangeHelmet);
					}else if(i.getItemMeta().getDisplayName().equals(silverHelmet.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("silver_leatherclothing");
						boughtItem = new ItemStack(silverHelmet);
					}else if(i.getItemMeta().getDisplayName().equals(whiteHelmet.getItemMeta().getDisplayName())){
						cost = priceConfig.buyPrices.get("white_leatherclothing");
						boughtItem = new ItemStack(whiteHelmet);
					}
				}else if(i.getType() == Material.GOLD_HELMET){
					cost = priceConfig.buyPrices.get("gold_clothing");
					boughtItem = new ItemStack(goldHelmet);
				}

//				if(boughtItem != null){
//					if(money >= cost){
//						money-=cost;
//						
//						session.setMoneyPocket(money);
//						if(session.getClickedNpc() != null){
//							session.getClickedNpc().money+=cost;
//						}
//						
//						ItemMeta meta = boughtItem.getItemMeta();
//						meta.setDisplayName(meta.getDisplayName().replace(Colors.BLACK + "  [I]", ""));
//						meta.setLore(null);
//						boughtItem.setItemMeta(meta);
//						
//						inv.addItem(boughtItem);
//					}else{
//						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
//					}
//				}
			}
		}
	}
	
	private void redArmor(){
		List<String> redLore = new ArrayList<String>();
		redLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("red_leatherclothing"));
		
		redHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta redHelmetMeta = (LeatherArmorMeta) redHelmet.getItemMeta();
		redHelmetMeta.setColor(Color.RED);
		redHelmetMeta.setLore(redLore);
		redHelmetMeta.setDisplayName(Colors.RED + "Roter Lederhelm" + Colors.BLACK + "  [I]");
		redHelmet.setItemMeta(redHelmetMeta);
		
		redChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta redChestplateMeta = (LeatherArmorMeta) redChestplate.getItemMeta();
		redChestplateMeta.setColor(Color.RED);
		redChestplateMeta.setLore(redLore);
		redChestplateMeta.setDisplayName(Colors.RED + "Rote Lederjacke" + Colors.BLACK + "  [I]");
		redChestplate.setItemMeta(redChestplateMeta);
		
		redLeggins = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta redLeggingsMeta = (LeatherArmorMeta) redLeggins.getItemMeta();
		redLeggingsMeta.setColor(Color.RED);
		redLeggingsMeta.setLore(redLore);
		redLeggingsMeta.setDisplayName(Colors.RED + "Rote Lederhose" + Colors.BLACK + "  [I]");
		redLeggins.setItemMeta(redLeggingsMeta);
		
		redBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta redBootsMeta = (LeatherArmorMeta) redBoots.getItemMeta();
		redBootsMeta.setColor(Color.RED);
		redBootsMeta.setLore(redLore);
		redBootsMeta.setDisplayName(Colors.RED + "Rote Lederstiefel" + Colors.BLACK + "  [I]");
		redBoots.setItemMeta(redBootsMeta);
	}
	
	private void yellowArmor(){
		List<String> yellowLore = new ArrayList<String>();
		yellowLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("yellow_leatherclothing"));
		
		yellowHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta yellowHelmetMeta = (LeatherArmorMeta) yellowHelmet.getItemMeta();
		yellowHelmetMeta.setColor(Color.YELLOW);
		yellowHelmetMeta.setLore(yellowLore);
		yellowHelmetMeta.setDisplayName(Colors.YELLOW + "Gelber Lederhelm" + Colors.BLACK + "  [I]");
		yellowHelmet.setItemMeta(yellowHelmetMeta);
		
		yellowChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta yellowChestplateMeta = (LeatherArmorMeta) yellowChestplate.getItemMeta();
		yellowChestplateMeta.setColor(Color.YELLOW);
		yellowChestplateMeta.setLore(yellowLore);
		yellowChestplateMeta.setDisplayName(Colors.YELLOW + "Gelbe Lederjacke" + Colors.BLACK + "  [I]");
		yellowChestplate.setItemMeta(yellowChestplateMeta);
		
		yellowLeggins = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta yellowLeggingsMeta = (LeatherArmorMeta) yellowLeggins.getItemMeta();
		yellowLeggingsMeta.setColor(Color.YELLOW);
		yellowLeggingsMeta.setLore(yellowLore);
		yellowLeggingsMeta.setDisplayName(Colors.YELLOW + "Gelbe Lederhose" + Colors.BLACK + "  [I]");
		yellowLeggins.setItemMeta(yellowLeggingsMeta);
		
		yellowBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta yellowBootsMeta = (LeatherArmorMeta) yellowBoots.getItemMeta();
		yellowBootsMeta.setColor(Color.YELLOW);
		yellowBootsMeta.setLore(yellowLore);
		yellowBootsMeta.setDisplayName(Colors.YELLOW + "Gelbe Lederstiefel" + Colors.BLACK + "  [I]");
		yellowBoots.setItemMeta(yellowBootsMeta);
	}

	private void orangeArmor(){
		List<String> orangeLore = new ArrayList<String>();
		orangeLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("orange_leatherclothing"));
		
		orangeHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta orangeHelmetMeta = (LeatherArmorMeta) orangeHelmet.getItemMeta();
		orangeHelmetMeta.setColor(Color.ORANGE);
		orangeHelmetMeta.setLore(orangeLore);
		orangeHelmetMeta.setDisplayName(Colors.DARK_YELLOW + "Orange Lederhelm" + Colors.BLACK + "  [I]");
		orangeHelmet.setItemMeta(orangeHelmetMeta);
		
		orangeChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta orangeChestplateMeta = (LeatherArmorMeta) orangeChestplate.getItemMeta();
		orangeChestplateMeta.setColor(Color.ORANGE);
		orangeChestplateMeta.setLore(orangeLore);
		orangeChestplateMeta.setDisplayName(Colors.DARK_YELLOW + "Orange Lederjacke" + Colors.BLACK + "  [I]");
		orangeChestplate.setItemMeta(orangeChestplateMeta);
		
		orangeLeggins = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta orangeLeggingsMeta = (LeatherArmorMeta) orangeLeggins.getItemMeta();
		orangeLeggingsMeta.setColor(Color.ORANGE);
		orangeLeggingsMeta.setLore(orangeLore);
		orangeLeggingsMeta.setDisplayName(Colors.DARK_YELLOW + "Orange Lederhose" + Colors.BLACK + "  [I]");
		orangeLeggins.setItemMeta(orangeLeggingsMeta);
		
		orangeBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta orangeBootsMeta = (LeatherArmorMeta) orangeBoots.getItemMeta();
		orangeBootsMeta.setColor(Color.ORANGE);
		orangeBootsMeta.setLore(orangeLore);
		orangeBootsMeta.setDisplayName(Colors.DARK_YELLOW + "Orange Lederstiefel" + Colors.BLACK + "  [I]");
		orangeBoots.setItemMeta(orangeBootsMeta);
	}

	private void silverArmor(){
		List<String> silverLore = new ArrayList<String>();
		silverLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("silver_leatherclothing"));
		
		silverHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta silverHelmetMeta = (LeatherArmorMeta) silverHelmet.getItemMeta();
		silverHelmetMeta.setColor(Color.SILVER);
		silverHelmetMeta.setLore(silverLore);
		silverHelmetMeta.setDisplayName(Colors.GREY + "Silberner Lederhelm" + Colors.BLACK + "  [I]");
		silverHelmet.setItemMeta(silverHelmetMeta);
		
		silverChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta silverChestplateMeta = (LeatherArmorMeta) silverChestplate.getItemMeta();
		silverChestplateMeta.setColor(Color.SILVER);
		silverChestplateMeta.setLore(silverLore);
		silverChestplateMeta.setDisplayName(Colors.GREY + "Silberne Lederjacke" + Colors.BLACK + "  [I]");
		silverChestplate.setItemMeta(silverChestplateMeta);
		
		silverLeggins = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta silverLeggingsMeta = (LeatherArmorMeta) silverLeggins.getItemMeta();
		silverLeggingsMeta.setColor(Color.SILVER);
		silverLeggingsMeta.setLore(silverLore);
		silverLeggingsMeta.setDisplayName(Colors.GREY + "Silberne Lederhose" + Colors.BLACK + "  [I]");
		silverLeggins.setItemMeta(silverLeggingsMeta);
		
		silverBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta silverBootsMeta = (LeatherArmorMeta) silverBoots.getItemMeta();
		silverBootsMeta.setColor(Color.SILVER);
		silverBootsMeta.setLore(silverLore);
		silverBootsMeta.setDisplayName(Colors.GREY + "Silberne Lederstiefel" + Colors.BLACK + "  [I]");
		silverBoots.setItemMeta(silverBootsMeta);
	}

	private void whiteArmor(){
		List<String> whiteLore = new ArrayList<String>();
		whiteLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("white_leatherclothing"));
		
		whiteHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta whiteHelmetMeta = (LeatherArmorMeta) whiteHelmet.getItemMeta();
		whiteHelmetMeta.setColor(Color.WHITE);
		whiteHelmetMeta.setLore(whiteLore);
		whiteHelmetMeta.setDisplayName(Colors.WHITE + "Weißer Lederhelm" + Colors.BLACK + "  [I]");
		whiteHelmet.setItemMeta(whiteHelmetMeta);
		
		whiteChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta whiteChestplateMeta = (LeatherArmorMeta) whiteChestplate.getItemMeta();
		whiteChestplateMeta.setColor(Color.WHITE);
		whiteChestplateMeta.setLore(whiteLore);
		whiteChestplateMeta.setDisplayName(Colors.WHITE + "Weiße Lederjacke" + Colors.BLACK + "  [I]");
		whiteChestplate.setItemMeta(whiteChestplateMeta);
		
		whiteLeggins = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta whiteLeggingsMeta = (LeatherArmorMeta) whiteLeggins.getItemMeta();
		whiteLeggingsMeta.setColor(Color.WHITE);
		whiteLeggingsMeta.setLore(whiteLore);
		whiteLeggingsMeta.setDisplayName(Colors.WHITE + "Weiße Lederhose" + Colors.BLACK + "  [I]");
		whiteLeggins.setItemMeta(whiteLeggingsMeta);
		
		whiteBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta whiteBootsMeta = (LeatherArmorMeta) whiteBoots.getItemMeta();
		whiteBootsMeta.setColor(Color.WHITE);
		whiteBootsMeta.setLore(whiteLore);
		whiteBootsMeta.setDisplayName(Colors.WHITE + "Weiße Lederstiefel" + Colors.BLACK + "  [I]");
		whiteBoots.setItemMeta(whiteBootsMeta);
	}

	private void goldArmor(){
		List<String> goldLore = new ArrayList<String>();
		goldLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN +  priceConfig.buyPrices.get("gold_clothing"));
		
		goldHelmet = new ItemStack(Material.GOLD_HELMET);
		ItemMeta goldHelmetMeta = goldHelmet.getItemMeta();
		goldHelmetMeta.setDisplayName(Colors.DARK_YELLOW + "Goldhelm" + Colors.BLACK + "  [I]");
		goldHelmetMeta.setLore(goldLore);
		goldHelmet.setItemMeta(goldHelmetMeta);
		
		goldChestplate = new ItemStack(Material.GOLD_CHESTPLATE);
		ItemMeta goldChestplateMeta = goldChestplate.getItemMeta();
		goldChestplateMeta.setDisplayName(Colors.DARK_YELLOW + "Goldjacke" + Colors.BLACK + "  [I]");
		goldChestplateMeta.setLore(goldLore);
		goldChestplate.setItemMeta(goldChestplateMeta);
		
		goldLeggins = new ItemStack(Material.GOLD_LEGGINGS);
		ItemMeta goldLeggingsMeta = goldLeggins.getItemMeta();
		goldLeggingsMeta.setDisplayName(Colors.DARK_YELLOW + "Goldhose" + Colors.BLACK + "  [I]");
		goldLeggingsMeta.setLore(goldLore);
		goldLeggins.setItemMeta(goldLeggingsMeta);
		
		goldBoots = new ItemStack(Material.GOLD_BOOTS);
		ItemMeta goldBootsMeta = goldBoots.getItemMeta();
		goldBootsMeta.setDisplayName(Colors.DARK_YELLOW + "Goldstiefel" + Colors.BLACK + "  [I]");
		goldBootsMeta.setLore(goldLore);
		goldBoots.setItemMeta(goldBootsMeta);
	}

	private void menuItems(){
		
		helmetMenuItem = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta helmetMenuMeta = helmetMenuItem.getItemMeta();
		helmetMenuMeta.setDisplayName(Colors.LILA + "Helme" + Colors.BLACK + "  [I]");
		helmetMenuItem.setItemMeta(helmetMenuMeta);
		
		chestplateMenuItem = new ItemStack(Material.CHAINMAIL_CHESTPLATE);
		ItemMeta chestplateMenuMeta = chestplateMenuItem.getItemMeta();
		chestplateMenuMeta.setDisplayName(Colors.LILA + "Jacken" + Colors.BLACK + "  [I]");
		chestplateMenuItem.setItemMeta(chestplateMenuMeta);
		
		legginsMenuItem = new ItemStack(Material.CHAINMAIL_LEGGINGS);
		ItemMeta legginsMenuMeta = legginsMenuItem.getItemMeta();
		legginsMenuMeta.setDisplayName(Colors.LILA + "Hosen" + Colors.BLACK + "  [I]");
		legginsMenuItem.setItemMeta(legginsMenuMeta);
		
		bootsMenuItem = new ItemStack(Material.CHAINMAIL_BOOTS);
		ItemMeta bootsMenuMeta = bootsMenuItem.getItemMeta();
		bootsMenuMeta.setDisplayName(Colors.LILA + "Stiefel" + Colors.BLACK + "  [I]");
		bootsMenuItem.setItemMeta(bootsMenuMeta);
		
		backItem = new ItemStack(Material.ARROW);
		ItemMeta backMeta = backItem.getItemMeta();
		backMeta.setDisplayName(Colors.RED + "Zurück" + Colors.BLACK + "  [I]");
		backItem.setItemMeta(backMeta);
	}
}
