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
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;

public class LicenceShop extends Menu{

	private Inventory menu;
	private String title;
	private int size;
	
	private ItemStack rebelLicence, ridingLicence, boatLicence, ironminingLicence, diamondminingLicence, redstoneminingLicence, coalminingLicence, smaragdminingLicence, goldminingLicence, fishingLicence, huntingLicence, courierLicence, woodcutterLicence,
					saltLicence, kokainLicence, marihuanaLicence, heroinLicence, weaponLicence;
	
	public LicenceShop(){
		title = "Lizenzen";
		size = 9;
		createItems();
	}
	
	private void createItems() {

		rebelLicence = new ItemStack(Material.PAPER);
		ItemMeta rebelLicenceMeta = rebelLicence.getItemMeta();
		rebelLicenceMeta.setDisplayName(Colors.DARK_RED + "Rebellentraining" + Colors.BLACK + "  [I]");
		List<String> rebelLicenceLore = new ArrayList<String>();
		rebelLicenceLore.add(Colors.RED + "Ermöglicht es dir Items");
		rebelLicenceLore.add(Colors.RED + "im Rebellenladen zu kaufen.");
		rebelLicenceLore.add(Colors.LILA + "Wenn du im Gefängnis landest");
		rebelLicenceLore.add(Colors.LILA + "verlierst du diese Lizenz");
		rebelLicenceLore.add(" ");
		rebelLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("rebeltraining"));
		rebelLicenceMeta.setLore(rebelLicenceLore);
		rebelLicence.setItemMeta(rebelLicenceMeta);
		
