package com.shinigami.sessions;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Random;

import me.confuser.barapi.BarAPI;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.metadata.FixedMetadataValue;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;

import com.shinigami.configs.Configuration;
import com.shinigami.housing.HousingManager.House;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.scripts.PlayerScripts;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class SessionManager {
	
	private Random random;
	private HashMap<String, Session> sessions;
	private String dir = "MinecraftLife/players";
	
	public SessionManager() {
		random = new Random();
		sessions = new HashMap<String, Session>();
		
		File file = new File(dir);
		if(!file.exists()){
			file.mkdirs();
		}
	}
	
	@SuppressWarnings("deprecation")
	public void createSession(Player p) {
		if(getSession(p.getUniqueId() + "") == null){
			Session session = new Session();
			
			File file = new File(dir + "/" + p.getUniqueId() + ".yml");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			Properties props = Utils.loadFile(file);
				
			if(props.contains("acccount")){
				session.setAccCount((Integer.parseInt(props.getProperty("acccount"))));
				if(session.getAccCount() == 3){
					session.setAcc1CharName("1.charname");
					session.setAcc1Money(Integer.parseInt(props.getProperty("1.moneyBank") + Integer.parseInt(props.getProperty("1.moneyInPocket"))));
					session.setAcc1Profession(props.getProperty("1.profession"));
					
					session.setAcc2CharName("2.charname");
					session.setAcc2Money(Integer.parseInt(props.getProperty("2.moneyBank") + Integer.parseInt(props.getProperty("2.moneyInPocket"))));
					session.setAcc2Profession(props.getProperty("2.profession"));
					
					session.setAcc3CharName("3.charname");
					session.setAcc3Money(Integer.parseInt(props.getProperty("3.moneyBank") + Integer.parseInt(props.getProperty("3.moneyInPocket"))));
					session.setAcc3Profession(props.getProperty("3.profession"));
				}else if(session.getAccCount() == 2){
					session.setAcc1CharName("1.charname");
					session.setAcc1Money(Integer.parseInt(props.getProperty("1.moneyBank") + Integer.parseInt(props.getProperty("1.moneyInPocket"))));
					session.setAcc1Profession(props.getProperty("1.profession"));
					
					session.setAcc2CharName("2.charname");
					session.setAcc2Money(Integer.parseInt(props.getProperty("2.moneyBank") + Integer.parseInt(props.getProperty("2.moneyInPocket"))));
					session.setAcc2Profession(props.getProperty("2.profession"));
				}else if(session.getAccCount() == 1){
					session.setAcc1CharName("1.charname");
					session.setAcc1Money(Integer.parseInt(props.getProperty("1.moneyBank") + Integer.parseInt(props.getProperty("1.moneyInPocket"))));
					session.setAcc1Profession(props.getProperty("1.profession"));
				}
			}else{
				session.setAccCount(0);
			}
			session.setPlayerName(p.getName());
			session.setUuid(p.getUniqueId() + "");
			
			sessions.put(p.getUniqueId() + "", session);
		}else{
			Session session = getSession(p.getUniqueId() + "");
			
			File file = new File(dir + "/" + p.getUniqueId() + ".yml");
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
	
			Properties props = Utils.loadFile(file);
			
			session.setAccCount((Integer.parseInt(props.getProperty("acccount"))));
			if(session.getAccCount() == 3){
				session.setAcc1Money(Integer.parseInt(props.getProperty("1.moneyBank") + Integer.parseInt(props.getProperty("1.moneyInPocket"))));
				session.setAcc2Money(Integer.parseInt(props.getProperty("2.moneyBank") + Integer.parseInt(props.getProperty("2.moneyInPocket"))));
				session.setAcc3Money(Integer.parseInt(props.getProperty("3.moneyBank") + Integer.parseInt(props.getProperty("3.moneyInPocket"))));
			}else if(session.getAccCount() == 2){
				session.setAcc1Money(Integer.parseInt(props.getProperty("1.moneyBank") + Integer.parseInt(props.getProperty("1.moneyInPocket"))));
				session.setAcc2Money(Integer.parseInt(props.getProperty("2.moneyBank") + Integer.parseInt(props.getProperty("2.moneyInPocket"))));
			}else if(session.getAccCount() == 1){
				session.setAcc1Money(Integer.parseInt(props.getProperty("1.moneyBank") + Integer.parseInt(props.getProperty("1.moneyInPocket"))));
			}
		}
	}
	
	public void loadData(Player p, int accNum){
		Session session = getSession(p.getUniqueId() + "");
		
		File file = new File(dir + "/" + p.getUniqueId() + ".yml");
		Properties props = Utils.loadFile(file);
		
		session.setJoined(true);
		if(!props.contains(accNum + ".default_channel")){
			session.setChannel(Configuration.getConfig().getEntry("default_channel"));
			session.setMoneyBank(Integer.parseInt(Configuration.getConfig().getEntry("start_money")));
			session.setInvSize(Integer.parseInt(Configuration.getConfig().getEntry("invSize")));
			session.setAccNumLoggedIn(accNum);
			session.setGarageLand(new Garage());
			session.setGarageWater(new Garage());
			session.setPlayerInv(Bukkit.createInventory(p, InventoryType.PLAYER));
			session.getPlayerInv().setContents(p.getInventory().getContents());
			session.setArmor(p.getInventory().getArmorContents());
			session.setDrinkLevel(20);
			session.setEatLevel(20);
			p.setHealthScaled(true);
			p.setHealthScale(40);
			p.setMaxHealth(40);
			p.setLevel(20);
			p.setFoodLevel(20);
			
			session.setShootables(new HashMap<String, Boolean>());
			session.setShootTimers(new HashMap<String, Float>());
			session.setShootTimersMax(new HashMap<String, Float>());
			session.setHouses(new HashMap<String, House>());
			session.setHorses(new ArrayList<Horse>());
			session.setBoats(new ArrayList<Boat>());

			HashMap<String, Boolean> licences = new HashMap<String, Boolean>();
			session.setLicences(licences);
			
			changeInv(session, p);
			sessions.put(p.getUniqueId() + "", session);
		}else{
			
			session.setChannel(Configuration.getConfig().getEntry("default_channel"));
			session.setMoneyBank(Integer.parseInt(props.getProperty(accNum + ".moneyBank")));
			session.setMoneyInPocket(Integer.parseInt(props.getProperty(accNum + ".moneyInPocket")));
			session.setInvSize(Integer.parseInt(props.getProperty(accNum + ".invSize")));
			session.setAlcoholPromile(Float.parseFloat(props.getProperty(accNum + ".alcoholPromile")));
			session.setDrugsInBlood(Float.parseFloat(props.getProperty(accNum + ".drugsInBlood")));
			session.setAccNumLoggedIn(accNum);
			session.setGarageLand(new Garage());
			session.getGarageLand().load(props.getProperty(accNum + ".garageLand"));
			session.setGarageWater(new Garage());
			session.getGarageLand().load(props.getProperty(accNum + ".garageWater"));
			session.setEatLevel(Integer.parseInt(props.getProperty(accNum + ".eatLevel")));
			session.setDrinkLevel(Integer.parseInt(props.getProperty(accNum + ".drinkLevel")));
			session.setPlayerInv(Bukkit.createInventory(p, InventoryType.PLAYER));
			session.getPlayerInv().setContents(p.getInventory().getContents());
			session.setArmor(p.getInventory().getArmorContents());
			p.setHealthScaled(true);
			p.setHealthScale(40);
			p.setMaxHealth(40);
			p.setLevel(session.getDrinkLevel());
			p.setFoodLevel(session.getEatLevel());
			
			session.setGrade(Integer.parseInt(props.getProperty("grade")));
			
			session.setShootables(new HashMap<String, Boolean>());
			session.setShootTimers(new HashMap<String, Float>());
			session.setShootTimersMax(new HashMap<String, Float>());
			session.setHouses(new HashMap<String, House>());
			session.setHorses(new ArrayList<Horse>());
			session.setBoats(new ArrayList<Boat>());
				
			HashMap<String, Boolean> licences = new HashMap<String, Boolean>();
			
			for(String s : Licence.LICENCES){
				licences.put(s, Boolean.parseBoolean(props.getProperty(s)));
			}
				
			session.setLicences(licences);
				
			changeInv(session, p);
			sessions.put(p.getUniqueId() + "", session);
		}
	}

	public void saveAll(){
		for(Session session : sessions.values()) {
			File file = new File(dir + "/" + session.getUuid() + ".yml");
			
			if(!file.exists()){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			
			Properties props = Utils.loadFile(file);
			
			props.setProperty("acccount", session.getAccCount() + "");
			props.setProperty("1.charname", session.getAcc1CharName() + "");
			props.setProperty("1.profession", session.getAcc1Profession() + "");
			props.setProperty("2.charname", session.getAcc2CharName() + "");
			props.setProperty("2.profession", session.getAcc2Profession() + "");
			props.setProperty("3.charname", session.getAcc3CharName() + "");
			props.setProperty("3.profession", session.getAcc3Profession() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".moneyBank", session.getMoneyBank() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".moneyInPocket", session.getMoneyInPocket() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".invSize", session.getInvSize() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".alcoholPromile", session.getAlcoholPromile() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".drugsInBlood", session.getDrugsInBlood() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".garageLand", session.getGarageLand().save());
			props.setProperty(session.getAccNumLoggedIn() + ".garageWater", session.getGarageWater().save());
			props.setProperty(session.getAccNumLoggedIn() + ".eatLevel", session.getEatLevel() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".drinkLevel", session.getDrinkLevel() + "");
			props.setProperty(session.getAccNumLoggedIn() + ".grade", session.getGrade() + "");
			
			for(String s : Licence.LICENCES){
				props.setProperty(session.getAccNumLoggedIn() + "." + s, session.getLicences().get(s) + "");
			}
			
			try {
				FileOutputStream fileOut = new FileOutputStream(file);
				props.store(fileOut, null);
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public Session getSession(String uuid){
		return sessions.get(uuid);
	}
	
	@SuppressWarnings("deprecation")
	public void changeInv(Session session, Player p){
		int invSize = 0;
		invSize = session.getInvSize();
		ItemStack restricted = new ItemStack(52);
		ItemMeta meta = restricted.getItemMeta();
		meta.setDisplayName(Colors.RED + "[Gesperrt]");
		restricted.setItemMeta(meta);
		
		for(ItemStack itemStack : p.getInventory().getContents()){
			if(itemStack != null){
				if(itemStack.hasItemMeta()){
					if(itemStack.getItemMeta().hasDisplayName()){
						if(itemStack.getItemMeta().getDisplayName().equals(Colors.RED + "[Gesperrt]")){
							p.getInventory().remove(itemStack);
						}
					}
				}
			}
		}
		
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
		
		
		if(invSize < 27){
			int slot = 9;
			for(int i = 0; i < 27; i++){
				p.getInventory().setItem(slot, new ItemStack(restricted));
				if(slot + invSize == 35){
					break;
				}else{
					slot++;
				}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateFast(JavaPlugin javaPlugin){
		if(javaPlugin.getServer().getOnlinePlayers().length != 0){
			for(Entry<String, Session> entry : sessions.entrySet()){
				Session session = entry.getValue();
				Player p = Bukkit.getPlayer(session.getPlayerName());
				
				if(session.isJoined())
					for(Entry<String, Boolean> entry1 : session.getShootables().entrySet()){
						boolean shot = entry1.getValue();
						float timer = session.getShootTimers().get(entry1.getKey());
						float timerMax = session.getShootTimersMax().get(entry1.getKey());
						
						if(!shot){
							if(timer == timerMax){
								session.getShootTimers().put(entry1.getKey(), 0f);
								session.getShootables().put(entry1.getKey(), true);
							}else{
								session.getShootTimers().put(entry1.getKey(), timer + 0.1f);
							}
						}
					}
					
					if(session.isZoomed()){
						if(p.getItemInHand().hasItemMeta()){
							if(p.getItemInHand().getItemMeta().getDisplayName() != null && !p.getItemInHand().getItemMeta().getDisplayName().equals("")){
								if(!p.getItemInHand().getItemMeta().getDisplayName().contains("[S]") && !p.getItemInHand().getItemMeta().getDisplayName().contains("Fernglass")){
									session.setZoomed(false);
									p.removePotionEffect(PotionEffectType.SLOW);
								}
							}
						}else{
							session.setZoomed(false);
							p.removePotionEffect(PotionEffectType.SLOW);
						}
					}
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	public void updateSlow(JavaPlugin javaPlugin){
		if(javaPlugin.getServer().getOnlinePlayers().length != 0){
			for(Entry<String, Session> entry : sessions.entrySet()){
				Session session = entry.getValue();
				Player p = Bukkit.getPlayer(session.getPlayerName());
				
				if(p != null){
					if(!session.isCharChoiceShowed()){
						ShinigamiLife.getMenuManager().open(p, session, "account");
						session.setCharChoiceShowed(true);
					}
					
					if(session.isJoined()){
						if(session.getTaxTimer() == Integer.parseInt(Configuration.getConfig().getEntry("tax_time"))){
							session.setTaxTimer(0);
							for(Player player : javaPlugin.getServer().getOnlinePlayers()){
								if(session.getPlayerName().equals("" + player.getUniqueId())){
									if(session.getProfession().equals("civ")){
										session.setMoneyBank(session.getMoneyBank()
												+ Integer.parseInt(Configuration.getConfig().getEntry("tax")));
										player.sendMessage(Colors.GREY + "Du hast dein Gehalt in der höhe von " + Colors.DARK_GREEN + Integer.parseInt(Configuration.getConfig().getEntry("tax")) + Colors.GREY + " erhalten.");
										player.sendMessage(Colors.GREY + "Dein nächstes Gehalt erhälst du in " + Colors.DARK_GREY + (Integer.parseInt(Configuration.getConfig().getEntry("tax_time")) / 60) + Colors.GREY + " minuten.");
									}else if(session.getProfession().equals("cop")){
										session.setMoneyBank(session.getMoneyBank()
												+ Integer.parseInt(Configuration.getConfig().getEntry("tax_cop")));
										player.sendMessage(Colors.GREY + "Du hast dein Gehalt in der höhe von " + Colors.DARK_GREEN + Integer.parseInt(Configuration.getConfig().getEntry("tax_cop")) + Colors.GREY + " erhalten.");
										player.sendMessage(Colors.GREY + "Dein nächstes Gehalt erhälst du in " + Colors.DARK_GREY + (Integer.parseInt(Configuration.getConfig().getEntry("tax_time")) / 60) + Colors.GREY + " minuten.");
									}
									break;
								}
							}
						}else{
							session.setTaxTimer(session.getTaxTimer() + 1);
						}
						
	//					List<Entity> entitiesInSight = p.getNearbyEntities(140, 140, 140);
	//					for(Entity entity : entitiesInSight){
	//						if(entity instanceof Player){
	//							Player pInSight = (Player) entity;
	//							if(p.canSee(pInSight)){
	//								p.showPlayer(pInSight);
	//							}else{
	//								p.hidePlayer(pInSight);
	//							}
	//						}
	//					}
						
						p.setLevel(session.getDrinkLevel());
						p.setExp(0);
						session.setDrinkTimer(session.getDrinkTimer() + 1);
						if(session.getDrinkTimer() > 1200){
							session.setDrinkTimer(0);
							session.setDrinkLevel(session.getDrinkLevel() - 1);
							if(session.getDrinkLevel() > 20){
								session.setDrinkLevel(20);
							}
							if(p.getLevel() < 20 / 3){
								p.sendMessage(Colors.GREY + "Du hast schon lange nichts mehr getrunken...");
							}
							if(session.getDrinkLevel() == 0){
								p.damage(0.5);
							}
						}
		
						p.setFoodLevel(session.getEatLevel());
						session.setEatTimer(session.getEatTimer() + 1);
						if(session.getEatTimer() > 1200){
							session.setEatTimer(0);
							session.setEatLevel(session.getEatLevel() - 1);
							if(p.getFoodLevel() < 20 / 3){
								p.sendMessage(Colors.GREY + "Du hast schon lange nichts mehr gegessen...");
							}
							if(session.getEatLevel() == 0){
								p.damage(0.5);
							}
						}
						
						if(session.isSmoking()){
							if(p.getItemInHand().getType() == Material.STICK && p.getItemInHand().getItemMeta().getDisplayName().equals(Colors.GREY + "Zigarrete")){
								PlayerScripts.smoke(p, session, p.getItemInHand());
							}
						}
						
						if(p.getInventory().getHelmet() != null){
							if(p.getInventory().getHelmet().hasItemMeta()){
								if(p.getInventory().getHelmet().getType() == Material.CHAINMAIL_HELMET){
									PlayerScripts.nightvision(p);
								}
							}
						}
						
						if(session.isMoving()){
							session.setMoving(false);
						}
						
						if(Utils.getLivingEntityHealth(p) != Utils.getLivingEntityMaxHealth(p)){
							PlayerScripts.bleed(p, session);
						}
						
						if(session.isFettedCop()){
							p.setWalkSpeed(0);
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, -15));
							p.setSneaking(true);
						}else if(session.isFetted()){
							p.setWalkSpeed(0);
							p.addPotionEffect(new PotionEffect(PotionEffectType.JUMP, 100, -4));
							p.setSneaking(true);
						}
						
						if(session.isTazzed()){
							if(p.hasPotionEffect(PotionEffectType.JUMP)){
								p.getWorld().playSound(p.getLocation(), Sound.FIZZ, 0.1f, 8);
							}else{
								p.setWalkSpeed(0.2f);
								p.setSneaking(false);
								session.setTazzed(false);
							}
						}
						
						if(!session.getProfession().equals("cop")){
							if(session.isBanksClosed()){
								session.setBanksClosedTimer(session
										.getBanksClosedTimer() + 1);
								if(session.getBanksClosedTimer() > 1800){
									session.setBanksClosed(false);
									session.setBanksClosedTimer(0);
								}
							}
							
							if(session.getAlcoholPromile() != 0){
								session.setAlcoholTimer(session.getAlcoholTimer() + 1);
								if(session.getAlcoholTimer() > 150){
									Location loc = p.getLocation();
									loc.setPitch(loc.getPitch() + (random.nextInt(10)-5));
									loc.setYaw(loc.getYaw() + (random.nextInt(10)-5));
									
									p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 4));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 4));
									p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 4));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SLOW, 600, 4));
									if(Utils.getLivingEntityHealth(p) < 19){
										p.setHealth(Utils.getLivingEntityHealth(p)+1f);
									}
									
									session.setAlcoholPromile(session
											.getAlcoholPromile() - 0.075f);
									session.setAlcoholTimer(0);
								}
							}
							
							if(session.getDrugsInBlood() != 0){
								session.setDrugTimer(session.getDrugTimer() + 1);
								if(session.getDrugTimer() > 90){
									p.addPotionEffect(new PotionEffect(PotionEffectType.CONFUSION, 600, 10));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SATURATION, 600, 4));
									p.addPotionEffect(new PotionEffect(PotionEffectType.WEAKNESS, 600, 4));
									p.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 400, 3));
									
									p.setHealth(Utils.getLivingEntityHealth(p)+1f);
									
									session.setDrugsInBlood(session
											.getDrugsInBlood() - 0.05f);
									session.setDrugTimer(0);
								}
							}
							
							if(session.isStealingHorse()){
								double x = p.getLocation().getX() - session.getStealHorse().getLocation().getX();
								double y = p.getLocation().getY() - session.getStealHorse().getLocation().getY();
								double z = p.getLocation().getZ() - session.getStealHorse().getLocation().getZ();
								
								if((x > -2 && x < 2) && (y > -2 && y < 2) && (z > -2 && z < 2)){
									if(!BarAPI.hasBar(p)){
										session.getStealHorse().setMetadata("stolen", new FixedMetadataValue(ShinigamiLife.getJavaPlugin(), true));
										session.setStealingHorse(false);
										p.sendMessage(Colors.GREEN + "Du hast das Pferd geknackt und kannst es nun entwenden.");
									}
								}else{
									BarAPI.removeBar(p);
									session.setStealingHorse(false);
									session.setStealHorse(null);
									p.sendMessage(Colors.RED + "Du hast dich zu weit von dem Pferd entfernt.");
								}
							}
							
							if(session.getProcessing() != null){
								double x = p.getLocation().getX() - session.getProcessing().npc.getLocation().getX();
								double y = p.getLocation().getY() - session.getProcessing().npc.getLocation().getY();
								double z = p.getLocation().getZ() - session.getProcessing().npc.getLocation().getZ();
								
								if((x > -4 && x < 4) && (y > -4 && y < 4) && (z > -4 && z < 4)){
									if(!BarAPI.hasBar(p)){
										PlayerScripts.processingSucces(p, session);
									}
								}else{
									BarAPI.removeBar(p);
									session.setProcessing(null);
									p.sendMessage(Colors.RED + "Du hast dich zu weit von dem Verarbeiter entfernt.");
								}
							}
							
							if(session.isRobNpc()){
								double x = p.getLocation().getX() - session.getInteractNpc().npcEntity.getLocation().getX();
								double y = p.getLocation().getY() - session.getInteractNpc().npcEntity.getLocation().getY();
								double z = p.getLocation().getZ() - session.getInteractNpc().npcEntity.getLocation().getZ();
								
								if((x > -5 && x < 5) && (y > -5 && y < 5) && (z > -5 && z < 5)){
									if(BarAPI.hasBar(p)){
										if(session.getInteractNpc().money >= session.getRobMoneySec()){
											session.setRobTimer(session
													.getRobTimer() + 1);
											
											if(session.getRobTimer() == session.getRobPoliceCallTime()){
												ShinigamiLife.getPoliceManager().fastAlert(p, 6);
												ShinigamiLife.getPoliceManager().wantedList.add(p);
											}else if(session.getRobTimer() == session.getRobBanksClosedTime()){
												session.setBanksClosed(true);
											}
											session.getInteractNpc().money-=session.getRobMoneySec();
											session.setMoneyInPocket(session
													.getMoneyInPocket()
													+ session.getRobMoneySec());
										}else{
											BarAPI.removeBar(p);
											session.setRobTimer(0);
											session.setRobNpc(false);
											p.sendMessage(Colors.GREEN + "Der Raub ist erfolgreich.");
										}
									}
								}else{
									BarAPI.removeBar(p);
									session.setRobTimer(0);
									session.setRobNpc(false);
									p.sendMessage(Colors.RED + "Der Raub ist abgebrochen.");
									p.sendMessage(Colors.RED + "Du hast dich zu weit von dem NPC entfernt.");
								}
								
								if(p.getWalkSpeed() == 0){
									BarAPI.removeBar(p);
									session.setRobTimer(0);
									session.setRobNpc(false);
									p.sendMessage(Colors.RED + "Der Raub ist abgebrochen.");
								}
							}
						}else{
							if(session.getEscort() != null){
								Location loc = p.getLocation();
								
								if(p.isInsideVehicle()){
									Vehicle v = (Vehicle) p.getVehicle();
									if(v.getType() == EntityType.HORSE){
										loc.setY(loc.getY()-1);
									}
								}
								
								session.getEscort().teleport(loc);
							}else if(session.isSearchHorse()){
								double x = p.getLocation().getX() - session.getSearchedHorse().getLocation().getX();
								double y = p.getLocation().getY() - session.getSearchedHorse().getLocation().getY();
								double z = p.getLocation().getZ() - session.getSearchedHorse().getLocation().getZ();
								
								if((x > -2 && x < 2) && (y > -2 && y < 2) && (z > -2 && z < 2)){
									if(!BarAPI.hasBar(p)){
										ShinigamiLife.getMenuManager().open(p, session, "searchhorsemenu");
										session.setSearchHorse(false);
									}
								}else{
									BarAPI.removeBar(p);
									session.setSearchHorse(false);
									p.sendMessage(Colors.RED + "Du hast dich zu weit von dem Pferd entfernt.");
								}
							}else if(session.isSearchPlayer()){
								double x = p.getLocation().getX() - session.getSearchedPlayer().getLocation().getX();
								double y = p.getLocation().getY() - session.getSearchedPlayer().getLocation().getY();
								double z = p.getLocation().getZ() - session.getSearchedPlayer().getLocation().getZ();
								
								if((x > -2 && x < 2) && (y > -2 && y < 2) && (z > -2 && z < 2)){
									if(!BarAPI.hasBar(p)){
										ShinigamiLife.getMenuManager().open(p, session, "searchpersonmenu");
										session.setSearchPlayer(false);
									}
								}else{
									BarAPI.removeBar(p);
									session.setSearchPlayer(false);
									p.sendMessage(Colors.RED + "Du hast dich zu weit von der Person entfernt.");
								}
							}else if(session.isSearchChest()){
								double x = p.getLocation().getX() - session.getSearchedChest().getLocation().getX();
								double y = p.getLocation().getY() - session.getSearchedChest().getLocation().getY();
								double z = p.getLocation().getZ() - session.getSearchedChest().getLocation().getZ();
								
								if((x > -2 && x < 2) && (y > -2 && y < 2) && (z > -2 && z < 2)){
									if(!BarAPI.hasBar(p)){
										ShinigamiLife.getMenuManager().open(p, session, "searchchestmenu");
										session.setSearchChest(false);
									}
								}else{
									BarAPI.removeBar(p);
									session.setSearchChest(false);
									p.sendMessage(Colors.RED + "Du hast dich zu weit von der Kiste entfernt.");
								}
							}else if(session.isSearchBoat()){
								double x = p.getLocation().getX() - session.getSearchedBoat().getLocation().getX();
								double y = p.getLocation().getY() - session.getSearchedBoat().getLocation().getY();
								double z = p.getLocation().getZ() - session.getSearchedBoat().getLocation().getZ();
								
								if((x > -2 && x < 2) && (y > -2 && y < 2) && (z > -2 && z < 2)){
									if(!BarAPI.hasBar(p)){
										ShinigamiLife.getMenuManager().open(p, session, "searchboatmenu");
										session.setSearchBoat(false);
									}
								}else{
									BarAPI.removeBar(p);
									session.setSearchBoat(false);
									p.sendMessage(Colors.RED + "Du hast dich zu weit von dem Boot entfernt.");
								}
							}
						}
					}
				}
			}
		}
	}
	
	public Processing createNewProcessing(){
		return new Processing();
	}
	
	public class Processing {
		public Entity npc;
		public String licence;
		public ItemStack processingItem, resultItem;
		public int amount;
	}
	
	public class Garage {
		public HashMap<String, Boolean> garageContent;
		
		public Garage(){
			garageContent = new HashMap<String, Boolean>();
		}
		
		public void load(String property){
			String[] content = property.split(";");
			
			for(String s : content){
				garageContent.put(s, true);
			}
		}
		
		public String save(){
			String content = "";
			
			for(String s : garageContent.keySet()){
				if(content.length() != 0){
					content = content + ";" + s;
				}else{
					content = s;
				}
			}
			
			return content;
		}
	}
	
	public static class Licence {
		public final static String RIDINGLICENCE          = "ridinglicence";
		public final static String BOATLICENCE            = "boatlicence";
		public final static String WEAPONGLICENCE         = "weaponlicence";
		public final static String REBELLICENCE           = "rebellicence";
		public final static String IRONMININGLICENCE      = "ironmininglicence";
		public final static String DIAMONDMININGLICENCE   = "diamondmininglicence";
		public final static String REDSTONEMININGLICENCE  = "redstonemininglicence";
		public final static String COALMININGLICENCE      = "coalmininglicence";
		public final static String SMARAGDMININGLICENCE   = "smaragdmininglicence";
		public final static String GOLDMININGLICENCE      = "goldmininglicence";
		public final static String FISHINGLICENCE         = "fishinglicence";
		public final static String HUNTERLICENCE          = "huntinglicence";
		public final static String COURIERLICENCE         = "courierlicence";
		public final static String WOODCUTTERLICENCE      = "woodcutterlicence";
		public final static String SALTMININGLICENCE      = "saltmininglicence";
		public final static String KOKAINLICENCE          = "kokainlicence";
		public final static String HEROINLICENCE          = "heroinlicence";
		public final static String MARIHUANALICENCE       = "marihuanalicence";
		
		public static final String[] LICENCES = {
			"ridinglicence",
			"boatlicence",
			"weaponlicence",
			"rebellicence",
			"ironmininglicence",
			"diamondmininglicence",
			"redstonemininglicence",
			"coalmininglicence",
			"goldmininglicence",
			"smaragdmininglicence",
			"fishinglicence",
			"huntinglicence",
			"courierlicence",
			"woodcutterlicence",
			"saltmininglicence",
			"kokainlicence",
			"heroinlicence",
			"marihuanalicence"
		};
	}

}
