package com.lishaodong.drcore.player.energy;

import java.util.Map;
import java.util.TreeMap;

import org.bukkit.Bukkit;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.entity.Player;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.player.AttributeSystem;

public class EnergySystem extends AttributeSystem{

	public static final int RUN_LOSE = 25;
	public static final int RUN_GAP = 10;

	public static final int REGEN_GAP = 4;

	public static final float PERCENT = (float) 1.0 / 100 / 20;
	public static final float RUN = RUN_LOSE * PERCENT * RUN_GAP;
	public static final float REGEN = PERCENT * REGEN_GAP;

	public int regenSpeed = 10;
	public int maxEnergy = 100;


	public boolean locked = false;
	public boolean canLoseEnergy = true;
	
	public EnergySystem(LocalPlayer localPlayer) {
		super(localPlayer);
	}
	
	public EnergySystem(Map<String, Object> map) {
		super(null);
		regenSpeed = (int) map.get("regenSpeed");
		maxEnergy = (Integer) map.get("maxEnergy");
	}

	public void changeEnergy(float change) {
		float current = localPlayer.player.getExp() + change;
		if (current <= 0) {
			Bukkit.getLogger().info("set sprint false");
			localPlayer.player.setSprinting(false);
			lockEnergyRegen();
			current = 0;
		}
		if (current > 1)
			current = 1;
		localPlayer.player.setExp(current);
	}
	public boolean canLoseEnergy() {
		// TODO Auto-generated method stub
		return canLoseEnergy;
	}
	

	public void lockEnergyRegen() {
		locked = true;
		localPlayer.plugin.logger.info("lock energy regen for "+localPlayer.name);
		localPlayer.plugin.scheduler.scheduleSyncDelayedTask(localPlayer.plugin, new Runnable() {
			public void run() {
				localPlayer.plugin.logger.info("unlock energy regen");
				locked = false;
			}
		}, 40L);
	}
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("regenSpeed", regenSpeed);
		map.put("maxEnergy", maxEnergy);
		return map;
	}
}
