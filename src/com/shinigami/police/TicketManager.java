package com.shinigami.police;

import java.io.File;
import java.util.ArrayList;
import java.util.Properties;

import com.shinigami.utils.Utils;

public class TicketManager {
	
	public File ticketDir;

	public ArrayList<Ticket> tickets;
	
	public TicketManager(){
		
		ticketDir = new File("MinecraftLife/db/tickets");
		if(!ticketDir.exists()){
			ticketDir.mkdirs();
		}
		
		
		tickets = new ArrayList<Ticket>();
		
		load();
	}
	
	private void load(){
		boolean loaded = false;
		while(!loaded){
			if(ticketDir.listFiles().length != 0){
				for(File f : ticketDir.listFiles()){
					
					Properties props = Utils.loadFile(f);

					Ticket ticket = new Ticket();
					
					ticket.name = f.getName().replace(".ticket", "").replace("_", " ");
					ticket.description = props.getProperty("description").split(";");
					ticket.pay = Integer.parseInt(props.getProperty("pay"));
					
					tickets.add(ticket);
					
					
				}
			}
			loaded = true;
		}
	}
	
	public class Ticket {
		public String name;
		public int pay;
		public String[] description;
	}
}
