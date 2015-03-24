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
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.commands.ZoneCommand;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.RegionQuery;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class Zone extends JavaPlugin{
	public enum ZoneType {SAFE,MILDERNESS,CHAOTIC, NONE};
	public Map<String, String> typeOfRegions = new HashMap<String, String>();
	WorldGuardPlugin worldGuard = WGBukkit.getPlugin();
	RegionContainer container = worldGuard.getRegionContainer();
	RegionQuery query = container.createQuery();
	CheckZoneTask checkZoneTask = new CheckZoneTask(this);
	
	ZoneCommand command;
	public DRCore drCore;

	@Override
	public void onDisable() {
		save();
	}
	
	@Override
	public void onEnable() {
		// TODO Auto-generated method stub
		drCore = (DRCore) Bukkit.getPluginManager().getPlugin("DRCore");
		Bukkit.getScheduler().scheduleSyncRepeatingTask(this, checkZoneTask, 0, 20L);
		super.onEnable();
		command = new ZoneCommand(this);
		getCommand("zone").setExecutor(command);
		saveDefaultConfig();
		load();
	}
	private void save() {
		try {
			// Create a new YAML configuration
			YamlConfiguration config = new YamlConfiguration();
			// Add each of our hashmaps to the config by creating sections
			config.createSection("zones", typeOfRegions);
			// Write the configuration to our save file
			config.save(new File(this.getDataFolder(), "zones.yml"));
		} catch (Exception saveFailed) {
			this.getLogger().log(Level.SEVERE, "Save Failed!", saveFailed);
		}
	}

	private void load() {
		try {
			// Ensure that the file exists before attempting to load it
			File file = new File(this.getDataFolder(), "zones.yml");
			if (file.exists()) {
				// Load the file as a YAML Configuration
				YamlConfiguration config = new YamlConfiguration();
				config.load(file);
				// Get the homes section which is our saved hash map of homes
				// Each key is the name of the Player
				// Each value is the location of their home
				ConfigurationSection section = config
						.getConfigurationSection("zones");
				for (String key : section.getKeys(false)) {
					// Get the location for each key
					typeOfRegions.put(key,(String) section.get(key));
				}

			}
		} catch (Exception loadFailed) {
			this.getLogger().log(Level.SEVERE, "Load Failed!", loadFailed);
		}
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
