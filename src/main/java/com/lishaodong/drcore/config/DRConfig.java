package com.lishaodong.drcore.config;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;

import com.lishaodong.drcore.Constants;

public enum DRConfig {
	zoneStat("zoneStat",new YamlConfiguration(),Constants.configPath("zone","zoneStat")),
	playerStat("playerStat",new YamlConfiguration(),Constants.configPath("player","playerStat"));
	
	private String name;
	private YamlConfiguration config;
	private String filePath;
	
	
	
	private DRConfig(String name, YamlConfiguration config, String filePath) {
		this.name = name;
		this.config = config;
		this.filePath = filePath;
	}


    private static final Map<String, DRConfig> NAME_MAP = new HashMap<String, DRConfig>();


    static {
        for (DRConfig config : values()) {
            NAME_MAP.put(config.name, config);
        }
    }
    public static YamlConfiguration getConfig(String name){
    		return NAME_MAP.get(name).config;
    }
	public void load(){
		try {
			config.load(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void save(){
		try {
			config.save(new File(filePath));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadAll(){
		for (DRConfig drconfig : values()) {
            drconfig.load();
        }
	}
	public static void saveAll(){
		for (DRConfig drconfig : values()) {
            drconfig.save();
        }
	}
}
