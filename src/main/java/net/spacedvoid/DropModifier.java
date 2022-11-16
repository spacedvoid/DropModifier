package net.spacedvoid;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public final class DropModifier extends JavaPlugin {
	private List<String> itemList;
	private boolean isIncluding;

	@Override
	public void onEnable() {
		if(config()) {
			Bukkit.getPluginManager().disablePlugin(this);
			return;
		}
		Bukkit.getPluginManager().registerEvents(new PlayerKillListener(itemList, isIncluding), this);
	}

	private boolean config() {
		if(Files.notExists(Path.of(getDataFolder().getAbsolutePath(), "config.yml"))) {
			saveDefaultConfig();
			return config();
		}
		List<String> included = getConfig().getStringList("included");
		List<String> excluded = getConfig().getStringList("excluded");
		if(included.size() != 0 && excluded.size() == 0) {
			itemList = included;
			isIncluding = true;
			return false;
		}
		else if(included.size() == 0 && excluded.size() != 0) {
			itemList = excluded;
			isIncluding = false;
			return false;
		}
		else {
			Bukkit.getLogger().warning("[DropModifier] Only one of either \"included\" or \"excluded\" item list should be provided");
			return true;
		}
	}
}
