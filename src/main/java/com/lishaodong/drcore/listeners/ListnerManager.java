package com.lishaodong.drcore.listeners;

import org.bukkit.plugin.PluginManager;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.Manager;
import com.lishaodong.drcore.energy.EnergyListener;
import com.lishaodong.drcore.health.HealthListener;

public class ListnerManager extends Manager{

	public PlayerListener playerListener;
	public EnergyListener energyListener;
	public HealthListener healthListener;
	
	public ListnerManager(DRCore plugin){
		super(plugin);
		playerListener = new PlayerListener(plugin);
		energyListener = new EnergyListener(plugin);
		healthListener = new HealthListener(plugin);

		PluginManager pm = plugin.getServer().getPluginManager();
		pm.registerEvents(energyListener, plugin);
		pm.registerEvents(playerListener, plugin);
		pm.registerEvents(healthListener, plugin);
	}
}
