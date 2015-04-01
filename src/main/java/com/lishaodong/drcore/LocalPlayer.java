package com.lishaodong.drcore;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.player.alignment.Alignment;
import com.lishaodong.drcore.player.alignment.AlignmentSystem;
import com.lishaodong.drcore.player.energy.EnergySystem;
import com.lishaodong.drcore.player.health.HealthSystem;

@SerializableAs("LocalPlayer")
public class LocalPlayer implements ConfigurationSerializable {
	public Player player;
	//Attribute Systems
	public HealthSystem health = new HealthSystem(this);
	public EnergySystem energy = new EnergySystem(this);
	public AlignmentSystem alignment = new AlignmentSystem(this);
	
	public DRCore plugin;
	public String name;
	
	// regenerate each second
	public String currentZone = "none";

	public LocalPlayer(Player player, DRCore plugin) {
		player.setMaxHealth(50);
		this.plugin = plugin;
		this.name = player.getName();
		this.player = player;
	}
	
	public LocalPlayer(Map<String, Object> map) {
		plugin=(DRCore) Bukkit.getPluginManager().getPlugin("DRCore");
		name = (String) map.get("name");
		health = (HealthSystem) map.get("health");
		health.localPlayer=this;
		energy = (EnergySystem) map.get("energy");
		energy.localPlayer=this;
		alignment = (AlignmentSystem) map.get("alignment");
		alignment.localPlayer=this;
	}

	public LocalPlayer() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Object> serialize() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("name", name);
		map.put("health", health);
		map.put("energy", energy);
		map.put("alignment",alignment);
		return map;
	}

	public void setCurrentZone(String type) {
		switch (type.toLowerCase()) {
		case "safe":
			energy.canLoseEnergy = false;
			//setAlignment(Alignment.LAWFUL);
			break;
		case "wilderness":
			energy.canLoseEnergy = true;
			break;
		case "choatic":
			energy.canLoseEnergy = true;
			break;
		case "none":
			energy.canLoseEnergy = true;
			break;
		default:
			break;
		}
	}

	public void gotDamaged() {
		plugin.taskManager.resetRegenHealthTask(this);
	}
	
	public void enterWorld(){
		plugin.taskManager.resetRegenHealthTask(this);
		plugin.taskManager.setEnergyTask(this);

		player.setHealthScale(20);
		player.setExp(1);
	}

	public void dead() {
		plugin.logger.info(name+" dead when he is in "+alignment.alignment +" alignment.");
	}
}
