package com.lishaodong.drcore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.avaje.ebeaninternal.api.LoadContext;
import com.lishaodong.drcore.commands.CommandManager;
import com.lishaodong.drcore.data.DRData;
import com.lishaodong.drcore.listeners.ListnerManager;
import com.lishaodong.drcore.player.PlayerManager;
import com.lishaodong.drcore.tasks.TaskManager;
import com.lishaodong.drcore.zone.ZoneManager;

public class DRCore extends JavaPlugin {

	public BukkitScheduler scheduler;
	public ListnerManager listnerManager; 
	public TaskManager taskManager;
	public Logger logger;

	public CommandManager commandManager;
	//feature manager
	public ZoneManager zoneManager;
	public PlayerManager playerManager;

	public DRCore() {
	}

	@Override
	public void onDisable() {
		saveConfig();
		DRData.saveAll();
	}

	@Override
	public void onEnable() {
		ConfigurationSerialization.registerClass(LocalPlayer.class,"PlayerInfo");
		logger = getLogger();
		
		
		scheduler = Bukkit.getServer().getScheduler();
		saveDefaultConfig();
		

		
		listnerManager = new ListnerManager(this);
		commandManager = new CommandManager(this);
		
		//feature manager
		playerManager = new PlayerManager(this);
		zoneManager = new ZoneManager(this);

		taskManager = new TaskManager(this);
		
		playerManager.loadOnlinePlayers();
	
	}
	public LocalPlayer getLocalPlayer(String name) {
		return playerManager.getLocalPlayer(name);
	}




	

	
}
