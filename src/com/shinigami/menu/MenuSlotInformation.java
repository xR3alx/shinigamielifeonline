package com.shinigami.menu;

import org.bukkit.Color;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

public class MenuSlotInformation {

	public ItemStack itemStack;
	public String displayname;
	public String[] loreWithoutPrice, enchantments;
	public Color color;
	public boolean closeInventoryAfterClick, addItemToInventory;
	public int removeMoney, addMoney, slot, durabilityInPercent;
	public String changeMenuTo, removeMoneyPriceConfig, addMoneyPriceConfig;
	
	public MenuSlotInformation(ItemStack itemStack, String displayname,
			String[] loreWithoutPrice, String[] enchantments, Color color,
			boolean closeInventoryAfterClick, boolean addItemToInventory,
			int removeMoney, int addMoney, int slot, int durabilityInPercent,
			String changeMenuTo, String removeMoneyPriceConfig,
			String addMoneyPriceConfig) {
		super();
		this.itemStack = itemStack;
		this.displayname = displayname;
		this.loreWithoutPrice = loreWithoutPrice;
		this.enchantments = enchantments;
		this.color = color;
		this.closeInventoryAfterClick = closeInventoryAfterClick;
		this.addItemToInventory = addItemToInventory;
		this.removeMoney = removeMoney;
		this.addMoney = addMoney;
		this.slot = slot;
		this.durabilityInPercent = durabilityInPercent;
		this.changeMenuTo = changeMenuTo;
		this.removeMoneyPriceConfig = removeMoneyPriceConfig;
		this.addMoneyPriceConfig = addMoneyPriceConfig;
	}
}
