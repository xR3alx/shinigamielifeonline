package com.shinigami.police;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class PoliceManager {

	public WantedList wantedList;
	public ArrayList<Player> loggedInAsCop;

	public PoliceManager(){
		
		wantedList = new WantedList();
		
		loggedInAsCop = new ArrayList<Player>();
	}

	public void logInAsCop(Player p, Session session){
		loggedInAsCop.add(p);
		session.setLoggedInAsCop(true);
		
		Inventory inv = Bukkit.createInventory(p, InventoryType.PLAYER);
		inv.setContents(p.getInventory().getContents());
		session.setPlayerInv(inv);
		p.getInventory().clear();
		
		ShinigamiLife.getSessionManager().changeInv(session, p);
		equipment(p);
	}
	
	public void logOutAsCop(Player p, Session session){
		loggedInAsCop.remove(p);
		session.setLoggedInAsCop(false);
		
		p.getInventory().setContents(session.getPlayerInv().getContents());
		p.updateInventory();
	}
	
	public void fastAlert(Player sender, int priority){
		
		for(Player p : loggedInAsCop){
			p.sendMessage(Colors.DARK_GREY + "--------------------------------");
			p.sendMessage(Colors.DARK_GREY + "-------------" + Colors.RED + Colors.UNDERLINE + Colors.BOLD + "Notruf" + Colors.RESET + Colors.DARK_GREY + "-------------");
			p.sendMessage(Colors.DARK_GREY + "--------------------------------");
			p.sendMessage(Colors.RED + Colors.BOLD + sender.getName() + Colors.RESET + Colors.BLUE + "hat einen Notruf mit der Priorität " + Colors.RED + Colors.BOLD + priority + Colors.RESET + Colors.BLUE + " abgesetzt.");
			p.sendMessage(Colors.GREY + Colors.UNDERLINE + "Seine Position ist:");
			p.sendMessage("X  " + (int) sender.getLocation().getX());
			p.sendMessage("Y  " + (int) sender.getLocation().getY());
			p.sendMessage("Z  " + (int) sender.getLocation().getZ());
			p.sendMessage(Colors.DARK_GREY + "--------------------------------");
			
			int x = (int) (sender.getLocation().getX() - p.getLocation().getX());
			int z = (int) (sender.getLocation().getZ() - p.getLocation().getZ());
			
			if(x <= 100 && z <= 100 && x >= -100 && z >= -100){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 100 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 250 && z <= 250 && x >= -250 && z >= -250){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 250 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 500 && z <= 500 && x >= -500 && z >= -500){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 500 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 750 && z <= 750 && x >= -750 && z >= -750){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 750 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 1000 && z <= 1000 && x >= -1000 && z >= -1000){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 1000 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}
		}
	}
	
	public void slowAlert(Player sender, Session senderData){
		
		for(Player p : loggedInAsCop){
			p.sendMessage(Colors.DARK_GREY + "--------------------------------");
			p.sendMessage(Colors.DARK_GREY + "-------------" + Colors.RED + Colors.UNDERLINE + Colors.BOLD + "Notruf" + Colors.RESET + Colors.DARK_GREY + "-------------");
			p.sendMessage(Colors.DARK_GREY + "--------------------------------");
			p.sendMessage(Colors.RED + Colors.BOLD + sender.getName() + Colors.RESET + Colors.BLUE + "hat einen Notruf mit der Priorität abgesetzt.");
			p.sendMessage(Colors.BLUE + Colors.UNDERLINE + "Grund: " + Colors.TURKISH + senderData.getAlertMsg());
			p.sendMessage(Colors.BLUE + Colors.UNDERLINE + "Verbrecher: " + Colors.TURKISH + senderData.getAlertPlayer());
			p.sendMessage(Colors.BLUE + Colors.UNDERLINE + "Waffe(n): " + Colors.TURKISH + senderData.getAlertWeapons());
			p.sendMessage(Colors.GREY + Colors.UNDERLINE + "Seine Position ist:");
			p.sendMessage("X  " + (int) sender.getLocation().getX());
			p.sendMessage("Y  " + (int) sender.getLocation().getY());
			p.sendMessage("Z  " + (int) sender.getLocation().getZ());
			p.sendMessage(Colors.DARK_GREY + "--------------------------------");
			
			int x = (int) (sender.getLocation().getX() - p.getLocation().getX());
			int z = (int) (sender.getLocation().getZ() - p.getLocation().getZ());
			
			if(x <= 100 && z <= 100 && x >= -100 && z >= -100){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 100 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 250 && z <= 250 && x >= -250 && z >= -250){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 250 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 500 && z <= 500 && x >= -500 && z >= -500){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 500 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 750 && z <= 750 && x >= -750 && z >= -750){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 750 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}else if(x <= 1000 && z <= 1000 && x >= -1000 && z >= -1000){
				p.sendMessage(Colors.TURKISH + "Du bist weniger als 1000 Blöcke entfernt");
				p.sendMessage(Colors.TURKISH + "Die Richtung ist " + Colors.BLUE + p.getCompassTarget().getDirection());
			}
			
			wantedList.add(senderData.getAlertPlayer());
		}
		
		senderData.setAlertMsg("");
		senderData.setAlertPlayer(null);
		senderData.setAlertWeapons("");
	}
	
	public void equipment(Player p){

		int grade = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getGrade();
		
		if(grade == 1){
			
			ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
			helmetMeta.setColor(Color.AQUA);
			helmetMeta.setDisplayName(Colors.TURKISH + "Anwärter Uniform (Helm)");
			helmet.setItemMeta(helmetMeta);
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
			chestplateMeta.setColor(Color.AQUA);
			chestplateMeta.setDisplayName(Colors.TURKISH + "Anwärter Uniform (Jacke)");
			chestplate.setItemMeta(chestplateMeta);
					
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
			leggingsMeta.setColor(Color.AQUA);
			leggingsMeta.setDisplayName(Colors.TURKISH + "Anwärter Uniform (Hose)");
			leggings.setItemMeta(leggingsMeta);
				
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
			bootsMeta.setColor(Color.AQUA);
			bootsMeta.setDisplayName(Colors.TURKISH + "Anwärter Uniform (Stiefel)");
			boots.setItemMeta(bootsMeta);
			
			p.getInventory().setHelmet(helmet);
			p.getInventory().setChestplate(chestplate);
			p.getInventory().setLeggings(leggings);
			p.getInventory().setBoots(boots);
				
		}else if(grade == 2){
				
			ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
			helmetMeta.setColor(Color.BLUE);
			helmetMeta.setDisplayName(Colors.DARK_TURKISH + "Streifenpolizist Uniform (Helm)");
			helmet.setItemMeta(helmetMeta);
				
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
			chestplateMeta.setColor(Color.BLUE);
			chestplateMeta.setDisplayName(Colors.DARK_TURKISH + "Streifenpolizist Uniform (Jacke)");
			chestplate.setItemMeta(chestplateMeta);
			
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
			leggingsMeta.setColor(Color.BLUE);
			leggingsMeta.setDisplayName(Colors.DARK_TURKISH + "Streifenpolizist Uniform (Hose)");
			leggings.setItemMeta(leggingsMeta);
			
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
			bootsMeta.setColor(Color.BLUE);
			bootsMeta.setDisplayName(Colors.DARK_TURKISH + "Streifenpolizist Uniform (Stiefel)");
			boots.setItemMeta(bootsMeta);
				
			p.getInventory().setHelmet(helmet);
			p.getInventory().setChestplate(chestplate);
			p.getInventory().setLeggings(leggings);
			p.getInventory().setBoots(boots);
			
		}else if(grade == 3){
				
			ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
			helmetMeta.setColor(Color.BLACK);
			helmetMeta.setDisplayName(Colors.DARK_GREY + "SEK Uniform (Helm)");
			helmet.setItemMeta(helmetMeta);
				
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
			chestplateMeta.setColor(Color.BLACK);
			chestplateMeta.setDisplayName(Colors.DARK_GREY + "SEK Uniform (Jacke)");
			chestplate.setItemMeta(chestplateMeta);
				
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
			leggingsMeta.setColor(Color.BLACK);
			leggingsMeta.setDisplayName(Colors.DARK_GREY + "SEK Uniform (Hose)");
			leggings.setItemMeta(leggingsMeta);
				
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
			bootsMeta.setColor(Color.BLACK);
			bootsMeta.setDisplayName(Colors.DARK_GREY + "SEK Uniform (Stiefel)");
			boots.setItemMeta(bootsMeta);
				
			p.getInventory().setHelmet(helmet);
			p.getInventory().setChestplate(chestplate);
			p.getInventory().setLeggings(leggings);
			p.getInventory().setBoots(boots);
			
		}else if(grade == 4){
				
			ItemStack helmet = new ItemStack(Material.LEATHER_HELMET);
			LeatherArmorMeta helmetMeta = (LeatherArmorMeta) helmet.getItemMeta();
			helmetMeta.setColor(Color.NAVY);
			helmetMeta.setDisplayName(Colors.DARK_BLUE + "Bundespolizist Uniform (Helm)");
			helmet.setItemMeta(helmetMeta);
			
			ItemStack chestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
			LeatherArmorMeta chestplateMeta = (LeatherArmorMeta) chestplate.getItemMeta();
			chestplateMeta.setColor(Color.NAVY);
			chestplateMeta.setDisplayName(Colors.DARK_BLUE + "Bundespolizist Uniform (Jacke)");
			chestplate.setItemMeta(chestplateMeta);
				
			ItemStack leggings = new ItemStack(Material.LEATHER_LEGGINGS);
			LeatherArmorMeta leggingsMeta = (LeatherArmorMeta) leggings.getItemMeta();
			leggingsMeta.setColor(Color.NAVY);
			leggingsMeta.setDisplayName(Colors.DARK_BLUE + "Bundespolizist Uniform (Hose)");
			leggings.setItemMeta(leggingsMeta);
				
			ItemStack boots = new ItemStack(Material.LEATHER_BOOTS);
			LeatherArmorMeta bootsMeta = (LeatherArmorMeta) boots.getItemMeta();
			bootsMeta.setColor(Color.NAVY);
			bootsMeta.setDisplayName(Colors.DARK_BLUE + "Bundespolizist Uniform (Stiefel)");
			boots.setItemMeta(bootsMeta);
				
			p.getInventory().setHelmet(helmet);
			p.getInventory().setChestplate(chestplate);
			p.getInventory().setLeggings(leggings);
			p.getInventory().setBoots(boots);
			
		}else if(grade == 5){
				
			ItemStack helmet = new ItemStack(Material.DIAMOND_HELMET);
			ItemMeta helmetMeta = helmet.getItemMeta();
			helmetMeta.setDisplayName(Colors.DARK_BLUE + "Polizeipräsident Uniform (Helm)");
			helmet.setItemMeta(helmetMeta);
				
			ItemStack chestplate = new ItemStack(Material.DIAMOND_CHESTPLATE);
			ItemMeta chestplateMeta = chestplate.getItemMeta();
			chestplateMeta.setDisplayName(Colors.DARK_BLUE + "Polizeipräsident Uniform (Jacke)");
			chestplate.setItemMeta(chestplateMeta);
			
			ItemStack leggings = new ItemStack(Material.DIAMOND_LEGGINGS);
			ItemMeta leggingsMeta = leggings.getItemMeta();
			leggingsMeta.setDisplayName(Colors.DARK_BLUE + "Polizeipräsident Uniform (Hose)");
			leggings.setItemMeta(leggingsMeta);
				
			ItemStack boots = new ItemStack(Material.DIAMOND_BOOTS);
			ItemMeta bootsMeta = boots.getItemMeta();
			bootsMeta.setDisplayName(Colors.DARK_BLUE + "Polizeipräsident Uniform (Stiefel)");
			boots.setItemMeta(bootsMeta);
			
			p.getInventory().setHelmet(helmet);
			p.getInventory().setChestplate(chestplate);
			p.getInventory().setLeggings(leggings);
			p.getInventory().setBoots(boots);
		}
			
			ItemStack playerMenu = new ItemStack(Material.BOOK);
			ItemMeta itemMeta = playerMenu.getItemMeta();
			itemMeta.setDisplayName(Colors.YELLOW + "Spielermenü");
			playerMenu.setItemMeta(itemMeta);
			
			ItemStack policeMenu = new ItemStack(Material.BOOK);
			ItemMeta policeMeta = policeMenu.getItemMeta();
			policeMeta.setDisplayName(Colors.TURKISH + "Polizeimenü");
			policeMenu.setItemMeta(policeMeta);
			
			ItemStack tazer = new ItemStack(Material.DIAMOND_HOE);
			ItemMeta tazerMeta = tazer.getItemMeta();
			tazerMeta.setDisplayName(Colors.DARK_GREY + "[P] " + Colors.RESET + "Elektroschocker");
			tazer.setItemMeta(tazerMeta);
			
			ItemStack tazermuni = new ItemStack(Material.BLAZE_ROD);
			tazermuni.setAmount(24);
			ItemMeta tazermuniMeta = tazermuni.getItemMeta();
			tazermuniMeta.setDisplayName(Colors.GREY + "Elektroschockerbatterie");
			tazermuni.setItemMeta(tazermuniMeta);
			
		p.getInventory().addItem(policeMenu);
		p.getInventory().addItem(playerMenu);
		p.getInventory().addItem(tazer);
		p.getInventory().addItem(tazermuni);
	}
	
	public class WantedList {
		public ArrayList<String> wantedList;
		
		public WantedList(){
			
			wantedList = new ArrayList<String>();
			
		}
		
		public void add(Player p){
			wantedList.add(p.getName());
		}
		
		public void remove(Player p){
			for(String s : wantedList){
				if(s.equals(p.getName())){
					wantedList.remove(s);
				}
			}
		}
		
		public boolean isOnList(Player p){
			for(String s : wantedList){
				if(s.equals(p.getName())){
					return true;
				}
			}
			
			return false;
		}
		
		public List<String> getLore(){
			
			List<String> list = new ArrayList<String>();
			
			for(String s : wantedList){
				list.add(s);
			}

			return list;
		}
	}
}
