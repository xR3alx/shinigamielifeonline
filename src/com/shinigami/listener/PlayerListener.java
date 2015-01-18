package com.shinigami.listener;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Chest;
import org.bukkit.block.Sign;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Item;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Villager;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDeathEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerPreLoginEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shinigami.housing.HousingManager.House;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.scripts.PlayerScripts;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Messages;
import com.shinigami.utils.Utils;

public class PlayerListener implements Listener {

	public Random random;
	
	public String[] eatItemNames = {Colors.WHITE + "Apfel", Colors.WHITE + "Keks", Colors.WHITE + "Karrote", Colors.WHITE + "Weintrauben", Colors.WHITE + "rohes Hühnen", Colors.WHITE + "gebratenes Hühnchen", Colors.WHITE + "rohes Fleisch", Colors.WHITE + "gebratenes Fleisch"};
	public String[] drinkItemNames = {Colors.WHITE + "Wasser", Colors.WHITE + "Milch", Colors.WHITE + "Fassbrause"};
	public String[] alcoholItemNames = {Colors.GREY + "Bier", Colors.GREY + "Wodka"};
	public String[] drugItemNames = {Colors.GREY + "Heroin", Colors.GREY + "Marihuana", Colors.GREY + "Kokain"};
	
	public PlayerListener(){
		random = new Random();
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerJoin(PlayerJoinEvent event){
		Player p = event.getPlayer();
		
		if(ShinigamiLife.isLoaded()){
	
			if(p.getVehicle() != null){
				p.getVehicle().setPassenger(null);
			}
			
			event.setJoinMessage(Colors.DARK_GREY + p.getName() + " connected");
			Messages.sendWelcomeMsg(p);
			
			ShinigamiLife.getSessionManager().createSession(p);
		}else{
			p.kickPlayer("Du kannst den Server jetzt noch nicht betreten. Probiere es in wenigen Minuten erneut.");
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerPreLogin(PlayerPreLoginEvent event){
		if(!ShinigamiLife.isLoaded()){
			event.disallow(PlayerPreLoginEvent.Result.KICK_FULL, "Du kannst den Server jetzt noch nicht betreten. Probiere es in wenigen Minuten erneut.");
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerLeave(PlayerQuitEvent event){
		Player p = event.getPlayer();
		
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		for(PotionEffect pEffect : p.getActivePotionEffects()){
			p.removePotionEffect(pEffect.getType());
		}
		
//		ShinigamiLife.getPoliceManager().logOutAsCop(p, session);
		session.setJoinTimer(0);
		session.setJoined(false);
		session.setSpawned(false);
		
		event.setQuitMessage(Colors.DARK_GREY + p.getName() + " disconnected");
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteractEntity(PlayerInteractEntityEvent event){
		Player p = event.getPlayer();
		Entity e = event.getRightClicked();
		LivingEntity lE = null;
		if(e instanceof LivingEntity){
			if(e.isOnGround()){
				lE = (LivingEntity) e;
			}
		}
		
		ItemStack itemInHand = event.getPlayer().getItemInHand();
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(!session.isEscorted() && p.getWalkSpeed() == 0.2f && !p.hasPotionEffect(PotionEffectType.JUMP)){
			if(lE != null){
				if(lE.getCustomName() != null){
					if(lE.getType() == EntityType.VILLAGER){
						event.setCancelled(true);
						Villager vil = (Villager) lE;
						if(vil.getMetadata("id").size() != 0){
							session.setClickedNpc(ShinigamiLife.getNpcManager().getByID(vil.getMetadata("id").get(0).asInt()));
	
							// ---- NPC / HAENDLER AUSRAUBEN ----
							if(p.isSneaking()){
								if(!session.isInteract()){
									if(!session.getProfession().equals("cop")){
										if(session.getInteractTimer() > 10){
											session.setInteractTimer(0);
											session.setInteractNpc(ShinigamiLife.getNpcManager().getByNameAndLoc(lE.getCustomName(), lE.getLocation()));
											session.setInteract(true);
											
											if(!session.getInteractNpc().robbed){
												if(session.getInteractNpc().money != 0){
													if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[MG]")
															|| itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[P]")
															|| itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[G]")
															|| itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[MP]")
															|| itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[PG]")
															|| itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[T]")
															|| itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[S]")){
														int moneySec = session.getInteractNpc().money / 900;
														if(moneySec > 0){
															session.setRobNpc(true);
															session.setRobPoliceCallTime(60 + random.nextInt(120));
															session.setRobBanksClosedTime(600 + random.nextInt(300));
															session.setRobMoneySec(moneySec);
															BarAPI.setMessage(p, "Du raubst den NPC aus.. ("+moneySec+"/Sek.)", 900);
															p.sendMessage(Colors.GREY + "Bleib in einem Radius von 5 Blöcken, damit der Raub nicht abbricht.");
														}
													}
												}
											}else{
												p.sendMessage(Colors.RED + "Du musst " + Colors.GREY + session.getInteractNpc().robbedTimer / 60 + Colors.RED + " Minuten warten, bevor du den NPC ausrauben kannst.");
											}
										}else{
											session.setInteractTimer(session
													.getInteractTimer() + 1);
										}
									}
								}
							}
							ShinigamiLife.getMenuManager().checkMenu(p, session, lE);
						}
					}
					return;
				}
			}
		}else{
			event.setCancelled(true);
		}
		
		if(e.getType() == EntityType.HORSE){
			Horse h = (Horse) e;
			if(p.isSneaking()){
				event.setCancelled(true);
				if(itemInHand.hasItemMeta()){
					// ---- PFERD DURCHSUCHEN ----
					if(itemInHand.getType() == Material.BOOK && itemInHand.getItemMeta().getDisplayName().equals(Colors.TURKISH + "Polizeimenü")){
						if(!BarAPI.hasBar(p)){
							BarAPI.setMessage(p, "durchsuche Pferd....", 5);
							p.sendMessage(Colors.GREY + "Du darfst dich nicht weiter als 2 Blöcke von dem Pferd entfernen.");
							session.setSearchedHorse(h);
							session.setSearchHorse(true);
						}else{
							p.sendMessage(Colors.RED + "Du führst bereits eine Aktion aus.");
						}
					// ---- PFERD KNACKEN / KLAUEN ----
					}else if(itemInHand.getType() == Material.SHEARS && itemInHand.getItemMeta().getDisplayName().equals(Colors.GREY + "Dietrich")){
						BarAPI.setMessage(p, "knacke Pferd...", 30);
						h.setCustomName(h.getCustomName() + Colors.GREY + " - wird von " + Colors.WHITE + p.getName() + Colors.GREY + " geknackt!");
						session.setStealingHorse(true);
						// horse zu eigenen hinzufügen
						p.setSneaking(true);
						p.sendMessage(Colors.GREY + "Du darfst dich nicht weiter als 5 Blöcke entfernen!");
					// ---- PFERD HEILEN / REPARIEREN ----
					}else if(itemInHand.getType() == Material.GOLDEN_APPLE && itemInHand.getItemMeta().getDisplayName().equals(Colors.WHITE + "Reperaturkit")){
						if(p.isSneaking()){
							if(Utils.getLivingEntityHealth(h) != Utils.getLivingEntityMaxHealth(h)){
								h.setHealth(Utils.getLivingEntityMaxHealth(h));
								p.sendMessage(Colors.GREY + "Du hast das Pferd repariert!");
								if(itemInHand.getAmount() == 1){
									p.getInventory().removeItem(itemInHand);
								}else{
									itemInHand.setAmount(itemInHand.getAmount()-1);
								}
							}
						}
					}else{
						p.sendMessage(Colors.GREY + "Du kannst das Pferd nur mit einem Dietrich knacken.");
					}
					event.setCancelled(true);
				// ---- NORMALE INTERACTION ----
				}else if(h.getOwner() == p){
					event.setCancelled(true);
					if(!h.getMetadata("locked").get(0).asBoolean()){
						h.setMetadata("locked", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), true));
						p.sendMessage(Colors.GREY + "Du hast das Pferd abgeschlossen.");
					}else{
						h.setMetadata("locked", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), false));
						p.sendMessage(Colors.GREY + "Du hast das Pferd aufgeschlossen.");
					}
				}else{
					event.setCancelled(true);
				}
			// ---- PFERD AUFSTEIGEN INTERACTION ----
			}else if(h.hasMetadata("stolen") && h.getMetadata("stolen").get(0).asBoolean()){
				p.sendMessage(Colors.GREY + "Dieses Pferd wurde gestohlen.");
				p.sendMessage(Colors.GREY + "Der Eigentümer ist " + h.getOwner().getName());
			}else if(h.hasMetadata("locked") && h.getMetadata("locked").get(0).asBoolean()){
				event.setCancelled(true);
				p.sendMessage(Colors.GREY + "Das Pferd ist abgeschlossen.");
			}else if(h.hasMetadata("locked") && !h.getMetadata("locked").get(0).asBoolean()){
			}else if(h.getOwner() != p){
				event.setCancelled(true);
				p.sendMessage(Colors.RED + "Das Pferd gehört nicht dir");
			}
			
			return;
		}else if(e.getType() == EntityType.BOAT){
			Boat b = (Boat) e;
			Player owner = Bukkit.getPlayer(b.getMetadata("owner").get(0).asString());
			if(p.isSneaking()){
				event.setCancelled(true);
				if(itemInHand.hasItemMeta()){
					// ---- BOOT DURCHSUCHEN
					if(itemInHand.getType() == Material.BOOK && itemInHand.getItemMeta().getDisplayName().equals(Colors.TURKISH + "Polizeimenü")){
						if(!BarAPI.hasBar(p)){
							BarAPI.setMessage(p, "durchsuche Boot....", 5);
							p.sendMessage(Colors.GREY + "Du darfst dich nicht weiter als 2 Blöcke von dem Boot entfernen.");
							session.setSearchedBoat(b);
							session.setSearchBoat(true);
						}else{
							p.sendMessage(Colors.RED + "Du führst bereits eine Aktion aus.");
						}
					// ---- BOOT AUFBRECHEN ----
					}else if(itemInHand.getType() == Material.SHEARS && itemInHand.getItemMeta().getDisplayName().equals(Colors.GREY + "Dietrich")){
						BarAPI.setMessage(p, "knacke Boot...", 30);
						session.setStealingBoat(true);
						// boat zu eigenen hinzufügen
						p.setSneaking(true);
						p.sendMessage(Colors.GREY + "Du darfst dich nicht weiter als 5 Blöcke entfernen!");
					}else{
						p.sendMessage(Colors.GREY + "Du kannst das Boot nur mit einem Dietrich knacken.");
					}
				// ---- NORMALE INTERACTION ----
				}else if(owner != null){
					if(!b.getMetadata("locked").get(0).asBoolean()){
						b.setMetadata("locked", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), true));
						p.sendMessage(Colors.GREY + "Du hast das Boot abgeschlossen.");
					}else{
						b.setMetadata("locked", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), false));
						p.sendMessage(Colors.GREY + "Du hast das Boot aufgeschlossen.");
					}
				}
			// ---- BOOT EINSTEIGEN INTERACTION ----
			}else if(b.getMetadata("stolen").get(0).asBoolean()){
				p.sendMessage(Colors.GREY + "Dieses Pferd wurde gestohlen.");
				p.sendMessage(Colors.GREY + "Der Eigentümer ist " + owner.getName());
			}else if(b.getMetadata("locked").get(0).asBoolean()){
				event.setCancelled(true);
				p.sendMessage(Colors.GREY + "Das Boot ist abgeschlossen.");
			}else if(!b.getMetadata("locked").get(0).asBoolean()){
			}else if(owner != p){
				event.setCancelled(true);
				p.sendMessage(Colors.RED + "Das Boot gehört nicht dir");
			}
			return;
		// ---- ESKORTIEREN BEENDEN ----
		}else if(e.getType() == EntityType.PLAYER){
			if(session.getProfession().equals("cop")){
				if(session.isEscortPlayer()){
					ShinigamiLife.getMenuManager().open(p, session, "");
				}
			}
		}
		
		if(itemInHand.hasItemMeta()){
			if(e instanceof Player){
				Player t = (Player) e;
				// ---- SPIELER DURCHSUCHEN ----
				if(itemInHand.getType() == Material.BOOK && itemInHand.getItemMeta().getDisplayName().equals(Colors.TURKISH + "Polizeimenü")){
					if(session.getProfession().equals("cop")){
						if(p.isSneaking()){
							if(!BarAPI.hasBar(p)){
								BarAPI.setMessage(p, "durchsuche Person....", 5);
								p.sendMessage(Colors.GREY + "Du darfst dich nicht weiter als 2 Blöcke von der Person entfernen.");
								session.setSearchedPlayer(t);
								session.setSearchPlayer(true);
							}else{
								p.sendMessage(Colors.RED + "Du führst bereits eine Aktion aus.");
							}
						}
					}
				// ---- SPIELER FESSELN ----
				}else if(itemInHand.getType() == Material.LEASH && itemInHand.getItemMeta().getDisplayName().equals(Colors.DARK_RED + "Fessel")){
					if(!session.getProfession().equals("cop")){
						PlayerScripts.fetterPlayer(p, t);
					}
				// ---- SPIELER MIT SCHERE BEFREIEN ----
				}if(itemInHand.getType() == Material.SHEARS && itemInHand.getItemMeta().getDisplayName().equals(Colors.YELLOW + "Schere")){
					if(t.getWalkSpeed() == 0){
						t.sendMessage(Colors.YELLOW + p.getName() + Colors.WHITE + " hat dich aus den Fesseln befreit.");
						t.setWalkSpeed(0.2f);
						t.removePotionEffect(PotionEffectType.JUMP);
						t.setSneaking(false);
					}
				/// ---- SPIELER AUSRAUBEN ----
				}else if(itemInHand.getType() == Material.STICK && itemInHand.getItemMeta().getDisplayName().equals(Colors.DARK_RED + "Prügelstock")){
					if(p.isSneaking()){
						PlayerScripts.robPlayer(p, t, session);
					}
				}
			}
			return;
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerInteract(PlayerInteractEvent event){
		Player p = (Player) event.getPlayer();
		Block b = event.getClickedBlock();
		ItemStack itemInHand = p.getItemInHand();
		
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(session != null){
			if(!session.isEscorted() && p.getWalkSpeed() == 0.2f && !p.hasPotionEffect(PotionEffectType.JUMP)){
				if(itemInHand.hasItemMeta()){
					if(itemInHand.getItemMeta().getDisplayName() != null){
						for(String s : eatItemNames){
							if(s.equals(itemInHand.getItemMeta().getDisplayName())){
								event.setCancelled(true);
								PlayerScripts.eat(p, itemInHand, session);
								break;
							}
						}
						for(String s : drinkItemNames){
							if(s.equals(itemInHand.getItemMeta().getDisplayName())){
								event.setCancelled(true);
								PlayerScripts.drink(p, itemInHand, session);
								break;
							}
						}
						for(String s : alcoholItemNames){
							if(s.equals(itemInHand.getItemMeta().getDisplayName())){
								event.setCancelled(true);
								if(s.contains("Bier")){
									PlayerScripts.drunkAlcohol(p, session, 0.2f, itemInHand);
								}else if(s.contains("Wodka")){
									PlayerScripts.drunkAlcohol(p, session, 0.4f, itemInHand);
								}
								break;
							}
						}		
						for(String s : drugItemNames){
							if(s.equals(itemInHand.getItemMeta().getDisplayName())){
								event.setCancelled(true);
								if(s.contains("Heroin")){
									PlayerScripts.consumeDrugs(p, session, 0.3f, itemInHand);
								}else if(s.contains("Kokain")){
									PlayerScripts.consumeDrugs(p, session, 0.4f, itemInHand);
								}else if(s.contains("Marihuana")){
									PlayerScripts.consumeDrugs(p, session, 0.2f, itemInHand);
								}
								break;
							}
						}
						
						if(itemInHand.getType() == Material.GOLDEN_APPLE){
							event.setCancelled(true);
							return;
						}
//						else if(itemInHand.getType() == Material.BOOK){
//							if(itemInHand.hasItemMeta()){
//								if(itemInHand.getItemMeta().getDisplayName().equals(Colors.TURKISH + "Polizeimenü")){
//									if(session.isLoggedInAsCop()){
//										if(session.isEscortPlayer()){
//											ShinigamiLife.getMenuManager().open(p, session, "policemenu");
//										}
//									}
//								}
//							}
//						}
						
						
						if(event.getAction() == Action.LEFT_CLICK_AIR || event.getAction() == Action.LEFT_CLICK_BLOCK){
							if(itemInHand.getType() == Material.SHEARS && itemInHand.getItemMeta().getDisplayName().equals(Colors.YELLOW + "Schere")){
								event.setCancelled(true);
								PlayerScripts.breakFetter(p, session);
							}else if(itemInHand.getType() == Material.BOOK && itemInHand.getItemMeta().getDisplayName().equals(Colors.TURKISH + "Polizeimenü")){
								event.setCancelled(true);
								ShinigamiLife.getMenuManager().open(p, session, "policemenu");
							}else if(itemInHand.getType() == Material.BOOK && itemInHand.getItemMeta().getDisplayName().equals(Colors.YELLOW + "Spielermenü")){
								event.setCancelled(true);
								ShinigamiLife.getMenuManager().open(p, session, "playermenu");
							}else if(itemInHand.getType() == Material.STICK && itemInHand.getItemMeta().getDisplayName().equals(Colors.GREY + "Zigarrete")){
								event.setCancelled(true);
								session.setSmoking(true);
							}else if(p.isSneaking()){
								if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[S]")){
									event.setCancelled(true);
									if(!session.isZoomed()){
										session.setZoomed(true);
										p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 99999, 9000));
									}else{
										session.setZoomed(false);
										p.removePotionEffect(PotionEffectType.SLOW);
									}
								}
							}
							return;
						}else if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
							if(itemInHand.getType() == Material.NAME_TAG && itemInHand.getItemMeta().getDisplayName().equals(Colors.WHITE + "Arzttasche")){
								event.setCancelled(true);
								if(p.isSneaking()){
									if(Utils.getLivingEntityHealth(p)+10 >= 40){
										p.setHealth(40);
									}else{
										p.setHealth(Utils.getLivingEntityHealth(p)+10);
									}
									if(itemInHand.getAmount() == 1){
										p.getInventory().remove(itemInHand);
									}else{
										itemInHand.setAmount(itemInHand.getAmount()-1);
									}
									p.sendMessage(Colors.GREEN + "Du hast dich geheilt.");
								}
							}else if(itemInHand.getType() == Material.BOOK && itemInHand.getItemMeta().getDisplayName().equals(Colors.YELLOW + "Spielermenü")){
								event.setCancelled(true);
								if(p.isInsideVehicle()){
									if(p.getVehicle() instanceof Horse){
										Horse h = (Horse) p.getVehicle();
											
										float speed = h.getMetadata("speed").get(0).asFloat();
										float gang1 = h.getMetadata("gang1").get(0).asFloat();
										float gang2 = h.getMetadata("gang2").get(0).asFloat();
										if(speed == 0){
											h.setMetadata("speed", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), gang1));
											p.sendMessage(Colors.GREY + "Du bist in den 2ten Gang gewechselt.");
										}else if(speed == gang1){
											h.setMetadata("speed", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), gang2));
											p.sendMessage(Colors.GREY + "Du bist in den 3ten Gang gewechselt.");
										}else if(speed == gang2){
											h.setMetadata("speed", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), 0));
											p.sendMessage(Colors.GREY + "Du bist in den 1ten Gang gewechselt.");
										}
									}
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[MG]")){
								if(!session.isZoomed()){
									PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().mgs, session);
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[P]")){
								if(!session.isZoomed()){
									PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().pistols, session);
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[G]")){
								if(!session.isZoomed()){
									PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().guns, session);
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[MP]")){
								if(!session.isZoomed()){								
									PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().mps, session);
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[PG]")){
								if(!session.isZoomed()){
									PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().pumpguns, session);
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[T]")){
								if(!session.isZoomed()){
									PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().throwers, session);
								}
							}else if(itemInHand.getItemMeta().getDisplayName().contains(Colors.DARK_GREY + "[S]")){
								PlayerScripts.shoot(p, itemInHand, event, ShinigamiLife.getWeaponManager().sniper, session);
							}
							
							return;
						}
					}
				}
				
				if(b != null){
					if(b.getType() == Material.SIGN_POST || b.getType() == Material.WALL_SIGN){
						Sign s = (Sign) b.getState();
						if(s.getLine(0).equals("[Hauskauf]")){
							if(!s.getLine(1).equals("")){
								if(!s.getLine(2).equals("")){
									if(s.getLine(3).equals("")){
										ShinigamiLife.getMenuManager().open(p, session, "houseshop");
									}else if(s.getLine(3).equals(p.getName())){
										ShinigamiLife.getMenuManager().open(p, session, "houseshop");
									}else{
										p.sendMessage(Colors.RED + "Diese Haus gehört bereits jemandem!");
									}
								}
							}
						}
			 		}else if(b.getType() == Material.CHEST){

			 			House house = null;
			 			for(House h : session.getHouses().values()){
			 				if(h.area.intersectsRegion(p.getLocation())){
			 					house = h;
			 					break;
			 				}
			 			}
						
						if(house != null){
							if(house.area.intersectsRegion(p.getLocation())){
								
							}else if(session.getProfession().equals("cop")){
								if(!BarAPI.hasBar(p)){
									Chest c = (Chest) b.getState();
									session.setSearchedChest(c);
									session.setSearchChest(true);
									BarAPI.setMessage(p, "durchsuche Kiste...", 5);
									p.sendMessage(Colors.GREY + "Du darfst dich nicht weiter als 2 Blöcke von der Kiste entfernen.");
								}else{
									p.sendMessage(Colors.RED + "Du führst bereits eine Aktion aus.");
								}
							}else{
								event.setCancelled(true);
							}
						}else{
							event.setCancelled(true);
						}
			 		}else if(b.getType() == Material.WOODEN_DOOR || b.getType() == Material.IRON_DOOR_BLOCK){
			 			if(itemInHand.hasItemMeta()){
			 				if(itemInHand.getType() == Material.NETHER_BRICK_ITEM && itemInHand.getItemMeta().getDisplayName().equals(Colors.WHITE + "Schlüssel")){
			 					if(b.getMetadata("keycode").size() != 0){
			 						if(itemInHand.getItemMeta().getLore() != null){
				 						String keycodeString = b.getMetadata("keycode").get(0).asString();
				 						String keycodeItemString = itemInHand.getItemMeta().getLore().get(0);
				 						
				 						if(Integer.parseInt(keycodeItemString) != Integer.parseInt(keycodeString)){
				 							event.setCancelled(true);
				 						}
			 						}
			 					}else{
			 						if(itemInHand.getItemMeta().getLore() == null){
				 						int keycode = random.nextInt(999999);
				 						b.setMetadata("keycode", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), keycode));
				 						
				 						ItemMeta itemMeta = itemInHand.getItemMeta();
				 						List<String> lore = new ArrayList<String>();
				 						lore.add("" + keycode);
				 						itemMeta.setLore(lore);
				 						itemInHand.setItemMeta(itemMeta);
				 						p.updateInventory();
			 						}
			 					}
			 				}
			 			}
			 		}
					
					return;
				}
			}
		}
	}

	@SuppressWarnings({ "deprecation", "static-access" })
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerChat(AsyncPlayerChatEvent event) {
		Player p = (Player) event.getPlayer();
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		String format = "";
//		if(session.faction != null){
//			if(session.channel.equals(session.faction.name)){
//				format = "§7[" + session.getChannel() + "]§r " + p.getName() + " <" + event.getMessage() + ">";
//			}else{
//				format = "§7[" + session.getChannel() + "] §e[" + session.faction.prefix + "]§r " + p.getName() + " <" + event.getMessage() + ">";
//			}
//		}else{
			format = Colors.GREY + "[" + session.getChannel() + "] " + Colors.RESET + p.getName() + "   <" + event.getMessage() + ">";
//		}
		
//		if(pd.group != null){
//			for(Player player : pd.group.members){
//				player.sendMessage(format);
//			}
//		}
		
		for(Player player : ShinigamiLife.getJavaPlugin().getServer().getOnlinePlayers()){
			Session sessionT = ShinigamiLife.getSessionManager().getSession(player.getUniqueId() + "");

			if(session.getChannel().equals("G")){
				player.sendMessage(format);
			}else if(session.getChannel().equals("L")){
				int local_radius = Integer.parseInt(ShinigamiLife.getConfiguration().getConfig().getEntry("local_radius"));
				if((player.getLocation().getX() > (p.getLocation().getX()-local_radius)) && (player.getLocation().getX() < (p.getLocation().getX()+local_radius))){
					if((player.getLocation().getZ() > (p.getLocation().getZ()-local_radius)) && (player.getLocation().getZ() < (p.getLocation().getZ()+local_radius))){
						if((player.getLocation().getY() > (p.getLocation().getY()-local_radius)) && (player.getLocation().getY() < (p.getLocation().getY()+local_radius))){
							player.sendMessage(format);
						}
					}
				}
			}else if(session.getChannel().equals(sessionT.getChannel())){
				player.sendMessage(format);
			}
		}
		
		event.setCancelled(true);
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerRespawn(PlayerRespawnEvent event) {
		Player p = (Player) event.getPlayer();
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		p.getActivePotionEffects().clear();
		
		p.sendMessage(Colors.GREY + "Das war verdammt knapp.. Zum Glück stehst du wieder auf deinen Beinen");
		session.setMoneyInPocket(0);
		
		if(!session.getProfession().equals("cop")){
			ItemStack itemStack = new ItemStack(Material.BOOK);
			ItemMeta itemMeta = itemStack.getItemMeta();
			itemMeta.setDisplayName(Colors.YELLOW + "Spielermenü");
			itemStack.setItemMeta(itemMeta);
			
			ItemStack playerMenuItem = null;
			for(ItemStack is : p.getInventory().getContents()){
				if(is != null && is.hasItemMeta()){
					if(is.getType() == Material.BOOK && is.getItemMeta().getDisplayName().equals(Colors.YELLOW + "Spielermenü")){
						playerMenuItem = is;
					}
				}
			}
			
			if(playerMenuItem == null){
				p.getInventory().addItem(itemStack);
			}
		}else{
			ShinigamiLife.getPoliceManager().equipment(p);
		}
		
		if(ShinigamiLife.getLocationsManager().exist("respawn_city_1")){
			p.teleport(ShinigamiLife.getLocationsManager().locations.get("respawn_city_1"));
		}
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerDeath(EntityDeathEvent event) {
		
		if(event.getEntityType() == EntityType.PLAYER){
			for(ItemStack item : event.getDrops()){
				if(item.getType() != Material.BOOK && item.getType() != Material.getMaterial(52)){
					event.getEntity().getWorld().dropItem(event.getEntity().getLocation(), item);
				}
			}
			
			event.getDrops().clear();
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onPlayerMove(PlayerMoveEvent event) {
		Player p = (Player) event.getPlayer();
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(session.isJoined()){
			if(event.getFrom().distance(event.getTo()) > 6){
				event.setCancelled(true);
			}
			
			session.setMoving(true);
		}
	}

	@EventHandler(priority = EventPriority.NORMAL)
	public void onDropItem(PlayerDropItemEvent event) {
		Item i = event.getItemDrop();
		ItemStack iStack = i.getItemStack();
		
		if(iStack != null){
			if(iStack.hasItemMeta()){
				if(iStack.getItemMeta().getDisplayName().equals(Colors.YELLOW + "Spielermenü")){
					event.setCancelled(true);
					event.getItemDrop().remove();
				}else if(iStack.getItemMeta().getDisplayName().equals(Colors.TURKISH + "Polizeimenü")){
					event.setCancelled(true);
					event.getItemDrop().remove();
				}else if(iStack.getItemMeta().getDisplayName().equals(Colors.BLACK + "  .")){
					event.setCancelled(true);
					event.getItemDrop().remove();
				}
			}
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBedEnter(PlayerBedEnterEvent event) {
		event.setCancelled(true);
	}
	
	
}
