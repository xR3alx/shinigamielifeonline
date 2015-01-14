package com.shinigami.sessions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.bukkit.Location;
import org.bukkit.block.Chest;
import org.bukkit.entity.Boat;
import org.bukkit.entity.Horse;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import com.shinigami.groups.GroupManager.Group;
import com.shinigami.housing.HousingManager.House;
import com.shinigami.npcs.NPCManager.NPC;
import com.shinigami.sessions.SessionManager.Garage;
import com.shinigami.sessions.SessionManager.Processing;

public class Session {
	private String playerName, channel, uuid, openMenu;
	private int moneyBank, moneyInPocket, moneyBankC, moneyInPocketC;
	private float stamina;
	private HashMap<String, Boolean> licences, licencesC;
	private Garage garageLand, garageWater, garageLandC, garageWaterC;
	private Group group;
	
	private HashMap<String, Boolean> shootables;
	private HashMap<String, Float> shootTimers, shootTimersMax;
	private float alcoholPromile, drugsInBlood;
	private int taxTimer, bleedTimer, smokeTimer, smokedTimes, joinTimer, interactTimer, drinkTimer, eatTimer, alcoholTimer, drugTimer, banksClosedTimer, robTimer;
	private int eatLevel, drinkLevel, moneyPayAmount, robNpcAmount, robMoneySec, robPoliceCallTime, robBanksClosedTime, invSize, invSizeC;
	private boolean smoking, hasTicket, isJoined, stealingHorse, stealingBoat, escorted, interact, tazzed, zoomed, robNpc, banksClosed, isSpawned, isLoggedInAsCop, isFetted, isFettedCop, moving;
	private String side, alertMsg, alertWeapons;
	private Player alertPlayer;
	private NPC interactNpc, clickedNpc;
	private Horse stealHorse;
	
	private Processing processing;
	private ArrayList<Horse> horses;
	private ArrayList<Boat> boats;
	private HashMap<String, House> houses;

	private Inventory playerInv;
	private ItemStack[] armor;
	
	private Location cmdLoc1, cmdLoc2;


	// cop
	private int grade;
	
	private Player escort, searchedPlayer;
	private Horse searchedHorse;
	private Boat searchedBoat;
	private Chest searchedChest;
	
	private boolean frezzePlayer, escortPlayer, searchPlayer, searchHorse, searchChest, searchBoat;
	private int ticketPay, arrestTime;
	private List<String> ticketMsg;
	
	
	
	public Garage getGarageLand(){
		if(isLoggedInAsCop()){
			return getGarageLandC();
		}else{
			return garageLand;
		}
	}
	
	public Garage getGarageWater(){
		if(isLoggedInAsCop()){
			return getGarageWaterC();
		}else{
			return garageWater;
		}
	}
	
	public boolean getLicence(String s){
		if(isLoggedInAsCop()){
			return licencesC.get(s);
		}else{
			return licences.get(s);
		}
	}
	
	public void setLicence(String s, boolean bool){
		if(isLoggedInAsCop()){
			licencesC.put(s, bool);
		}else{
			licences.put(s, bool);
		}
	}
	
	public void setMoneyPocket(int amount){
		if(isLoggedInAsCop()){
			moneyInPocketC=amount;
		}else{
			moneyInPocket=amount;
		}
	}
	
	public int getMoneyPocket(){
		if(isLoggedInAsCop()){
			return moneyInPocketC;
		}else{
			return moneyInPocket;
		}
	}
	
	public void setMoneyInBank(int amount){
		if(isLoggedInAsCop()){
			moneyBankC=amount;
		}else{
			moneyBank=amount;
		}
	}
	
	public int getMoneyInBank(){
		if(isLoggedInAsCop()){
			return moneyBankC;
		}else{
			return moneyBank;
		}
	}
	
	public boolean isFetted() {
		return isFetted;
	}

	public void setFetted(boolean isFetted) {
		this.isFetted = isFetted;
	}

	public boolean isFettedCop() {
		return isFettedCop;
	}

	public void setFettedCop(boolean isFettedCop) {
		this.isFettedCop = isFettedCop;
	}

	public boolean isLoggedInAsCop() {
		return isLoggedInAsCop;
	}

	public void setLoggedInAsCop(boolean isLoggedInAsCop) {
		this.isLoggedInAsCop = isLoggedInAsCop;
	}
	
	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public int getMoneyBankC() {
		return moneyBankC;
	}

	public void setMoneyBankC(int moneyBankC) {
		this.moneyBankC = moneyBankC;
	}

