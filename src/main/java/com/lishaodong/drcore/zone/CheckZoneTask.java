package com.lishaodong.drcore.zone;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.*;
import com.lishaodong.drcore.zone.ZoneManager.ZoneType;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.protection.ApplicableRegionSet;

public class CheckZoneTask extends RunnableTask<DRCore>{
	public CheckZoneTask(DRCore plugin) {
		super(plugin);
	}
@Override
	public void run() {
	Player[] players = Bukkit.getOnlinePlayers();
	RegionQuery query = plugin.zoneManager.query;
	for(Player player: players){
		LocalPlayer localPlayer = plugin.getLocalPlayer(player.getName());
		String type = null;
		ApplicableRegionSet set = query.getApplicableRegions(player.getLocation());
		if(set.size()!=0){
			String region = set.iterator().next().getId();
			type = plugin.zoneManager.getTypeOfRegion(region);
			
		}
		if(type==null)
			type="none";
		localPlayer.setCurrentZone(type);
		//Bukkit.getLogger().info("now in "+type);
	}
}
}
