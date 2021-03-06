package com.lishaodong.drcore.listeners;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
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

		
		LocalPlayer localPlayer;
		if (plugin.getLocalPlayer(player.getName())==null) {
			localPlayer=plugin.playerManager.addLocalPlayer(player);
		}else{
			localPlayer = plugin.getLocalPlayer(player.getName());
			localPlayer.player = player;
		}
		localPlayer.enterWorld();
	}


	@EventHandler(priority = EventPriority.MONITOR)
	public void onPlayerDead(PlayerDeathEvent event) {
		Player player = event.getEntity();
	
		LocalPlayer localPlayer = plugin.getLocalPlayer(player.getName());
		localPlayer.dead();
	}
}
