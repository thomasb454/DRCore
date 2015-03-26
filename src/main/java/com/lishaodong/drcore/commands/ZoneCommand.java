package com.lishaodong.drcore.commands;

import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.zone.ZoneManager;
import com.sk89q.worldedit.regions.Region;
import com.sk89q.worldguard.bukkit.RegionContainer;
import com.sk89q.worldguard.bukkit.WGBukkit;
import com.sk89q.worldguard.bukkit.WorldGuardPlugin;
import com.sk89q.worldguard.protection.flags.StateFlag.State;
import com.sk89q.worldguard.protection.flags.*;
import com.sk89q.worldguard.protection.managers.RegionManager;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class ZoneCommand implements CommandExecutor{
	ZoneManager manager;
	
	public ZoneCommand(ZoneManager manager) {
		super();
		this.manager = manager;
	}


	public boolean onCommand(CommandSender sender,
			Command cmnd, String alias, String[] arg) {
		// TODO Auto-generated method stub
		
		if(arg[0].equals("reload")){
			manager.plugin.reloadConfig();
			manager.loadConfig();
			sender.sendMessage("Config of Zone Plugin has been reloaded");
			return true;
		}
		if(arg.length!=2){
			sender.sendMessage("format of command should be \"/zone <region name> safe|chaotic|wilderness\"");
			return false;
		}
		ProtectedRegion region = manager.getRegion(arg[0], "world"); 
		return setFlags(region, arg[1]);
	}

	private boolean setFlags(ProtectedRegion region, String type) {
		if(type.equals("safe")||type.equals("chaotic")||type.equals("wilderness")){
			manager.typeOfRegions.put(region.getId(), type);
			if(manager.config==null)
				return false;
			Map<String, Object> map = manager.config.getConfigurationSection(type).getValues(false);
			Class<DefaultFlag> c = DefaultFlag.class;
			try {
				for(String key:map.keySet()){
					Object flag = c.getField(key).get(null);
					if(flag instanceof StringFlag){
						region.setFlag((StringFlag) flag, (String) map.get(key));
					}else if(flag instanceof IntegerFlag){
						region.setFlag((IntegerFlag) flag, (Integer) map.get(key));
					}else if(flag instanceof StateFlag){
						region.setFlag((StateFlag) flag, b2s((Boolean) map.get(key)));
					}else if(flag instanceof BooleanFlag){
						region.setFlag((BooleanFlag) flag, (Boolean) map.get(key));
					}else if(flag instanceof DoubleFlag){
						region.setFlag((DoubleFlag) flag, (Double) map.get(key));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			return true;
		}
		return false;
	}
	private static State b2s(boolean b){
		return b?StateFlag.State.ALLOW:StateFlag.State.DENY;		
	}
}
