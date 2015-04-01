package com.lishaodong.drcore.player.zone;

import java.util.Map;
import java.util.TreeMap;

import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.player.AttributeSystem;
import com.lishaodong.drcore.player.alignment.Alignment;

public class ZoneSystem extends AttributeSystem{
	public Zone zone;
	
	public ZoneSystem(LocalPlayer localPlayer) {
		super(localPlayer);
		// TODO Auto-generated constructor stub
	}
	public ZoneSystem(Map<String, Object> map) {
		super(null);
		zone = Zone.valueOf((String) map.get("zone"));
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("zone", zone.name());
		return map;
	}
	
	
}