package com.lishaodong.drcore;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

@SerializableAs("LocalPlayer")
public class LocalPlayer implements ConfigurationSerializable {
	public Player player;
	
	public DRCore plugin;
	public String name;
	public int maxEnergy = 100;
	public Alignment alignment = Alignment.NONE;
	// regenerate each second
	public int energySpeed = 10;
	public int healthSpeed = 5;
	public boolean locked = false;
	public boolean canLoseEnergy = true;
	public String currentZone = "none";

	public LocalPlayer(Map<String, Object> map) {
		name = (String) map.get("name");
		maxEnergy = (Integer) map.get("maxEnergy");
		energySpeed = (Integer) map.get("energySpeed");
		healthSpeed = (Integer) map.get("healthSpeed");
	}

	public LocalPlayer() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Object> serialize() {

		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("name", name);
		map.put("maxEnergy", maxEnergy);
		map.put("energySpeed", energySpeed);
		map.put("healthSpeed", healthSpeed);
		return map;
	}

	public void lockEnergyRegen() {
		locked = true;
		plugin.logger.info("lock energy regen");
		plugin.scheduler.scheduleSyncDelayedTask(plugin, new Runnable() {
			public void run() {
				plugin.logger.info("unlock energy regen");
				locked = false;
			}
		}, 40L);
	}

	public boolean canLoseEnergy() {
		// TODO Auto-generated method stub
		return canLoseEnergy;
	}

	public void setCurrentZone(String type) {
		switch (type.toLowerCase()) {
		case "safe":
			canLoseEnergy = false;
			setAlignment(Alignment.LAWFUL);
			break;
		case "wilderness":
			canLoseEnergy = true;
			break;

		case "choatic":
			canLoseEnergy = true;
			break;

		case "none":
			canLoseEnergy = true;
			break;
		default:
			break;
		}
		
	}
	public void setAlignment(Alignment alignment){
		this.alignment = alignment;
		player.sendMessage(plugin.getConfig().getString("alignment."+alignment.name+".GREETING_MESSAGE"));
	}
}
