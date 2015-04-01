package com.lishaodong.drcore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;

public class LocalPlayerData extends DRData{

	private DRCore plugin;
	
	public HashMap<String, LocalPlayer> localPlayers = new HashMap<String, LocalPlayer>();
	public LocalPlayerData(DRCore plugin) {
		super("player",plugin);
	}

	@Override
	public void load() {
		super.load();
		ConfigurationSection section = getConfigurationSection("players");
		if(section==null)
			return;
		for(String key: section.getKeys(false)){
			LocalPlayer player = (LocalPlayer) section.get(key);
			localPlayers.put(key, player);
		}
	}
	@Override
	public void save() {
		this.createSection("players",localPlayers);
		super.save();
	}
}
