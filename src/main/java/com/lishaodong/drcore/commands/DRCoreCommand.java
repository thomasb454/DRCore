package com.lishaodong.drcore.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;

import com.lishaodong.drcore.DRCore;
import com.lishaodong.drcore.data.DRData;
import com.sk89q.worldguard.protection.regions.ProtectedRegion;

public class DRCoreCommand  implements CommandExecutor{
	DRCore plugin;
	
	public DRCoreCommand(DRCore core){
		this.plugin = core;
	}
	
	public boolean onCommand(CommandSender sender,
			Command cmnd, String alias, String[] arg) {
		// TODO Auto-generated method stub
		
		if(arg[0].equals("reload")){
			plugin.reloadConfig();
			sender.sendMessage("Config of DRCore Plugin has been reloaded");
			return true;
		}
		if(arg[0].equals("save")){
			DRData.saveAll();
			sender.sendMessage("All data have been saved");
			return true;
		}
		return true;
	}
}
