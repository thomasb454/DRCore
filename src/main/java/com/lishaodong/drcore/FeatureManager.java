package com.lishaodong.drcore;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.MemorySection;

import com.lishaodong.drcore.data.DRData;

public class FeatureManager extends Manager{
	public FeatureManager(DRCore plugin, String name) {
		super(plugin);
		this.name =name;
		config=plugin.getConfig().getConfigurationSection(name);
		// TODO Auto-generated constructor stub
	}
	public String name;
	public DRData data;
	public ConfigurationSection config;
	
	public void reloadConfig(){
		plugin.reloadConfig();
		loadConfig();
	}
	public void loadConfig(){
		config=plugin.getConfig().getConfigurationSection(name);
		
	}
}
