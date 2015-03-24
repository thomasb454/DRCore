package com.lishaodong.drcore.health;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.inventory.ItemStack;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.energy.RegenerateEnergyTask;

public class HealthListener implements Listener {
	DRCore plugin;

	public HealthListener(DRCore plugin) {
		super();
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void entityDamage(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		Entity defender = event.getEntity();
		Player player;
		if (damager instanceof Player) {
			resetHealthTask((Player) damager);
		}
	}

	@EventHandler(priority = EventPriority.HIGHEST)
	public void onEntityRegainHealth(EntityRegainHealthEvent event) {
		if (!(event.getEntity() instanceof Player))
			return;
		event.setAmount(0.0);
	}

	@EventHandler(priority = EventPriority.HIGH)
	public void entityDamage(EntityDamageEvent event) {
		Entity defender = event.getEntity();
		Player player;
		if (defender instanceof Player) {
			player = (Player) defender;

			resetHealthTask(player);
			if (event.getCause() == DamageCause.FALL) {
				double health = player.getHealth();
				if (event.getDamage() >= health)
					event.setDamage(health - 1);
			}
		}
	}

	public void resetHealthTask(Player player) {
		plugin.resetHealthTask();
	}
}