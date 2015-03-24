package com.lishaodong.drcore;

import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

public class RunnableTask<T extends JavaPlugin> extends BukkitRunnable {
	public T plugin;

	public RunnableTask(T plugin) {
		super();
		this.plugin = plugin;
	}

	public void run() {
	}
}
