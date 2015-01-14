package com.shinigami.menus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class GeneralShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack appleItem, chickenItem, cookedChickenItem, rawBeefItem, steakItem, waterItem, milkItem, fassbrauseItem, cigaretItem, cheapNightvisionItem, nightvisionItem,
						cookieItem, carrotItem, berriesItem, beerItem, wodkaItem;
	
	public GeneralShop(){
		title = "Allgemeiner Laden";
		size = 18;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {
		
		appleItem = new ItemStack(Material.APPLE);
		ItemMeta appleMeta = appleItem.getItemMeta();
		appleMeta.setDisplayName(Colors.WHITE + "Apfel" + Colors.BLACK + "  [I]");
		ArrayList<String> appleLore = new ArrayList<String>();
		appleLore.add("Ein leckerer Apfel.");
		appleLore.add(" ");
		appleLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("apple"));
		appleMeta.setLore(appleLore);
		appleItem.setItemMeta(appleMeta);
		
		cookieItem = new ItemStack(Material.COOKIE);
		ItemMeta cookieMeta = cookieItem.getItemMeta();
		cookieMeta.setDisplayName(Colors.WHITE + "Keks" + Colors.BLACK + "  [I]");
		ArrayList<String> cookieLore = new ArrayList<String>();
		cookieLore.add("Ein leckerer Keks.");
		cookieLore.add(" ");
		cookieLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("cookie"));
		cookieMeta.setLore(cookieLore);
		cookieItem.setItemMeta(cookieMeta);
		
		carrotItem = new ItemStack(Material.CARROT_ITEM);
		ItemMeta carrotMeta = carrotItem.getItemMeta();
		carrotMeta.setDisplayName(Colors.WHITE + "Karrote" + Colors.BLACK + "  [I]");
		ArrayList<String> carrotLore = new ArrayList<String>();
		carrotLore.add("Eine leckere Karrote.");
		carrotLore.add(" ");
		carrotLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("carrot"));
		carrotMeta.setLore(carrotLore);
		carrotItem.setItemMeta(carrotMeta);
		
		berriesItem = new ItemStack(Material.NETHER_WARTS);
		ItemMeta berriesMeta = berriesItem.getItemMeta();
		berriesMeta.setDisplayName(Colors.WHITE + "Weintrauben" + Colors.BLACK + "  [I]");
		ArrayList<String> berriesLore = new ArrayList<String>();
		berriesLore.add("Einige leckere Weintrauben.");
		berriesLore.add(" ");
		berriesLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("berries"));
		berriesMeta.setLore(berriesLore);
		berriesItem.setItemMeta(berriesMeta);
		
		chickenItem = new ItemStack(Material.RAW_CHICKEN);
		ItemMeta chickenMeta = chickenItem.getItemMeta();
		chickenMeta.setDisplayName(Colors.WHITE + "rohes Hühnchen" + Colors.BLACK + "  [I]");
		ArrayList<String> chickenLore = new ArrayList<String>();
		chickenLore.add("Ein leckeres Hühnchen.");
		chickenLore.add(" ");
		chickenLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("chicken"));
		chickenMeta.setLore(chickenLore);
		chickenItem.setItemMeta(chickenMeta);
		
		cookedChickenItem = new ItemStack(Material.COOKED_CHICKEN);
		ItemMeta cookedChickenMeta = cookedChickenItem.getItemMeta();
		cookedChickenMeta.setDisplayName(Colors.WHITE + "gebratenes Hühnchen" + Colors.BLACK + "  [I]");
		ArrayList<String> cookedChickenLore = new ArrayList<String>();
		cookedChickenLore.add("Ein leckeres gebratenes Hühnchen.");
		cookedChickenLore.add(" ");
		cookedChickenLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("cooked_chicken"));
		cookedChickenMeta.setLore(cookedChickenLore);
		cookedChickenItem.setItemMeta(cookedChickenMeta);
		
		rawBeefItem = new ItemStack(Material.RAW_BEEF);
		ItemMeta rawBeefMeta = rawBeefItem.getItemMeta();
		rawBeefMeta.setDisplayName(Colors.WHITE + "rohes Fleisch" + Colors.BLACK + "  [I]");
		ArrayList<String> rawBeefLore = new ArrayList<String>();
		rawBeefLore.add("Ein leckeres Stück Fleisch.");
		rawBeefLore.add(" ");
		rawBeefLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("raw_beef"));
		rawBeefMeta.setLore(rawBeefLore);
		rawBeefItem.setItemMeta(rawBeefMeta);
		
		steakItem = new ItemStack(Material.COOKED_BEEF);
		ItemMeta steakMeta = steakItem.getItemMeta();
		steakMeta.setDisplayName(Colors.WHITE + "gebratenes Fleisch" + Colors.BLACK + "  [I]");
		ArrayList<String> steakLore = new ArrayList<String>();
		steakLore.add("Ein leckeres Stück gebratenes Fleisch.");
		steakLore.add(" ");
		steakLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("steak"));
		steakMeta.setLore(steakLore);
		steakItem.setItemMeta(steakMeta);
		
		waterItem = new ItemStack(Material.POTION);
		ItemMeta waterMeta = waterItem.getItemMeta();
		waterMeta.setDisplayName(Colors.WHITE + "Wasser" + Colors.BLACK + "  [I]");
		ArrayList<String> waterLore = new ArrayList<String>();
		waterLore.add("Ein Eimer mit dem besten Wasser.");
		waterLore.add(" ");
		waterLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("water"));
		waterMeta.setLore(waterLore);
		waterItem.setItemMeta(waterMeta);
		
		milkItem = new ItemStack(Material.POTION, 1, (short) 14);
		ItemMeta milkMeta = milkItem.getItemMeta();
		milkMeta.setDisplayName(Colors.WHITE + "Milch" + Colors.BLACK + "  [I]");
		ArrayList<String> milkLore = new ArrayList<String>();
		milkLore.add("Ein leckerer Eimer voll Milch.");
		milkLore.add(" ");
		milkLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("milk"));
		milkMeta.setLore(milkLore);
		milkItem.setItemMeta(milkMeta);
		
		fassbrauseItem = new ItemStack(Material.POTION, 1, (short) 5);
		ItemMeta fassbrauseMeta = fassbrauseItem.getItemMeta();
		fassbrauseMeta.setDisplayName(Colors.WHITE + "Fassbrause" + Colors.BLACK + "  [I]");
		ArrayList<String> fassbrauseLore = new ArrayList<String>();
		fassbrauseLore.add("Ein leckerer Eimer voll Fassbrause.");
		fassbrauseLore.add(" ");
		fassbrauseLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("fassbrause"));
		fassbrauseMeta.setLore(fassbrauseLore);
		fassbrauseItem.setItemMeta(fassbrauseMeta);
		
		cigaretItem = new ItemStack(Material.STICK);
		ItemMeta cigaretMeta = cigaretItem.getItemMeta();
		cigaretMeta.setDisplayName(Colors.GREY + "Zigarrete" + Colors.BLACK + "  [I]");
		ArrayList<String> cigaretLore = new ArrayList<String>();
		cigaretLore.add("Eine ungesunde Zigarrete.");
		cigaretLore.add(" ");
		cigaretLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("cigaret"));
		cigaretMeta.setLore(cigaretLore);
		cigaretItem.setItemMeta(cigaretMeta);
		
		cheapNightvisionItem = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta cheapNightvisionMeta = cheapNightvisionItem.getItemMeta();
		cheapNightvisionMeta.setDisplayName(Colors.LILA + "billiges Nachtsichtgerät" + Colors.BLACK + "  [I]");
		ArrayList<String> cheapNightvisionLore = new ArrayList<String>();
		cheapNightvisionLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("cheap_nightvision"));
		cheapNightvisionMeta.setLore(cheapNightvisionLore);
		cheapNightvisionItem.setItemMeta(cheapNightvisionMeta);
		
		nightvisionItem = new ItemStack(Material.CHAINMAIL_HELMET);
		ItemMeta nightvisionMeta = nightvisionItem.getItemMeta();
		nightvisionMeta.setDisplayName(Colors.PINK + "Nachtsichtgerät" + Colors.BLACK + "  [I]");
		ArrayList<String> nightvisionLore = new ArrayList<String>();
		nightvisionLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("nightvision"));
		nightvisionMeta.setLore(nightvisionLore);
		nightvisionItem.setItemMeta(nightvisionMeta);
		
		beerItem = new ItemStack(Material.POTION, 1, (short) 12);
		ItemMeta beerMeta = beerItem.getItemMeta();
		beerMeta.setDisplayName(Colors.GREY + "Bier" + Colors.BLACK + "  [I]");
		ArrayList<String> beerLore = new ArrayList<String>();
		beerLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("beer"));
		beerMeta.setLore(beerLore);
		beerItem.setItemMeta(beerMeta);
		
		wodkaItem = new ItemStack(Material.POTION, 1, (short) 2);
		ItemMeta wodkaMeta = wodkaItem.getItemMeta();
		wodkaMeta.setDisplayName(Colors.GREY + "Wodka" + Colors.BLACK + "  [I]");
		ArrayList<String> wodkaLore = new ArrayList<String>();
		wodkaLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("wodka"));
		wodkaMeta.setLore(wodkaLore);
		wodkaItem.setItemMeta(wodkaMeta);
	}
	
	private void createInventory(Session session) {
		
		menu.addItem(appleItem);
		menu.addItem(cookieItem);
		menu.addItem(carrotItem);
		menu.addItem(berriesItem);
		menu.addItem(berriesItem);
		menu.addItem(chickenItem);
		menu.addItem(cookedChickenItem);
		menu.addItem(rawBeefItem);
		menu.addItem(steakItem);
		menu.addItem(waterItem);
		menu.addItem(milkItem);
		menu.addItem(fassbrauseItem);
		menu.addItem(cheapNightvisionItem);
		menu.addItem(nightvisionItem);
		
		if(session.getSide().equals("civ")){
			menu.addItem(cigaretItem);
			menu.addItem(beerItem);
			menu.addItem(wodkaItem);
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory(session);
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.APPLE && i.getItemMeta().getDisplayName().equals(appleItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("apple");
					boughtItem = new ItemStack(appleItem);
				}else if(i.getType() == Material.RAW_CHICKEN && i.getItemMeta().getDisplayName().equals(chickenItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("chicken");
					boughtItem = new ItemStack(chickenItem);
				}else if(i.getType() == Material.COOKIE && i.getItemMeta().getDisplayName().equals(cookieItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("cookie");
					boughtItem = new ItemStack(cookieItem);
				}else if(i.getType() == Material.CARROT_ITEM && i.getItemMeta().getDisplayName().equals(carrotItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("carrot");
					boughtItem = new ItemStack(carrotItem);
				}else if(i.getType() == Material.NETHER_WARTS && i.getItemMeta().getDisplayName().equals(berriesItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("berries");
					boughtItem = new ItemStack(berriesItem);
				}else if(i.getType() == Material.COOKED_CHICKEN && i.getItemMeta().getDisplayName().equals(cookedChickenItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("cooked_chicken");
					boughtItem = new ItemStack(cookedChickenItem);
				}else if(i.getType() == Material.RAW_BEEF && i.getItemMeta().getDisplayName().equals(rawBeefItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("raw_beef");
					boughtItem = new ItemStack(rawBeefItem);
				}else if(i.getType() == Material.COOKED_BEEF && i.getItemMeta().getDisplayName().equals(steakItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("steak");
					boughtItem = new ItemStack(steakItem);
				}else if(i.getItemMeta().getDisplayName().equals(waterItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("water");
					boughtItem = new ItemStack(waterItem);
				}else if(i.getItemMeta().getDisplayName().equals(milkItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("milk");
					boughtItem = new ItemStack(milkItem);
				}else if( i.getItemMeta().getDisplayName().equals(fassbrauseItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("fassbrause");
					boughtItem = new ItemStack(fassbrauseItem);
				}else if(i.getType() == Material.STICK && i.getItemMeta().getDisplayName().equals(cigaretItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("cigaret");
					boughtItem = new ItemStack(cigaretItem);
				}else if(i.getType() == Material.CHAINMAIL_HELMET && i.getItemMeta().getDisplayName().equals(cheapNightvisionItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("cheap_nightvision");
					boughtItem = new ItemStack(cheapNightvisionItem);
				}else if(i.getType() == Material.CHAINMAIL_HELMET && i.getItemMeta().getDisplayName().equals(nightvisionItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("nightvision");
					boughtItem = new ItemStack(nightvisionItem);
				}else if(i.getType() == Material.POTION && i.getItemMeta().getDisplayName().equals(beerItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("beer");
					boughtItem = new ItemStack(beerItem);
				}else if(i.getType() == Material.POTION && i.getItemMeta().getDisplayName().equals(wodkaItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("wodka");
					boughtItem = new ItemStack(wodkaItem);
				}
				
				if(boughtItem != null){
					if(money >= cost){
						money-=cost;
	
						session.setMoneyPocket(money);
						if(session.getClickedNpc() != null){
							session.getClickedNpc().money+=cost;
						}
							
						ItemMeta meta = boughtItem.getItemMeta();
						meta.setDisplayName(meta.getDisplayName().replace(Colors.BLACK + "  [I]", ""));
						meta.setLore(null);
						boughtItem.setItemMeta(meta);
						
						inv.addItem(boughtItem);
					}else{
						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
					}
				}
			}
		}
	}
	
}
