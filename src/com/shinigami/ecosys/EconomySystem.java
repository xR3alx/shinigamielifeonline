package com.shinigami.ecosys;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Properties;

import com.shinigami.configs.Configuration;
import com.shinigami.configs.PriceConfig;

public class EconomySystem {

	private EconomyLoader ecoLoader;
	private HashMap<String, Integer> ecoProcessedMap, ecoUnprocessedMap, ecoProcessedDrugMap, ecoUnprocessedDrugMap;
	
	private int timer, lastEcos, lastEcosUn, lastEcosDrug, lastEcosDrugUn;
	
	private HashMap<String, Integer> sellPrices;
	
	public EconomySystem(){
		sellPrices = new HashMap<String, Integer>();
		sellPrices.putAll(Configuration.getPriceConfig().sellPrices);
		
		init();
		load();
	}
	
	public void update(PriceConfig priceConfig){
		if(timer > 1800){
			timer = 0;
			
			updateUnprocessed(priceConfig);
			updateProcessed(priceConfig);
			updateUnprocessedDrug(priceConfig);
			updateProcessedDrug(priceConfig);
		}else{
			timer++;
		}
	}
	
	public void addProcessed(String eco, int amount){
		if(ecoProcessedMap.get(eco) != null){
			ecoProcessedMap.put(eco, ecoProcessedMap.get(eco) + amount);
		}
	}
	
	public void addUnprocessed(String eco, int amount){
		if(ecoUnprocessedMap.get(eco) != null){
			ecoUnprocessedMap.put(eco, ecoUnprocessedMap.get(eco) + amount);
		}
	}
	
	public void addProcessedDrug(String eco, int amount){
		if(ecoProcessedDrugMap.get(eco) != null){
			ecoProcessedDrugMap.put(eco, ecoProcessedDrugMap.get(eco) + amount);
		}
	}
	
	public void addUnprocessedDrug(String eco, int amount){
		if(ecoUnprocessedDrugMap.get(eco) != null){
			ecoUnprocessedDrugMap.put(eco, ecoUnprocessedDrugMap.get(eco) + amount);
		}
	}
	
	public void updateProcessed(PriceConfig priceConfig){
		int allEcos = 0;
		int allEcoPrices = 0;
		
		for(String s : ecoProcessedMap.keySet()){
			if(ecoProcessedMap.get(s) != 0){
				allEcos+=ecoProcessedMap.get(s);
				allEcoPrices+=sellPrices.get(s);
			}
		}

		if(allEcos != lastEcos){
			lastEcos = allEcos;
			
			HashMap<String, Integer> percents = new HashMap<String, Integer>();
			
			for(String s : ecoProcessedMap.keySet()){
				int ecoPerct = ecoProcessedMap.get(s) * 100 / allEcos;
				percents.put(s, ecoPerct);
			}
			
			for(String s : ecoProcessedMap.keySet()){
				if(ecoProcessedMap.get(s) != 0){
					int ecoPerct = 0;
					for(String st : percents.keySet()){
						if(!s.equals(st)){
							ecoPerct+=percents.get(s);
						}
					}

					int ecoPerct2 = ecoPerct * 100 / allEcos;
					int price = (int) (allEcoPrices - (ecoPerct2 * allEcoPrices / 100) * 1.25f);
						
					if(price >= priceConfig.sellPrices.get(s) / 9){
						priceConfig.sellPrices.put(s, price);
						
						String sUnprocessed = s.replace("verarbeitet", "unverarbeitet").replace("gekochter", "rocher");
						priceConfig.sellPrices.put(sUnprocessed, priceConfig.sellPrices.get(sUnprocessed) / 9);
					}else{
						price = priceConfig.sellPrices.get(s) / 9;
						priceConfig.sellPrices.put(s, price);
					}
				}
			}
		}
	}
	
