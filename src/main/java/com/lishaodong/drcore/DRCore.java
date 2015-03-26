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
import com.lishaodong.drcore.data.DataManager;
import com.lishaodong.drcore.listeners.ListnerManager;
import com.lishaodong.drcore.tasks.TaskManager;
import com.lishaodong.drcore.zone.ZoneManager;

public class DRCore extends JavaPlugin {
	public HashMap<String, LocalPlayer> localPlayers = new HashMap<String, LocalPlayer>();

	public BukkitScheduler scheduler;
	public DataManager dataManager;
	public ListnerManager listnerManager; 
	public TaskManager taskManager;
	public Logger logger;

	public ZoneManager zoneManager;
	public CommandManager commandManager;

	public DRCore() {
	}

	@Override
	public void onDisable() {
		saveConfig();
		dataManager.saveAll();
	}

	@Override
	public void onEnable() {
		ConfigurationSerialization.registerClass(LocalPlayer.class,"PlayerInfo");
		logger = getLogger();
		
		for(Player player:Bukkit.getOnlinePlayers()){
			if(!localPlayers.containsKey(player.getName())){
				addLocalPlayer(player);
			}
		}
		scheduler = Bukkit.getServer().getScheduler();
		saveDefaultConfig();
		

		dataManager = new DataManager(this);
		
		listnerManager = new ListnerManager(this);
		commandManager = new CommandManager(this);
		taskManager = new TaskManager(this);
		
		//feature manager
		zoneManager = new ZoneManager(this);
		
		dataManager.loadAll();
	
	}



	

	public LocalPlayer getLocalPlayer(String name) {
		return localPlayers.get(name);
	}


	public LocalPlayer addLocalPlayer(Player player) {
		LocalPlayer localPlayer = new LocalPlayer(player,this);
		localPlayers.put(player.getName(), localPlayer);
		return localPlayer;
	}
}
