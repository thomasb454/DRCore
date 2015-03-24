package com.lishaodong.drcore.config;

import org.bukkit.configuration.file.YamlConfiguration;

import com.lishaodong.drcore.DRCore;

public class ConfigManager {
	private DRCore plugin;
	
	public ConfigManager(DRCore plugin){
		this.plugin = plugin;
	}
	
	public void loadAll(){
		DRConfig.loadAll();
	}
	
	public void saveAll(){
		DRConfig.saveAll();
	}
	
	public YamlConfiguration getConfig(String name){
		return DRConfig.getConfig(name);
	}
}
