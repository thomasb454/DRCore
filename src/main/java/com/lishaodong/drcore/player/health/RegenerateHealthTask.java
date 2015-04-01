package com.lishaodong.drcore.player.health;

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
	private LocalPlayer localPlayer;

	public RegenerateHealthTask(DRCore plugin,LocalPlayer localPlayer) {
		super(plugin);
		this.localPlayer=localPlayer;
	}

	Logger logger = Bukkit.getLogger();

	public void run() {
		double change = localPlayer.health.regenSpeed / REPEAT;
		// manager.logger.info("health regen!"+player.toString()+":"+change);
		localPlayer.health.changeHealth(change);
		
	}

}
