package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class GarageLandMenu extends Menu{

	private Inventory menu, vehicleList;
	private String title;
	private int size;
	
	private ItemStack storeVehicle, unpackVehicle;
	
	public GarageLandMenu(){
		title = "Garage (Land)";
		size = 9;
		createItems();
	}
	
	private void createItems() {

		storeVehicle = new ItemStack(Material.CHEST);
		ItemMeta storeMeta = storeVehicle.getItemMeta();
		storeMeta.setDisplayName("Pferd in der Garage verstauen" + Colors.BLACK + "  [I]");
		storeVehicle.setItemMeta(storeMeta);
		
		unpackVehicle = new ItemStack(Material.MINECART);
		ItemMeta unpackMeta = unpackVehicle.getItemMeta();
		unpackMeta.setDisplayName("Pferd aus der Garage holen" + Colors.BLACK + "  [I]");
		unpackVehicle.setItemMeta(unpackMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(storeVehicle);
		menu.addItem(unpackVehicle);
	}
	
	private void createVehicleList(Session session){
		ItemStack horse = new ItemStack(Material.SADDLE);
		ItemMeta horseMeta = horse.getItemMeta();
		
		for(String s : session.getGarageLand().garageContent.keySet()){
			if(session.getGarageLand().garageContent.get(s)){
				if(!s.equals("Sattle")){
					horseMeta.setDisplayName(Utils.changeSpaces(s, false));
					ItemStack horseItem = new ItemStack(Material.SADDLE);
					horseItem.setItemMeta(horseMeta);
					
					vehicleList.addItem(new ItemStack(horseItem));
				}
			}
		}
	}
	
	@Override
	public void open(Player p, Session session) {
		menu = Bukkit.createInventory(p, size, title);
		vehicleList = Bukkit.createInventory(p, 45, title);
		createInventory();
		p.openInventory(menu);
	}
	
	@Override
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				if(i.getType() == Material.SADDLE){
					((HorseShop) ShinigamiLife.getMenuManager().get("horseshop")).spawnHorse(i, p, session);
					
					session.getGarageLand().garageContent.put(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), false), false);
					p.closeInventory();
				}else if(i.getItemMeta().getDisplayName().equals(storeVehicle.getItemMeta().getDisplayName())){
					if(p.getVehicle() != null){
						if(p.getVehicle().getType() == EntityType.HORSE){
							Horse h = (Horse) p.getVehicle();
							p.eject();
							session.getGarageLand().garageContent.put(Utils.changeDisplayname(h.getInventory().getSaddle().getItemMeta().getDisplayName(), false), true);
							p.getVehicle().remove();
							p.closeInventory();
						}else{
							p.sendMessage(Colors.RED + "Du sitz nicht auf einem Pferd.");
							p.closeInventory();
						}
					}else{
						p.sendMessage(Colors.RED + "Du musst auf dem Pferd sitzen, welches du verstauen möchtest.");
						p.closeInventory();
					}
				}else if(i.getItemMeta().getDisplayName().equals(unpackVehicle.getItemMeta().getDisplayName())){
					p.closeInventory();
					createVehicleList(session);
					p.openInventory(vehicleList);
					session.setOpenMenu("garagelandmenu");
				}
			}
		}
	}
	
}
