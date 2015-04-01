package com.lishaodong.drcore.player;

import java.util.HashMap;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.FeatureManager;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.data.LocalPlayerData;

public class PlayerManager extends FeatureManager {
	
	public PlayerManager(DRCore plugin) {
		super(plugin, "player");
		data = new LocalPlayerData(plugin);
		data.load();
		// TODO Auto-generated constructor stub
	}

	public void loadOnlinePlayers(){
		for(Player player:Bukkit.getOnlinePlayers()){
			if(!((LocalPlayerData) data).localPlayers.containsKey(player.getName())){
				addLocalPlayer(player);
			}
		}
	}
	
	public LocalPlayer getLocalPlayer(String name) {
		return ((LocalPlayerData) data).localPlayers.get(name);
	}


	public LocalPlayer addLocalPlayer(Player player) {
		LocalPlayer localPlayer = new LocalPlayer(player,plugin);
		((LocalPlayerData) data).localPlayers.put(player.getName(), localPlayer);
		return localPlayer;
	}
}
