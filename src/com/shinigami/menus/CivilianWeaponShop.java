package com.shinigami.menus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;

public class CivilianWeaponShop extends Menu{
	
	private PriceConfig priceConfig;
	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack uspItem, colt45Item,
						pistolAmmoItem;
	
	public CivilianWeaponShop(){
		title = "Waffenladen";
		size = 18;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {

		uspItem = new ItemStack(Material.STONE_SWORD);
		ItemMeta uspMeta = uspItem.getItemMeta();
		uspMeta.setDisplayName(Colors.DARK_GREY + "[P] " + Colors.RESET + Colors.BOLD + "USP" + Colors.BLACK + "  [I]");
		List<String> uspLore = new ArrayList<String>();
		uspLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("usp"));
		uspMeta.setLore(uspLore);
		uspItem.setItemMeta(uspMeta);
		
		colt45Item = new ItemStack(Material.DIAMOND_SWORD);
		ItemMeta colt45Meta = colt45Item.getItemMeta();
		colt45Meta.setDisplayName(Colors.DARK_GREY + "[P] " + Colors.RESET + Colors.BOLD + "Colt 45" + Colors.BLACK + "  [I]");
		List<String> colt45Lore = new ArrayList<String>();
		colt45Lore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("colt45"));
		colt45Meta.setLore(colt45Lore);
		colt45Item.setItemMeta(colt45Meta);
		
		
		pistolAmmoItem = new ItemStack(Material.ROTTEN_FLESH);
		pistolAmmoItem.setAmount(4);
		ItemMeta pistolAmmoMeta = pistolAmmoItem.getItemMeta();
		pistolAmmoMeta.setDisplayName(Colors.GREY + Colors.BOLD + "Pistolenmagazin" + Colors.BLACK + "  [I]");
		List<String> pistolAmmoLore = new ArrayList<String>();
		pistolAmmoLore.add(Colors.DARK_GREEN + "Preis: " + Colors.GREEN + priceConfig.buyPrices.get("pistolAmmo"));
		pistolAmmoMeta.setLore(pistolAmmoLore);
		pistolAmmoItem.setItemMeta(pistolAmmoMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(uspItem);
		menu.addItem(colt45Item);
		menu.addItem(pistolAmmoItem);
	}
	
	@Override
	public void open(Player p, Session session) {
		if(session.getLicence(Licence.WEAPONGLICENCE)){
			menu = Bukkit.createInventory(p, size, title);
			createInventory();
			p.openInventory(menu);
		}else{
			p.sendMessage(Colors.GREY + "Du hast benötigst einen Waffenschein um in diesem Laden einzukaufen.");
			ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {Licence.WEAPONGLICENCE});
		}
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
				
				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.STONE_SWORD && i.getItemMeta().getDisplayName().equals(uspItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("usp");
					boughtItem = new ItemStack(uspItem);
				}else if(i.getType() == Material.DIAMOND_SWORD && i.getItemMeta().getDisplayName().equals(colt45Item.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("colt45");
					boughtItem = new ItemStack(colt45Item);
				}else if(i.getType() == Material.ROTTEN_FLESH && i.getItemMeta().getDisplayName().equals(pistolAmmoItem.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("pistolAmmo");
					boughtItem = new ItemStack(pistolAmmoItem);
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
