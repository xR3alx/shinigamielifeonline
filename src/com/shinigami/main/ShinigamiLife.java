package com.shinigami.main;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.bukkit.Bukkit;
import org.bukkit.Chunk;
import org.bukkit.World;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.util.Vector;

import com.shinigami.blockrespawner.BlockRespawner;
import com.shinigami.configs.Configuration;
import com.shinigami.ecosys.EconomySystem;
import com.shinigami.groups.GroupManager;
import com.shinigami.housing.HousingManager;
import com.shinigami.items.WeaponManager;
import com.shinigami.listener.BlockListener;
import com.shinigami.listener.EntityListener;
import com.shinigami.listener.InventoryListener;
import com.shinigami.listener.PlayerListener;
import com.shinigami.listener.UtilsListener;
import com.shinigami.listener.VehicleListener;
import com.shinigami.locations.LocationsManager;
import com.shinigami.npcs.NPCManager;
import com.shinigami.menu.MenuManager;
import com.shinigami.police.PoliceManager;
import com.shinigami.police.TicketManager;
import com.shinigami.regions.RegionsManager;
import com.shinigami.sessions.SessionManager;
import com.shinigami.utils.DebugMessages;

public class ShinigamiLife {

	private static JavaPlugin javaPlugin;
	
	private static Configuration configuration;
	private static SessionManager sessionManager;
	private static BlockRespawner blockRespawner;
	private static EconomySystem ecoSystem;
	private static GroupManager groupManager;
	private static LocationsManager locationsManager;
	private static WeaponManager weaponManager;
	private static NPCManager npcManager;
	private static RegionsManager regionsManager;
	private static PoliceManager policeManager;
	private static TicketManager ticketManager;
	private static MenuManager menuManager;
	private static HousingManager houseManager;
	
	private EntityListener entityListener;
	private VehicleListener vehicleListener;
	private BlockListener blockListener;
	private PlayerListener playerListener;
	private InventoryListener inventoryListener;
	private UtilsListener utilsListener;
	
	private static boolean loaded;
	
	public static final String LOGTAG = "[SHINIGAMILIFEONLINE]  -  ";
	
	public ShinigamiLife(JavaPlugin arg) {
		javaPlugin = arg;
		
		DebugMessages.setDebug(true);
	}
	
	public static void log(String s){
		System.out.println(s);
	}
	
