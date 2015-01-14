package com.shinigami.utils;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;

import com.shinigami.fanciful.FancyMessage;


public class Messages {

	public static void sendWelcomeMsg(Player p){
		new FancyMessage("Willkommen auf ")
				.color(ChatColor.GREEN)
			.then("Minecraft Life")
				.color(ChatColor.GOLD)
				.style(ChatColor.BOLD)
				.link("http://minecraft-life.de/")
				.tooltip("Klick hier für unsere Homepage.")
			.then(", " + p.getName())
				.color(ChatColor.BLUE)
				.style(ChatColor.ITALIC)
			.send(p);
	}
}
