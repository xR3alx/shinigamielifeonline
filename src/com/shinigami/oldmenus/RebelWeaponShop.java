package com.shinigami.oldmenus;

import java.util.ArrayList;
import java.util.List;

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
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;

public class RebelWeaponShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack desertEagleItem, ak47Item, g3Item, m14Item, m60e4Item, barretItem, m1014Item, skorpionItem, granatenwerferItem,
						pistolAmmoItem, gunAmmoItem, sniperAmmoItem, pumpgunAmmoItem, mpAmmoItem, throwerAmmoItem, mgAmmoItem;
	
	public RebelWeaponShop(){
		title = "Rebellen Waffenladen";
		size = 18;
		this.priceConfig = Configuration.getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		desertEagleItem = new ItemStack(Material.STONE_SWORD);
		ItemMeta desertEagleMeta = desertEagleItem.getItemMeta();
		desertEagleMeta.setDisplayName(Colors.DARK_GREY + "[P] " + Colors.RESET + "Desert Eagle" + Colors.BLACK + "  [I]");
		List<String> desertEagleLore = new ArrayList<String>();
		desertEagleLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("desertEagle"));
		desertEagleMeta.setLore(desertEagleLore);
		desertEagleItem.setItemMeta(desertEagleMeta);
		
		ak47Item = new ItemStack(Material.WOOD_SPADE);
		ItemMeta ak47Meta = ak47Item.getItemMeta();
		ak47Meta.setDisplayName(Colors.DARK_GREY + "[G] " + Colors.RESET + "AK-47" + Colors.BLACK + "  [I]");
		List<String> ak47Lore = new ArrayList<String>();
		ak47Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("ak47"));
		ak47Meta.setLore(ak47Lore);
		ak47Item.setItemMeta(ak47Meta);
		
		g3Item = new ItemStack(Material.GOLD_SPADE);
		ItemMeta g3Meta = g3Item.getItemMeta();
		g3Meta.setDisplayName(Colors.DARK_GREY + "[G] " + Colors.RESET + "G3" + Colors.BLACK + "  [I]");
		List<String> g3Lore = new ArrayList<String>();
		g3Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("g3"));
		g3Meta.setLore(g3Lore);
		g3Item.setItemMeta(g3Meta);
		
		m14Item = new ItemStack(Material.GOLD_SPADE);
		ItemMeta m14Meta = m14Item.getItemMeta();
		m14Meta.setDisplayName(Colors.DARK_GREY + "[G] " + Colors.RESET + "M14" + Colors.BLACK + "  [I]");
		List<String> m14Lore = new ArrayList<String>();
		m14Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m14"));
		m14Meta.setLore(m14Lore);
		m14Item.setItemMeta(m14Meta);
		
		m60e4Item = new ItemStack(Material.GOLD_HOE);
		ItemMeta m60e4Meta = m60e4Item.getItemMeta();
		m60e4Meta.setDisplayName(Colors.DARK_GREY + "[MG] " + Colors.RESET + "M60E4" + Colors.BLACK + "  [I]");
		List<String> m60e4Lore = new ArrayList<String>();
		m60e4Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m60e4Reb"));
		m60e4Meta.setLore(m60e4Lore);
		m60e4Item.setItemMeta(m60e4Meta);
		
		barretItem = new ItemStack(Material.STONE_SPADE);
		ItemMeta barretMeta = barretItem.getItemMeta();
		barretMeta.setDisplayName(Colors.DARK_GREY + "[S] " + Colors.RESET + "Barret" + Colors.BLACK + "  [I]");
		List<String> barretLore = new ArrayList<String>();
		barretLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("barret"));
		barretMeta.setLore(barretLore);
		barretItem.setItemMeta(barretMeta);
		
		m1014Item = new ItemStack(Material.WOOD_PICKAXE);
		ItemMeta m1014Meta = m1014Item.getItemMeta();
		m1014Meta.setDisplayName(Colors.DARK_GREY + "[PG] " + Colors.RESET + "M1014" + Colors.BLACK + "  [I]");
		List<String> m1014Lore = new ArrayList<String>();
		m1014Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m1014"));
		m1014Meta.setLore(m1014Lore);
		m1014Item.setItemMeta(m1014Meta);
		
		skorpionItem = new ItemStack(Material.WOOD_AXE);
		ItemMeta skorpionMeta = skorpionItem.getItemMeta();
		skorpionMeta.setDisplayName(Colors.DARK_GREY + "[MP] " + Colors.RESET + "Skorpion" + Colors.BLACK + "  [I]");
		List<String> skorpionLore = new ArrayList<String>();
		skorpionLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("skorpion"));
		skorpionMeta.setLore(skorpionLore);
		skorpionItem.setItemMeta(skorpionMeta);
		
		granatenwerferItem = new ItemStack(Material.STONE_HOE);
		ItemMeta granatenwerferMeta = granatenwerferItem.getItemMeta();
		granatenwerferMeta.setDisplayName(Colors.DARK_GREY + "[T] " + Colors.RESET + "Granatenwerfer" + Colors.BLACK + "  [I]");
		List<String> granatenwerferLore = new ArrayList<String>();
		granatenwerferLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("granatenwerfer"));
		granatenwerferMeta.setLore(granatenwerferLore);
		granatenwerferItem.setItemMeta(granatenwerferMeta);
		
		pistolAmmoItem = new ItemStack(Material.ROTTEN_FLESH);
		pistolAmmoItem.setAmount(4);
		ItemMeta pistolAmmoMeta = pistolAmmoItem.getItemMeta();
		pistolAmmoMeta.setDisplayName(Colors.GREY + "Pistolenmagazin" + Colors.BLACK + "  [I]");
		List<String> pistolAmmoLore = new ArrayList<String>();
		pistolAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("pistolAmmo"));
		pistolAmmoMeta.setLore(pistolAmmoLore);
		pistolAmmoItem.setItemMeta(pistolAmmoMeta);
		
		gunAmmoItem = new ItemStack(Material.CLAY_BRICK);
		gunAmmoItem.setAmount(4);
		ItemMeta gunAmmoMeta = gunAmmoItem.getItemMeta();
		gunAmmoMeta.setDisplayName(Colors.GREY + "Gewehrmagazin" + Colors.BLACK + "  [I]");
		List<String> gunAmmoLore = new ArrayList<String>();
		gunAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("gunAmmo"));
		gunAmmoMeta.setLore(gunAmmoLore);
		gunAmmoItem.setItemMeta(gunAmmoMeta);
		
		sniperAmmoItem = new ItemStack(Material.GHAST_TEAR);
		sniperAmmoItem.setAmount(4);
		ItemMeta sniperAmmoMeta = sniperAmmoItem.getItemMeta();
		sniperAmmoMeta.setDisplayName(Colors.GREY + "Scharfschuetzengewehrmagazin" + Colors.BLACK + "  [I]");
		List<String> sniperAmmoLore = new ArrayList<String>();
		sniperAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("sniperAmmo"));
		sniperAmmoMeta.setLore(sniperAmmoLore);
		sniperAmmoItem.setItemMeta(sniperAmmoMeta);
		
		pumpgunAmmoItem = new ItemStack(Material.GLOWSTONE_DUST);
		pumpgunAmmoItem.setAmount(4);
		ItemMeta pumpgunAmmoMeta = pumpgunAmmoItem.getItemMeta();
		pumpgunAmmoMeta.setDisplayName(Colors.GREY + "Schrotladung" + Colors.BLACK + "  [I]");
		List<String> pumpgunAmmoLore = new ArrayList<String>();
		pumpgunAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("pumpgunAmmo"));
		pumpgunAmmoMeta.setLore(pumpgunAmmoLore);
		pumpgunAmmoItem.setItemMeta(pumpgunAmmoMeta);
		
		mpAmmoItem = new ItemStack(Material.FLINT);
		mpAmmoItem.setAmount(4);
		ItemMeta mpAmmoMeta = mpAmmoItem.getItemMeta();
		mpAmmoMeta.setDisplayName(Colors.GREY + "Maschinenpistolenmagazin" + Colors.BLACK + "  [I]");
		List<String> mpAmmoLore = new ArrayList<String>();
		mpAmmoLore.add(Colors.DARK_GREEN  + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("mpAmmo"));
		mpAmmoMeta.setLore(mpAmmoLore);
		mpAmmoItem.setItemMeta(mpAmmoMeta);
		
		throwerAmmoItem = new ItemStack(Material.CLAY_BALL);
		throwerAmmoItem.setAmount(4);
		ItemMeta throwerAmmoMeta = throwerAmmoItem.getItemMeta();
		throwerAmmoMeta.setDisplayName(Colors.GREY + "Granaten" + Colors.BLACK + "  [I]");
		List<String> throwerAmmoLore = new ArrayList<String>();
		throwerAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("throwerAmmo"));
		throwerAmmoMeta.setLore(throwerAmmoLore);
		throwerAmmoItem.setItemMeta(throwerAmmoMeta);
		
		mgAmmoItem = new ItemStack(Material.SLIME_BALL);
		mgAmmoItem.setAmount(4);
		ItemMeta mgAmmoMeta = mgAmmoItem.getItemMeta();
		mgAmmoMeta.setDisplayName(Colors.GREY + "Trommelmagazin" + Colors.BLACK + "  [I]");
		List<String> mgAmmoLore = new ArrayList<String>();
		mgAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("mgAmmo"));
		mgAmmoMeta.setLore(mgAmmoLore);
		mgAmmoItem.setItemMeta(mgAmmoMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(desertEagleItem);
		menu.addItem(pistolAmmoItem);
		menu.addItem(ak47Item);
		menu.addItem(g3Item);
		menu.addItem(gunAmmoItem);
		menu.addItem(m60e4Item);
		menu.addItem(mgAmmoItem);
		menu.addItem(barretItem);
		menu.addItem(sniperAmmoItem);
		menu.addItem(m1014Item);
		menu.addItem(pumpgunAmmoItem);
		menu.addItem(skorpionItem);
		menu.addItem(mpAmmoItem);
		menu.addItem(granatenwerferItem);
		menu.addItem(throwerAmmoItem);
	}
	
	@Override
	public void open(Player p, Session session) {
//		if(session.getLicence(Licence.REBELLICENCE)){
//			menu = Bukkit.createInventory(p, size, title);
//			createInventory();
//			p.openInventory(menu);
//		}else{
//			p.sendMessage(Colors.GREY + "Du hast nicht die benötigte Lizenz.");
//			ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {Licence.REBELLICENCE});
//		}
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
				
//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.STONE_SWORD && i.getItemMeta().getDisplayName().equals(desertEagleItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("desertEagle");
					boughtItem = new ItemStack(desertEagleItem);
				}else if(i.getType() == Material.WOOD_SPADE && i.getItemMeta().getDisplayName().equals(ak47Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("ak47");
					boughtItem = new ItemStack(ak47Item);
				}else if(i.getType() == Material.GOLD_SPADE && i.getItemMeta().getDisplayName().equals(g3Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("g3");
					boughtItem = new ItemStack(g3Item);
				}else if(i.getType() == Material.GOLD_HOE && i.getItemMeta().getDisplayName().equals(m60e4Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("m60e4Reb");
					boughtItem = new ItemStack(m60e4Item);
				}else if(i.getType() == Material.STONE_SPADE && i.getItemMeta().getDisplayName().equals(barretItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("barret");
					boughtItem = new ItemStack(barretItem);
				}else if(i.getType() == Material.WOOD_PICKAXE && i.getItemMeta().getDisplayName().equals(m1014Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("m1014");
					boughtItem = new ItemStack(m1014Item);
				}else if(i.getType() == Material.WOOD_AXE && i.getItemMeta().getDisplayName().equals(skorpionItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("skorpion");
					boughtItem = new ItemStack(skorpionItem);
				}else if(i.getType() == Material.STONE_HOE && i.getItemMeta().getDisplayName().equals(granatenwerferItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("granatenwerfer");
					boughtItem = new ItemStack(granatenwerferItem);
				}else if(i.getType() == Material.ROTTEN_FLESH && i.getItemMeta().getDisplayName().equals(pistolAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("pistolAmmo");
					boughtItem = new ItemStack(pistolAmmoItem);
				}else if(i.getType() == Material.CLAY_BRICK && i.getItemMeta().getDisplayName().equals(gunAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("gunAmmo");
					boughtItem = new ItemStack(gunAmmoItem);
				}else if(i.getType() == Material.GHAST_TEAR && i.getItemMeta().getDisplayName().equals(sniperAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("sniperAmmo");
					boughtItem = new ItemStack(sniperAmmoItem);
				}else if(i.getType() == Material.GLOWSTONE_DUST && i.getItemMeta().getDisplayName().equals(pumpgunAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("pumpgunAmmo");
					boughtItem = new ItemStack(pumpgunAmmoItem);
				}else if(i.getType() == Material.FLINT && i.getItemMeta().getDisplayName().equals(mpAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("mpAmmo");
					boughtItem = new ItemStack(mpAmmoItem);
				}else if(i.getType() == Material.CLAY_BALL && i.getItemMeta().getDisplayName().equals(throwerAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("throwerAmmo");
					boughtItem = new ItemStack(throwerAmmoItem);
				}else if(i.getType() == Material.SLIME_BALL && i.getItemMeta().getDisplayName().equals(mgAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("mgAmmo");
					boughtItem = new ItemStack(mgAmmoItem);
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
	
}
