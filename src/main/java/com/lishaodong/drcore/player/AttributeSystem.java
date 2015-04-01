package com.lishaodong.drcore.player;

import org.bukkit.configuration.serialization.ConfigurationSerializable;

import com.lishaodong.drcore.LocalPlayer;

public abstract class AttributeSystem implements ConfigurationSerializable{
	public LocalPlayer localPlayer;
	
	public AttributeSystem(LocalPlayer localPlayer) {
		this.localPlayer = localPlayer;
	}
}
