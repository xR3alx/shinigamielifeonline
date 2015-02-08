package com.shinigami.scripts;

import java.util.ArrayList;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.ThrownPotion;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import com.shinigami.items.WeaponManager.Weapon;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.sessions.SessionManager.Licence;
import com.shinigami.sessions.SessionManager.Processing;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class PlayerScripts {

	private static Random random;
	
	public PlayerScripts(){
		random = new Random();
	}
	
	public static void fetterPlayer(Player p, Player t){
		if(t.getWalkSpeed() == 0){
			t.sendMessage(Colors.GREY + "Du wurdest von " + Colors.DARK_GREY + p.getName() + Colors.GREY + " entfesselt.");
			t.getActivePotionEffects().clear();
			ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "").setFetted(false);
		}else{
			t.sendMessage(Colors.GREY + "Du wurdest von " + Colors.DARK_GREY + p.getName() + Colors.GREY + " gefesselt.");
			t.getActivePotionEffects().clear();
			ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "").setFetted(true);
		}
	}
	
	public static void fetterPlayerCop(Player p, Player t){
		if(t.getWalkSpeed() == 0){
			t.sendMessage(Colors.GREY + "Du wurdest von " + Colors.DARK_GREY + p.getName() + Colors.GREY + " freigelassen.");
			t.getActivePotionEffects().clear();
			ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "").setFettedCop(false);
		}else{
			t.sendMessage(Colors.GREY + "Du wurdest von " + Colors.DARK_GREY + p.getName() + Colors.GREY + " festgenommen.");
			t.getActivePotionEffects().clear();
			ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "").setFettedCop(true);
		}
	}
	
	public static void drunkAlcohol(Player p, Session session, float promile, ItemStack itemInHand){
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 4));
		
		session.setAlcoholPromile(session.getAlcoholPromile() + promile);
		p.getInventory().removeItem(itemInHand);
	}
	
	public static void consumeDrugs(Player p, Session session, float drugInBlood, ItemStack itemInHand){
		
		p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 10));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 4));
		p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 10));
		
		session.setDrugsInBlood(session.getDrugsInBlood() + drugInBlood);
		
		if(itemInHand.getAmount() == 1){
			p.getInventory().removeItem(itemInHand);
		}else{
			itemInHand.setAmount(itemInHand.getAmount()-1);
		}
	}
	
	public static void robPlayer(Player p, Player t, Session session){
		t.sendMessage(Colors.GREY + "Du wirst von " + Colors.DARK_GREY + p.getName() + Colors.GREY + " ausgeraubt!");
		t.setSneaking(true);
		Damageable d = t;
		t.damage(d.getHealth() / 3);
		t.getWorld().playEffect(t.getLocation(), Effect.POTION_BREAK, 5);
		t.playSound(t.getLocation(), Sound.ANVIL_LAND, 20, 1);
		t.addPotionEffect(new PotionEffect(PotionEffectType.BLINDNESS, 96, 1));
		t.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 94, 1));
		t.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 98, 1));
		t.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 100, 1));

		Session robSession = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
		
		int amount = robSession.getMoneyInPocket();
		
		session.setMoneyInPocket(session.getMoneyInPocket() + amount);
		robSession.setMoneyInPocket(0);
		
		Bukkit.broadcastMessage(Colors.DARK_GREY + p.getName() + Colors.GREY + " hat " + Colors.DARK_GREY + t.getName() + Colors.GREY + " ausgraubt und " + Colors.DARK_GREY + amount + Colors.GREY + " gestohlen!");
		t.sendMessage(Colors.DARK_GREY + p.getName() + Colors.GREY + " hat dir " + Colors.DARK_GREY + amount + Colors.GREY + " gestohlen!");
	}

	public static void smoke(Player p, Session session, ItemStack cigaret){
		session.setSmokeTimer(session.getSmokeTimer() + 1);
		if(session.getSmokedTimes() == 6){
			if(p.getInventory().getItemInHand().getAmount() != 1){
				p.getInventory().getItemInHand().setAmount(p.getItemInHand().getAmount()-1);
			}else{
				p.getInventory().removeItem(cigaret);
			}
			session.setSmokedTimes(0);
			session.setSmoking(false);
			session.setSmokeTimer(0);
		}
		if(session.getSmokeTimer() / 20 > 2){
			p.damage(0.05d);
			p.getWorld().playEffect(p.getEyeLocation(), Effect.SMOKE, 4);
			p.getWorld().playSound(p.getEyeLocation(), Sound.FIRE, 1, 1);
			session.setSmokedTimes(session.getSmokedTimes() + 1);
			session.setSmokeTimer(0);
		}
	}
	
	public static void taze(Player p, Session session, Player t, Session shockedSession){
		BarAPI.setMessage(t, Colors.GREY + "Du wurdest elektrogeschockt!", 10);
		shockedSession.setTazzed(true);
		t.setSneaking(true);
		t.setWalkSpeed(0f);
		t.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 200, -6));
		t.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 200, -6));
		
		if(t.isInsideVehicle()){
			t.eject();
			
			if(t.getVehicle() instanceof Horse){
				Horse h = (Horse) t.getVehicle();
				h.setMetadata("speed", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0));
			}
		}
	}
	
	public static void nightvision(Player p){
		if(p.hasPotionEffect(PotionEffectType.NIGHT_VISION)){
			p.removePotionEffect(PotionEffectType.NIGHT_VISION);
		}
		if(p.getInventory().getHelmet().getItemMeta().getDisplayName().equals(Colors.LILA + "billiges Nachtsichtgerät")){
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 140, 1));
		}else if(p.getInventory().getHelmet().getItemMeta().getDisplayName().equals(Colors.PINK + "Nachtsichtgerät")){
			p.addPotionEffect(new PotionEffect(PotionEffectType.NIGHT_VISION, 220, 8));
		}
	}
	
	public static void bleed(Player p, Session session){
		if(Utils.getLivingEntityHealth(p) <= Utils.getLivingEntityMaxHealth(p) / 2){
			session.setBleedTimer(session.getBleedTimer() + 1);
			if(session.getBleedTimer() / 50 > 4){
				session.setBleedTimer(0);
			}
		}
	}
	
	public static void eat(Player p, ItemStack itemInHand, Session session){
		if(itemInHand.getType() == Material.APPLE){
			session.setEatLevel(session.getEatLevel() + 1);
		}else if(itemInHand.getType() == Material.CARROT_ITEM){
			session.setEatLevel(session.getEatLevel() + 1);
		}else if(itemInHand.getType() == Material.COOKIE){
			session.setEatLevel(session.getEatLevel() + 1);
		}else if(itemInHand.getType() == Material.NETHER_WARTS){
			session.setEatLevel(session.getEatLevel() + 2);
		}else if(itemInHand.getType() == Material.RAW_CHICKEN){
			session.setEatLevel(session.getEatLevel() + 3);
		}else if(itemInHand.getType() == Material.COOKED_CHICKEN){
			session.setEatLevel(session.getEatLevel() + 5);
		}else if(itemInHand.getType() == Material.RAW_BEEF){
			session.setEatLevel(session.getEatLevel() + 4);
		}else if(itemInHand.getType() == Material.COOKED_BEEF){
			session.setEatLevel(session.getEatLevel() + 6);
		}
		if(session.getEatLevel() > 20){
			session.setEatLevel(20);
		}
		
		p.setFoodLevel(session.getEatLevel());
		if(itemInHand.getAmount() > 1){
			itemInHand.setAmount(itemInHand.getAmount()-1);
		}else{
			p.getInventory().removeItem(itemInHand);
		}
	}
	
	public static void drink(Player p, ItemStack itemInHand, Session session){
		if(itemInHand.getItemMeta().getDisplayName().contains(Colors.WHITE + "Wasser")){
			session.setDrinkLevel(session.getDrinkLevel() + 1);
		}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.WHITE + "Milch")){
			session.setDrinkLevel(session.getDrinkLevel() + 2);
		}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.WHITE + "Fassbrause")){
			session.setDrinkLevel(session.getDrinkLevel() + 4);
		}
		if(session.getDrinkLevel() > 20){
			session.setDrinkLevel(20);
		}
		p.setLevel(session.getDrinkLevel());
		p.getInventory().removeItem(itemInHand);
	}
	
	public static void breakFetter(Player p, Session session){
		if(session.isFetted()){
			p.sendMessage(Colors.YELLOW + "Du hast dich erfolgreich aus den Fesseln befreit.");
			session.setFetted(false);
		}
	}
	
	public static void processingSucces(Player p, Session session){
		p.getInventory().remove(session.getProcessing().processingItem);
		p.getInventory().addItem(session.getProcessing().resultItem);
		session.setProcessing(null);
	}
	
	@SuppressWarnings("deprecation")
	public static void shoot(Player p, ItemStack item, PlayerInteractEvent event, ArrayList<Weapon> items, Session session){
		
		String itemName = Utils.changeDisplayname(item.getItemMeta().getDisplayName(), true).replace("[mg]_", "").replace("[p]_", "").replace("[g]_", "").replace("[mp]_", "").replace("[pg]_", "").replace("[t]_", "").replace("[s]_", "");
		for(Weapon b : items){
			if(b.name.equalsIgnoreCase(itemName)){
				if(!ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getShootables().containsKey(b.name) || ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getShootables().get(b.name)){
					event.setCancelled(true);
	
					ItemStack ammoPlayer = null;
					for(ItemStack i : p.getInventory().getContents()){
						if(i != null){
							if(i.getType() == Material.getMaterial(b.ammo)){
								if(i.hasItemMeta()){
									if(b.ammoDisplayname.equalsIgnoreCase(Utils.changeDisplayname(i.getItemMeta().getDisplayName(), false))){
										ammoPlayer = i;
										break;
									}
								}
							}
						}
					}
					
					if(ammoPlayer != null){
						if(ammoPlayer.getAmount() >= b.ammoCost){
							if(ammoPlayer.getAmount() == 1){
								p.getInventory().removeItem(ammoPlayer);
							}else{
								ammoPlayer.setAmount(ammoPlayer.getAmount()-b.ammoCost);
							}
							p.updateInventory();
							
							float spreadTazer = 0, spreadPistol = 0, spreadGun = 0, spreadSniper = 0, spreadPumpgun = 0, spreadMp = 0, spreadThrower = 0, spreadMg = 0;
							if(p.isSneaking()){
								spreadTazer = 10f;
								spreadPistol = 8f;
								spreadGun = 6f;
								spreadSniper = 8f;
								spreadPumpgun = 5f;
								spreadMp = 5f;
								spreadThrower = 5f;
								spreadMg = 5f;
							}else if(p.isSprinting()){
								spreadTazer = 2f;
								spreadPistol = 1.5f;
								spreadGun = 0.5f;
								spreadSniper = 0.5f;
								spreadPumpgun = 0f;
								spreadMp = 0.5f;
								spreadThrower = 1f;
								spreadMg = 0f;
							}else{
								spreadTazer = 6f;
								spreadPistol = 4f;
								spreadGun = 2.5f;
								spreadSniper = 6f;
								spreadPumpgun = 2.5f;
								spreadMp = 2.5f;
								spreadThrower = 2.5f;
								spreadMg = 2.5f;
								
								if(session.isMoving()){
									spreadTazer -= 0.25f;
									spreadPistol -= 1f;
									spreadGun -= 1f;
									spreadSniper -= 1f;
									spreadPumpgun -= 1f;
									spreadMp -= 1f;
									spreadThrower -= 1f;
									spreadMg -= 1f;
								}
							}
								
							
							if(b.type.equals("pistol")){
								Snowball projectile = p.getWorld().spawn(p.getEyeLocation(), Snowball.class);
								projectile.setShooter(p);
								
								if(b.projectile.equals("tazer")){
									projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "tazer"));
									projectile.setVelocity(p.getLocation().getDirection().multiply(2.5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadTazer, (random.nextFloat()-random.nextFloat()) / spreadTazer, (random.nextFloat()-random.nextFloat()) / spreadTazer)));
								}else{
									projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "snowball"));
									projectile.setVelocity(p.getLocation().getDirection().multiply(2.5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadPistol, (random.nextFloat()-random.nextFloat()) / spreadPistol, (random.nextFloat()-random.nextFloat()) / spreadPistol)));
								}
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}else if(b.type.equals("gun")){
								
								Arrow projectile = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile.setVelocity(p.getLocation().getDirection().multiply(5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadGun, (random.nextFloat()-random.nextFloat()) / spreadGun, (random.nextFloat()-random.nextFloat()) / spreadGun)));
								projectile.setShooter(p);
								
								projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}else if(b.type.equals("sniper")){
								
								Arrow projectile = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile.setVelocity(p.getLocation().getDirection().multiply(5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadSniper, (random.nextFloat()-random.nextFloat()) / spreadSniper, (random.nextFloat()-random.nextFloat()) / spreadSniper)));
								projectile.setCritical(true);
								projectile.setShooter(p);
								
								projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}else if(b.type.equals("pumpgun")){
								
								Arrow projectile = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile.setVelocity(p.getLocation().getDirection().multiply(5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun)));
								projectile.setShooter(p);
								
								Arrow projectile2 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile2.setVelocity(p.getLocation().getDirection().add(new Vector(0, 0.1, 0)).multiply(1.5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun)));
								projectile2.setShooter(p);
								
								Arrow projectile3 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile3.setVelocity(p.getLocation().getDirection().add(new Vector(-0.1, -0.05, 0.1)).multiply(1.5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun)));
								projectile3.setShooter(p);
								
								Arrow projectile4 = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile4.setVelocity(p.getLocation().getDirection().add(new Vector(0.1, -0.05, -0.1)).multiply(1.5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun, (random.nextFloat()-random.nextFloat()) / spreadPumpgun)));
								projectile4.setShooter(p);
								
								projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
								projectile2.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile2.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
								projectile3.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile3.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
								projectile4.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile4.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}else if(b.type.equals("mp")){
								
								Arrow projectile = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile.setVelocity(p.getLocation().getDirection().multiply(5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadMp, (random.nextFloat()-random.nextFloat()) / spreadMp, (random.nextFloat()-random.nextFloat()) / spreadMp)));
								projectile.setShooter(p);
								
								projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}else if(b.type.equals("thrower")){
								
								ThrownPotion projectile = p.getWorld().spawn(p.getEyeLocation(), ThrownPotion.class);
								projectile.setVelocity(p.getLocation().getDirection().multiply(5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadThrower, (random.nextFloat()-random.nextFloat()) / spreadThrower, (random.nextFloat()-random.nextFloat()) / spreadThrower)));
								projectile.setShooter(p);
								
								if(b.ammoDisplayname.contains("Traenengas")){
									projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "traenengas"));
								}else{
									projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "grenade"));
								}
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}else if(b.type.equals("mg")){
								
								Arrow projectile = p.getWorld().spawn(p.getEyeLocation(), Arrow.class);
								projectile.setVelocity(p.getLocation().getDirection().multiply(5f).add(new Vector((random.nextFloat()-random.nextFloat()) / spreadMg, (random.nextFloat()-random.nextFloat()) / spreadMg, (random.nextFloat()-random.nextFloat()) / spreadMg)));
								projectile.setShooter(p);
								
								projectile.setMetadata("projectile", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), "arrow"));
								projectile.setMetadata("damage", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), b.damage));
								
							}
							
							Location loc = p.getLocation();
							if(p.isSneaking()){
								loc.setPitch(loc.getPitch() - (b.recoil / 3 * 2));
							}else{
								loc.setPitch(loc.getPitch() - b.recoil);
							}
							
							p.teleport(loc);
							
							session.getShootables().put(b.name, false);
							session.getShootTimers().put(b.name, 0f);
							session.getShootTimersMax().put(b.name, b.shootRate);
								
							p.getWorld().playSound(p.getEyeLocation(), Sound.FIREWORK_LARGE_BLAST, 5, 1);
							p.getWorld().playEffect(p.getEyeLocation(), Effect.SMOKE, 4);						
						}else{
							p.sendMessage(Colors.GREY + "Du hast nicht genügend Munition!");
						}
					}else{
						p.sendMessage(Colors.GREY + "Du hast keine Munition!");
					}
				}
				break;
			}
		}
	}
	
	public static void processingStart(Player p, String licence, Session session, Entity npc){
		if(session.getProcessing() == null){
			Processing processing = ShinigamiLife.getSessionManager().createNewProcessing();
			if(licence.equals(Licence.COALMININGLICENCE)){
				ItemStack item = new ItemStack(Material.COAL_ORE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitete Kohle");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.COAL);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeitete Kohle");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.IRONMININGLICENCE)){
				ItemStack item = new ItemStack(Material.IRON_ORE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Eisen");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.IRON_INGOT);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeitetes Eisen");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.REDSTONEMININGLICENCE)){
				ItemStack item = new ItemStack(Material.REDSTONE_ORE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeiteter Rotstein");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.REDSTONE);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeiteter Rotstein");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.DIAMONDMININGLICENCE)){
				ItemStack item = new ItemStack(Material.DIAMOND_ORE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeiteter Diamant");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.DIAMOND);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeiteter Diamant");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.SMARAGDMININGLICENCE)){
				ItemStack item = new ItemStack(Material.EMERALD_ORE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeiteter Smaragd");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.EMERALD);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeiteter Smaragd");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.GOLDMININGLICENCE)){
				ItemStack item = new ItemStack(Material.GOLD_ORE);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Gold");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.GOLD_INGOT);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeitetes Gold");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.WOODCUTTERLICENCE)){
				ItemStack item = new ItemStack(Material.LOG);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Holz");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.WOOD);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeitetes Holz");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.SALTMININGLICENCE)){
				ItemStack item = new ItemStack(Material.SNOW_BLOCK);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Salz");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.QUARTZ);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "verarbeitetes Salz");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.KOKAINLICENCE)){
				ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 2);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Kokain");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.SUGAR);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "Kokain");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.HEROINLICENCE)){
				ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 1);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Heroin");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.SUGAR);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "Heroin");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}else if(licence.equals(Licence.MARIHUANALICENCE)){
				ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 3);
				ItemMeta meta = item.getItemMeta();
				meta.setDisplayName(Colors.GREY + "unverarbeitetes Marihuana");
				item.setItemMeta(meta);
				
				ItemStack result = new ItemStack(Material.SUGAR);
				ItemMeta resultMeta = result.getItemMeta();
				resultMeta.setDisplayName(Colors.WHITE + "Marihuana");
				result.setItemMeta(resultMeta);
				
				for(ItemStack i : p.getInventory().getContents()){
					if(i != null){
						if(i.hasItemMeta()){
							if(i.getItemMeta().getDisplayName().equals(item.getItemMeta().getDisplayName())){
								processing.processingItem = item;
								processing.processingItem.setAmount(i.getAmount());
								processing.resultItem = result;
								processing.resultItem.setAmount(i.getAmount());
								processing.licence = licence;
								break;
							}
						}
					}
				}
			}
			
			if(processing.processingItem != null){
				processing.npc = npc;
				p.sendMessage(Colors.RED + "Du darfst dich nicht weiter als 4 Blöcke vom Verarbeiter entfernen.");
				BarAPI.setMessage(p, "Verarbeite...", 45);
				session.setProcessing(processing);
			}
		}
	}
	
}