	public void updateUnprocessed(PriceConfig priceConfig){
		int allEcos = 0;
		int allEcoPrices = 0;
		
		for(String s : ecoUnprocessedMap.keySet()){
			if(ecoUnprocessedMap.get(s) != 0){
				allEcos+=ecoUnprocessedMap.get(s);
				allEcoPrices+=sellPrices.get(s);
			}
		}

		if(allEcos != lastEcosUn){
			lastEcosUn = allEcos;
			
			HashMap<String, Integer> percents = new HashMap<String, Integer>();
			
			for(String s : ecoUnprocessedMap.keySet()){
				int ecoPerct = ecoUnprocessedMap.get(s) * 100 / allEcos;
				percents.put(s, ecoPerct);
			}
			
			for(String s : ecoUnprocessedMap.keySet()){
				if(ecoUnprocessedMap.get(s) != 0){
					int ecoPerct = 0;
					for(String st : percents.keySet()){
						if(!s.equals(st)){
							ecoPerct+=percents.get(s);
						}
					}

					int ecoPerct2 = ecoPerct * 100 / allEcos;
					int price = (int) (allEcoPrices - (ecoPerct2 * allEcoPrices / 100) * 1.25f);
						
					if(price >= priceConfig.sellPrices.get(s) / 9){
						priceConfig.sellPrices.put(s, price);
					}else{
						price = priceConfig.sellPrices.get(s) / 9;
						priceConfig.sellPrices.put(s, price);
					}
				}
			}
		}
	}
	
	public void updateProcessedDrug(PriceConfig priceConfig){
		int allEcos = 0;
		int allEcoPrices = 0;
		
		for(String s : ecoProcessedDrugMap.keySet()){
			if(ecoProcessedDrugMap.get(s) != 0){
				allEcos+=ecoProcessedDrugMap.get(s);
				allEcoPrices+=sellPrices.get(s);
			}
		}

		if(allEcos != lastEcosDrug){
			lastEcosDrug = allEcos;
			
			HashMap<String, Integer> percents = new HashMap<String, Integer>();
			
			for(String s : ecoProcessedDrugMap.keySet()){
				int ecoPerct = ecoProcessedDrugMap.get(s) * 100 / allEcos;
				percents.put(s, ecoPerct);
			}
			
			for(String s : ecoProcessedDrugMap.keySet()){
				if(ecoProcessedDrugMap.get(s) != 0){
					int ecoPerct = 0;
					for(String st : percents.keySet()){
						if(!s.equals(st)){
							ecoPerct+=percents.get(s);
						}
					}

					int ecoPerct2 = ecoPerct * 100 / allEcos;
					int price = (int) (allEcoPrices - (ecoPerct2 * allEcoPrices / 100) * 1.25f);
						
					if(price >= priceConfig.sellPrices.get(s) / 9){
						priceConfig.sellPrices.put(s, price);
						
						String sUnprocessed = s.replace("verarbeitet", "unverarbeitet").replace("gekochter", "rocher");
						priceConfig.sellPrices.put(sUnprocessed, priceConfig.sellPrices.get(sUnprocessed) / 9);
					}else{
						price = priceConfig.sellPrices.get(s) / 9;
						priceConfig.sellPrices.put(s, price);
					}
				}
			}
		}
	}
	
	public void updateUnprocessedDrug(PriceConfig priceConfig){
		int allEcos = 0;
		int allEcoPrices = 0;
		
		for(String s : ecoUnprocessedDrugMap.keySet()){
			if(ecoUnprocessedDrugMap.get(s) != 0){
				allEcos+=ecoUnprocessedDrugMap.get(s);
				allEcoPrices+=sellPrices.get(s);
			}
		}

		if(allEcos != lastEcosDrugUn){
			lastEcosDrugUn = allEcos;
			
			HashMap<String, Integer> percents = new HashMap<String, Integer>();
			
			for(String s : ecoUnprocessedDrugMap.keySet()){
				int ecoPerct = ecoUnprocessedDrugMap.get(s) * 100 / allEcos;
				percents.put(s, ecoPerct);
			}
			
			for(String s : ecoUnprocessedDrugMap.keySet()){
				if(ecoUnprocessedDrugMap.get(s) != 0){
					int ecoPerct = 0;
					for(String st : percents.keySet()){
						if(!s.equals(st)){
							ecoPerct+=percents.get(s);
						}
					}

					int ecoPerct2 = ecoPerct * 100 / allEcos;
					int price = (int) (allEcoPrices - (ecoPerct2 * allEcoPrices / 100) * 1.25f);
						
					if(price >= priceConfig.sellPrices.get(s) / 9){
						priceConfig.sellPrices.put(s, price);
					}else{
						price = priceConfig.sellPrices.get(s) / 9;
						priceConfig.sellPrices.put(s, price);
					}
				}
			}
		}
	}
	
