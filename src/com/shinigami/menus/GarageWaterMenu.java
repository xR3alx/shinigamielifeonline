package com.shinigami.menus;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class GarageWaterMenu extends Menu{

	private Inventory menu, vehicleList;
	private String title;
	private int size;
	
	private ItemStack storeVehicle, unpackVehicle;
	
	public GarageWaterMenu(){
		title = "Garage (Wasser)";
		size = 9;
		createItems();
	}
	
	private void createItems() {

		storeVehicle = new ItemStack(Material.CHEST);
		ItemMeta storeMeta = storeVehicle.getItemMeta();
		storeMeta.setDisplayName("Boot in der Garage verstauen" + Colors.BLACK + "  [I]");
		storeVehicle.setItemMeta(storeMeta);
		
		unpackVehicle = new ItemStack(Material.MINECART);
		ItemMeta unpackMeta = unpackVehicle.getItemMeta();
		unpackMeta.setDisplayName("Boot aus der Garage holen" + Colors.BLACK + "  [I]");
		unpackVehicle.setItemMeta(unpackMeta);
		
	}
	
	private void createInventory() {
		menu.addItem(storeVehicle);
		menu.addItem(unpackVehicle);
	}
	
	private void createVehicleList(Session session){
		ItemStack boat = new ItemStack(Material.BOAT);
		ItemMeta boatMeta = boat.getItemMeta();
		
		for(String s : session.getGarageLand().garageContent.keySet()){
			if(session.getGarageLand().garageContent.get(s)){
				boatMeta.setDisplayName(Utils.changeSpaces(s, false));
				ItemStack boatItem = new ItemStack(Material.BOAT);
				boatItem.setItemMeta(boatMeta);
					
				vehicleList.addItem(new ItemStack(boatItem));
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
				if(i.getItemMeta().getDisplayName().equals(storeVehicle.getItemMeta().getDisplayName())){
					if(p.getVehicle() != null){
						if(p.getVehicle().getType() == EntityType.BOAT){
							p.eject();
							session.getGarageWater().garageContent.put("Boot", true);
							p.getVehicle().remove();
							p.closeInventory();
						}else{
							p.sendMessage(Colors.RED + "Du sitz nicht in einem Boot.");
							p.closeInventory();
						}
					}else{
						p.sendMessage(Colors.RED + "Du musst in einem Fahrzeug sitzen.");
						p.closeInventory();
					}
				}else if(i.getItemMeta().getDisplayName().equals(unpackVehicle.getItemMeta().getDisplayName())){
					p.closeInventory();
					createVehicleList(session);
					p.openInventory(vehicleList);
				}else if(i.getType() == Material.BOAT){
					((BoatShop) ShinigamiLife.getMenuManager().get("boatshop")).spawnBoat(i, p, session);
					
					session.getGarageWater().garageContent.put("Boot", false);
					p.closeInventory();
				}
			}
		}
	}
	
}
