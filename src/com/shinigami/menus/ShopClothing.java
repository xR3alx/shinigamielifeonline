package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.shinigami.configs.Configuration;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class ShopClothing extends Menu{

	private Inventory menuTab1, menuTab2;
	private ItemStack whiteHelmet, whiteChestplate, whitePants, whiteBoots,
			blackHelmet, blackChestplate, blackPants, blackBoots,
			brownHelmet, brownChestplate, brownPants, brownBoots,
			redHelmet, redChestplate, redPants, redBoots,
			greenHelmet, greenChestplate, greenPants, greenBoots,
			blueHelmet, blueChestplate, bluePants, blueBoots,
			greyHelmet, greyChestplate, greyPants, greyBoots,
			orangeHelmet, orangeChestplate, orangePants, orangeBoots,
			limeHelmet, limeChestplate, limePants, limeBoots,
			lightblueHelmet, lightblueChestplate, lightbluePants, lightblueBoots,
			cyanHelmet, cyanChestplate, cyanPants, cyanBoots,
			pinkHelmet, pinkChestplate, pinkPants, pinkBoots,
			purpleHelmet, purpleChestplate, purplePants, purpleBoots,
			magentaHelmet, magentaChestplate, magentaPants, magentaBoots,
			scrollLeft, scrollRight;
	
	private final String title = "Clothing Shop ";
	private int startTab;
	
	public ShopClothing(int startTab) {
		this.startTab = startTab;
		createItems();
	}
	
	private void createItems(){
		scrollLeft = new ItemStack(Material.SLIME_BALL);
		Utils.changeItemMeta(scrollLeft, "Left" + Colors.BLACK + "  .", null);
		
		scrollRight = new ItemStack(Material.SLIME_BALL);
		Utils.changeItemMeta(scrollRight, "Right" + Colors.BLACK + "  .", null);	
		
		
		whiteHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(whiteHelmet, "white Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("whiteHelmet")}, Color.WHITE);
		whiteChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(whiteChestplate, "white Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("whiteChestplate")}, Color.WHITE);
		whitePants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(whitePants, "white Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("whitePants")}, Color.WHITE);
		whiteBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(whiteBoots, "white Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("whiteBoots")}, Color.WHITE);
		
		blackHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(blackHelmet, "black Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blackHelmet")}, Color.BLACK);
		blackChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(blackChestplate, "black Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blackChestplate")}, Color.BLACK);
		blackPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(blackPants, "black Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blackPants")}, Color.BLACK);
		blackBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(blackBoots, "black Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blackBoots")}, Color.BLACK);
		
		brownHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(brownHelmet, "brown Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("brownHelmet")}, Color.fromRGB(139, 69, 19));
		brownChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(brownChestplate, "brown Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("brownChestplate")}, Color.fromRGB(139, 69, 19));
		brownPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(brownPants, "brown Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("brownPants")}, Color.fromRGB(139, 69, 19));
		brownBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(brownBoots, "brown Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("brownBoots")}, Color.fromRGB(139, 69, 19));
		
		redHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(redHelmet, "red Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("redHelmet")}, Color.RED);
		redChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(redChestplate, "red Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("redChestplate")}, Color.RED);
		redPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(redPants, "red Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("redPants")}, Color.RED);
		redBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(redBoots, "red Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("redBoots")}, Color.RED);
		
		greenHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(greenHelmet, "green Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greenHelmet")}, Color.GREEN);
		greenChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(greenChestplate, "green Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greenChestplate")}, Color.GREEN);
		greenPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(greenPants, "green Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greenPants")}, Color.GREEN);
		greenBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(greenBoots, "green Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greenBoots")}, Color.GREEN);
		
		blueHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(blueHelmet, "blue Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blueHelmet")}, Color.NAVY);
		blueChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(blueChestplate, "blue Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blueChestplate")}, Color.NAVY);
		bluePants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(bluePants, "blue Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("bluePants")}, Color.NAVY);
		blueBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(blueBoots, "blue Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("blueBoots")}, Color.NAVY);
		
		greyHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(greyHelmet, "grey Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greyHelmet")}, Color.GRAY);
		greyChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(greyChestplate, "grey Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greyChestplate")}, Color.GRAY);
		greyPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(greyPants, "grey Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greyPants")}, Color.GRAY);
		greyBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(greyBoots, "grey Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("greyBoots")}, Color.GRAY);
		
		orangeHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(orangeHelmet, "orange Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("orangeHelmet")}, Color.ORANGE);
		orangeChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(orangeChestplate, "orange Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("orangeChestplate")}, Color.ORANGE);
		orangePants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(orangePants, "orange Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("orangePants")}, Color.ORANGE);
		orangeBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(orangeBoots, "orange Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("orangeBoots")}, Color.ORANGE);
		
		limeHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(limeHelmet, "lime Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("limeHelmet")}, Color.LIME);
		limeChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(limeChestplate, "lime Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("limeChestplate")}, Color.LIME);
		limePants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(limePants, "lime Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("limePants")}, Color.LIME);
		limeBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(limeBoots, "lime Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("limeBoots")}, Color.LIME);
		
		lightblueHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(lightblueHelmet, "lightblue Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("lightblueHelmet")}, Color.BLUE);
		lightblueChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(lightblueChestplate, "lightblue Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("lightblueChestplate")}, Color.BLUE);
		lightbluePants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(lightbluePants, "lightblue Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("lightbluePants")}, Color.BLUE);
		lightblueBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(lightblueBoots, "lightblue Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("lightblueBoots")}, Color.BLUE);
		
		cyanHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(cyanHelmet, "cyan Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("cyanHelmet")}, Color.fromRGB(0, 245, 255));
		cyanChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(cyanChestplate, "cyan Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("cyanChestplate")}, Color.fromRGB(0, 245, 255));
		cyanPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(cyanPants, "cyan Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("cyanPants")}, Color.fromRGB(0, 245, 255));
		cyanBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(cyanBoots, "cyan Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("cyanBoots")}, Color.fromRGB(0, 245, 255));
		
		pinkHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(pinkHelmet, "pink Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("pinkHelmet")}, Color.fromRGB(219, 112, 147));
		pinkChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(pinkChestplate, "pink Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("pinkChestplate")}, Color.fromRGB(219, 112, 147));
		pinkPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(pinkPants, "pink Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("pinkPants")}, Color.fromRGB(219, 112, 147));
		pinkBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(pinkBoots, "pink Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("pinkBoots")}, Color.fromRGB(219, 112, 147));
		
		purpleHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(purpleHelmet, "purple Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("purpleHelmet")}, Color.PURPLE);
		purpleChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(purpleChestplate, "purple Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("purpleChestplate")}, Color.PURPLE);
		purplePants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(purplePants, "purple Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("purplePants")}, Color.PURPLE);
		purpleBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(purpleBoots, "purple Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("purpleBoots")}, Color.PURPLE);
		
		magentaHelmet = new ItemStack(Material.LEATHER_HELMET);
		Utils.changeItemMeta(magentaHelmet, "magenta Helmet " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("magentaHelmet")}, Color.FUCHSIA);
		magentaChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		Utils.changeItemMeta(magentaChestplate, "magenta Chestplate " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("magentaChestplate")}, Color.FUCHSIA);
		magentaPants = new ItemStack(Material.LEATHER_LEGGINGS);
		Utils.changeItemMeta(magentaPants, "magenta Pants " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("magentaPants")}, Color.FUCHSIA);
		magentaBoots = new ItemStack(Material.LEATHER_BOOTS);
		Utils.changeItemMeta(magentaBoots, "magenta Boots " + Colors.BLACK + "  .", new String[] {Colors.GREEN + "Price: " + Configuration.getPriceConfig().getBuyPrice("magentaBoots")}, Color.FUCHSIA);
	}
	
	private void createInventory(Player p, Session session){
		menuTab1.addItem(whiteHelmet);
		menuTab1.addItem(blackHelmet);
		menuTab1.addItem(brownHelmet);
		menuTab1.addItem(redHelmet);
		menuTab1.addItem(greenHelmet);
		menuTab1.addItem(blueHelmet);
		menuTab1.addItem(greyHelmet);
		menuTab1.addItem(orangeHelmet);
		menuTab1.addItem(limeHelmet);
		
		menuTab1.addItem(whiteChestplate);
		menuTab1.addItem(blackChestplate);
		menuTab1.addItem(brownChestplate);
		menuTab1.addItem(redChestplate);
		menuTab1.addItem(greenChestplate);
		menuTab1.addItem(blueChestplate);
		menuTab1.addItem(greyChestplate);
		menuTab1.addItem(orangeChestplate);
		menuTab1.addItem(limeChestplate);
		
		menuTab1.addItem(whitePants);
		menuTab1.addItem(blackPants);
		menuTab1.addItem(brownPants);
		menuTab1.addItem(redPants);
		menuTab1.addItem(greenPants);
		menuTab1.addItem(bluePants);
		menuTab1.addItem(greyPants);
		menuTab1.addItem(orangePants);
		menuTab1.addItem(limePants);
		
		menuTab1.addItem(whiteBoots);
		menuTab1.addItem(blackBoots);
		menuTab1.addItem(brownBoots);
		menuTab1.addItem(redBoots);
		menuTab1.addItem(greenBoots);
		menuTab1.addItem(blueBoots);
		menuTab1.addItem(greyBoots);
		menuTab1.addItem(orangeBoots);
		menuTab1.addItem(limeBoots);
		
		menuTab1.addItem(scrollLeft);
		
		
		
		
		menuTab2.addItem(lightblueHelmet);
		menuTab2.addItem(cyanHelmet);
		menuTab2.addItem(pinkHelmet);
		menuTab2.addItem(purpleHelmet);
		menuTab2.addItem(magentaHelmet);
		
		menuTab2.setItem(9, lightblueChestplate);
		menuTab2.setItem(10, cyanChestplate);
		menuTab2.setItem(11, pinkChestplate);
		menuTab2.setItem(12, purpleChestplate);
		menuTab2.setItem(13, magentaChestplate);
		
		menuTab2.setItem(18, lightbluePants);
		menuTab2.setItem(19, cyanPants);
		menuTab2.setItem(20, pinkPants);
		menuTab2.setItem(21, purplePants);
		menuTab2.setItem(22, magentaPants);
		
		menuTab2.setItem(27, lightblueBoots);
		menuTab2.setItem(28, cyanBoots);
		menuTab2.setItem(29, pinkBoots);
		menuTab2.setItem(30, purpleBoots);
		menuTab2.setItem(31, magentaBoots);
		
		menuTab2.setItem(44, scrollRight);
	}
	
	@Override
	public void open(Player p, Session session) {
		menuTab1 = Bukkit.createInventory(p, 45, title);
		menuTab2 = Bukkit.createInventory(p, 45, title);
		createInventory(p, session);
		if(startTab == 1){
			p.openInventory(menuTab1);
		}else if(startTab == 2){
			p.openInventory(menuTab2);
		}
	}

	@Override
	public void event(Player p, Inventory inv, ItemStack i,
			InventoryClickEvent event, Session session) {
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(i.getType() != Material.SLIME_BALL){
					int cost = Integer.parseInt(i.getItemMeta().getLore().get(0).replace(Colors.GREEN + "Price: ", ""));
					
					ItemStack boughtItem = new ItemStack(i);
					Utils.changeItemMeta(boughtItem, boughtItem.getItemMeta().getDisplayName().replace(Colors.BLACK + "  .", ""), null, ((LeatherArmorMeta) boughtItem.getItemMeta()).getColor());
					
					if(ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getMoneyInPocket() >= cost){
						ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").setMoneyInPocket(ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getMoneyInPocket()-cost);
						p.getInventory().addItem(boughtItem);
						p.sendMessage(Colors.GREY + "Bought 1x " + boughtItem.getItemMeta().getDisplayName() + "for $" + cost + ".");
					}else{
						p.sendMessage(Colors.RED + "You don't have enought money in your pocket to buy that!");
					}
					
					p.closeInventory();
				}else{
					if(i.getItemMeta().getDisplayName().contains("Left")){
						ShinigamiLife.getMenuManager().open(p, session, "shop_clothing_tab2");
					}else if(i.getItemMeta().getDisplayName().contains("Right")){
						ShinigamiLife.getMenuManager().open(p, session, "shop_clothing_tab1");
					}
				}
			}
		}
	}
}
