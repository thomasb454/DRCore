package com.lishaodong.drcore.energy;

import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerToggleSprintEvent;
import org.bukkit.inventory.ItemStack;

import com.lishaodong.drcore.DRCore;

public class EnergyListener implements Listener {
	DRCore plugin;

	public EnergyListener(DRCore plugin) {
		super();
		this.plugin = plugin;
	}

	@EventHandler(priority = EventPriority.MONITOR)
	public void entityDamage(EntityDamageByEntityEvent event) {
		Entity damager = event.getDamager();
		if (damager instanceof Player) {
			Player player = (Player) damager;
			ItemStack stack = player.getInventory().getItemInHand();
			// set depend on tier of stack
			EnergySystem.changeEnergy(player, -0.08f);
		}
	}
}
