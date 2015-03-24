package com.lishaodong.drcore.energy;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.RunnableTask;

public class RunningTask extends RunnableTask<DRCore> {
	public RunningTask(DRCore plugin) {
		super(plugin);
	}

	Logger logger = Bukkit.getLogger();

	public void run() {
		Player[] players = Bukkit.getOnlinePlayers();
		for (Player player : players) {
			if (player.isSprinting()
					&& plugin.getPlayer(player.getName()).canLoseEnergy()){
				EnergySystem.changeEnergy(player, -EnergySystem.RUN);
				
			}
		}
	}

}
