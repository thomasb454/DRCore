package com.lishaodong.drcore.tasks;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitScheduler;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.Manager;
import com.lishaodong.drcore.energy.EnergySystem;
import com.lishaodong.drcore.energy.RegenerateEnergyTask;
import com.lishaodong.drcore.energy.RunningTask;
import com.lishaodong.drcore.health.RegenerateHealthTask;

public class TaskManager extends Manager {

	public BukkitScheduler scheduler;
	private Map<String,RegenerateHealthTask> regenHealthTaskMap;
	
	public TaskManager(DRCore plugin) {
		super(plugin);

		regenHealthTaskMap = new HashMap<String,RegenerateHealthTask>();
		// damageListener = new DamageListener(this);

		scheduler = plugin.scheduler;
		
	}
	public void resetRegenHealthTask(LocalPlayer localPlayer) {
		try {
			regenHealthTaskMap.get(localPlayer.name).cancel();
			plugin.logger.info("cancel health task for "+localPlayer.name);
		} catch (Exception e) {
			plugin.logger.info("health task not scheduled");
		}
		// 20s later
		RegenerateHealthTask task = new RegenerateHealthTask(plugin,localPlayer);
		task.runTaskTimer(plugin, 400L, RegenerateHealthTask.GAP);
		regenHealthTaskMap.put(localPlayer.name, task);
		
	}
	public void setEnergyTask(LocalPlayer localPlayer){
		scheduler.scheduleSyncRepeatingTask(plugin, new RunningTask(plugin,localPlayer), 0L,
				EnergySystem.RUN_GAP);

		scheduler.scheduleSyncRepeatingTask(plugin,
				new RegenerateEnergyTask(plugin,localPlayer), 0L, EnergySystem.REGEN_GAP);
	}
}
