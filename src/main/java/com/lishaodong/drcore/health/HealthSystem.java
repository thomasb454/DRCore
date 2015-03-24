package com.lishaodong.drcore.health;

import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;

public class HealthSystem {
	public static DRCore plugin;

	public static void setHealth(Player player, double current) {
		if (player == null)
			return;
		Integer maxHealth = (int) player.getMaxHealth();
		player.setHealth(current);
		// Bukkit.getLogger().log(Level.INFO, player+"health:"+
		// current+"/"+maxHealth);

		player.setLevel((int) current);

	}
}
