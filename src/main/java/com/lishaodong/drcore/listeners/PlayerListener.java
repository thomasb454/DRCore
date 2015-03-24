package com.lishaodong.drcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;

public class PlayerListener implements Listener {
	private final DRCore plugin;

	public PlayerListener(DRCore instance) {
		plugin = instance;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();

		player.setHealthScale(20);
		player.setExp(1);
		if (!plugin.infos.containsKey(player.getName())) {
			plugin.addPlayer(player);
		}else{
			LocalPlayer localPlayer = plugin.infos.get(player.getName());
			localPlayer.player = player;
		}
	}

}
