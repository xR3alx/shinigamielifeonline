package com.shinigami.oldmenus;

import java.util.ArrayList;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.configs.Configuration;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class HousingShop extends Menu{

	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack chestItem, lanternItem, leavesItem, cauldronItem, fenceItem,
						stairsItem, slabItem, flowerPotItem, glowstoneItem, furnaceItem, workbenchItem, brownCarpetItem, redCarpetItem, yellowCarpetItem, blueCarpetItem, purpleCarpetItem, greenCarpetItem, 
						mohnItem, orangeTulpeItem, redTulpeItem, loewenzahnItem, blueOrchideeItem, sternlauchItem, porzellansternItem, whiteTulpeItem, rosaTulpeItem, margeritItem;
	
	public HousingShop(){
		title = "Möbelland";
		size = 36;
		createItems();
	}
	
	private void createItems() {
		
		chestItem = new ItemStack(Material.CHEST);
		ItemMeta chestMeta = chestItem.getItemMeta();
		chestMeta.setDisplayName(Colors.YELLOW + "Kiste" + Colors.BLACK + "  [I]");
		ArrayList<String> chestLore = new ArrayList<String>();
		chestLore.add(" ");
		chestLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("chest"));
		chestMeta.setLore(chestLore);
		chestItem.setItemMeta(chestMeta);
		
		brownCarpetItem = new ItemStack(Material.CARPET, 1, (short) 12);
		ItemMeta brownCarpetMeta = brownCarpetItem.getItemMeta();
		brownCarpetMeta.setDisplayName(Colors.YELLOW + "Brauner Teppich" + Colors.BLACK + "  [I]");
		ArrayList<String> brownCarpetLore = new ArrayList<String>();
		brownCarpetLore.add(" ");
		brownCarpetLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("brownCarpet"));
		brownCarpetMeta.setLore(brownCarpetLore);
		brownCarpetItem.setItemMeta(brownCarpetMeta);
		
		redCarpetItem = new ItemStack(Material.CARPET, 1, (short) 14);
		ItemMeta redCarpetMeta = redCarpetItem.getItemMeta();
		redCarpetMeta.setDisplayName(Colors.YELLOW + "Roter Teppich" + Colors.BLACK + "  [I]");
		ArrayList<String> redCarpetLore = new ArrayList<String>();
		redCarpetLore.add(" ");
		redCarpetLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("redCarpet"));
		redCarpetMeta.setLore(redCarpetLore);
		redCarpetItem.setItemMeta(redCarpetMeta);
		
		yellowCarpetItem = new ItemStack(Material.CARPET, 1, (short) 4);
		ItemMeta yellowCarpetMeta = yellowCarpetItem.getItemMeta();
		yellowCarpetMeta.setDisplayName(Colors.YELLOW + "Gelber Teppich" + Colors.BLACK + "  [I]");
		ArrayList<String> yellowCarpetLore = new ArrayList<String>();
		yellowCarpetLore.add(" ");
		yellowCarpetLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("yellowCarpet"));
		yellowCarpetMeta.setLore(yellowCarpetLore);
		yellowCarpetItem.setItemMeta(yellowCarpetMeta);
		
		blueCarpetItem = new ItemStack(Material.CARPET, 1, (short) 11);
		ItemMeta blueCarpetMeta = blueCarpetItem.getItemMeta();
		blueCarpetMeta.setDisplayName(Colors.YELLOW + "Blauer Teppich" + Colors.BLACK + "  [I]");
		ArrayList<String> blueCarpetLore = new ArrayList<String>();
		blueCarpetLore.add(" ");
		blueCarpetLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("blueCarpet"));
		blueCarpetMeta.setLore(blueCarpetLore);
		blueCarpetItem.setItemMeta(blueCarpetMeta);
		
		purpleCarpetItem = new ItemStack(Material.CARPET, 1, (short) 10);
		ItemMeta purpleCarpetMeta = purpleCarpetItem.getItemMeta();
		purpleCarpetMeta.setDisplayName(Colors.YELLOW + "Lila Teppich" + Colors.BLACK + "  [I]");
		ArrayList<String> purpleCarpetLore = new ArrayList<String>();
		purpleCarpetLore.add(" ");
		purpleCarpetLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("purpleCarpet"));
		purpleCarpetMeta.setLore(purpleCarpetLore);
		purpleCarpetItem.setItemMeta(purpleCarpetMeta);
		
		greenCarpetItem = new ItemStack(Material.CARPET, 1, (short) 13);
		ItemMeta greenCarpetMeta = greenCarpetItem.getItemMeta();
		greenCarpetMeta.setDisplayName(Colors.YELLOW + "Grüner Teppich" + Colors.BLACK + "  [I]");
		ArrayList<String> greenCarpetLore = new ArrayList<String>();
		greenCarpetLore.add(" ");
		greenCarpetLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("greenCarpet"));
		greenCarpetMeta.setLore(greenCarpetLore);
		greenCarpetItem.setItemMeta(greenCarpetMeta);
		
		lanternItem = new ItemStack(Material.JACK_O_LANTERN);
		ItemMeta lanternMeta = lanternItem.getItemMeta();
		lanternMeta.setDisplayName(Colors.YELLOW + "Laterne" + Colors.BLACK + "  [I]");
		ArrayList<String> lanternLore = new ArrayList<String>();
		lanternLore.add(" ");
		lanternLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("lantern"));
		lanternMeta.setLore(lanternLore);
		lanternItem.setItemMeta(lanternMeta);
		
		leavesItem = new ItemStack(Material.LEAVES);
		ItemMeta leavesMeta = leavesItem.getItemMeta();
		leavesMeta.setDisplayName(Colors.YELLOW + "Blätter" + Colors.BLACK + "  [I]");
		ArrayList<String> leavesLore = new ArrayList<String>();
		leavesLore.add(" ");
		leavesLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("leaves"));
		leavesMeta.setLore(leavesLore);
		leavesItem.setItemMeta(leavesMeta);
		
		mohnItem = new ItemStack(38, 1, (short) 0);
		ItemMeta mohnMeta = mohnItem.getItemMeta();
		mohnMeta.setDisplayName(Colors.YELLOW + "Mohn" + Colors.BLACK + "  [I]");
		ArrayList<String> mohnLore = new ArrayList<String>();
		mohnLore.add(" ");
		mohnLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("mohn"));
		mohnMeta.setLore(mohnLore);
		mohnItem.setItemMeta(mohnMeta);
		
		orangeTulpeItem = new ItemStack(38, 1, (short) 5);
		ItemMeta orangeTulpeMeta = orangeTulpeItem.getItemMeta();
		orangeTulpeMeta.setDisplayName(Colors.YELLOW + "Orangene Tulpe" + Colors.BLACK + "  [I]");
		ArrayList<String> orangeTulpeLore = new ArrayList<String>();
		orangeTulpeLore.add(" ");
		orangeTulpeLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("orangeTulpe"));
		orangeTulpeMeta.setLore(orangeTulpeLore);
		orangeTulpeItem.setItemMeta(orangeTulpeMeta);
		
		redTulpeItem = new ItemStack(38, 1, (short) 4);
		ItemMeta redTulpeMeta = redTulpeItem.getItemMeta();
		redTulpeMeta.setDisplayName(Colors.YELLOW + "Rote Tulpe" + Colors.BLACK + "  [I]");
		ArrayList<String> redTulpeLore = new ArrayList<String>();
		redTulpeLore.add(" ");
		redTulpeLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("redTulpe"));
		redTulpeMeta.setLore(redTulpeLore);
		redTulpeItem.setItemMeta(redTulpeMeta);
		
		loewenzahnItem = new ItemStack(37, 1, (short) 0);
		ItemMeta loewenzahnMeta = loewenzahnItem.getItemMeta();
		loewenzahnMeta.setDisplayName(Colors.YELLOW + "Loewenzahn" + Colors.BLACK + "  [I]");
		ArrayList<String> loewenzahnLore = new ArrayList<String>();
		loewenzahnLore.add(" ");
		loewenzahnLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("loewenzahn"));
		loewenzahnMeta.setLore(loewenzahnLore);
		loewenzahnItem.setItemMeta(loewenzahnMeta);
		
		blueOrchideeItem = new ItemStack(38, 1, (short) 1);
		ItemMeta blueOrchideeMeta = blueOrchideeItem.getItemMeta();
		blueOrchideeMeta.setDisplayName(Colors.YELLOW + "Blaue Orchidee" + Colors.BLACK + "  [I]");
		ArrayList<String> blueOrchideeLore = new ArrayList<String>();
		blueOrchideeLore.add(" ");
		blueOrchideeLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("blueOrchidee"));
		blueOrchideeMeta.setLore(blueOrchideeLore);
		blueOrchideeItem.setItemMeta(blueOrchideeMeta);
		
		sternlauchItem = new ItemStack(38, 1, (short) 2);
		ItemMeta sternlauchMeta = sternlauchItem.getItemMeta();
		sternlauchMeta.setDisplayName(Colors.YELLOW + "Sternlauch" + Colors.BLACK + "  [I]");
		ArrayList<String> sternlauchLore = new ArrayList<String>();
		sternlauchLore.add(" ");
		sternlauchLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("sternlauch"));
		sternlauchMeta.setLore(sternlauchLore);
		sternlauchItem.setItemMeta(sternlauchMeta);
		
		porzellansternItem = new ItemStack(38, 1, (short) 3);
		ItemMeta porzellansternMeta = porzellansternItem.getItemMeta();
		porzellansternMeta.setDisplayName(Colors.YELLOW + "Porzellanstern" + Colors.BLACK + "  [I]");
		ArrayList<String> porzellansternLore = new ArrayList<String>();
		porzellansternLore.add(" ");
		porzellansternLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("porzellanstern"));
		porzellansternMeta.setLore(porzellansternLore);
		porzellansternItem.setItemMeta(porzellansternMeta);
		
		whiteTulpeItem = new ItemStack(38, 1, (short) 6);
		ItemMeta whiteTulpeMeta = whiteTulpeItem.getItemMeta();
		whiteTulpeMeta.setDisplayName(Colors.YELLOW + "Weiße Tulpe" + Colors.BLACK + "  [I]");
		ArrayList<String> whiteTulpeLore = new ArrayList<String>();
		whiteTulpeLore.add(" ");
		whiteTulpeLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("whiteTulpe"));
		whiteTulpeMeta.setLore(whiteTulpeLore);
		whiteTulpeItem.setItemMeta(whiteTulpeMeta);
		
		rosaTulpeItem = new ItemStack(38, 1, (short) 7);
		ItemMeta rosaTulpeMeta = rosaTulpeItem.getItemMeta();
		rosaTulpeMeta.setDisplayName(Colors.YELLOW + "Rosa Tulpe" + Colors.BLACK + "  [I]");
		ArrayList<String> rosaTulpeLore = new ArrayList<String>();
		rosaTulpeLore.add(" ");
		rosaTulpeLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("rosaTulpe"));
		rosaTulpeMeta.setLore(rosaTulpeLore);
		rosaTulpeItem.setItemMeta(rosaTulpeMeta);
		
		margeritItem = new ItemStack(38, 1, (short) 8);
		ItemMeta margeritMeta = margeritItem.getItemMeta();
		margeritMeta.setDisplayName(Colors.YELLOW + "Margerite" + Colors.BLACK + "  [I]");
		ArrayList<String> margeritLore = new ArrayList<String>();
		margeritLore.add(" ");
		margeritLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("margerit"));
		margeritMeta.setLore(margeritLore);
		margeritItem.setItemMeta(margeritMeta);
		
		cauldronItem = new ItemStack(Material.CAULDRON_ITEM);
		ItemMeta cauldronMeta = cauldronItem.getItemMeta();
		cauldronMeta.setDisplayName(Colors.YELLOW + "Wasserbehälter" + Colors.BLACK + "  [I]");
		ArrayList<String> cauldronLore = new ArrayList<String>();
		cauldronLore.add(" ");
		cauldronLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("cauldron"));
		cauldronMeta.setLore(cauldronLore);
		cauldronItem.setItemMeta(cauldronMeta);
		
		fenceItem = new ItemStack(Material.FENCE);
		ItemMeta fenceMeta = fenceItem.getItemMeta();
		fenceMeta.setDisplayName(Colors.YELLOW + "Zaun" + Colors.BLACK + "  [I]");
		ArrayList<String> fenceLore = new ArrayList<String>();
		fenceLore.add(" ");
		fenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("fence"));
		fenceMeta.setLore(fenceLore);
		fenceItem.setItemMeta(fenceMeta);
		
		stairsItem = new ItemStack(Material.WOOD_STAIRS);
		ItemMeta stairsMeta = stairsItem.getItemMeta();
		stairsMeta.setDisplayName(Colors.YELLOW + "Treppe" + Colors.BLACK + "  [I]");
		ArrayList<String> stairsLore = new ArrayList<String>();
		stairsLore.add(" ");
		stairsLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("stairs"));
		stairsMeta.setLore(stairsLore);
		stairsItem.setItemMeta(stairsMeta);
		
		slabItem = new ItemStack(Material.WOOD_STEP);
		ItemMeta slabMeta = slabItem.getItemMeta();
		slabMeta.setDisplayName(Colors.YELLOW + "Stufe" + Colors.BLACK + "  [I]");
		ArrayList<String> slabLore = new ArrayList<String>();
		slabLore.add(" ");
		slabLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("slab"));
		slabMeta.setLore(slabLore);
		slabItem.setItemMeta(slabMeta);
		
		flowerPotItem = new ItemStack(Material.FLOWER_POT_ITEM);
		ItemMeta flowerPotMeta = flowerPotItem.getItemMeta();
		flowerPotMeta.setDisplayName(Colors.YELLOW + "Blumentopf" + Colors.BLACK + "  [I]");
		ArrayList<String> flowerPotLore = new ArrayList<String>();
		flowerPotLore.add(" ");
		flowerPotLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("flowerpot"));
		flowerPotMeta.setLore(flowerPotLore);
		flowerPotItem.setItemMeta(flowerPotMeta);
		
		glowstoneItem = new ItemStack(Material.GLOWSTONE);
		ItemMeta glowstoneMeta = glowstoneItem.getItemMeta();
		glowstoneMeta.setDisplayName(Colors.YELLOW + "Glühstein" + Colors.BLACK + "  [I]");
		ArrayList<String> glowstoneLore = new ArrayList<String>();
		glowstoneLore.add(" ");
		glowstoneLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("glowstone"));
		glowstoneMeta.setLore(glowstoneLore);
		glowstoneItem.setItemMeta(glowstoneMeta);
		
		furnaceItem = new ItemStack(Material.FURNACE);
		ItemMeta furnaceMeta = furnaceItem.getItemMeta();
		furnaceMeta.setDisplayName(Colors.YELLOW + "Herd" + Colors.BLACK + "  [I]");
		ArrayList<String> furnaceLore = new ArrayList<String>();
		furnaceLore.add(" ");
		furnaceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("furnace"));
		furnaceMeta.setLore(furnaceLore);
		furnaceItem.setItemMeta(furnaceMeta);
		
		workbenchItem = new ItemStack(Material.WORKBENCH);
		ItemMeta workbenchMeta = workbenchItem.getItemMeta();
		workbenchMeta.setDisplayName(Colors.YELLOW + "Arbeitsfläche" + Colors.BLACK + "  [I]");
		ArrayList<String> workbenchLore = new ArrayList<String>();
		workbenchLore.add(" ");
		workbenchLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("workbench"));
		workbenchMeta.setLore(workbenchLore);
		workbenchItem.setItemMeta(workbenchMeta);
		
	}
	
	private void createInventory() {
		
		menu.addItem(chestItem);
		menu.addItem(furnaceItem);
		menu.addItem(workbenchItem);
		menu.addItem(lanternItem);
		menu.addItem(cauldronItem);
		menu.addItem(stairsItem);
		menu.addItem(fenceItem);
		menu.addItem(slabItem);
		menu.addItem(brownCarpetItem);
		menu.addItem(redCarpetItem);
		menu.addItem(yellowCarpetItem);
		menu.addItem(blueCarpetItem);
		menu.addItem(purpleCarpetItem);
		menu.addItem(greenCarpetItem);
		menu.addItem(flowerPotItem);
		menu.addItem(leavesItem);
		menu.addItem(mohnItem);
		menu.addItem(orangeTulpeItem);
		menu.addItem(redTulpeItem);
		menu.addItem(loewenzahnItem);
		menu.addItem(blueOrchideeItem);
		menu.addItem(sternlauchItem);
		menu.addItem(porzellansternItem);
		menu.addItem(whiteTulpeItem);
		menu.addItem(rosaTulpeItem);
		menu.addItem(margeritItem);
		menu.addItem(glowstoneItem);
	
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory();
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				ItemStack boughtItem = null;
//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.CHEST && i.getItemMeta().getDisplayName().equals(chestItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("chest");
					boughtItem = new ItemStack(chestItem);
				}else if(i.getType() == Material.FURNACE && i.getItemMeta().getDisplayName().equals(furnaceItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("furnace");
					boughtItem = new ItemStack(furnaceItem);
				}else if(i.getType() == Material.WORKBENCH && i.getItemMeta().getDisplayName().equals(workbenchItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("workbench");
					boughtItem = new ItemStack(workbenchItem);
				}else if(i.getType() == Material.JACK_O_LANTERN && i.getItemMeta().getDisplayName().equals(lanternItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("lantern");
					boughtItem = new ItemStack(lanternItem);
				}else if(i.getType() == Material.CAULDRON_ITEM && i.getItemMeta().getDisplayName().equals(cauldronItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("cauldron");
					boughtItem = new ItemStack(cauldronItem);
				}else if(i.getType() == Material.WOOD_STAIRS && i.getItemMeta().getDisplayName().equals(stairsItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("stairs");
					boughtItem = new ItemStack(stairsItem);
				}else if(i.getType() == Material.FENCE && i.getItemMeta().getDisplayName().equals(fenceItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("fence");
					boughtItem = new ItemStack(fenceItem);
				}else if(i.getType() == Material.WOOD_STEP && i.getItemMeta().getDisplayName().equals(slabItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("slab");
					boughtItem = new ItemStack(slabItem);
				}else if(i.getType() == Material.FLOWER_POT_ITEM && i.getItemMeta().getDisplayName().equals(flowerPotItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("flowerpot");
					boughtItem = new ItemStack(flowerPotItem);
				}else if(i.getType() == Material.LEAVES && i.getItemMeta().getDisplayName().equals(leavesItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("leaves");
					boughtItem = new ItemStack(leavesItem);
				}else if(i.getType() == Material.GLOWSTONE && i.getItemMeta().getDisplayName().equals(glowstoneItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("glowstone");
					boughtItem = new ItemStack(glowstoneItem);
				}else if(i.getType() == Material.CARPET && i.getItemMeta().getDisplayName().equals(brownCarpetItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("brownCarpet");
					boughtItem = new ItemStack(brownCarpetItem);
				}else if(i.getType() == Material.CARPET && i.getItemMeta().getDisplayName().equals(redCarpetItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("redCarpet");
					boughtItem = new ItemStack(redCarpetItem);
				}else if(i.getType() == Material.CARPET && i.getItemMeta().getDisplayName().equals(yellowCarpetItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("yellowCarpet");
					boughtItem = new ItemStack(yellowCarpetItem);
				}else if(i.getType() == Material.CARPET && i.getItemMeta().getDisplayName().equals(blueCarpetItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("blueCarpet");
					boughtItem = new ItemStack(blueCarpetItem);
				}else if(i.getType() == Material.CARPET && i.getItemMeta().getDisplayName().equals(purpleCarpetItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("purpleCarpet");
					boughtItem = new ItemStack(purpleCarpetItem);
				}else if(i.getType() == Material.CARPET && i.getItemMeta().getDisplayName().equals(greenCarpetItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("greenCarpet");
					boughtItem = new ItemStack(greenCarpetItem);
				}else if(i.getItemMeta().getDisplayName().equals(mohnItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("mohn");
					boughtItem = new ItemStack(mohnItem);
				}else if(i.getItemMeta().getDisplayName().equals(orangeTulpeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("orangeTulpe");
					boughtItem = new ItemStack(orangeTulpeItem);
				}else if(i.getItemMeta().getDisplayName().equals(redTulpeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("redTulpe");
					boughtItem = new ItemStack(redTulpeItem);
				}else if(i.getItemMeta().getDisplayName().equals(loewenzahnItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("loewenzahn");
					boughtItem = new ItemStack(loewenzahnItem);
				}else if(i.getItemMeta().getDisplayName().equals(blueOrchideeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("blueOrchidee");
					boughtItem = new ItemStack(blueOrchideeItem);
				}else if(i.getItemMeta().getDisplayName().equals(orangeTulpeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("orangeTulpe");
					boughtItem = new ItemStack(orangeTulpeItem);
				}else if(i.getItemMeta().getDisplayName().equals(sternlauchItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("sternlauch");
					boughtItem = new ItemStack(sternlauchItem);
				}else if(i.getItemMeta().getDisplayName().equals(porzellansternItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("porzellanstern");
					boughtItem = new ItemStack(porzellansternItem);
				}else if(i.getItemMeta().getDisplayName().equals(whiteTulpeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("whiteTulpe");
					boughtItem = new ItemStack(whiteTulpeItem);
				}else if(i.getItemMeta().getDisplayName().equals(orangeTulpeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("orangeTulpe");
					boughtItem = new ItemStack(orangeTulpeItem);
				}else if(i.getItemMeta().getDisplayName().equals(rosaTulpeItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("rosaTulpe");
					boughtItem = new ItemStack(rosaTulpeItem);
				}else if(i.getItemMeta().getDisplayName().equals(margeritItem.getItemMeta().getDisplayName())){
					cost = Configuration.getPriceConfig().getBuyPrice("margerit");
					boughtItem = new ItemStack(margeritItem);
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
