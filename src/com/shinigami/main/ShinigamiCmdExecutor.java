package com.shinigami.main;

import java.awt.Color;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class ShinigamiCmdExecutor implements CommandExecutor {

	@SuppressWarnings({ "deprecation", "static-access" })
	@Override
	public boolean onCommand(CommandSender sender, Command command,
			String label, String[] args) {

		if(sender instanceof Player){
			final Player p = (Player) sender;
			Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
			
			if(command.getName().equals("mclife")){
				if(args.length == 0){

					p.sendMessage(Colors.DARK_GREY + "--- " + Colors.RESET + Colors.DARK_YELLOW + "Minecraft Life " + Colors.RESET + Colors.DARK_GREY + "---");
					p.sendMessage(Colors.DARK_GREY + "------ " + Colors.RESET + Colors.WHITE + "Commands " + Colors.RESET + Colors.DARK_GREY + "------");
					p.sendMessage(Colors.WHITE + "/mclife housing create [housename]");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					p.sendMessage(Colors.WHITE + "/mclife money give [playername] [amount]");
					p.sendMessage(Colors.WHITE + "/mclife money set [playername] [amount]");
					p.sendMessage(Colors.WHITE + "/mclife money remove [playername] [amount]");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					p.sendMessage(Colors.WHITE + "/mclife cops add [playername] [grade]");
					p.sendMessage(Colors.WHITE + "/mclife cops remove [playername]");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					p.sendMessage(Colors.WHITE + "/mclife npcs create [name] [menu]");
					p.sendMessage(Colors.WHITE + "/mclife menulist");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					p.sendMessage(Colors.WHITE + "/mclife regions create [name]");
					p.sendMessage(Colors.WHITE + "/mclife regions loc1");
					p.sendMessage(Colors.WHITE + "/mclife regions loc2");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					p.sendMessage(Colors.WHITE + "/mclife loc create [name]");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					p.sendMessage(Colors.WHITE + "/load");
					p.sendMessage(Colors.WHITE + "/unload");
					p.sendMessage(Colors.WHITE + "/adminmsg [player or @all] [msg]");
					p.sendMessage(Colors.WHITE + "/countentities");
					p.sendMessage(Colors.WHITE + "/killallentities");
					p.sendMessage(Colors.DARK_GREY + "------------------------------");
					
					return true;
				}if(args[0].equals("menulist")){
					if(p.hasPermission("mclife.menu")){
						if(args.length == 1){
							
							p.sendMessage(Colors.DARK_GREY + "----- " + Colors.DARK_YELLOW + "Menus " + Colors.DARK_GREY + "-----");
							String menuslist = "";
							for(String s : ShinigamiLife.getMenuManager().getMenus().keySet()){
								menuslist = menuslist + Colors.BLACK + "  ;  " + Colors.YELLOW + s;
							}
							p.sendMessage(menuslist);
							
							return true;
						}else{
							return false;
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}else if(args[0].equals("housing")){
					if(p.getGameMode() == GameMode.CREATIVE){
						if(args[1].equals("create")){
							if(args[2] != null){
								if(session.getCmdLoc1() != null && session.getCmdLoc2() != null){
									ShinigamiLife.getHouseManager().createHouse(args[2], p, session);
									p.sendMessage(Colors.GREEN + "Haus erfolgreich erstellt!");
									return true;
								}else{
									p.sendMessage(Colors.RED + "Es ist ein Fehler aufgetreten. Eine der beiden Positionen sind fehlerhaft!");
									return false;
								}
							}
							p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
							p.sendMessage(Colors.RED + "/mclife housing create [housename]");
							return false;
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}else if(args[0].equals("money")){
					if(p.getGameMode() == GameMode.CREATIVE){
						if(args[1].equals("give")){
							if(args[2] != null && args[3] != null){
								
								int money = Integer.parseInt(args[3]);
								Player t = Bukkit.getPlayer(args[2]);
								Session sessionT = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
								
								p.sendMessage(Colors.GREEN + "Du hast " + Colors.DARK_GREEN + t.getName() + " " + money + Colors.GREEN + " gegeben.");
								t.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat dir " + Colors.DARK_GREEN + money + Colors.GREEN + " gegeben.");
								
								sessionT.setMoneyInPocket(sessionT
										.getMoneyInPocket() + money);
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife money give [playername] [amount]");
								return false;
							}
						}else if(args[1].equals("set")){
							if(args[2] != null && args[3] != null){
								
								int money = Integer.parseInt(args[3]);
								Player t = Bukkit.getPlayer(args[2]);
								Session sessionT = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
								
								p.sendMessage(Colors.GREEN + "Du hast " + Colors.DARK_GREEN + t.getName() + " " + money + Colors.GREEN + " als Geld gesetzt.");
								t.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat dir " + Colors.DARK_GREEN + money + Colors.GREEN + " als Geld gesetzt.");
								
								sessionT.setMoneyInPocket(money);
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife money set [playername] [amount]");
								return false;
							}
						}else if(args[1].equals("remove")){
							if(args[2] != null && args[3] != null){
								
								int money = Integer.parseInt(args[3]);
								Player t = Bukkit.getPlayer(args[2]);
								Session sessionT = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
								
								p.sendMessage(Colors.GREEN + "Du hast " + Colors.DARK_GREEN + t.getName() + " " + money + Colors.GREEN + " genommen.");
								t.sendMessage(Colors.DARK_GREEN + p.getName() + Colors.GREEN + " hat dir " + Colors.DARK_GREEN + money + Colors.GREEN + " genommen.");
								
								sessionT.setMoneyInPocket(sessionT
										.getMoneyInPocket() - money);
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife money remove [playername] [amount]");
								return false;
							}
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}else if(args[0].equals("cops")){
					if(p.getGameMode() == GameMode.CREATIVE){
						if(args[1].equals("add")){
							if(args[2] != null && args[3] != null){
								
								int grade = Integer.parseInt(args[3]);
								Player t = Bukkit.getPlayer(args[2]);
								Session sessionT = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
								
								sessionT.setGrade(grade);
								
								p.sendMessage(Colors.GREEN + "Du hast " + Colors.DARK_GREEN + t.getName() + Colors.GREEN + " als Polizist Grad " + Colors.DARK_GREEN + grade + Colors.GREEN + " hinzugefügt.");
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife cops add [playername] [grade]");
								return false;
							}
						}else if(args[1].equals("remove")){
							if(args[2] != null){
								
								Player t = Bukkit.getPlayer(args[2]);
								Session sessionT = ShinigamiLife.getSessionManager().getSession(t.getUniqueId() + "");
								
								sessionT.setGrade(0);
								
								p.sendMessage(Colors.RED + "Du hast " + Colors.DARK_RED + t.getName() + Colors.RED + " als Polizist entfernt.");
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife cops remove [playername]");
								return false;
							}
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}else if(args[0].equals("npcs")){
					if(p.getGameMode() == GameMode.CREATIVE){
						if(args[1].equals("create")){
							if(args[2] != null && args[3] != null){
								
								ShinigamiLife.getNpcManager().addNPC(args[2].replace("_", " "), args[3], p);
								
								p.sendMessage(Colors.GREEN + "Du hast den NPC " + Colors.DARK_GREEN + args[2] + Colors.GREEN + " mit dem Menu " + Colors.DARK_GREEN + args[3] + Colors.GREEN + " erfolgreich erstellt!");
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife npcs create [name]");
								return false;
							}
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}else if(args[0].equals("regions")){
					if(p.getGameMode() == GameMode.CREATIVE){
						if(args[1].equals("create")){
							if(args[2] != null){
								
								ShinigamiLife.getRegionsManager().createRegion(args[2], p, session);
								
								p.sendMessage(Colors.GREEN + "Du hast die Region " + Colors.DARK_GREEN + args[2] + Colors.GREEN + " erfolgreich erstellt!");
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife regions create [name]");
								return false;
							}
						}else if(args[1].equals("loc1")){
							session.setCmdLoc1(p.getLocation());
							p.sendMessage(Colors.GREEN + "Position 1 gesetzt");
							return true;
						}else if(args[1].equals("loc2")){
							session.setCmdLoc2(p.getLocation());
							p.sendMessage(Colors.GREEN + "Position 2 gesetzt");
							return true;
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}else if(args[0].equals("loc")){
					if(p.getGameMode() == GameMode.CREATIVE){
						if(args[1].equals("create")){
							if(args[2] != null){
								
								ShinigamiLife.getLocationsManager().add(p.getLocation(), args[2]);
								
								p.sendMessage(Colors.GREEN + "Du hast die Position " + Colors.DARK_GREEN + args[2] + Colors.GREEN + " erfolgreich erstellt!");
								
								return true;
							}else{
								p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
								p.sendMessage(Colors.RED + "/mclife loc create [name]");
								return false;
							}
						}
					}else{
						p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
						return false;
					}
				}
			}else if(command.getName().equals("adminmsg")){
				if(p.getGameMode() == GameMode.CREATIVE){
					if(args[0] != null){
						String msg = "";
						for(int i = 0; i < args.length; i++){
							if(i != 0){
								msg = msg + " " + args[i] + " ";
							}
						}
						
						if(args[0].equals("@all")){
							for(Player player : Bukkit.getOnlinePlayers()){
								Inventory inv = Bukkit.createInventory(player, 0, msg);
								player.openInventory(inv);
							}
						}else{
							Player player = Bukkit.getPlayer(args[0]);
							Inventory inv = Bukkit.createInventory(player, 0, msg);
							player.openInventory(inv);
						}
					}else{
						p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
						p.sendMessage(Colors.RED + "/adminmsg [player] [nachricht]");
						return false;
					}
				}else{
					p.sendMessage(Colors.RED + "Du hast keine Berechtigung dazu!");
					return false;
				}
			}else if(command.getName().equals("regchar")){
				if(session.getAccCount() != 3){
					if(args.length != 0){
						String charname = "";
						for (String string : args) {
							charname = charname + " " + string;
						}
						
						p.sendMessage("Create Character with name " + charname + ".");
						p.sendMessage("You're now playing as " + charname + ".");
						
						session.setAccCount(session.getAccCount()+1);
						if(session.getAccCount() == 0){
							session.setAcc1CharName(charname);
							session.setAcc1Profession("civ");
							session.setProfession("civ");
							session.setCharname(charname);
							session.setAccNumLoggedIn(1);
							ShinigamiLife.getSessionManager().loadData(p, 1);
						}else if(session.getAccCount() == 1){
							session.setAcc2CharName(charname);
							session.setAcc2Profession("civ");
							session.setProfession("civ");
							session.setCharname(charname);
							session.setAccNumLoggedIn(2);
							ShinigamiLife.getSessionManager().loadData(p, 2);
						}else if(session.getAccCount() == 2){
							session.setAcc3CharName(charname);
							session.setAcc3Profession("civ");
							session.setProfession("civ");
							session.setCharname(charname);
							session.setAccNumLoggedIn(3);
							ShinigamiLife.getSessionManager().loadData(p, 3);
						}
						
					}else{
						p.sendMessage(Colors.RED + "Du musst das Command so benutzen:");
						p.sendMessage(Colors.RED + "/regchar <character name>");
						return false;
					}
				}else{
					p.sendMessage(Colors.RED + "You already created the maximum amount of characters!");
					return false;
				}
			}
		}
		
		return false;
	}

}
