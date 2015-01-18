package com.shinigami.oldmenus;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Horse.Style;
import org.bukkit.entity.Horse.Variant;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;

import com.shinigami.configs.PriceConfig;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;

public class HorseShop extends Menu{

	private PriceConfig priceConfig;
	private Inventory menu;
	private String title, type;
	private int size;
	
	private ItemStack horseStandard, horseBlackDots, horseWhite, horseWhiteDots, horseWhiteField,
					donkeyStandard, donkeyBlackDots, donkeyWhite, donkeyWhiteDots, donkeyWhiteField,
						policeHorse1, policeHorse2, policeHorse3, policeHorse4, policeHorse5,
						rebelHorse1, rebelHorse2, rebelHorse3, rebelHorse4, rebelHorse5,
						repkit;
	
	public HorseShop(String type){
		title = "Pferdehändler";
		this.type = type;
		size = 27;
		this.priceConfig = ShinigamiLife.getConfiguration().getPriceConfig();
		createItems();
	}
	
	private void createItems() {
		
		repkit = new ItemStack(Material.LEASH);
		ItemMeta repkitMeta = repkit.getItemMeta();
		repkitMeta.setDisplayName(Colors.WHITE + "Reperaturkit" + Colors.BLACK + "  [I]");
		List<String> repkitLore = new ArrayList<String>();
		repkitLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("repkit"));
		repkitMeta.setLore(repkitLore);
		repkit.setItemMeta(repkitMeta);