		weaponLicence = new ItemStack(Material.PAPER);
		ItemMeta weaponLicenceMeta = weaponLicence.getItemMeta();
		weaponLicenceMeta.setDisplayName(Colors.RED + "Waffenlizenz" + Colors.BLACK + "  [I]");
		List<String> weaponLicenceLore = new ArrayList<String>();
		weaponLicenceLore.add(Colors.YELLOW + "Ermöglicht es dir Items");
		weaponLicenceLore.add(Colors.YELLOW + "bei dem Waffenhändler zu kaufen.");
		weaponLicenceLore.add(" ");
		weaponLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("weaponlicence"));
		weaponLicenceMeta.setLore(weaponLicenceLore);
		weaponLicence.setItemMeta(weaponLicenceMeta);
		
		ridingLicence = new ItemStack(Material.PAPER);
		ItemMeta ridingLicenceMeta = ridingLicence.getItemMeta();
		ridingLicenceMeta.setDisplayName(Colors.TURKISH + "Reitlizenz" + Colors.BLACK + "  [I]");
		List<String> ridingLicenceLore = new ArrayList<String>();
		ridingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		ridingLicenceLore.add(Colors.YELLOW + "auf Pferden zu reiten");
		ridingLicenceLore.add(" ");
		ridingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("ridinglicence"));
		ridingLicenceMeta.setLore(ridingLicenceLore);
		ridingLicence.setItemMeta(ridingLicenceMeta);
		
		boatLicence = new ItemStack(Material.PAPER);
		ItemMeta boatLicenceMeta = boatLicence.getItemMeta();
		boatLicenceMeta.setDisplayName(Colors.TURKISH + "Bootslizenz" + Colors.BLACK + "  [I]");
		List<String> boatLicenceLore = new ArrayList<String>();
		boatLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		boatLicenceLore.add(Colors.YELLOW + "mit Booten zu fahren");
		boatLicenceLore.add(" ");
		boatLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("boatlicence"));
		boatLicenceMeta.setLore(boatLicenceLore);
		boatLicence.setItemMeta(boatLicenceMeta);
		
		ironminingLicence = new ItemStack(Material.PAPER);
		ItemMeta ironminingLicenceMeta = ironminingLicence.getItemMeta();
		ironminingLicenceMeta.setDisplayName(Colors.TURKISH + "Eisenverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> ironminingLicenceLore = new ArrayList<String>();
		ironminingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		ironminingLicenceLore.add(Colors.YELLOW + "Eisen zu verarbeiten");
		ironminingLicenceLore.add(" ");
		ironminingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("ironmininglicence"));
		ironminingLicenceMeta.setLore(ironminingLicenceLore);
		ironminingLicence.setItemMeta(ironminingLicenceMeta);
		
		diamondminingLicence = new ItemStack(Material.PAPER);
		ItemMeta diamondminingLicenceMeta = diamondminingLicence.getItemMeta();
		diamondminingLicenceMeta.setDisplayName(Colors.TURKISH + "Diamantenverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> diamondminingLicenceLore = new ArrayList<String>();
		diamondminingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		diamondminingLicenceLore.add(Colors.YELLOW + "Diamanten zu verarbeiten");
		diamondminingLicenceLore.add(" ");
		diamondminingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("diamondmininglicence"));
		diamondminingLicenceMeta.setLore(diamondminingLicenceLore);
		diamondminingLicence.setItemMeta(diamondminingLicenceMeta);
		
		redstoneminingLicence = new ItemStack(Material.PAPER);
		ItemMeta redstoneminingLicenceMeta = redstoneminingLicence.getItemMeta();
		redstoneminingLicenceMeta.setDisplayName(Colors.TURKISH + "Rotsteinverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> redstoneminingLicenceLore = new ArrayList<String>();
		redstoneminingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		redstoneminingLicenceLore.add(Colors.YELLOW + "Rotstein zu verarbeiten");
		redstoneminingLicenceLore.add(" ");
		redstoneminingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("redstonemininglicence"));
		redstoneminingLicenceMeta.setLore(redstoneminingLicenceLore);
		redstoneminingLicence.setItemMeta(redstoneminingLicenceMeta);
		
		coalminingLicence = new ItemStack(Material.PAPER);
		ItemMeta coalminingLicenceMeta = coalminingLicence.getItemMeta();
		coalminingLicenceMeta.setDisplayName(Colors.TURKISH + "Kohleverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> coalminingLicenceLore = new ArrayList<String>();
		coalminingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		coalminingLicenceLore.add(Colors.YELLOW + "Kohle zu verarbeiten");
		coalminingLicenceLore.add(" ");
		coalminingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("coalmininglicence"));
		coalminingLicenceMeta.setLore(coalminingLicenceLore);
		coalminingLicence.setItemMeta(coalminingLicenceMeta);
		
		smaragdminingLicence = new ItemStack(Material.PAPER);
		ItemMeta smaragdminingLicenceMeta = smaragdminingLicence.getItemMeta();
		smaragdminingLicenceMeta.setDisplayName(Colors.TURKISH + "Smaragdverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> smaragdminingLicenceLore = new ArrayList<String>();
		smaragdminingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		smaragdminingLicenceLore.add(Colors.YELLOW + "Smaragde zu verarbeiten");
		smaragdminingLicenceLore.add(" ");
		smaragdminingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("smaragdmininglicence"));
		smaragdminingLicenceMeta.setLore(smaragdminingLicenceLore);
		smaragdminingLicence.setItemMeta(smaragdminingLicenceMeta);
		
		goldminingLicence = new ItemStack(Material.PAPER);
		ItemMeta goldminingLicenceMeta = goldminingLicence.getItemMeta();
		goldminingLicenceMeta.setDisplayName(Colors.TURKISH + "Goldverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> goldminingLicenceLore = new ArrayList<String>();
		goldminingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		goldminingLicenceLore.add(Colors.YELLOW + "Gold zu verarbeiten");
		goldminingLicenceLore.add(" ");
		goldminingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("goldmininglicence"));
		goldminingLicenceMeta.setLore(goldminingLicenceLore);
		goldminingLicence.setItemMeta(goldminingLicenceMeta);
		
		fishingLicence = new ItemStack(Material.PAPER);
		ItemMeta fishingLicenceMeta = fishingLicence.getItemMeta();
		fishingLicenceMeta.setDisplayName(Colors.TURKISH + "Fischerlizenz" + Colors.BLACK + "  [I]");
		List<String> fishingLicenceLore = new ArrayList<String>();
		fishingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		fishingLicenceLore.add(Colors.YELLOW + "zu fischen");
		fishingLicenceLore.add(" ");
		fishingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("fishinglicence"));
		fishingLicenceMeta.setLore(fishingLicenceLore);
		fishingLicence.setItemMeta(fishingLicenceMeta);
		
		huntingLicence = new ItemStack(Material.PAPER);
		ItemMeta huntingLicenceMeta = huntingLicence.getItemMeta();
		huntingLicenceMeta.setDisplayName(Colors.TURKISH + "Jaegerlizenz" + Colors.BLACK + "  [I]");
		List<String> huntingLicenceLore = new ArrayList<String>();
		huntingLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		huntingLicenceLore.add(Colors.YELLOW + "zu jagen");
		huntingLicenceLore.add(" ");
		huntingLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("huntinglicence"));
		huntingLicenceMeta.setLore(huntingLicenceLore);
		huntingLicence.setItemMeta(huntingLicenceMeta);
		
		courierLicence = new ItemStack(Material.PAPER);
		ItemMeta courierLicenceMeta = courierLicence.getItemMeta();
		courierLicenceMeta.setDisplayName(Colors.TURKISH + "Kurierlizenz" + Colors.BLACK + "  [I]");
		List<String> courierLicenceLore = new ArrayList<String>();
		courierLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		courierLicenceLore.add(Colors.YELLOW + "Pakete zu liefern");
		courierLicenceLore.add(" ");
		courierLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("courierlicence"));
		courierLicenceMeta.setLore(courierLicenceLore);
		courierLicence.setItemMeta(courierLicenceMeta);
		
		woodcutterLicence = new ItemStack(Material.PAPER);
		ItemMeta woodcutterLicenceMeta = woodcutterLicence.getItemMeta();
		woodcutterLicenceMeta.setDisplayName(Colors.TURKISH + "Holzverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> woodcutterLicenceLore = new ArrayList<String>();
		woodcutterLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		woodcutterLicenceLore.add(Colors.YELLOW + "Holz zu verarbeiten");
		woodcutterLicenceLore.add(" ");
		woodcutterLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("woodcutterlicence"));
		woodcutterLicenceMeta.setLore(woodcutterLicenceLore);
		woodcutterLicence.setItemMeta(woodcutterLicenceMeta);
		
		saltLicence = new ItemStack(Material.PAPER);
		ItemMeta saltLicenceMeta = saltLicence.getItemMeta();
		saltLicenceMeta.setDisplayName(Colors.TURKISH + "Salzverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> saltLicenceLore = new ArrayList<String>();
		saltLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		saltLicenceLore.add(Colors.YELLOW + "Salz zu verarbeiten");
		saltLicenceLore.add(" ");
		saltLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("saltlicence"));
		saltLicenceMeta.setLore(saltLicenceLore);
		saltLicence.setItemMeta(saltLicenceMeta);
		
		kokainLicence = new ItemStack(Material.PAPER);
		ItemMeta kokainLicenceMeta = kokainLicence.getItemMeta();
		kokainLicenceMeta.setDisplayName(Colors.TURKISH + "Kokainverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> kokainLicenceLore = new ArrayList<String>();
		kokainLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		kokainLicenceLore.add(Colors.YELLOW + "Kokain zu verarbeiten");
		kokainLicenceLore.add(" ");
		kokainLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("kokainlicence"));
		kokainLicenceMeta.setLore(kokainLicenceLore);
		kokainLicence.setItemMeta(kokainLicenceMeta);
		
		heroinLicence = new ItemStack(Material.PAPER);
		ItemMeta heroinLicenceMeta = heroinLicence.getItemMeta();
		heroinLicenceMeta.setDisplayName(Colors.TURKISH + "Heroinverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> heroinLicenceLore = new ArrayList<String>();
		heroinLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		heroinLicenceLore.add(Colors.YELLOW + "Heroin zu verarbeiten");
		heroinLicenceLore.add(" ");
		heroinLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("heroinlicence"));
		heroinLicenceMeta.setLore(heroinLicenceLore);
		heroinLicence.setItemMeta(heroinLicenceMeta);
		
		marihuanaLicence = new ItemStack(Material.PAPER);
		ItemMeta marihuanaLicenceMeta = marihuanaLicence.getItemMeta();
		marihuanaLicenceMeta.setDisplayName(Colors.TURKISH + "Marihuanaverarbeitungslizenz" + Colors.BLACK + "  [I]");
		List<String> marihuanaLicenceLore = new ArrayList<String>();
		marihuanaLicenceLore.add(Colors.YELLOW + "Erlaubt es dir");
		marihuanaLicenceLore.add(Colors.YELLOW + "Marihuana zu verarbeiten");
		marihuanaLicenceLore.add(" ");
		marihuanaLicenceLore.add(Colors.GREEN + "Preis: " + Colors.DARK_GREEN + Configuration.getPriceConfig().getBuyPrice("marihuanalicence"));
		marihuanaLicenceMeta.setLore(marihuanaLicenceLore);
		marihuanaLicence.setItemMeta(marihuanaLicenceMeta);
		
	}
	
	private void createInventory(String[] type) {
	
		for(String s : type){
			if(s.equalsIgnoreCase(Licence.BOATLICENCE)){
				menu.addItem(boatLicence);
			}
			if(s.equalsIgnoreCase(Licence.COALMININGLICENCE)){
				menu.addItem(coalminingLicence);
			}
			if(s.equalsIgnoreCase(Licence.COURIERLICENCE)){
				menu.addItem(courierLicence);
			}
			if(s.equalsIgnoreCase(Licence.DIAMONDMININGLICENCE)){
				menu.addItem(diamondminingLicence);
			}
			if(s.equalsIgnoreCase(Licence.FISHINGLICENCE)){
				menu.addItem(fishingLicence);
			}
			if(s.equalsIgnoreCase(Licence.GOLDMININGLICENCE)){
				menu.addItem(goldminingLicence);
			}
			if(s.equalsIgnoreCase(Licence.HUNTERLICENCE)){
				menu.addItem(huntingLicence);
			}
			if(s.equalsIgnoreCase(Licence.IRONMININGLICENCE)){
				menu.addItem(ironminingLicence);
			}
			if(s.equalsIgnoreCase(Licence.REBELLICENCE)){
				menu.addItem(rebelLicence);
			}
			if(s.equalsIgnoreCase(Licence.REDSTONEMININGLICENCE)){
				menu.addItem(redstoneminingLicence);
			}
			if(s.equalsIgnoreCase(Licence.RIDINGLICENCE)){
				menu.addItem(ridingLicence);
			}
			if(s.equalsIgnoreCase(Licence.SMARAGDMININGLICENCE)){
				menu.addItem(smaragdminingLicence);
			}
			if(s.equalsIgnoreCase(Licence.WOODCUTTERLICENCE)){
				menu.addItem(woodcutterLicence);
			}
			if(s.equalsIgnoreCase(Licence.SALTMININGLICENCE)){
				menu.addItem(saltLicence);
			}
			if(s.equalsIgnoreCase(Licence.KOKAINLICENCE)){
				menu.addItem(kokainLicence);
			}
			if(s.equalsIgnoreCase(Licence.HEROINLICENCE)){
				menu.addItem(heroinLicence);
			}
			if(s.equalsIgnoreCase(Licence.MARIHUANALICENCE)){
				menu.addItem(marihuanaLicence);
			}
			if(s.equalsIgnoreCase(Licence.WEAPONGLICENCE)){
				menu.addItem(weaponLicence);
			}
			
		}
	}
	
	public void open(Player p, String[] type) {
		menu = Bukkit.createInventory(p, size, title);
		createInventory(type);
		p.openInventory(menu);
	}
	
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				int cost = 0;
				boolean close = false;
				String licence = "";

