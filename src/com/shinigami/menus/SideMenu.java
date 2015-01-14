package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class SideMenu extends Menu{

	private Inventory menu, locMenu, locCMenu;
	private String title;
	private int size;
	
	private ItemStack civilianItem, policeItem, adminItem, abortItem, locItem;
	
	public SideMenu(){
		title = "Seite";
		size = 9;
		createItems();
	}
	
	private void createItems(){
		
		civilianItem = new ItemStack(Material.STONE);
		ItemMeta civMeta = civilianItem.getItemMeta();
		civMeta.setDisplayName("Zivilist" + Colors.BLACK + "  [I]");
		civilianItem.setItemMeta(civMeta);
		
		policeItem = new ItemStack(Material.LAPIS_BLOCK);
		ItemMeta copMeta = policeItem.getItemMeta();
		copMeta.setDisplayName("Polizist" + Colors.BLACK + "  [I]");
		policeItem.setItemMeta(copMeta);
		
		adminItem = new ItemStack(Material.DIAMOND_BLOCK);
		ItemMeta adminMeta = policeItem.getItemMeta();
		adminMeta.setDisplayName("Admin" + Colors.BLACK + "  [I]");
		adminItem.setItemMeta(adminMeta);
		
		abortItem = new ItemStack(Material.REDSTONE_BLOCK);
		ItemMeta abortMeta = abortItem.getItemMeta();
		abortMeta.setDisplayName("Abbrechen" + Colors.BLACK + "  [I]");
		abortItem.setItemMeta(abortMeta);
		
		locItem = new ItemStack(Material.MAP);
	}
	
	private void createInventory(Player p, Session session) {
		menu.addItem(civilianItem);
		menu.setItem(8, abortItem);
		
		if(session.getGrade() != 0){
			menu.setItem(3, policeItem);
		}
		
		if(p.isOp()){
			menu.setItem(5, adminItem);
		}
	}
	
	public void createLocInventory(Session session){
		if(session.getSide().equals("civ")){
			if(ShinigamiLife.getLocationsManager().exist("spawn_city_1")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Stadt 1]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawn_city_2")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Stadt 2]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawn_city_3")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Stadt 3]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locMenu.addItem(new ItemStack(locItem));
			}
		}else if(session.getSide().equals("cop")){
			if(ShinigamiLife.getLocationsManager().exist("spawnc_city_1")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Station 1]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locCMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawnc_city_2")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Station 2]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locCMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawnc_city_3")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Station 3]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locCMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawnc_checkpoint_1")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Checkpoint 1]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locCMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawnc_checkpoint_2")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Checkpoint 2]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locCMenu.addItem(new ItemStack(locItem));
			}
			if(ShinigamiLife.getLocationsManager().exist("spawnc_checkpoint_3")){
				ItemMeta locMeta = locItem.getItemMeta();
				locMeta.setDisplayName("[Checkpoint 3]" + Colors.BLACK + "  [I]");
				locItem.setItemMeta(locMeta);
				
				locCMenu.addItem(new ItemStack(locItem));
			}
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		locMenu = Bukkit.createInventory(p, size, "Spawn");
		locCMenu = Bukkit.createInventory(p, size, "Spawn");
		createInventory(p, session);
		
		p.closeInventory();
		p.openInventory(menu);
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(i.getType() == Material.MAP){
					if(i.getItemMeta().getDisplayName().equals("[Stadt 1]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawn_city_1"));
					}else if(i.getItemMeta().getDisplayName().equals("[Stadt 2]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawn_city_2"));
					}else if(i.getItemMeta().getDisplayName().equals("[Stadt 3]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawn_city_3"));
					}if(i.getItemMeta().getDisplayName().equals("[Station 1]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawnc_city_1"));
					}else if(i.getItemMeta().getDisplayName().equals("[Station 2]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawnc_city_2"));
					}else if(i.getItemMeta().getDisplayName().equals("[Station 3]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawnc_city_3"));
					}else if(i.getItemMeta().getDisplayName().equals("[Checkpoint 1]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawnc_checkpoint_1"));
					}else if(i.getItemMeta().getDisplayName().equals("[Checkpoint 2]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawnc_checkpoint_2"));
					}else if(i.getItemMeta().getDisplayName().equals("[Checkpoint 3]")){
						p.teleport(ShinigamiLife.getLocationsManager().locations.get("spawnc_checkpoint_3"));
					}
					session.setSpawned(true);
					p.closeInventory();
				}else if(i.getType() == Material.STONE && i.getItemMeta().getDisplayName().equals(civilianItem.getItemMeta().getDisplayName())){
					session.setSide("civ");
					p.setPlayerListName(p.getName());
					session.setJoined(true);
					session.setJoinTimer(999);
					p.setPlayerListName(p.getName());
					
					createLocInventory(session);
					p.setGameMode(GameMode.SURVIVAL);
					p.closeInventory();
					p.openInventory(locMenu);
					session.setOpenMenu("sidemenu");
				}else if(i.getType() == Material.LAPIS_BLOCK && i.getItemMeta().getDisplayName().equals(policeItem.getItemMeta().getDisplayName())){
					session.setSide("cop");
					p.setPlayerListName(Colors.BLUE + p.getName());
					session.setJoined(true);
					session.setJoinTimer(999);
					
					createLocInventory(session);
					p.setGameMode(GameMode.SURVIVAL);
					p.closeInventory();
					p.openInventory(locCMenu);
					session.setOpenMenu("sidemenu");
					ShinigamiLife.getPoliceManager().logInAsCop(p, session);
				}else if(i.getType() == Material.DIAMOND_BLOCK && i.getItemMeta().getDisplayName().equals(adminItem.getItemMeta().getDisplayName())){
					session.setSide("civ");
					session.setJoined(true);
					session.setSpawned(true);
					session.setJoinTimer(999);
					p.setPlayerListName(Colors.BLACK + p.getName());
					p.setGameMode(GameMode.CREATIVE);
					p.closeInventory();
				}else if(i.getType() == Material.REDSTONE_BLOCK && i.getItemMeta().getDisplayName().equals(abortItem.getItemMeta().getDisplayName())){
					p.kickPlayer("Du hast die Seitenauswahl abgebrochen.");
				}
			}
		}
	}
	
}
