package com.shinigami.listener;

import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Fireball;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.entity.HorseJumpEvent;
import org.bukkit.event.entity.PotionSplashEvent;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.scripts.PlayerScripts;
import com.shinigami.sessions.Session;

public class EntityListener implements Listener{

	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
		if(event.getDamager() instanceof Snowball){
			Snowball s = (Snowball) event.getDamager();
			if(s.getShooter() instanceof Player){
				Player p = (Player) s.getShooter();
				if(event.getEntity() instanceof Player){
					Player t = (Player) event.getEntity();
					
					Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
					Session sessionT = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
					
					if(s.getMetadata("projectile").get(0).asString().equals("tazer")){
						PlayerScripts.taze(p, session, t, sessionT);
					}else if(s.getMetadata("projectile").get(0).asString().equals("fireball")){
						s.getWorld().createExplosion(s.getLocation(), 2);
					}else if(s.getMetadata("projectile").get(0).asString().equals("grenade")){
						s.getWorld().createExplosion(s.getLocation(), 1);
					}
						
					t.damage(s.getMetadata("damage").get(0).asDouble());
					
				}else if(event.getEntity() instanceof Villager){
					event.setCancelled(true);
				}
			}else{
				event.setCancelled(true);
			}
		}else if(event.getDamager() instanceof Arrow){
			Arrow s = (Arrow) event.getDamager();
			if(s.getShooter() instanceof Player){
				Player p = (Player) s.getShooter();
				if(event.getEntity() instanceof Player){
					Player t = (Player) event.getEntity();
					
					if(s.hasMetadata("damge") && s.hasMetadata("projectile")){
						if(s.getMetadata("projectile").get(0).asString().equals("fireball")){
							s.getWorld().createExplosion(s.getLocation(), 6);
						}else if(s.getMetadata("projectile").get(0).asString().equals("grenade")){
							s.getWorld().createExplosion(s.getLocation(), 2);
						}
						
						t.damage(s.getMetadata("damage").get(0).asDouble());
					}
				}else if(event.getEntity() instanceof Villager){
					event.setCancelled(true);
				}
			}else{
				event.setCancelled(true);
			}
		}else if(event.getDamager() instanceof Player){
			if(event.getEntity().getType() != EntityType.VILLAGER){
				Player p = (Player) event.getDamager();
				
				if(p.getItemInHand().getType() == Material.IRON_AXE
						|| p.getItemInHand().getType() == Material.IRON_PICKAXE
						|| p.getItemInHand().getType() == Material.IRON_HOE){
					event.setCancelled(true);
				}else if(p.getItemInHand() == null && p.getItemInHand().getType() == Material.AIR){
					event.setCancelled(true);
				}else if(p.getItemInHand().getType() == Material.BOOK){
					event.setCancelled(true);
				}
			}else{
				event.setCancelled(true);
			}
		}else{
			event.setCancelled(true);
		}
	}
	
