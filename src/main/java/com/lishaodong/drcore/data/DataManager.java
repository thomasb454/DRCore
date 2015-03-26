package com.lishaodong.drcore.data;

import org.bukkit.configuration.file.YamlConfiguration;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.Manager;

public class DataManager extends Manager{
	
	public DataManager(DRCore plugin){
		super(plugin);
	}
	
	public void loadAll(){
		DRData.loadAll();
	}
	
	public void saveAll(){
		DRData.saveAll();
	}
	
	public DRData getData(String name){
		return DRData.getData(name);
	}
}
