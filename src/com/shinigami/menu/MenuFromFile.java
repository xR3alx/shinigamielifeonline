package com.shinigami.menu;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.Bukkit;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.EnchantingInventory;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;

import com.shinigami.configs.Configuration;
import com.shinigami.main.ShinigamiLife;
import com.shinigami.sessions.Session;
import com.shinigami.utils.Colors;
import com.shinigami.utils.Utils;

public class MenuFromFile extends Menu{
	
	private Inventory menu;
	private InventoryType invType;
	private int size;
	private String title;
	private ArrayList<MenuSlotInformation> slotInformations;
	
	public MenuFromFile(InventoryType invType, int size, String title, ArrayList<MenuSlotInformation> slotInformations) {
		this.invType = invType;
		this.size = size;
		this.title = title;
		this.slotInformations = slotInformations;
	}
	
	private void createMenu(){
		for(MenuSlotInformation slotInf : slotInformations){
			ItemStack item = new ItemStack(slotInf.itemStack);
			
			List<String> loreList = new ArrayList<String>();
			if(slotInf.loreWithoutPrice.length != 0){
				for(String s : slotInf.loreWithoutPrice){
					loreList.add(s.replace("°", ""));
				}
			}
			if(!slotInf.addMoneyPriceConfig.equals("")){
				loreList.add(Colors.GREEN + "Sell price: " + Configuration.getPriceConfig().getSellPrice(slotInf.addMoneyPriceConfig));
			}else if(!slotInf.removeMoneyPriceConfig.equals("")){
				loreList.add(Colors.GREEN + "Buy price: " + Configuration.getPriceConfig().getBuyPrice(slotInf.removeMoneyPriceConfig));
			}else if(slotInf.removeMoney != 0){
				loreList.add(Colors.GREEN + "Buy price: " + slotInf.removeMoney);
			}else if(slotInf.addMoney != 0){
				loreList.add(Colors.GREEN + "Sell price: " + slotInf.addMoney);
			}
			
			if(item.getItemMeta() instanceof LeatherArmorMeta){
				if(slotInf.color != null){
					Utils.changeItemMeta(item, slotInf.displayname + Colors.BLACK + "  .", loreList.toArray(new String[] {}), slotInf.color);
				}
			}else{
				Utils.changeItemMeta(item, slotInf.displayname + Colors.BLACK + "  .", loreList.toArray(new String[] {}));
			}
			
			if(slotInf.enchantments.length != 0){
				for(String entchantmentStringRaw : slotInf.enchantments){
					String[] enchantmentStringSplitted = entchantmentStringRaw.split(":");
					if(enchantmentStringSplitted.length == 2){
						String enchantmentString = enchantmentStringSplitted[0];
						int level = Integer.parseInt(enchantmentStringSplitted[1]);
						
						if(Enchantment.getByName(enchantmentString) != null){
							item.addEnchantment(Enchantment.getByName(enchantmentString), level);
						}
					}
				}
			}
			
			item.setDurability((short) (slotInf.durabilityInPercent*item.getDurability()/100));
			
			menu.setItem(slotInf.slot, item);
		}
	}
	
	public void open(Player p, Session session) {
		if(invType != null){
			menu = Bukkit.createInventory(p, invType, title);
		}else{
			menu = Bukkit.createInventory(p, size, title);
		}
		
		if(menu != null){
			createMenu();
			p.openInventory(menu);
		}
	}
	
	public void event(Player p, Inventory inv, ItemStack i, InventoryClickEvent event, Session session){
		if(i != null){
			event.getView().setCursor(null);
			event.setCancelled(true);
			if(i.hasItemMeta()){
				for(MenuSlotInformation slotInf : slotInformations){
					if(event.getSlot() == slotInf.slot){
						int cost = 0;
						
						if(!slotInf.addMoneyPriceConfig.equals("")){
							cost = Configuration.getPriceConfig().getSellPrice(slotInf.addMoneyPriceConfig);
						}else if(!slotInf.removeMoneyPriceConfig.equals("")){
							cost = Configuration.getPriceConfig().getBuyPrice(slotInf.removeMoneyPriceConfig) * -1;
						}else if(slotInf.removeMoney != 0){
							cost = slotInf.removeMoney * -1;
						}else if(slotInf.addMoney != 0){
							cost = slotInf.addMoney;
						}
						
						if(cost < 0){
							if(ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getMoneyInPocket() >= cost){
								ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").setMoneyInPocket(ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getMoneyInPocket()-cost);
							
								if(slotInf.addItemToInventory){
									ItemStack boughtItem = new ItemStack(i);
									ItemMeta itemMeta = i.getItemMeta();
									itemMeta.setDisplayName(itemMeta.getDisplayName().replace(Colors.BLACK + "  .", ""));
									itemMeta.getLore().remove(itemMeta.getLore().size()-1);
									boughtItem.setItemMeta(itemMeta);
									
									p.getInventory().addItem(boughtItem);
									p.sendMessage(Colors.GREY + "Bought 1x " + boughtItem.getItemMeta().getDisplayName() + "for $" + cost + ".");
								}else{
									p.sendMessage(Colors.RED + "You don't have enought money in your pocket to buy that!");
								}
							}else{
								p.sendMessage("");
							}
						}else{
							ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").setMoneyInPocket(ShinigamiLife.getSessionManager().getSession(p.getUniqueId() + "").getMoneyInPocket()+cost);
						}
						
						if(!slotInf.changeMenuTo.equals("")){
							p.closeInventory();
							ShinigamiLife.getMenuManager().checkMenu(p, session, slotInf.changeMenuTo);
						}else if(slotInf.closeInventoryAfterClick){
							p.closeInventory();
						}
					}
				}
			}
		}
	}
}