	public void saveAll(){
		boolean saved = false;
		while(!saved){
			ecoLoader.save(ecoProcessedMap);
			ecoLoader.save(ecoUnprocessedMap);
			ecoLoader.save(ecoProcessedDrugMap);
			ecoLoader.save(ecoUnprocessedDrugMap);
			saved = true;
		}
	}
	
	private void init(){
		ecoProcessedMap = new HashMap<String, Integer>();
		ecoUnprocessedMap = new HashMap<String, Integer>();
		ecoProcessedDrugMap = new HashMap<String, Integer>();
		ecoUnprocessedDrugMap = new HashMap<String, Integer>();
		ecoLoader = new EconomyLoader();
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			ecoProcessedMap.put("verarbeitete_kohle", ecoLoader.load("verarbeitete_kohle"));
			ecoProcessedMap.put("verarbeitetes_eisen", ecoLoader.load("verarbeitetes_eisen"));
			ecoProcessedMap.put("verarbeiteter_rotstein", ecoLoader.load("verarbeiteter_rotstein"));
			ecoProcessedMap.put("verarbeiteter_diamant", ecoLoader.load("verarbeiteter_diamant"));
			ecoProcessedMap.put("verarbeiteter_smaragd", ecoLoader.load("verarbeiteter_smaragd"));
			ecoProcessedMap.put("verarbeitetes_gold", ecoLoader.load("verarbeitetes_gold"));
			ecoProcessedMap.put("verarbeitetes_holz", ecoLoader.load("verarbeitetes_holz"));
			ecoProcessedMap.put("gekochter_fisch", ecoLoader.load("gekochter_fisch"));
			ecoProcessedMap.put("verarbeitetes_salz", ecoLoader.load("verarbeitetes_salz"));
			
			ecoProcessedDrugMap.put("kokain", ecoLoader.load("kokain"));
			ecoProcessedDrugMap.put("heroin", ecoLoader.load("heroin"));
			ecoProcessedDrugMap.put("marihuana", ecoLoader.load("marihuana"));
			
			ecoUnprocessedMap.put("unverarbeitete_kohle", ecoLoader.load("unverarbeitete_kohle"));
			ecoUnprocessedMap.put("unverarbeitetes_eisen", ecoLoader.load("unverarbeitetes_eisen"));
			ecoUnprocessedMap.put("unverarbeiteter_rotstein", ecoLoader.load("unverarbeiteter_rotstein"));
			ecoUnprocessedMap.put("unverarbeiteter_diamant", ecoLoader.load("unverarbeiteter_diamant"));
			ecoUnprocessedMap.put("unverarbeiteter_smaragd", ecoLoader.load("unverarbeiteter_smaragd"));
			ecoUnprocessedMap.put("unverarbeitetes_gold", ecoLoader.load("unverarbeitetes_gold"));
			ecoUnprocessedMap.put("unverarbeitetes_holz", ecoLoader.load("unverarbeitetes_holz"));
			ecoUnprocessedMap.put("roher_fisch", ecoLoader.load("roher_fisch"));
			ecoUnprocessedMap.put("unverarbeitetes_salz", ecoLoader.load("unverarbeitetes_salz"));
			
			ecoUnprocessedDrugMap.put("unverarbeites_kokain", ecoLoader.load("unverarbeites_kokain"));
			ecoUnprocessedDrugMap.put("unverarbeites_heroin", ecoLoader.load("unverarbeites_heroin"));
			ecoUnprocessedDrugMap.put("unverarbeites_marihuana", ecoLoader.load("unverarbeites_marihuana"));
			loaded = true;
		}
	}
	
	private class EconomyLoader {
		public Properties props;
		public File economyDataFile;
		
		public EconomyLoader(){

			File dataDir = new File("MinecraftLife/db");
			if(!dataDir.exists()){
				dataDir.mkdirs();
			}
			
			economyDataFile = new File("MinecraftLife/db/economy.data");
			props = new Properties();
			
			try {
				
				if(!economyDataFile.exists()){
					economyDataFile.createNewFile();
				}
				
				FileInputStream fileIn = new FileInputStream(economyDataFile);
				props.load(fileIn);
				fileIn.close();
				
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			
		}
		
		public int load(String prop){
			return Integer.parseInt(props.getProperty(prop));
		}
		
		public void save(HashMap<String, Integer> map){
			for(String s : map.keySet()){
				props.setProperty(s, map.get(s).toString());
			}
			
			try {
				FileOutputStream fileOut = new FileOutputStream(economyDataFile);
				props.store(fileOut, null);
				fileOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
