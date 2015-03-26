package com.lishaodong.drcore.data;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;

public class LocalPlayerData extends DRData{

	private DRCore plugin;

	public LocalPlayerData(DRCore plugin) {
		super("LocalPlayer",plugin);
	}

	@Override
	public void load() {
		super.load();
		plugin.localPlayers=((HashMap<String, LocalPlayer>) get("players"));
	}
	@Override
	public void save() {
		set("players", plugin.localPlayers);
		super.save();
	}
}