	public void load(){
		while(!loaded){
			
			configuration = new Configuration();
			log(LOGTAG + "Configuration loaded!");
			
			sessionManager = new SessionManager();
			log(LOGTAG + "SessionManager loaded!");
				
			blockRespawner = new BlockRespawner();
			log(LOGTAG + "BlockRespawner loaded!");
				
			ecoSystem = new EconomySystem();
			log(LOGTAG + "EconomySystem loaded!");
				
			groupManager = new GroupManager();
			log(LOGTAG + "GroupManager loaded!");
				
			locationsManager = new LocationsManager();
			log(LOGTAG + "LocationsManager loaded!");
				
			weaponManager = new WeaponManager();
			log(LOGTAG + "WeaponManager loaded!");
				
			npcManager = new NPCManager();
			log(LOGTAG + "NPCManager loaded!");
				
			regionsManager = new RegionsManager();
			log(LOGTAG + "RegionsManager loaded!");
				
			policeManager = new PoliceManager();
			log(LOGTAG + "PoliceManager loaded!");
				
			ticketManager = new TicketManager();
			log(LOGTAG + "TicketManager loaded!");
				
			houseManager = new HousingManager();
			log(LOGTAG + "HousingManager loaded!");
				
			menuManager = new MenuManager();
			log(LOGTAG + "MenuManager loaded!");
				
			vehicleListener = new VehicleListener();
			blockListener = new BlockListener();
			playerListener = new PlayerListener();
			inventoryListener = new InventoryListener();
			entityListener = new EntityListener();
			utilsListener = new UtilsListener();
				
			javaPlugin.getServer().getPluginManager().registerEvents(vehicleListener, javaPlugin);
			javaPlugin.getServer().getPluginManager().registerEvents(playerListener, javaPlugin);
			javaPlugin.getServer().getPluginManager().registerEvents(blockListener, javaPlugin);
			javaPlugin.getServer().getPluginManager().registerEvents(inventoryListener, javaPlugin);
			javaPlugin.getServer().getPluginManager().registerEvents(entityListener, javaPlugin);
			javaPlugin.getServer().getPluginManager().registerEvents(utilsListener, javaPlugin);
			
			log(LOGTAG + "Listener loaded!");
			
			loaded = true;
		}
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
			
			@Override
			public void run() {
				updateSlow();
			}
		}, 0L, 20L);
		
		Bukkit.getScheduler().scheduleSyncRepeatingTask(javaPlugin, new Runnable() {
			
			@Override
			public void run() {
				updateFast();
			}
		}, 0L, 1L);
	}
	
	private void updateFast(){
		sessionManager.updateFast(javaPlugin);
	}
	
	@SuppressWarnings("static-access")
	private void updateSlow(){
		sessionManager.updateSlow(javaPlugin);
		blockRespawner.update();
		ecoSystem.update(getConfiguration().getPriceConfig());
		npcManager.update();
				
		World w = Bukkit.getWorld(getConfiguration().getConfig().getEntry("rp_world"));
		if(w != null){
			if(w.getEntities().size() != 0){
				for(Entity e : w.getEntities()){
					if(e != null){
						if(e.getType() == EntityType.BOAT){
							Boat b = (Boat) e;
								
							if(b.getPassenger() == null){
								b.setVelocity(new Vector(0, 0, 0));
							}
						}
					}
				}
			}
		}
	}
	
	public void unload(){
		while(loaded){
			sessionManager.saveAll();
			log(LOGTAG + "SessionManager saved!");
			
			blockRespawner.replacAll();
			log(LOGTAG + "BlockRespawner saved!");
				
			ecoSystem.saveAll();
			log(LOGTAG + "EconomySystem saved!");
				
			locationsManager.saveAll();
			log(LOGTAG + "LocationsManager saved!");
				
			npcManager.saveAll();
			log(LOGTAG + "NPCManager saved!");
				
			regionsManager.saveAll();
			log(LOGTAG + "RegionsManager saved!");
			
			removeAllEntites(Bukkit.getWorld(configuration.getConfig().getEntry("rp_world")));
			log(LOGTAG + "All Entities removed!");
			
			loaded = false;
		}
	}
	
	public int removeAllEntites(World world) {
		int countEntitiesRemoved = 0;
        final Pattern regionPattern = Pattern.compile("r\\.([0-9-]+)\\.([0-9-]+)\\.mca");
 
        File worldDir = new File(Bukkit.getWorldContainer(), world.getName());
        File regionDir = new File(worldDir, "region");
 
        File[] regionFiles = regionDir.listFiles(new FilenameFilter() {
            @Override
            public boolean accept(File dir, String name) {
                return regionPattern.matcher(name).matches();
            }
        });
 
        for (File f : regionFiles) {
            // extract coordinates from filename
            Matcher matcher = regionPattern.matcher(f.getName());
            if (!matcher.matches()) {
                continue;
            }
 
            int mcaX = Integer.parseInt(matcher.group(1));
            int mcaZ = Integer.parseInt(matcher.group(2));
 
            for (int cx = 0; cx < 32; cx++) {
                for (int cz = 0; cz < 32; cz++) {
                    // local chunk coordinates need to be transformed into global ones
                    boolean didLoad = world.loadChunk((mcaX << 5) + cx, (mcaZ << 5) + cz, false);
                    if(didLoad){
	                    for(Chunk chunk : world.getLoadedChunks()){
	    					for(int i = 0; i < chunk.getEntities().length; i++){
	    						chunk.getEntities()[i].remove();
	    						countEntitiesRemoved++;
	    					}
	    					world.unloadChunk(chunk);
	    				}
                    }
                }
            }
        }
        
        return countEntitiesRemoved;
    }
	
	public static Configuration getConfiguration() {
		return configuration;
	}

	public static void setConfiguration(Configuration arg) {
		configuration = arg;
	}

	public static SessionManager getSessionManager() {
		return sessionManager;
	}

	public static void setSessionManager(SessionManager arg) {
		sessionManager = arg;
	}

	public static LocationsManager getLocationsManager() {
		return locationsManager;
	}

	public static void setLocationsManager(LocationsManager arg) {
		locationsManager = arg;
	}

	public static PoliceManager getPoliceManager() {
		return policeManager;
	}

	public static void setPoliceManager(PoliceManager arg) {
		policeManager = arg;
	}

	public static JavaPlugin getJavaPlugin() {
		return javaPlugin;
	}

	public static void setJavaPlugin(JavaPlugin arg) {
		javaPlugin = arg;
	}

	public static BlockRespawner getBlockRespawner() {
		return blockRespawner;
	}

	public static void setBlockRespawner(BlockRespawner arg) {
		blockRespawner = arg;
	}

	public static EconomySystem getEcoSystem() {
		return ecoSystem;
	}

	public static void setEcoSystem(EconomySystem arg) {
		ecoSystem = arg;
	}

	public static GroupManager getGroupManager() {
		return groupManager;
	}

	public static void setGroupManager(GroupManager arg) {
		groupManager = arg;
	}

	public static WeaponManager getWeaponManager() {
		return weaponManager;
	}

	public static void setWeaponManager(WeaponManager arg) {
		weaponManager = arg;
	}

	public static NPCManager getNpcManager() {
		return npcManager;
	}

	public static void setNpcManager(NPCManager arg) {
		npcManager = arg;
	}

	public static RegionsManager getRegionsManager() {
		return regionsManager;
	}

	public static void setRegionsManager(RegionsManager arg) {
		regionsManager = arg;
	}

	public static TicketManager getTicketManager() {
		return ticketManager;
	}

	public static void setTicketManager(TicketManager arg) {
		ticketManager = arg;
	}


	public static MenuManager getMenuManager() {
		return menuManager;
	}

	public static void setMenuManager(MenuManager arg) {
		menuManager = arg;
	}

	public static HousingManager getHouseManager() {
		return houseManager;
	}

	public static void setHouseManager(HousingManager arg) {
		houseManager = arg;
	}

	public static boolean isLoaded() {
		return loaded;
	}
	
}
