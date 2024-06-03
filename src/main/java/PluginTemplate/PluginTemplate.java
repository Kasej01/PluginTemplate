package plugintemplate;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.configuration.ConfigurationSection;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
import java.util.UUID;

public class PluginTemplate extends JavaPlugin {

    @Override
    public void onEnable() {
        saveDefaultConfig();  // Save the default config from resources if not present
        reloadConfig();  // Load the configuration

        // Setup commands and other initialization
        this.getCommand("commandName").setExecutor(new SetHomeCommand());
        getLogger().info("Plugin Enabled");
    }

    @Override
    public void onDisable() {
        saveHomes();
        getLogger().info("PluginTemplate Disabled");
    }
    
    private class PluginTemplate implements CommandExecutor {
        @Override
        public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
            if (sender instanceof Player) {
                Player player = (Player) sender;
            } else {
                sender.sendMessage(ChatColor.RED + "This command can only be used by players.");
                return false;
            }
        }
    }

        private void delHomeFromConfig(UUID playerId, String homeName) {
            // Check if the player's homes exist in the config
            if (homesConfig.contains(playerId.toString() + "." + homeName)) {
                // Remove the specific home entry from the configuration
                homesConfig.set(playerId.toString() + "." + homeName, null);
                // Save the config to file
                try {
                    homesConfig.save(homesFile);
                    getLogger().info("Updated homes configuration file.");
                } catch (IOException e) {
                    getLogger().severe("Could not save the homes configuration file: " + e.getMessage());
                }
            }
        }


}
