package com.lishaodong.drcore.zone;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.*;
import com.lishaodong.drcore.zone.Zone.ZoneType;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

public class CheckZoneTask extends RunnableTask<Zone>{
	RegionQuery query = plugin.query;
	public CheckZoneTask(Zone plugin) {
		super(plugin);
	}
@Override
	public void run() {
	Player[] players = Bukkit.getOnlinePlayers();
	for(Player player: players){
		LocalPlayer localPlayer = plugin.drCore.getPlayer(player.getName());
		String type = null;
		ApplicableRegionSet set = query.getApplicableRegions(player.getLocation());
		if(set.size()!=0){
			String region = set.iterator().next().getId();
			type = plugin.getTypeOfRegion(region);
			
		}
		if(type==null)
			type="none";
		localPlayer.setCurrentZone(type);
		//Bukkit.getLogger().info("now in "+type);
	}
}
}