		horseStandard = new ItemStack(Material.SADDLE);
		ItemMeta horseStandardMeta = horseStandard.getItemMeta();
		horseStandardMeta.setDisplayName("Pferd [Standard]" + Colors.BLACK + "  [I]");
		List<String> horseStandardLore = new ArrayList<String>();
		horseStandardLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("horse_1"));
		horseStandardMeta.setLore(horseStandardLore);
		horseStandard.setItemMeta(horseStandardMeta);
		
		horseBlackDots = new ItemStack(Material.SADDLE);
		ItemMeta horseBlackDotsMeta = horseBlackDots.getItemMeta();
		horseBlackDotsMeta.setDisplayName("Pferd [Schwarze Punkte]" + Colors.BLACK + "  [I]");
		List<String> horseBlackDotsLore = new ArrayList<String>();
		horseBlackDotsLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("horse_2"));
		horseBlackDotsMeta.setLore(horseBlackDotsLore);
		horseBlackDots.setItemMeta(horseBlackDotsMeta);
		
		horseWhite = new ItemStack(Material.SADDLE);
		ItemMeta horseWhiteMeta = horseWhite.getItemMeta();
		horseWhiteMeta.setDisplayName("Pferd [Weiss]" + Colors.BLACK + "  [I]");
		List<String> horseWhiteLore = new ArrayList<String>();
		horseWhiteLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("horse_3"));
		horseWhiteMeta.setLore(horseWhiteLore);
		horseWhite.setItemMeta(horseWhiteMeta);
		
		horseWhiteDots = new ItemStack(Material.SADDLE);
		ItemMeta horseWhiteDotsMeta = horseWhiteDots.getItemMeta();
		horseWhiteDotsMeta.setDisplayName("Pferd [Weisse Punkte]" + Colors.BLACK + "  [I]");
		List<String> horseWhiteDotsLore = new ArrayList<String>();
		horseWhiteDotsLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("horse_4"));
		horseWhiteDotsMeta.setLore(horseWhiteDotsLore);
		horseWhiteDots.setItemMeta(horseWhiteDotsMeta);
		
		horseWhiteField = new ItemStack(Material.SADDLE);
		ItemMeta horseWhiteFieldMeta = horseWhiteField.getItemMeta();
		horseWhiteFieldMeta.setDisplayName("Pferd [Weisse Flecken]" + Colors.BLACK + "  [I]");
		List<String> horseWhiteFieldLore = new ArrayList<String>();
		horseWhiteFieldLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("horse_5"));
		horseWhiteFieldMeta.setLore(horseWhiteFieldLore);
		horseWhiteField.setItemMeta(horseWhiteFieldMeta);
		
		
		
		donkeyStandard = new ItemStack(Material.SADDLE);
		ItemMeta donkeyStandardMeta = donkeyStandard.getItemMeta();
		donkeyStandardMeta.setDisplayName("Esel [Standard]" + Colors.BLACK + "  [I]");
		List<String> donkeyStandardLore = new ArrayList<String>();
		donkeyStandardLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("donkey_1"));
		donkeyStandardMeta.setLore(donkeyStandardLore);
		donkeyStandard.setItemMeta(donkeyStandardMeta);
		
		donkeyBlackDots = new ItemStack(Material.SADDLE);
		ItemMeta donkeyBlackDotsMeta = donkeyBlackDots.getItemMeta();
		donkeyBlackDotsMeta.setDisplayName("Esel [Schwarze Punkte]" + Colors.BLACK + "  [I]");
		List<String> donkeyBlackDotsLore = new ArrayList<String>();
		donkeyBlackDotsLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("donkey_2"));
		donkeyBlackDotsMeta.setLore(donkeyBlackDotsLore);
		donkeyBlackDots.setItemMeta(donkeyBlackDotsMeta);
		
		donkeyWhite = new ItemStack(Material.SADDLE);
		ItemMeta donkeyWhiteMeta = donkeyWhite.getItemMeta();
		donkeyWhiteMeta.setDisplayName("Esel [Weiss]" + Colors.BLACK + "  [I]");
		List<String> donkeyWhiteLore = new ArrayList<String>();
		donkeyWhiteLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("donkey_3"));
		donkeyWhiteMeta.setLore(donkeyWhiteLore);
		donkeyWhite.setItemMeta(donkeyWhiteMeta);
		
		donkeyWhiteDots = new ItemStack(Material.SADDLE);
		ItemMeta donkeyWhiteDotsMeta = donkeyWhiteDots.getItemMeta();
		donkeyWhiteDotsMeta.setDisplayName("Esel [Weisse Punkte]" + Colors.BLACK + "  [I]");
		List<String> donkeyWhiteDotsLore = new ArrayList<String>();
		donkeyWhiteDotsLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("donkey_4"));
		donkeyWhiteDotsMeta.setLore(donkeyWhiteDotsLore);
		donkeyWhiteDots.setItemMeta(donkeyWhiteDotsMeta);
		
		donkeyWhiteField = new ItemStack(Material.SADDLE);
		ItemMeta donkeyWhiteFieldMeta = donkeyWhiteField.getItemMeta();
		donkeyWhiteFieldMeta.setDisplayName("Esel [Weisse Flecken]" + Colors.BLACK + "  [I]");
		List<String> donkeyWhiteFieldLore = new ArrayList<String>();
		donkeyWhiteFieldLore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("donkey_5"));
		donkeyWhiteFieldMeta.setLore(donkeyWhiteFieldLore);
		donkeyWhiteField.setItemMeta(donkeyWhiteFieldMeta);
		
		
		
		policeHorse1 = new ItemStack(Material.SADDLE);
		ItemMeta policeHorse1Meta = policeHorse1.getItemMeta();
		policeHorse1Meta.setDisplayName("Polizeipferd [Kremig]" + Colors.BLACK + "  [I]");
		List<String> policeHorse1Lore = new ArrayList<String>();
		policeHorse1Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("police_horse_1"));
		policeHorse1Meta.setLore(policeHorse1Lore);
		policeHorse1.setItemMeta(policeHorse1Meta);
		
		policeHorse2 = new ItemStack(Material.SADDLE);
		ItemMeta policeHorse2Meta = policeHorse2.getItemMeta();
		policeHorse2Meta.setDisplayName("Polizeipferd [Weiss]" + Colors.BLACK + "  [I]");
		List<String> policeHorse2Lore = new ArrayList<String>();
		policeHorse2Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("police_horse_2"));
		policeHorse2Meta.setLore(policeHorse2Lore);
		policeHorse2.setItemMeta(policeHorse2Meta);
		
		policeHorse3 = new ItemStack(Material.SADDLE);
		ItemMeta policeHorse3Meta = policeHorse3.getItemMeta();
		policeHorse3Meta.setDisplayName("Polizeipferd [Dunkelbraun]" + Colors.BLACK + "  [I]");
		List<String> policeHorse3Lore = new ArrayList<String>();
		policeHorse3Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("police_horse_3"));
		policeHorse3Meta.setLore(policeHorse3Lore);
		policeHorse3.setItemMeta(policeHorse3Meta);
		
		policeHorse4 = new ItemStack(Material.SADDLE);
		ItemMeta policeHorse4Meta = policeHorse4.getItemMeta();
		policeHorse4Meta.setDisplayName("Polizeipferd [Schwarz]" + Colors.BLACK + "  [I]");
		List<String> policeHorse4Lore = new ArrayList<String>();
		policeHorse4Lore.add(Colors.DARK_GREEN + "2Preis: " +  priceConfig.buyPrices.get("police_horse_4"));
		policeHorse4Meta.setLore(policeHorse4Lore);
		policeHorse4.setItemMeta(policeHorse4Meta);
		
		policeHorse5 = new ItemStack(Material.SADDLE);
		ItemMeta policeHorse5Meta = policeHorse5.getItemMeta();
		policeHorse5Meta.setDisplayName("Polizeipferd [Grau]" + Colors.BLACK + "  [I]");
		List<String> policeHorse5Lore = new ArrayList<String>();
		policeHorse5Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("police_horse_5"));
		policeHorse5Meta.setLore(policeHorse5Lore);
		policeHorse5.setItemMeta(policeHorse5Meta);
		
		
		
		rebelHorse1 = new ItemStack(Material.SADDLE);
		ItemMeta rebelHorse1Meta = rebelHorse1.getItemMeta();
		rebelHorse1Meta.setDisplayName("Rebellenpferd [Kremig]" + Colors.BLACK + "  [I]");
		List<String> rebelHorse1Lore = new ArrayList<String>();
		rebelHorse1Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("rebel_horse_1"));
		rebelHorse1Meta.setLore(rebelHorse1Lore);
		rebelHorse1.setItemMeta(rebelHorse1Meta);
		
		rebelHorse2 = new ItemStack(Material.SADDLE);
		ItemMeta rebelHorse2Meta = rebelHorse2.getItemMeta();
		rebelHorse2Meta.setDisplayName("Rebellenpferd [Weiss]" + Colors.BLACK + "  [I]");
		List<String> rebelHorse2Lore = new ArrayList<String>();
		rebelHorse2Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("rebel_horse_2"));
		rebelHorse2Meta.setLore(rebelHorse2Lore);
		rebelHorse2.setItemMeta(rebelHorse2Meta);
		
		rebelHorse3 = new ItemStack(Material.SADDLE);
		ItemMeta rebelHorse3Meta = rebelHorse3.getItemMeta();
		rebelHorse3Meta.setDisplayName("Rebellenpferd [Dunkelbraun]" + Colors.BLACK + "  [I]");
		List<String> rebelHorse3Lore = new ArrayList<String>();
		rebelHorse3Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("rebel_horse_3"));
		rebelHorse3Meta.setLore(rebelHorse3Lore);
		rebelHorse3.setItemMeta(rebelHorse3Meta);
		
		rebelHorse4 = new ItemStack(Material.SADDLE);
		ItemMeta rebelHorse4Meta = rebelHorse4.getItemMeta();
		rebelHorse4Meta.setDisplayName("Rebellenpferd [Schwarz]" + Colors.BLACK + "  [I]");
		List<String> rebelHorse4Lore = new ArrayList<String>();
		rebelHorse4Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("rebel_horse_4"));
		rebelHorse4Meta.setLore(rebelHorse4Lore);
		rebelHorse4.setItemMeta(rebelHorse4Meta);
		
		rebelHorse5 = new ItemStack(Material.SADDLE);
		ItemMeta rebelHorse5Meta = rebelHorse5.getItemMeta();
		rebelHorse5Meta.setDisplayName("Rebellenpferd [Grau]" + Colors.BLACK + "  [I]");
		List<String> rebelHorse5Lore = new ArrayList<String>();
		rebelHorse5Lore.add(Colors.DARK_GREEN + "Preis: " +  priceConfig.buyPrices.get("rebel_horse_5"));
		rebelHorse5Meta.setLore(rebelHorse5Lore);
		rebelHorse5.setItemMeta(rebelHorse5Meta);
		
	}
	
	private void createInventory(Player p, Session session) {
	
		if(type.equals("civ")){
			menu.addItem(donkeyStandard);
			menu.addItem(donkeyBlackDots);
			menu.addItem(donkeyWhite);
			menu.addItem(donkeyWhiteDots);
			menu.addItem(donkeyWhiteField);	
			menu.addItem(horseStandard);
			menu.addItem(horseBlackDots);
			menu.addItem(horseWhite);
			menu.addItem(horseWhiteDots);
			menu.addItem(horseWhiteField);		
		}else if(type.equals("cop")){
//			if(session.isLoggedInAsCop()){
//				menu.addItem(policeHorse1);
//				menu.addItem(policeHorse2);
//				menu.addItem(policeHorse3);
//				menu.addItem(policeHorse4);
//				menu.addItem(policeHorse5);
//			}else{
//				p.sendMessage(Colors.RED + "Nur Polizisten können hier kaufen.");
//				p.closeInventory();
//			}
		}else if(type.equals("reb")){
//			if(session.getLicence(Licence.REBELLICENCE)){
//				menu.addItem(rebelHorse1);
//				menu.addItem(rebelHorse2);
//				menu.addItem(rebelHorse3);
//				menu.addItem(rebelHorse4);
//				menu.addItem(rebelHorse5);
//			}else{
//				p.sendMessage(Colors.RED + "Nur Rebellen können hier kaufen.");
//				p.closeInventory();
//				ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {Licence.REBELLICENCE});
//			}
		}
		
		menu.addItem(repkit);
	}
	
	@Override
	public void open(Player p, Session session) {
//		if(session.getLicence(Licence.RIDINGLICENCE)){
//			menu = Bukkit.createInventory(p, size, title);
//			createInventory(p, session);
//			p.openInventory(menu);
//		}else{
//			p.sendMessage(Colors.GREY + "Du hast nicht die benötigte Lizenz.");
//			ShinigamiLife.getMenuManager().openLicenceShop(p, session, new String[] {Licence.RIDINGLICENCE});
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
				
				if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(horseStandard.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("horse_1");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(horseBlackDots.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("horse_2");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(horseWhite.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("horse_3");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(horseWhiteDots.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("horse_4");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(horseWhiteField.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("horse_5");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(donkeyStandard.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("donkey_1");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(donkeyBlackDots.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("donkey_2");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(donkeyWhite.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("donkey_3");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(donkeyWhiteDots.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("donkey_4");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(donkeyWhiteField.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("donkey_5");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(policeHorse1.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("police_horse_1");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(policeHorse2.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("police_horse_2");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(policeHorse3.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("police_horse_3");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(policeHorse4.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("police_horse_4");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(policeHorse5.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("police_horse_5");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(rebelHorse1.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("rebel_horse_1");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(rebelHorse2.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("rebel_horse_2");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(rebelHorse3.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("rebel_horse_3");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(rebelHorse4.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("rebel_horse_4");
				}else if(i.getType() == Material.SADDLE && i.getItemMeta().getDisplayName().equals(rebelHorse5.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("rebel_horse_5");
				}else if(i.getType() == Material.LEASH && i.getItemMeta().getDisplayName().equals(repkit.getItemMeta().getDisplayName())){
					cost = priceConfig.buyPrices.get("repkit");
					boughtItem = new ItemStack(repkit);
				}
				
				
//				if(money >= cost){
//					money-=cost;
//					
//					session.setMoneyPocket(money);
//					if(session.getClickedNpc() != null){
//						session.getClickedNpc().money+=cost;
//					}
//					
//					if(boughtItem == null){
//						spawnHorse(i, p, session);
//					}else{
//						ItemMeta boughtItemMeta = boughtItem.getItemMeta();
//						boughtItemMeta.setDisplayName(boughtItemMeta.getDisplayName().replace(Colors.BLACK + "  [I]", ""));
//						boughtItemMeta.getLore().remove(boughtItemMeta.getLore().size()-1);
//						boughtItem.setItemMeta(boughtItemMeta);
//						
//						p.getInventory().addItem(boughtItem);
//					}
//				}else{
//					p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
//				}
			}
		}
	}
	
	public void spawnHorse(ItemStack i, Player p, Session session){
		ItemMeta meta = i.getItemMeta();
		meta.setDisplayName(meta.getDisplayName().replace(Colors.BLACK + "  [I]", ""));
		i.setItemMeta(meta);
		
		Location loc = p.getLocation();
		Horse horse = (Horse) loc.getWorld().spawnEntity(loc, EntityType.HORSE);
		horse.setCustomName(p.getName() + "'s Fahrzeug");
		horse.setOwner(p);
		horse.setDomestication(horse.getMaxDomestication());
		horse.setAgeLock(true);
		horse.setAdult();
		horse.setTamed(true);
		horse.getInventory().setSaddle(i);
		horse.setMetadata("stolen", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), false));
		horse.setMetadata("locked", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), true));
		horse.setMetadata("speed", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0));
		
		if(i.getItemMeta().getDisplayName().equals("Pferd [Standard]")){
			
			horse.setStyle(Style.NONE);
			horse.setVariant(Variant.HORSE);
			horse.setColor(Horse.Color.BROWN);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.8));
			
		}else if(i.getItemMeta().getDisplayName().equals("Pferd [Schwarze Punkte]")){

			horse.setStyle(Style.BLACK_DOTS);
			horse.setVariant(Variant.HORSE);
			horse.setColor(Horse.Color.CREAMY);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.9));

		}else if(i.getItemMeta().getDisplayName().equals("Pferd [Weiss]")){

			horse.setStyle(Style.WHITE);
			horse.setVariant(Variant.HORSE);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.8));
			
		}else if(i.getItemMeta().getDisplayName().equals("Pferd [Weisse Punkte]")){
			
			horse.setStyle(Style.WHITE_DOTS);
			horse.setVariant(Variant.HORSE);
			horse.setColor(Horse.Color.BROWN);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.9));
			
		}else if(i.getItemMeta().getDisplayName().equals("Pferd [Weisse Flecken]")){
			
			horse.setStyle(Style.WHITEFIELD);
			horse.setVariant(Variant.HORSE);
			horse.setColor(Horse.Color.BROWN);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.6));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 1));
			
		}else if(i.getItemMeta().getDisplayName().equals("Esel [Standard]")){
			
			horse.setStyle(Style.NONE);
			horse.setVariant(Variant.DONKEY);
			horse.setColor(Horse.Color.BROWN);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.3));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
			
		}else if(i.getItemMeta().getDisplayName().equals("Esel [Schwarze Punkte]")){

			horse.setStyle(Style.BLACK_DOTS);
			horse.setVariant(Variant.DONKEY);
			horse.setColor(Horse.Color.CREAMY);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.3));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.6));

		}else if(i.getItemMeta().getDisplayName().equals("Esel [Weiss]")){

			horse.setStyle(Style.WHITE);
			horse.setVariant(Variant.DONKEY);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
			
		}else if(i.getItemMeta().getDisplayName().equals("Esel [Weisse Punkte]")){
			
			horse.setStyle(Style.WHITE_DOTS);
			horse.setVariant(Variant.DONKEY);
			horse.setColor(Horse.Color.BROWN);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.6));

		}else if(i.getItemMeta().getDisplayName().equals("Esel [Weisse Flecken]")){
			
			horse.setStyle(Style.WHITEFIELD);
			horse.setVariant(Variant.DONKEY);
			horse.setColor(Horse.Color.BROWN);
			horse.setCarryingChest(true);
			horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
			horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.7));
			
		}else if(i.getItemMeta().getDisplayName().contains("Polizeipferd")){
			
			horse.getInventory().setArmor(new ItemStack(Material.DIAMOND_BARDING));
			horse.setMaxHealth(80);
			horse.setHealth(80);
			
			if(i.getItemMeta().getDisplayName().contains("[Kremig]")){
				
				horse.setStyle(Style.NONE);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.CREAMY);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.7));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Weiss]")){
				
				horse.setStyle(Style.BLACK_DOTS);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.WHITE);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.7));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Dunkelbraun]")){
				
				horse.setStyle(Style.WHITEFIELD);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.DARK_BROWN);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.8));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Schwarz]")){
				
				horse.setStyle(Style.WHITE_DOTS);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.BLACK);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.8));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Grau]")){
				
				horse.setStyle(Style.BLACK_DOTS);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.GRAY);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.9));
			}
		}else if(i.getItemMeta().getDisplayName().contains("Rebellenpferd")){
			
			horse.getInventory().setArmor(new ItemStack(Material.IRON_BARDING));
			
			if(i.getItemMeta().getDisplayName().contains("[Braun]")){
				
				horse.setStyle(Style.WHITE);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.BROWN);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.8));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Nuggat]")){
				
				horse.setStyle(Style.BLACK_DOTS);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.CHESTNUT);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.8));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Dunkelbraun]")){
				
				horse.setStyle(Style.WHITEFIELD);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.DARK_BROWN);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.4));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.9));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Schwarz]")){
				
				horse.setStyle(Style.WHITE_DOTS);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.BLACK);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.5));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.9));
				
			}else if(i.getItemMeta().getDisplayName().contains("[Grau]")){
				
				horse.setStyle(Style.BLACK_DOTS);
				horse.setVariant(Variant.HORSE);
				horse.setColor(Horse.Color.WHITE);
				horse.setMetadata("gang1", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0.6));
				horse.setMetadata("gang2", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 1));
			}
		}
		
		p.getInventory().remove(i);
		session.getGarageLand().garageContent.put(i.getItemMeta().getDisplayName(), false);
		session.getHorses().add(horse);
	}
}