	public int getMoneyInPocketC() {
		return moneyInPocketC;
	}

	public void setMoneyInPocketC(int moneyInPocketC) {
		this.moneyInPocketC = moneyInPocketC;
	}

	public int getInvSizeC() {
		return invSizeC;
	}

	public void setInvSizeC(int invSizeC) {
		this.invSizeC = invSizeC;
	}

	public String getPlayerName() {
		return playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getChannel() {
		return channel;
	}

	public void setChannel(String channel) {
		this.channel = channel;
	}

	public int getMoneyBank() {
		return moneyBank;
	}

	public void setMoneyBank(int moneyBank) {
		this.moneyBank = moneyBank;
	}

	public int getMoneyInPocket() {
		return moneyInPocket;
	}

	public void setMoneyInPocket(int moneyInPocket) {
		this.moneyInPocket = moneyInPocket;
	}

	public float getStamina() {
		return stamina;
	}

	public void setStamina(float stamina) {
		this.stamina = stamina;
	}

	public HashMap<String, Boolean> getLicences() {
		return licences;
	}

	public void setLicences(HashMap<String, Boolean> licences) {
		this.licences = licences;
	}

	public Group getGroup() {
		return group;
	}

	public void setGroup(Group group) {
		this.group = group;
	}

	public float getAlcoholPromile() {
		return alcoholPromile;
	}

	public void setAlcoholPromile(float alcoholPromile) {
		this.alcoholPromile = alcoholPromile;
	}

	public float getDrugsInBlood() {
		return drugsInBlood;
	}

	public void setDrugsInBlood(float drugsInBlood) {
		this.drugsInBlood = drugsInBlood;
	}

	public int getTaxTimer() {
		return taxTimer;
	}

	public void setTaxTimer(int taxTimer) {
		this.taxTimer = taxTimer;
	}

	public int getBleedTimer() {
		return bleedTimer;
	}

	public void setBleedTimer(int bleedTimer) {
		this.bleedTimer = bleedTimer;
	}

	public int getSmokeTimer() {
		return smokeTimer;
	}

	public void setSmokeTimer(int smokeTimer) {
		this.smokeTimer = smokeTimer;
	}

	public int getSmokedTimes() {
		return smokedTimes;
	}

	public void setSmokedTimes(int smokedTimes) {
		this.smokedTimes = smokedTimes;
	}

	public int getJoinTimer() {
		return joinTimer;
	}

	public void setJoinTimer(int joinTimer) {
		this.joinTimer = joinTimer;
	}

	public int getInteractTimer() {
		return interactTimer;
	}

	public void setInteractTimer(int interactTimer) {
		this.interactTimer = interactTimer;
	}

	public int getDrinkTimer() {
		return drinkTimer;
	}

	public void setDrinkTimer(int drinkTimer) {
		this.drinkTimer = drinkTimer;
	}

	public int getEatTimer() {
		return eatTimer;
	}

	public void setEatTimer(int eatTimer) {
		this.eatTimer = eatTimer;
	}

	public int getAlcoholTimer() {
		return alcoholTimer;
	}

	public void setAlcoholTimer(int alcoholTimer) {
		this.alcoholTimer = alcoholTimer;
	}

	public int getDrugTimer() {
		return drugTimer;
	}

	public void setDrugTimer(int drugTimer) {
		this.drugTimer = drugTimer;
	}

	public int getBanksClosedTimer() {
		return banksClosedTimer;
	}

	public void setBanksClosedTimer(int banksClosedTimer) {
		this.banksClosedTimer = banksClosedTimer;
	}

	public int getRobTimer() {
		return robTimer;
	}

	public void setRobTimer(int robTimer) {
		this.robTimer = robTimer;
	}

	public int getEatLevel() {
		return eatLevel;
	}

	public void setEatLevel(int eatLevel) {
		this.eatLevel = eatLevel;
	}

	public int getDrinkLevel() {
		return drinkLevel;
	}

	public void setDrinkLevel(int drinkLevel) {
		this.drinkLevel = drinkLevel;
	}

	public int getMoneyPayAmount() {
		return moneyPayAmount;
	}

	public void setMoneyPayAmount(int moneyPayAmount) {
		this.moneyPayAmount = moneyPayAmount;
	}

	public int getRobNpcAmount() {
		return robNpcAmount;
	}

	public void setRobNpcAmount(int robNpcAmount) {
		this.robNpcAmount = robNpcAmount;
	}

	public int getRobMoneySec() {
		return robMoneySec;
	}

	public void setRobMoneySec(int robMoneySec) {
		this.robMoneySec = robMoneySec;
	}

	public int getRobPoliceCallTime() {
		return robPoliceCallTime;
	}

	public void setRobPoliceCallTime(int robPoliceCallTime) {
		this.robPoliceCallTime = robPoliceCallTime;
	}

	public int getRobBanksClosedTime() {
		return robBanksClosedTime;
	}

	public void setRobBanksClosedTime(int robBanksClosedTime) {
		this.robBanksClosedTime = robBanksClosedTime;
	}

	public int getInvSize() {
		return invSize;
	}

	public void setInvSize(int invSize) {
		this.invSize = invSize;
	}

	public boolean isSmoking() {
		return smoking;
	}

	public void setSmoking(boolean smoking) {
		this.smoking = smoking;
	}

	public boolean isHasTicket() {
		return hasTicket;
	}

	public void setHasTicket(boolean hasTicket) {
		this.hasTicket = hasTicket;
	}

	public boolean isJoined() {
		return isJoined;
	}

	public void setJoined(boolean isJoined) {
		this.isJoined = isJoined;
	}

	public boolean isStealingHorse() {
		return stealingHorse;
	}

	public void setStealingHorse(boolean stealingHorse) {
		this.stealingHorse = stealingHorse;
	}

	public boolean isStealingBoat() {
		return stealingBoat;
	}

	public void setStealingBoat(boolean stealingBoat) {
		this.stealingBoat = stealingBoat;
	}

	public boolean isEscorted() {
		return escorted;
	}

	public void setEscorted(boolean escorted) {
		this.escorted = escorted;
	}

	public boolean isInteract() {
		return interact;
	}

	public void setInteract(boolean interact) {
		this.interact = interact;
	}

	public boolean isTazzed() {
		return tazzed;
	}

	public void setTazzed(boolean tazzed) {
		this.tazzed = tazzed;
	}

	public boolean isZoomed() {
		return zoomed;
	}

	public void setZoomed(boolean zoomed) {
		this.zoomed = zoomed;
	}

	public boolean isRobNpc() {
		return robNpc;
	}

	public void setRobNpc(boolean robNpc) {
		this.robNpc = robNpc;
	}

	public boolean isBanksClosed() {
		return banksClosed;
	}

	public void setBanksClosed(boolean banksClosed) {
		this.banksClosed = banksClosed;
	}

	public boolean isSpawned() {
		return isSpawned;
	}

	public void setSpawned(boolean isSpawned) {
		this.isSpawned = isSpawned;
	}

	public String getSide() {
		return side;
	}

	public void setSide(String side) {
		this.side = side;
	}

	public String getAlertMsg() {
		return alertMsg;
	}

	public void setAlertMsg(String alertMsg) {
		this.alertMsg = alertMsg;
	}

	public String getAlertWeapons() {
		return alertWeapons;
	}

	public void setAlertWeapons(String alertWeapons) {
		this.alertWeapons = alertWeapons;
	}

	public Player getAlertPlayer() {
		return alertPlayer;
	}

	public void setAlertPlayer(Player alertPlayer) {
		this.alertPlayer = alertPlayer;
	}

	public NPC getInteractNpc() {
		return interactNpc;
	}

	public void setInteractNpc(NPC interactNpc) {
		this.interactNpc = interactNpc;
	}

	public NPC getClickedNpc() {
		return clickedNpc;
	}

	public void setClickedNpc(NPC clickedNpc) {
		this.clickedNpc = clickedNpc;
	}

	public Processing getProcessing() {
		return processing;
	}

	public void setProcessing(Processing processing) {
		this.processing = processing;
	}

	public Inventory getPlayerInv() {
		return playerInv;
	}

	public void setPlayerInv(Inventory playerInv) {
		this.playerInv = playerInv;
	}

	public ItemStack[] getArmor() {
		return armor;
	}

	public void setArmor(ItemStack[] armor) {
		this.armor = armor;
	}

	public Location getCmdLoc1() {
		return cmdLoc1;
	}

	public void setCmdLoc1(Location cmdLoc1) {
		this.cmdLoc1 = cmdLoc1;
	}

	public Location getCmdLoc2() {
		return cmdLoc2;
	}

	public void setCmdLoc2(Location cmdLoc2) {
		this.cmdLoc2 = cmdLoc2;
	}

	public int getGrade() {
		return grade;
	}

	public void setGrade(int grade) {
		this.grade = grade;
	}

	public Player getEscort() {
		return escort;
	}

	public void setEscort(Player escort) {
		this.escort = escort;
	}

	public Player getSearchedPlayer() {
		return searchedPlayer;
	}

	public void setSearchedPlayer(Player searchedPlayer) {
		this.searchedPlayer = searchedPlayer;
	}

	public Horse getSearchedHorse() {
		return searchedHorse;
	}

	public void setSearchedHorse(Horse searchedHorse) {
		this.searchedHorse = searchedHorse;
	}

	public Boat getSearchedBoat() {
		return searchedBoat;
	}

	public void setSearchedBoat(Boat searchedBoat) {
		this.searchedBoat = searchedBoat;
	}

	public Chest getSearchedChest() {
		return searchedChest;
	}

	public void setSearchedChest(Chest searchedChest) {
		this.searchedChest = searchedChest;
	}

	public boolean isFrezzePlayer() {
		return frezzePlayer;
	}

	public void setFrezzePlayer(boolean frezzePlayer) {
		this.frezzePlayer = frezzePlayer;
	}

	public boolean isEscortPlayer() {
		return escortPlayer;
	}

	public void setEscortPlayer(boolean escortPlayer) {
		this.escortPlayer = escortPlayer;
	}

	public boolean isSearchPlayer() {
		return searchPlayer;
	}

	public void setSearchPlayer(boolean searchPlayer) {
		this.searchPlayer = searchPlayer;
	}

	public boolean isSearchHorse() {
		return searchHorse;
	}

	public void setSearchHorse(boolean searchHorse) {
		this.searchHorse = searchHorse;
	}

	public boolean isSearchChest() {
		return searchChest;
	}

	public void setSearchChest(boolean searchChest) {
		this.searchChest = searchChest;
	}

	public boolean isSearchBoat() {
		return searchBoat;
	}

	public void setSearchBoat(boolean searchBoat) {
		this.searchBoat = searchBoat;
	}

	public int getTicketPay() {
		return ticketPay;
	}

	public void setTicketPay(int ticketPay) {
		this.ticketPay = ticketPay;
	}

	public int getArrestTime() {
		return arrestTime;
	}

	public void setArrestTime(int arrestTime) {
		this.arrestTime = arrestTime;
	}

	public List<String> getTicketMsg() {
		return ticketMsg;
	}

	public void setTicketMsg(List<String> ticketMsg) {
		this.ticketMsg = ticketMsg;
	}

	public HashMap<String, Float> getShootTimers() {
		return shootTimers;
	}

	public void setShootTimers(HashMap<String, Float> shootTimers) {
		this.shootTimers = shootTimers;
	}

	public HashMap<String, Float> getShootTimersMax() {
		return shootTimersMax;
	}

	public void setShootTimersMax(HashMap<String, Float> shootTimersMax) {
		this.shootTimersMax = shootTimersMax;
	}

	public HashMap<String, Boolean> getShootables() {
		return shootables;
	}

	public void setShootables(HashMap<String, Boolean> shootables) {
		this.shootables = shootables;
	}

	public HashMap<String, House> getHouses() {
		return houses;
	}

	public void setHouses(HashMap<String, House> houses) {
		this.houses = houses;
	}

	public ArrayList<Horse> getHorses() {
		return horses;
	}

	public void setHorses(ArrayList<Horse> horses) {
		this.horses = horses;
	}

	public ArrayList<Boat> getBoats() {
		return boats;
	}

	public void setBoats(ArrayList<Boat> boats) {
		this.boats = boats;
	}

	public HashMap<String, Boolean> getLicencesC() {
		return licencesC;
	}

	public void setLicencesC(HashMap<String, Boolean> licencesC) {
		this.licencesC = licencesC;
	}

	public String getOpenMenu() {
		return openMenu;
	}

	public void setOpenMenu(String openMenu) {
		this.openMenu = openMenu;
	}

	public Horse getStealHorse() {
		return stealHorse;
	}

	public void setStealHorse(Horse stealHorse) {
		this.stealHorse = stealHorse;
	}

	public boolean isMoving() {
		return moving;
	}

	public void setMoving(boolean moving) {
		this.moving = moving;
	}

	public void setGarageLand(Garage garageLand) {
		this.garageLand = garageLand;
	}

	public void setGarageWater(Garage garageWater) {
		this.garageWater = garageWater;
	}

	public void setGarageLandC(Garage garageLandC) {
		this.garageLandC = garageLandC;
	}

	public void setGarageWaterC(Garage garageWaterC) {
		this.garageWaterC = garageWaterC;
	}

	public Garage getGarageLandC() {
		return garageLandC;
	}

	public Garage getGarageWaterC() {
		return garageWaterC;
	}

}
