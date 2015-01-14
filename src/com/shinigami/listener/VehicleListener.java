package com.shinigami.listener;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.vehicle.VehicleEnterEvent;
import org.bukkit.event.vehicle.VehicleExitEvent;
import org.bukkit.event.vehicle.VehicleMoveEvent;
import org.bukkit.event.vehicle.VehicleUpdateEvent;
import org.bukkit.metadata.FixedMetadataValue;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;

public class VehicleListener implements Listener{

	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleEnter(VehicleEnterEvent event) {
		Entity e = event.getEntered();
		Vehicle v = event.getVehicle();
		
		if(e instanceof Player){
			Player p = (Player) e;
			Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
			
			if(v.getType() == EntityType.MINECART){
				if(v.getMetadata("moving").size() != 0){
					if(v.getMetadata("moving").get(0).asBoolean() == false){
						if(!session.isHasTicket()){
							event.setCancelled(true);
						}
					}else{
						event.setCancelled(true);
					}
				}else{
					event.setCancelled(true);
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleUpdate(VehicleUpdateEvent event) {
		Vehicle v = event.getVehicle();
		
		if(v.getType() == EntityType.MINECART){
			v.setMetadata("moving", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), false));
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleMove(VehicleMoveEvent event) {
		Vehicle v = event.getVehicle();
		
		if(v.getType() == EntityType.MINECART){
			v.setMetadata("moving", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), true));
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onVehicleExit(VehicleExitEvent event) {
		Player p = (Player) event.getExited();
		Vehicle v = event.getVehicle();
		
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(v.getType() == EntityType.MINECART){
			if(v.getMetadata("moving").size() != 0){
				if(v.getMetadata("moving").get(0).asBoolean() == false){
					session.setHasTicket(false);
				}else{
					event.setCancelled(true);
				}
			}else{
				event.setCancelled(true);
			}
		}
	}
	
}
