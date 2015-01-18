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
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class PoliceWeaponShop extends Menu{
	
	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack m9Item, g36Item, m16a4Item, m60e4Item, m40a3Item, w1200Item, mp5Item, p90Item, traenengaswerferItem,
						pistolAmmoItem, gunAmmoItem, sniperAmmoItem, pumpgunAmmoItem, mpAmmoItem, throwerAmmoItem, mgAmmoItem, tazerAmmoItem;
	
	public PoliceWeaponShop(){
		title = "Polizei Waffenladen";
		size = 18;
		this.priceConfig = Configuration.getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		m9Item = new ItemStack(Material.GOLD_SWORD);
		ItemMeta m9Meta = m9Item.getItemMeta();
		m9Meta.setDisplayName(Colors.DARK_GREY + "[P] " + Colors.RESET + "M9" + Colors.BLACK + "  [I]");
		List<String> m9Lore = new ArrayList<String>();
		m9Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m9"));
		m9Meta.setLore(m9Lore);
		m9Item.setItemMeta(m9Meta);
		
		g36Item = new ItemStack(Material.DIAMOND_SPADE);
		ItemMeta g36Meta = g36Item.getItemMeta();
		g36Meta.setDisplayName(Colors.DARK_GREY + "[G] " + Colors.RESET + "G36" + Colors.BLACK + "  [I]");
		List<String> g36Lore = new ArrayList<String>();
		g36Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("g36"));
		g36Meta.setLore(g36Lore);
		g36Item.setItemMeta(g36Meta);
		
		m16a4Item = new ItemStack(Material.STONE_PICKAXE);
		ItemMeta m16a4Meta = m16a4Item.getItemMeta();
		m16a4Meta.setDisplayName(Colors.DARK_GREY + "[G] " + Colors.RESET + "M16A4" + Colors.BLACK + "  [I]");
		List<String> m16a4Lore = new ArrayList<String>();
		m16a4Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m16a4"));
		m16a4Meta.setLore(m16a4Lore);
		m16a4Item.setItemMeta(m16a4Meta);
		
		m60e4Item = new ItemStack(Material.GOLD_HOE);
		ItemMeta m60e4Meta = m60e4Item.getItemMeta();
		m60e4Meta.setDisplayName(Colors.DARK_GREY + "[MG] " + Colors.RESET + "M60E4" + Colors.BLACK + "  [I]");
		List<String> m60e4Lore = new ArrayList<String>();
		m60e4Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m60e4Cop"));
		m60e4Meta.setLore(m60e4Lore);
		m60e4Item.setItemMeta(m60e4Meta);
		
		m40a3Item = new ItemStack(Material.DIAMOND_AXE);
		ItemMeta m40a3Meta = m40a3Item.getItemMeta();
		m40a3Meta.setDisplayName(Colors.DARK_GREY + "[S] " + Colors.RESET + "M40A3" + Colors.BLACK + "  [I]");
		List<String> m40a3Lore = new ArrayList<String>();
		m40a3Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("m40a3"));
		m40a3Meta.setLore(m40a3Lore);
		m40a3Item.setItemMeta(m40a3Meta);
		
		w1200Item = new ItemStack(Material.GOLD_PICKAXE);
		ItemMeta w1200Meta = w1200Item.getItemMeta();
		w1200Meta.setDisplayName(Colors.DARK_GREY + "[PG] " + Colors.RESET + "W1200" + Colors.BLACK + "  [I]");
		List<String> w1200Lore = new ArrayList<String>();
		w1200Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("w1200"));
		w1200Meta.setLore(w1200Lore);
		w1200Item.setItemMeta(w1200Meta);
		
		mp5Item = new ItemStack(Material.GOLD_AXE);
		ItemMeta mp5Meta = mp5Item.getItemMeta();
		mp5Meta.setDisplayName(Colors.DARK_GREY + "[MP] " + Colors.RESET + "MP5" + Colors.BLACK + "  [I]");
		List<String> mp5Lore = new ArrayList<String>();
		mp5Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("mp5"));
		mp5Meta.setLore(mp5Lore);
		mp5Item.setItemMeta(mp5Meta);
		
		p90Item = new ItemStack(Material.STONE_AXE);
		ItemMeta p90Meta = p90Item.getItemMeta();
		p90Meta.setDisplayName(Colors.DARK_GREY + "[MP] " + Colors.RESET + "P90" + Colors.BLACK + "  [I]");
		List<String> p90Lore = new ArrayList<String>();
		p90Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("p90"));
		p90Meta.setLore(p90Lore);
		p90Item.setItemMeta(p90Meta);
		
		traenengaswerferItem = new ItemStack(Material.WOOD_HOE);
		ItemMeta traenengaswerferMeta = traenengaswerferItem.getItemMeta();
		traenengaswerferMeta.setDisplayName(Colors.DARK_GREY + "[T] " + Colors.RESET + "Traenengaswerfer" + Colors.BLACK + "  [I]");
		List<String> traenengaswerferLore = new ArrayList<String>();
		traenengaswerferLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("traenengaswerfer"));
		traenengaswerferMeta.setLore(traenengaswerferLore);
		traenengaswerferItem.setItemMeta(traenengaswerferMeta);
		
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
		mpAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("mpAmmo"));
		mpAmmoMeta.setLore(mpAmmoLore);
		mpAmmoItem.setItemMeta(mpAmmoMeta);
		
		throwerAmmoItem = new ItemStack(Material.MAGMA_CREAM);
		throwerAmmoItem.setAmount(4);
		ItemMeta throwerAmmoMeta = throwerAmmoItem.getItemMeta();
		throwerAmmoMeta.setDisplayName(Colors.GREY + "Traenengas" + Colors.BLACK + "  [I]");
		List<String> throwerAmmoLore = new ArrayList<String>();
		throwerAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("traenengasAmmo"));
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
		
		tazerAmmoItem = new ItemStack(Material.BLAZE_ROD);
		tazerAmmoItem.setAmount(2);
		ItemMeta tazerAmmoMeta = tazerAmmoItem.getItemMeta();
		tazerAmmoMeta.setDisplayName(Colors.GREY + "Elektroschockerbatterie" + Colors.BLACK + "  [I]");
		List<String> tazerAmmoLore = new ArrayList<String>();
		tazerAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("tazerAmmo"));
		tazerAmmoMeta.setLore(tazerAmmoLore);
		tazerAmmoItem.setItemMeta(tazerAmmoMeta);
		
	}
	
	private void createInventory(Session session) {
		
		menu.addItem(tazerAmmoItem);
		
		if(session.getGrade() >= 1){
			menu.addItem(tazerAmmoItem);
		}
		
		if(session.getGrade() >= 2){
			menu.addItem(m9Item);
			menu.addItem(pistolAmmoItem);
		}
		
		if(session.getGrade() >= 3){
			menu.addItem(mp5Item);
			menu.addItem(p90Item);
			menu.addItem(mpAmmoItem);
			
			menu.addItem(g36Item);
			menu.addItem(m16a4Item);
			menu.addItem(gunAmmoItem);
			
			menu.addItem(w1200Item);
			menu.addItem(pumpgunAmmoItem);
		}
		
		if(session.getGrade() >= 4){
			menu.addItem(m60e4Item);
			menu.addItem(mgAmmoItem);
			
			menu.addItem(m40a3Item);
			menu.addItem(sniperAmmoItem);
		}
		
		if(session.getGrade() >= 5){
			menu.addItem(traenengaswerferItem);
			menu.addItem(throwerAmmoItem);
		}
	}
	
	@Override
	public void open(Player p, Session session) {
//		if(session.isLoggedInAsCop()){
//			menu = Bukkit.createInventory(p, size, title);
//			createInventory(session);
//			p.openInventory(menu);
//		}else{
//			p.sendMessage(Colors.RED + "Du kannst diesen Laden öffnen, weil du kein Polizist bist.");
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
				
				if(i.getType() == Material.GOLD_SWORD && i.getItemMeta().getDisplayName().equals(m9Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("m9");
					boughtItem = new ItemStack(m9Item);
				}else if(i.getType() == Material.DIAMOND_SPADE && i.getItemMeta().getDisplayName().equals(g36Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("g36");
					boughtItem = new ItemStack(g36Item);
				}else if(i.getType() == Material.STONE_PICKAXE && i.getItemMeta().getDisplayName().equals(m16a4Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("m16a4");
					boughtItem = new ItemStack(m16a4Item);
				}else if(i.getType() == Material.GOLD_HOE && i.getItemMeta().getDisplayName().equals(m60e4Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("m60e4Cop");
					boughtItem = new ItemStack(m60e4Item);
				}else if(i.getType() == Material.DIAMOND_AXE && i.getItemMeta().getDisplayName().equals(m40a3Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("m40a3");
					boughtItem = new ItemStack(m40a3Item);
				}else if(i.getType() == Material.GOLD_PICKAXE && i.getItemMeta().getDisplayName().equals(w1200Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("w1200");
					boughtItem = new ItemStack(w1200Item);
				}else if(i.getType() == Material.GOLD_AXE && i.getItemMeta().getDisplayName().equals(mp5Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("mp5");
					boughtItem = new ItemStack(mp5Item);
				}else if(i.getType() == Material.STONE_AXE && i.getItemMeta().getDisplayName().equals(p90Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("p90");
					boughtItem = new ItemStack(p90Item);
				}else if(i.getType() == Material.WOOD_HOE && i.getItemMeta().getDisplayName().equals(traenengaswerferItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("traenengaswerfer");
					boughtItem = new ItemStack(traenengaswerferItem);
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
				}else if(i.getType() == Material.MAGMA_CREAM && i.getItemMeta().getDisplayName().equals(throwerAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("traenengasAmmo");
					boughtItem = new ItemStack(throwerAmmoItem);
				}else if(i.getType() == Material.SLIME_BALL && i.getItemMeta().getDisplayName().equals(mgAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("mgAmmo");
					boughtItem = new ItemStack(mgAmmoItem);
				}else if(i.getType() == Material.BLAZE_ROD && i.getItemMeta().getDisplayName().equals(tazerAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("tazerAmmo");
					boughtItem = new ItemStack(tazerAmmoItem);
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
