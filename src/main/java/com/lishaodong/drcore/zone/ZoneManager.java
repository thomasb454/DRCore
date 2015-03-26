package com.lishaodong.drcore.zone;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.java.JavaPlugin;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.FeatureManager;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.commands.ZoneCommand;
import com.lishaodong.drcore.data.DRData;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class ZoneManager extends FeatureManager{
	public enum ZoneType {SAFE,MILDERNESS,CHAOTIC, NONE};
	
	
	public Map<String, String> typeOfRegions = new HashMap<String, String>();
	WorldGuardPlugin worldGuard = WGBukkit.getPlugin();
	RegionContainer container = worldGuard.getRegionContainer();
	RegionQuery query = container.createQuery();
	CheckZoneTask checkZoneTask ;
	
	public DRCore plugin;

	
	public ZoneManager(DRCore plugin) {
		super(plugin,"zone");
		data= new ZoneData(plugin);
		checkZoneTask = new CheckZoneTask(plugin);
		Bukkit.getScheduler().scheduleSyncRepeatingTask(plugin, checkZoneTask, 0, 20L);

		ZoneCommand zoneCommand = new ZoneCommand(this);
		plugin.getCommand("zone").setExecutor(zoneCommand);
	}
	public ProtectedRegion getRegion(String name, String worldName){
		RegionManager regions = container.get(Bukkit.getWorld(worldName));
		if(regions!=null)
			return regions.getRegion(name);
		return null;
	}
	
	public String getTypeOfRegion(String region){
		return typeOfRegions.get(region);
	}
}