//	@EventHandler(priority = EventPriority.NORMAL)
//	public void onEntityMove(EntityMoveEvent event){
//		Entity e = event.getEntity();
//		Location fromLoc = new Location(event.getWorld(), event.getFromX(), event.getFromY(), event.getFromZ());
//		
//		if(e.getType() == EntityType.HORSE){
//			Horse h = (Horse) e;
//			CommonEntity<Entity> comEntity = CommonEntity.get(e);
//			if(h.getPassenger() == null){
//				comEntity.teleport(fromLoc);
//			}else{
//				if(h.hasMetadata("speed")){
//					float speed = h.getMetadata("speed").get(0).asFloat();
//					if(h.isOnGround()){
//						if(speed > 0){
//							if(h.getPassenger().getLocation().getDirection().getY() <= 0.0){
//								comEntity.setVelocity(h.getPassenger().getLocation().getDirection().multiply(speed));
//							}
//						}else{
//							comEntity.setVelocity(0, 0, 0);
//						}
//					}
//				}
//			}
//		}
//	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onEntityExplode(EntityExplodeEvent event){
		Entity e = event.getEntity();
		
		if(event.blockList().size() != 0){
			for(Block b : event.blockList()){
				b.getDrops().clear();
				ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 1200, b.getData());
			}
		}
		
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onHorseJump(HorseJumpEvent event){
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onCreatureSpawn(CreatureSpawnEvent event){
		Entity e = event.getEntity();
		
		if(e.getType() == EntityType.HORSE){
			for(Entity entity : e.getNearbyEntities(30, 30, 30)){
				if(entity instanceof Villager){
					Villager villager = (Villager) entity;
					event.setCancelled(true);
					
					String menu = villager.getMetadata("menu").get(0).asString();
					if(menu.contains("garagelandmenu") || menu.contains("horseshop")){
						event.setCancelled(false);
						break;
					}
				}
			}
		
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onProjectileHit(ProjectileHitEvent event) {
		if(event.getEntity() instanceof ThrownPotion){
			ThrownPotion potion = (ThrownPotion) event.getEntity();
			
			if(potion.getMetadata("projectile").get(0).asString().equals("grenade")){
				event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 3);
			}
		}else if(event.getEntity() instanceof Fireball){
			Fireball potion = (Fireball) event.getEntity();
			
			if(potion.getMetadata("projectile").get(0).asString().equals("fireball")){
				event.getEntity().getWorld().createExplosion(event.getEntity().getLocation(), 8);
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPotionSplash(PotionSplashEvent event) {
		if(event.getEntity() instanceof ThrownPotion){
			ThrownPotion potion = (ThrownPotion) event.getEntity();
			if(potion.getMetadata("projectile").get(0).asString().equals("traenengas")){
				traenengasEffect(event);
				for(Entity e : event.getAffectedEntities()){
					if(e instanceof Player){
						Player p = (Player) e;
						
						p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 800, 6));
						p.addPotionEffect(new PotionEffect(PotionEffectType.ABSORPTION, 800, 6));
						p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 800, 2));
					}
				}
			}else if(potion.getMetadata("projectile").get(0).asString().equals("grenade")){
				for(Entity e : event.getAffectedEntities()){
					if(e instanceof Player){
						Player p = (Player) e;
						
						p.addPotionEffect(new PotionEffect(PotionEffectType.HEAL, 260, -2));
					}
				}
			}
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	private void traenengasEffect(PotionSplashEvent event){
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, 1), Effect.SMOKE, 0);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, 2), Effect.SMOKE, 1);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, 1), Effect.SMOKE, 2);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, 2), Effect.SMOKE, 3);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, 2), Effect.SMOKE, 4);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, 1), Effect.SMOKE, 5);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, 1), Effect.SMOKE, 6);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, 2), Effect.SMOKE, 7);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, 1), Effect.SMOKE, 8);
		
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, -1), Effect.SMOKE, 0);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, -2), Effect.SMOKE, 1);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, -1), Effect.SMOKE, 2);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, -2), Effect.SMOKE, 3);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, -2), Effect.SMOKE, 4);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, -1), Effect.SMOKE, 5);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(2, 1, -1), Effect.SMOKE, 6);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, -2), Effect.SMOKE, 7);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(1, 1, -1), Effect.SMOKE, 8);
		
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, 1), Effect.SMOKE, 0);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, 2), Effect.SMOKE, 1);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, 1), Effect.SMOKE, 2);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, 2), Effect.SMOKE, 3);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, 2), Effect.SMOKE, 4);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, 1), Effect.SMOKE, 5);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, 1), Effect.SMOKE, 6);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, 2), Effect.SMOKE, 7);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, 1), Effect.SMOKE, 8);
		
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, -1), Effect.SMOKE, 0);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, -2), Effect.SMOKE, 1);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, -1), Effect.SMOKE, 2);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, -2), Effect.SMOKE, 3);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, -2), Effect.SMOKE, 4);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, -1), Effect.SMOKE, 5);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-2, 1, -1), Effect.SMOKE, 6);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, -2), Effect.SMOKE, 7);
		event.getEntity().getWorld().playEffect(event.getEntity().getLocation().add(-1, 1, -1), Effect.SMOKE, 8);
	}
	
}
