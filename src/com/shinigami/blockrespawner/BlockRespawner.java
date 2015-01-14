package com.shinigami.blockrespawner;

import java.util.ArrayList;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

public class BlockRespawner {

	public ArrayList<RespawnBlock> blocks;
	
	public BlockRespawner(){
		blocks = new ArrayList<>();
	}
	
	public void addBlock(World w, Location loc, Material mat, int respawntime, short data){
		RespawnBlock rBlock = new RespawnBlock();
		rBlock.world = w;
		rBlock.loc = loc;
		rBlock.blockMaterial = mat;
		rBlock.respawntime = respawntime;
		rBlock.data = data;
		
		blocks.add(rBlock);
	}
	
	public void update(){
		for(int i = 0; i<blocks.size(); i++){
			RespawnBlock rb = blocks.get(i);
			rb.timer++;
			if(rb.timer > rb.respawntime){
				rb.world.getBlockAt(rb.loc).setData((byte) rb.data);
				rb.world.getBlockAt(rb.loc).setType(rb.blockMaterial);
				
				blocks.remove(rb);
			}
		}
	}
	
	public void replacAll(){
		while(blocks.size() != 0){
			for(int i = 0; i<blocks.size(); i++){
				RespawnBlock rb = blocks.get(i);
				rb.world.getBlockAt(rb.loc).setData((byte) rb.data);
				rb.world.getBlockAt(rb.loc).setType(rb.blockMaterial);
					
				blocks.remove(rb);
			}
		}
	}
	
	private class RespawnBlock {
		public World world;
		public short data;
		public Location loc;
		public Material blockMaterial;
		public int timer, respawntime;
	}
	
}
