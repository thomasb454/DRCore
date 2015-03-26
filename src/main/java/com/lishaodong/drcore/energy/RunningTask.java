package com.lishaodong.drcore.energy;

import java.util.logging.Logger;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.RunnableTask;

public class RunningTask extends RunnableTask<DRCore> {
	private LocalPlayer localPlayer;

	public RunningTask(DRCore plugin, LocalPlayer localPlayer) {
		super(plugin);
		this.localPlayer = localPlayer;
	}

	Logger logger = Bukkit.getLogger();

	public void run() {
			if (localPlayer.player.isSprinting()
					&& localPlayer.energy.canLoseEnergy()){
				localPlayer.energy.changeEnergy(-0.08f);
				
			}
	}

}
