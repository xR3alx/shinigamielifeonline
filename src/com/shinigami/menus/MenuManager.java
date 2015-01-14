package com.shinigami.menus;

import java.util.ArrayList;
import java.util.HashMap;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.utils.Colors;
import com.shinigami.utils.DebugMessages;
import com.shinigami.utils.SellInventory;

public class MenuManager {

	private LicenceShop licenceShop;
	
	private HashMap<String, Menu> menus;
	private ArrayList<SellInventory> sellInventories;
	
	public MenuManager(){
		menus = new HashMap<String, Menu>();
		
		boolean loaded = false;
		while(!loaded){
			menus.put("rebelshop", new RebelShop());
			menus.put("cashpoint", new Cashpoint());
			menus.put("clothingshop", new ClothingShop());
			menus.put("horseshop", new HorseShop("civ"));
			menus.put("horseshopc", new HorseShop("cop"));
			menus.put("horseshopr", new HorseShop("reb"));
			menus.put("generalshop", new GeneralShop());
			menus.put("houseshop", new HouseShop());
			menus.put("rebelweaponshop", new RebelWeaponShop());
			menus.put("policeweaponshop", new PoliceWeaponShop());
			menus.put("weaponshop", new CivilianWeaponShop());
			menus.put("hospitalshop", new HospitalShop());
			menus.put("fishingshop", new FishingShop());
			menus.put("boatshop", new BoatShop("civ"));
			menus.put("boatshopc", new BoatShop("cop"));
			menus.put("housingshop", new HousingShop());
			menus.put("barshop", new BarShop());
			menus.put("toolshop", new ToolShop());
			menus.put("backpackshop", new BackpackShop());
			
			ArrayList<String> itemnames = new ArrayList<String>();
			itemnames.add(Colors.YELLOW + "Schere");
			itemnames.add(Colors.GREY + "Zigarrete");
			itemnames.add(Colors.WHITE + "Apfel");
			itemnames.add(Colors.WHITE + "rohes Hühnen");
			itemnames.add(Colors.WHITE + "gebratenes Hühnchen");
			itemnames.add(Colors.WHITE + "rohes Fleisch");
			itemnames.add(Colors.WHITE + "gebratenes Fleisch");
			itemnames.add(Colors.WHITE + "Wasser");
			itemnames.add(Colors.WHITE + "Milch");
			itemnames.add(Colors.WHITE + "Fassbrause");
			itemnames.add(Colors.LILA + "billiges Nachtsichtgerät");
			itemnames.add(Colors.PINK + "Nachtsichtgerät");
			menus.put("sellshop", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeitetes Eisen");
			itemnames.add(Colors.WHITE + "verarbeitetes Eisen");
			menus.put("ironseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeitete Kohle");
			itemnames.add(Colors.WHITE + "verarbeitete Kohle");
			menus.put("coalseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeiteter Rotstein");
			itemnames.add(Colors.WHITE + "verarbeiteter Rotstein");
			menus.put("redstoneseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.WHITE + "Apfel");
			itemnames.add(Colors.WHITE + "Melone");
			menus.put("obstseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeiteter Diamant");
			itemnames.add(Colors.WHITE + "verarbeiteter Diamant");
			menus.put("diamondseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeiteter Smaragd");
			itemnames.add(Colors.WHITE + "verarbeiteter Smaragd");
			menus.put("smaragdseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.WHITE + "Kokain");
			itemnames.add(Colors.WHITE + "Heroin");
			itemnames.add(Colors.WHITE + "Marihuana");
			menus.put("drugseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeitetes Gold");
			itemnames.add(Colors.WHITE + "verarbeitetes Gold");
			menus.put("goldseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeitetes Holz");
			itemnames.add(Colors.WHITE + "verarbeitetes Holz");
			menus.put("woodseller", new SellShop(itemnames));
			itemnames.clear();
			itemnames.add(Colors.GREY + "unverarbeitetes Salz");
			itemnames.add(Colors.WHITE + "verarbeitetes Salz");
			menus.put("saltseller", new SellShop(itemnames));
			itemnames.clear();
			
			menus.put("ironprocesser", new ProcessingMenu(Licence.IRONMININGLICENCE));
			menus.put("diamondprocesser", new ProcessingMenu(Licence.DIAMONDMININGLICENCE));
			menus.put("redstoneprocesser", new ProcessingMenu(Licence.REDSTONEMININGLICENCE));
			menus.put("coalprocesser", new ProcessingMenu(Licence.COALMININGLICENCE));
			menus.put("saltprocesser", new ProcessingMenu(Licence.SALTMININGLICENCE));
			menus.put("smaragdprocesser", new ProcessingMenu(Licence.SMARAGDMININGLICENCE));
			menus.put("goldprocesser", new ProcessingMenu(Licence.GOLDMININGLICENCE));
			menus.put("woodprocesser", new ProcessingMenu(Licence.WOODCUTTERLICENCE));
			menus.put("cocainprocesser", new ProcessingMenu(Licence.KOKAINLICENCE));
			menus.put("heroinprocesser", new ProcessingMenu(Licence.HEROINLICENCE));
			menus.put("marihuanaprocesser", new ProcessingMenu(Licence.MARIHUANALICENCE));
			
			menus.put("smartphone", new SmartphoneMenu());
			menus.put("playermenu", new PlayerMenu());
			menus.put("groupmenu", new GroupMenu());
			menus.put("sidemenu", new SideMenu());
			menus.put("policemenu", new PoliceMenu());
			menus.put("garagelandmenu", new GarageLandMenu());
			menus.put("garagewatermenu", new GarageWaterMenu());
			menus.put("searchhorsemenu", new SearchHorseMenu());
			menus.put("searchpersonmenu", new SearchPersonMenu());
			menus.put("searchchestmenu", new SearchChestMenu());
			menus.put("searchboatmenu", new SearchBoatMenu());
			
			licenceShop = new LicenceShop();
			menus.put("licenceshop", licenceShop);
			
			loaded = true;
		}
	}
	
	@SuppressWarnings("null")
	public void checkMenu(Player p, Session session, Entity npc){
		String menuName = npc.getMetadata("menu").get(0).asString();
			
		if(menuName != null || !menuName.equals("")){
			Menu menu = menus.get(menuName);
			session.setOpenMenu(menuName);
			menu.open(p, session);
			
			DebugMessages.openMenu(session);
		}
	}
	
	public void checkEvent(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(session.getOpenMenu() != null || !session.getOpenMenu().equals("")){
			Menu menu = menus.get(session.getOpenMenu());
			menu.event(p, inv, i, event, session);
			
			DebugMessages.clickMenu(inv, i, event, session);
		}
	}
	
	public void open(Player p, Session session, String name){
		Menu menu = menus.get(name);
		menu.open(p, session);
		session.setOpenMenu(name);
	}
	
	public void openLicenceShop(Player p, Session session, String[] type){
		licenceShop.open(p, type);
		session.setOpenMenu("licenceshop");
	}
	
	public Menu get(String name){
		return menus.get(name);
	}
	
	public HashMap<String, Menu> getMenus(){
		return menus;
	}
	
	
	
	public void addSellInvetory(SellInventory sellInventory){
		sellInventories.add(sellInventory);
	}
	
	public SellInventory getSellInventory(Player p){
		SellInventory inv = null;
		for(SellInventory s : sellInventories){
			if(s.name.equals(p.getUniqueId() + "")){
				inv = s;
				break;
			}
		}
		return inv;
	}
	
	public void closeInventory(Player p){
		for(SellInventory s : sellInventories){
			if(s.name.equals(p.getUniqueId() + "")){
				sellInventories.remove(s);
				break;
			}
		}
	}
}
