package com.lishaodong.drcore;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginDescriptionFile;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitScheduler;

import com.lishaodong.drcore.config.ConfigManager;
import com.lishaodong.drcore.config.DRConfig;
import com.lishaodong.drcore.energy.EnergyListener;
import com.lishaodong.drcore.energy.EnergySystem;
import com.lishaodong.drcore.energy.RegenerateEnergyTask;
import com.lishaodong.drcore.energy.RunningTask;
import com.lishaodong.drcore.health.HealthDelayTask;
import com.lishaodong.drcore.health.HealthListener;
import com.lishaodong.drcore.health.HealthSystem;
import com.lishaodong.drcore.health.RegenerateHealthTask;
import com.lishaodong.drcore.listeners.PlayerListener;

public class DRCore extends JavaPlugin {
	public HashMap<String, LocalPlayer> infos = new HashMap<String, LocalPlayer>();
	public ConfigManager configManager = new ConfigManager(this);
	
	public PlayerListener playerListener;
	public EnergyListener energyListener;
	public HealthListener healthListener;
	public BukkitScheduler scheduler;
	private RegenerateHealthTask healthTask;
	public Logger logger;

	public DRCore() {
	}

	@Override
	public void onDisable() {
		save();
		configManager.saveAll();
	}

	@Override
	public void onEnable() {
		ConfigurationSerialization.registerClass(LocalPlayer.class,"PlayerInfo");

		playerListener = new PlayerListener(this);
		healthTask = new RegenerateHealthTask(this);
		// damageListener = new DamageListener(this);
		logger = getLogger();

		scheduler = Bukkit.getServer().getScheduler();
		scheduler.scheduleSyncRepeatingTask(this,
				new RegenerateEnergyTask(this), 0L, EnergySystem.REGEN_GAP);
		scheduler.scheduleSyncRepeatingTask(this, new RunningTask(this), 0L,
				EnergySystem.RUN_GAP);
		resetHealthTask();

		HealthSystem.plugin = this;
		EnergySystem.plugin = this;
		energyListener = new EnergyListener(this);
		healthListener = new HealthListener(this);
		// Register our events
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(energyListener, this);
		pm.registerEvents(playerListener, this);
		pm.registerEvents(healthListener, this);
		// pm.registerEvents(damageListener, this);

		// Register our commands
		// load
		saveDefaultConfig();
		load();
		configManager.loadAll();
	}

	private void save() {
		try {
			// Create a new YAML configuration
			configManager.getConfig("playerStat").set("", infos);
		} catch (Exception saveFailed) {
			this.getLogger().log(Level.SEVERE, "Save Failed!", saveFailed);
		}
	}

	private void load() {
		try {
			
				ConfigurationSection section = configManager.getConfig("playerStat");
				for (String key : section.getKeys(false)) {
					// Get the location for each key
					LocalPlayer info = (LocalPlayer) section.get(key);
					info.plugin = this;
					// Only add the warp location if it is valid
					infos.put(key, info);
				}
		} catch (Exception loadFailed) {
			this.getLogger().log(Level.SEVERE, "Load Failed!", loadFailed);
		}
	}

	public void addPlayer(Player player) {
		player.setMaxHealth(50);
		LocalPlayer localPlayer = new LocalPlayer();
		localPlayer.plugin = this;
		localPlayer.name = player.getName();
		localPlayer.player = player;
		
		infos.put(localPlayer.name, localPlayer);
	}

	public void resetHealthTask() {
		try {
			healthTask.cancel();
			logger.info("cancel health task");
		} catch (Exception e) {
			logger.info("health task not scheduled");
		}
		// 20s later
		healthTask = new RegenerateHealthTask(this);
		healthTask.runTaskTimer(this, 400L, RegenerateHealthTask.GAP);
	}

	public LocalPlayer getPlayer(String name) {
		return infos.get(name);
	}
}
