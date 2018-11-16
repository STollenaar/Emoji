package emoji.tollenaar.stephen.Emoji;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;


public class EMain extends JavaPlugin{
	private FileConfiguration config;
	
	//called when the plugin is enabled
	public void onEnable(){
		config = this.getConfig();
		config.options().copyDefaults(true);
		config.options().header("If a command has only 1 '/' don't type it. If it has 2 '/' only type 1. ");
		saveConfig();
		
		PluginManager pm = getServer().getPluginManager();
		pm.registerEvents(new PlayerChat(this), this);
	}
	
	
	//called when the plugin is disabled
	public void onDisable(){
		
	}
	
}
