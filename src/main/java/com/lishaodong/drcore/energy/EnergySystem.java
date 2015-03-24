package com.lishaodong.drcore.energy;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;

public class EnergySystem {

	public static final int RUN_LOSE = 25;
	public static final int RUN_GAP = 10;

	public static final int REGEN_GAP = 4;

	public static final float PERCENT = (float) 1.0 / 100 / 20;
	public static final float RUN = RUN_LOSE * PERCENT * RUN_GAP;
	public static final float REGEN = PERCENT * REGEN_GAP;
	public static DRCore plugin;

	public static void changeEnergy(Player player, float change) {
		float current = player.getExp() + change;
		if (current <= 0) {
			Bukkit.getLogger().info("set sprint false");
			player.setSprinting(false);
			plugin.infos.get(player.getName()).lockEnergyRegen();
			current = 0;
		}
		if (current > 1)
			current = 1;
		player.setExp(current);
	}
}
