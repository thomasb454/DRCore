package com.lishaodong.drcore.health;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.RunnableTask;

public class RegenerateHealthTask extends RunnableTask<DRCore> {
	public static final int GAP = 20;
	public static final double REPEAT = 20 / GAP;

	public RegenerateHealthTask(DRCore plugin) {
		super(plugin);
	}

	Logger logger = Bukkit.getLogger();

	public void run() {
		// plugin.logger.info("health task run!");
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players) {
			regenerateHealth(player);
		}
	}

	public static void changeHealth(Player player, double change) {
		double current = player.getHealth() + change;
		int max = (int) player.getMaxHealth();
		if (current > max)
			current = max;
		HealthSystem.setHealth(player, current);
	}

	public void regenerateHealth(Player player) {

		double change = plugin.infos.get(player.getName()).healthSpeed / REPEAT;
		// plugin.logger.info("health regen!"+player.toString()+":"+change);
		changeHealth(player, change);
	}

}
