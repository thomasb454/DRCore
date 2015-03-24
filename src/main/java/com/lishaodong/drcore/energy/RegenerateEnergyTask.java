package com.lishaodong.drcore.energy;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.RunnableTask;

public class RegenerateEnergyTask extends RunnableTask<DRCore> {
	public boolean locked = false;

	public RegenerateEnergyTask(DRCore plugin) {
		super(plugin);
	}

	Logger logger = Bukkit.getLogger();

	public void run() {
		// Main info = (Main) Bukkit.getPluginManager().getPlugin("PlayerInfo");
		// if(info==null){
		// info = (Main) Bukkit.getPluginManager().getPlugin("PlayerInfo");
		// infos = info.infos;
		// }
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players) {
			regenerateEnergy(player);

		}
	}

	public void regenerateEnergy(Player player) {
		LocalPlayer info = plugin.infos.get(player.getName());
		if(info==null){
			plugin.addPlayer(player);
		}
		if (info.locked)
			return;
		float amount = info.energySpeed * EnergySystem.REGEN;
		// logger.info("regen:"+amount);
		EnergySystem.changeEnergy(player, amount);
	}

}
