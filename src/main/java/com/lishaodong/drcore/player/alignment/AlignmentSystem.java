package com.lishaodong.drcore.player.alignment;

import java.util.Map;
import java.util.TreeMap;

import com.lishaodong.drcore.LocalPlayer;
import com.lishaodong.drcore.player.AttributeSystem;

public class AlignmentSystem extends AttributeSystem{
	public Alignment alignment = Alignment.LAWFUL;
	
	public AlignmentSystem(LocalPlayer localPlayer) {
		super(localPlayer);
		// TODO Auto-generated constructor stub
	}
	public AlignmentSystem(Map<String, Object> map) {
		super(null);
		alignment = Alignment.valueOf((String) map.get("alignment"));
	}
	
	@Override
	public Map<String, Object> serialize() {
		Map<String, Object> map = new TreeMap<String, Object>();
		map.put("alignment", alignment.name());
		return map;
	}


	public void setAlignment(Alignment alignment){
		this.alignment = alignment;
		localPlayer.player.sendMessage(localPlayer.plugin.playerManager.config.getString("alignment."+alignment+".GREETING_MESSAGE"));
	}
}
