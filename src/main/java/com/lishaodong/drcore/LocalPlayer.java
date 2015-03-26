package com.lishaodong.drcore;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.energy.EnergySystem;
import com.lishaodong.drcore.health.HealthSystem;

@SerializableAs("LocalPlayer")
public class LocalPlayer implements ConfigurationSerializable {
	public Player player;
	
	public HealthSystem health = new HealthSystem(this);
	public EnergySystem energy = new EnergySystem(this);
	
	public DRCore plugin;
	public String name;
	public Alignment alignment = Alignment.NONE;
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
	}

	public LocalPlayer() {
		// TODO Auto-generated constructor stub
	}

	public Map<String, Object> serialize() {

		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("name", name);
		map.put("health", health);
		return map;
	}


	

	public void setCurrentZone(String type) {
		switch (type.toLowerCase()) {
		case "safe":
			energy.canLoseEnergy = false;
			setAlignment(Alignment.LAWFUL);
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
	public void setAlignment(Alignment alignment){
		this.alignment = alignment;
		player.sendMessage(plugin.getConfig().getString("alignment."+alignment.name+".GREETING_MESSAGE"));
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
}
