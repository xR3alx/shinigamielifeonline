package com.shinigami.main;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

	private ShinigamiLife shinigamiLife;
	private ShinigamiCmdExecutor shinigamiCmdExecutor;
	
	@Override
	public void onEnable() {
		shinigamiLife = new ShinigamiLife(this);
		shinigamiLife.load();
		
		
		shinigamiCmdExecutor = new ShinigamiCmdExecutor();
		getCommand("mclife").setExecutor(shinigamiCmdExecutor);
		getCommand("adminmsg").setExecutor(shinigamiCmdExecutor);
		getCommand("regchar").setExecutor(shinigamiCmdExecutor);
		
		
		ShinigamiLife.log("--------------------");
		ShinigamiLife.log("SHINIGAMILIFEONLINE loaded!");
		ShinigamiLife.log("--------------------");
	}
	
	@Override
	public void onDisable() {
		shinigamiLife.unload();
		ShinigamiLife.log("--------------------");
		ShinigamiLife.log("SHINIGAMILIFEONLINE unloaded!");
		ShinigamiLife.log("--------------------");
	}
	
}
