package com.lishaodong.drcore.data;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.file.YamlConfiguration;

import com.lishaodong.drcore.Constants;
import com.lishaodong.drcore.DRCore;

public class DRData extends YamlConfiguration{
	public DRCore plugin;
	private String name;
	private String filePath;

    public static final Map<String, DRData> DATA_MAP = new HashMap<String, DRData>();
	
	
	public DRData(String name, DRCore plugin) {
		this.plugin  = plugin;
		this.name = name;
		this.filePath = Constants.ymlPath(name);
		DATA_MAP.put(name, this);
	}




    public static DRData getData(String name){
    		return DATA_MAP.get(name);
    }
    
	public void load(){
		try {
			this.load(filePath);
		} catch (Exception e) {
			;
		}
	}
	
	public void save(){
		try {
			this.save(filePath);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void loadAll(){
		for (DRData drData : DATA_MAP.values()) {
            drData.load();
        }
	}
	public static void saveAll(){
		for (DRData drData : DATA_MAP.values()) {
            drData.save();
        }
	}
}
