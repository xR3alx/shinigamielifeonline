package com.shinigami.groups;

import java.util.ArrayList;

import org.bukkit.entity.Player;

public class GroupManager {

	public ArrayList<Group> groups;
	
	public GroupManager(){
		
		groups = new ArrayList<Group>();
		
	}
	
	public Group createGroup(Player p){
		Group group = new Group();
		
		group.leader = p;
		group.id = groups.size();
		group.members.add(p);
		
		groups.add(group);
	
		return group;
	}
	
	public class Group {
		public int id;
		public Player leader;
		public boolean locked;
		public ArrayList<Player> members;
		
		public Group(){
			members = new ArrayList<Player>();
		}
	}
}
