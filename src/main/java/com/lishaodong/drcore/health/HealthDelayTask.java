package com.lishaodong.drcore.health;

import org.bukkit.Bukkit;
import org.bukkit.scheduler.BukkitRunnable;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.RunnableTask;

public class HealthDelayTask extends RunnableTask<DRCore> {

	RegenerateHealthTask task = new RegenerateHealthTask(plugin);

	public HealthDelayTask(DRCore plugin) {
		super(plugin);
	}

	public void run() {
		Bukkit.getScheduler().scheduleSyncDelayedTask(plugin, task, 0L);

	}

	@Override
	public void cancel() {
		super.cancel();
		task.cancel();
	}
}