//				int money = session.getMoneyPocket();
				
				if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(rebelLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("rebeltraining");
					close = true;
					licence = Licence.REBELLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(ridingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("ridinglicence");
					close = true;
					licence = Licence.RIDINGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(boatLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("boatlicence");
					close = true;
					licence = Licence.BOATLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(ironminingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("ironmininglicence");
					close = true;
					licence = Licence.IRONMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(diamondminingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("diamondmininglicence");
					close = true;
					licence = Licence.DIAMONDMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(redstoneminingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("redstonemininglicence");
					close = true;
					licence = Licence.REDSTONEMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(coalminingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("coalmininglicence");
					close = true;
					licence = Licence.COALMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(smaragdminingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("smaragdmininglicence");
					close = true;
					licence = Licence.SMARAGDMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(goldminingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("goldmininglicence");
					close = true;
					licence = Licence.GOLDMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(fishingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("fishinglicence");
					close = true;
					licence = Licence.FISHINGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(huntingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("huntinglicence");
					close = true;
					licence = Licence.HUNTERLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(courierLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("courierlicence");
					close = true;
					licence = Licence.COURIERLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(ridingLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("ridinglicence");
					close = true;
					licence = Licence.RIDINGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(woodcutterLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("woodcutterlicence");
					close = true;
					licence = Licence.WOODCUTTERLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(saltLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("saltlicence");
					close = true;
					licence = Licence.SALTMININGLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(kokainLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("kokainlicence");
					close = true;
					licence = Licence.KOKAINLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(heroinLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("heroinlicence");
					close = true;
					licence = Licence.HEROINLICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(marihuanaLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("marihuanalicence");
					close = true;
					licence = Licence.MARIHUANALICENCE;
				}else if(i.getType() == Material.PAPER && i.getItemMeta().getDisplayName().equals(weaponLicence.getItemMeta().getDisplayName())){
					cost =  Configuration.getPriceConfig().getBuyPrice("weaponlicence");
					close = true;
					licence = Licence.WEAPONGLICENCE;
				}
				
//				if(!licence.equals("")){
//					if(money >= cost){
//						money-=cost;
//	
//						session.setMoneyPocket(money);
//						if(session.getClickedNpc() != null){
//							session.getClickedNpc().money+=cost;
//						}
//						
//						session.setLicence(licence, true);
//						
//						if(close){
//							p.closeInventory();
//						}
//					}else{
//						p.sendMessage(Colors.RED + "Du hast nicht genügend Geld dabei.");
//					}
//				}
			}
		}
	}
}
