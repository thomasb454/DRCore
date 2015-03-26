package com.lishaodong.drcore.health;

import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;

public class HealthSystem implements ConfigurationSerializable{

	public LocalPlayer localPlayer;

	public int regenSpeed = 5;
	
	public HealthSystem(LocalPlayer player) {
		this.localPlayer = player;
	}
	public HealthSystem(Map<String, Object> map) {
		regenSpeed = (int) map.get("regenSpeed");
	}
	public void setHealth(double current) {
		if (localPlayer == null)
			return;
		localPlayer.player.setHealth(current);
		// Bukkit.getLogger().log(Level.INFO, localPlayer+"health:"+
		// current+"/"+maxHealth);

		localPlayer.player.setLevel((int) current);
	}
	public void changeHealth(double change) {
		double current = localPlayer.player.getHealth() + change;
		int max = (int) localPlayer.player.getMaxHealth();
		if (current > max)
			current = max;
		if(current<0)
			current=0;
		setHealth( current);
	}

	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("regenSpeed", regenSpeed);
		return map;
	}
}
