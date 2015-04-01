package com.lishaodong.drcore.player.energy;

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
	private LocalPlayer localPlayer;

	public RegenerateEnergyTask(DRCore plugin, LocalPlayer localPlayer) {
		super(plugin);
		this.localPlayer = localPlayer;
	}

	Logger logger = Bukkit.getLogger();

	public void run() {
		if (localPlayer.energy.locked)
			return;
		float amount = localPlayer.energy.regenSpeed * EnergySystem.REGEN;
		// logger.info("regen:"+amount);
		localPlayer.energy.changeEnergy(amount);
	}


}
