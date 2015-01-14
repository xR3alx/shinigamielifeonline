package com.shinigami.listener;

import org.bukkit.GameMode;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Sign;

import com.shinigami.housing.HousingManager.House;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;

public class BlockListener implements Listener{

	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockPlace(BlockPlaceEvent event) {
		Player p = event.getPlayer();
		Block b = event.getBlockPlaced();
		ItemStack i = event.getItemInHand();
		
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(session != null){
			if(b.getType() == Material.WEB){
				if(session.getSide().equals("cop")){
					ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), Material.AIR, 300, (short) 0);
				}else{
					if(i.getAmount() == 1){
						p.getInventory().addItem(i);
					}else{
						i.setAmount(i.getAmount()+1);
					}
					event.setBuild(false);
					event.setCancelled(true);
				}
			}else if(session.getHouses().size() != 0){
				for(House house : session.getHouses().values()){
					if(house.area.intersectsRegion(p.getLocation())){
						if(b.getType() != Material.CHEST && b.getType() != Material.ENDER_CHEST && b.getType() != Material.FURNACE && b.getType() != Material.WORKBENCH && b.getType() != Material.JACK_O_LANTERN
								 && b.getType() != Material.CAULDRON_ITEM && b.getType() != Material.WOOD_STAIRS && b.getType() != Material.FENCE && b.getType() != Material.WOOD_STEP && b.getType() != Material.FLOWER_POT_ITEM
								 && b.getType() != Material.LEAVES && b.getType() != Material.YELLOW_FLOWER && b.getType() != Material.RED_ROSE && b.getType() != Material.GLOWSTONE && b.getType() != Material.CARPET){
							event.setBuild(false);
							event.setCancelled(true);
						}
					}
				}
			}
			else if(p.getGameMode() != GameMode.CREATIVE){
				p.getInventory().addItem(i);
				event.setBuild(false);
				event.setCancelled(true);
			}
		}else{
			if(i.getAmount() == 1){
				p.getInventory().addItem(i);
			}else{
				i.setAmount(i.getAmount()+1);
			}
			event.setBuild(false);
			event.setCancelled(true);
		}
	}
	
	@EventHandler(priority = EventPriority.NORMAL)
	public void onBlockBreak(BlockBreakEvent event){
		Player p = event.getPlayer();
		Block b = event.getBlock();
		
		event.getBlock().getDrops().clear();
		
		Sign s = new Sign();
		
		Session session = ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "");
		
		if(session != null){
			if(session.getHouses().size() != 0){
				for(House house : session.getHouses().values()){
					if(house.area.intersectsRegion(p.getLocation())){
						if(b.getType() != Material.CHEST && b.getType() != Material.ENDER_CHEST && b.getType() != Material.FURNACE && b.getType() != Material.WORKBENCH && b.getType() != Material.JACK_O_LANTERN
								 && b.getType() != Material.CAULDRON_ITEM && b.getType() != Material.WOOD_STAIRS && b.getType() != Material.FENCE && b.getType() != Material.WOOD_STEP && b.getType() != Material.FLOWER_POT_ITEM
								 && b.getType() != Material.LEAVES && b.getType() != Material.YELLOW_FLOWER && b.getType() != Material.RED_ROSE && b.getType() != Material.GLOWSTONE && b.getType() != Material.CARPET){
							event.setCancelled(true);
						}
					}
				}
				
			}else if(p.getGameMode() != GameMode.CREATIVE){
				if(p.getItemInHand().getType() == Material.IRON_PICKAXE){
					if(b.getType() == Material.IRON_ORE){
						
						ItemStack item = new ItemStack(Material.IRON_ORE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(Colors.GREY + "unverarbeitetes Eisen");
						item.setItemMeta(meta);
						
						p.getInventory().addItem(item);
						
						ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
						b.setType(Material.AIR);
						event.setCancelled(true);
					}else if(b.getType() == Material.COAL_ORE){
						
						ItemStack item = new ItemStack(Material.COAL_ORE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(Colors.GREY + "unverarbeitete Kohle");
						item.setItemMeta(meta);
						
						p.getInventory().addItem(item);
	
						ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
						b.setType(Material.AIR);
						event.setCancelled(true);
					}else if(b.getType() == Material.REDSTONE_ORE || b.getType() == Material.GLOWING_REDSTONE_ORE){
						
						ItemStack item = new ItemStack(Material.REDSTONE_ORE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(Colors.GREY + "unverarbeiteter Rotstein");
						item.setItemMeta(meta);
						
						p.getInventory().addItem(item);
	
						ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
						b.setType(Material.AIR);
						event.setCancelled(true);
					}else if(b.getType() == Material.DIAMOND_ORE){
						
						ItemStack item = new ItemStack(Material.DIAMOND_ORE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(Colors.GREY + "unverarbeiteter Diamant");
						item.setItemMeta(meta);
						
						p.getInventory().addItem(item);
	
						ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
						b.setType(Material.AIR);
						event.setCancelled(true);
					}else if(b.getType() == Material.EMERALD_ORE){
						
						ItemStack item = new ItemStack(Material.EMERALD_ORE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(Colors.GREY + "unverarbeiteter Smaragd");
						item.setItemMeta(meta);
						
						p.getInventory().addItem(item);
	
						ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
						b.setType(Material.AIR);
						event.setCancelled(true);
					}else if(b.getType() == Material.GOLD_ORE){
						
						ItemStack item = new ItemStack(Material.GOLD_ORE);
						ItemMeta meta = item.getItemMeta();
						meta.setDisplayName(Colors.GREY + "unverarbeitetes Gold");
						item.setItemMeta(meta);
						
						p.getInventory().addItem(item);
	
						ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
						b.setType(Material.AIR);
						event.setCancelled(true);
					}else if(b.getType() == Material.QUARTZ_BLOCK){
						
						if(ShinigamiLife.getRegionsManager().getRegion("saltfield") != null){
							if(ShinigamiLife.getRegionsManager().getRegion("saltfield").intersectsRegion(b.getLocation())){
								ItemStack item = new ItemStack(Material.SNOW_BLOCK);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(Colors.GREY + "unverarbeitetes Salz");
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
			
								ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
								b.setType(Material.AIR);
							}
						}
						
						event.setCancelled(true);
					}else{
						event.setCancelled(true);
					}
				}else if(p.getItemInHand().getType() == Material.IRON_AXE){
					if(b.getType() == Material.LOG){
						
						if(ShinigamiLife.getRegionsManager().getRegion("forest") != null){
							if(ShinigamiLife.getRegionsManager().getRegion("forest").intersectsRegion(b.getLocation())){
								ItemStack item = new ItemStack(Material.LOG);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(Colors.GREY + "unverarbeitetes Holz");
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
								
								ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
								b.setType(Material.AIR);
							}else{
								event.setCancelled(true);
							}
						}else{
							event.setCancelled(true);
						}
						
						event.setCancelled(true);
					}else if(b.getType() == Material.LOG_2){
						
						if(ShinigamiLife.getRegionsManager().getRegion("forest") != null){
							if(ShinigamiLife.getRegionsManager().getRegion("forest").intersectsRegion(b.getLocation())){
								ItemStack item = new ItemStack(Material.LOG_2);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(Colors.GREY + "unverarbeitetes Holz");
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
								
								ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
								b.setType(Material.AIR);
							}else{
								event.setCancelled(true);
							}
						}else{
							event.setCancelled(true);
						}
						
						event.setCancelled(true);
					}else{
						event.setCancelled(true);
					}
				}else if(p.getItemInHand().getType() == Material.IRON_HOE){
					if(b.getType() == Material.DOUBLE_PLANT){
						
						if(ShinigamiLife.getRegionsManager().getRegion("marihuanafield") != null){
							if(ShinigamiLife.getRegionsManager().getRegion("marihuanafield").intersectsRegion(b.getLocation())){
								ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 3);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(Colors.GREY + "unverarbeitetes Marihuana");
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
								ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
								b.setType(Material.AIR);
								return;
							}
						}
						
						if(ShinigamiLife.getRegionsManager().getRegion("kokainfield") != null){
							if(ShinigamiLife.getRegionsManager().getRegion("kokainfield").intersectsRegion(b.getLocation())){
								ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 2);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(Colors.GREY + "unverarbeitetes Kokain");
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
								ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
								b.setType(Material.AIR);
								return;
							}
						}
						
						if(ShinigamiLife.getRegionsManager().getRegion("heroinfield") != null){
							if(ShinigamiLife.getRegionsManager().getRegion("heroinfield").intersectsRegion(b.getLocation())){
								ItemStack item = new ItemStack(Material.DOUBLE_PLANT, 1, (short) 1);
								ItemMeta meta = item.getItemMeta();
								meta.setDisplayName(Colors.GREY + "unverarbeitetes Heroin");
								item.setItemMeta(meta);
								
								p.getInventory().addItem(item);
								ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
								b.setType(Material.AIR);
								return;
							}
						}
						
						event.setCancelled(true);
					}else if(b.getType() == Material.LEAVES || b.getType() == Material.LEAVES_2){
						String[] applefields = {"applefield", "applefield_2", "applefield_3"};
						
						for(String string : applefields){
							if(ShinigamiLife.getRegionsManager().getRegion(string) != null){
								if(ShinigamiLife.getRegionsManager().getRegion(string).intersectsRegion(b.getLocation())){
									ItemStack item = new ItemStack(Material.APPLE);
									ItemMeta meta = item.getItemMeta();
									meta.setDisplayName(Colors.WHITE + "Apfel");
									item.setItemMeta(meta);
										
									p.getInventory().addItem(item);
									ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
									b.setType(Material.AIR);
									break;
								}
							}
						}
						event.setCancelled(true);
					}else if(b.getType() == Material.MELON_BLOCK){
						String[] melonefields = {"melonefield", "melonefield_2", "melonefield_3"};
						
						for(String string : melonefields){
							if(ShinigamiLife.getRegionsManager().getRegion(string) != null){
								if(ShinigamiLife.getRegionsManager().getRegion(string).intersectsRegion(b.getLocation())){
									ItemStack item = new ItemStack(Material.MELON);
									ItemMeta meta = item.getItemMeta();
									meta.setDisplayName(Colors.WHITE + "Melone");
									item.setItemMeta(meta);
										
									p.getInventory().addItem(item);
									ShinigamiLife.getBlockRespawner().addBlock(b.getWorld(), b.getLocation(), b.getType(), 240, b.getData());
									b.setType(Material.AIR);
									event.setCancelled(true);
									break;
								}
							}
						}
					}
					p.getItemInHand().setDurability((short) (p.getItemInHand().getDurability()-1));
				}
				event.setCancelled(true);
				event.setExpToDrop(0);
			}
		}
	}
	
}
