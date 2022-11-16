package net.spacedvoid;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;
import org.bukkit.inventory.ItemStack;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class PlayerKillListener implements Listener {
	private final List<Material> ITEMS;
	private final boolean IS_INCLUDING;

	public PlayerKillListener(List<String> items, boolean isIncluding) {
		this.ITEMS = items.stream().map(string -> {
			Material material = Material.matchMaterial(string);
			if(material == null)
				Bukkit.getLogger().warning("[DropModifier] Cannot match material \"" + string + "\"");
			return Optional.ofNullable(material);
		}).filter(Optional::isPresent).map(Optional::get).toList();
		this.IS_INCLUDING = isIncluding;
	}

	@EventHandler
	public void onPlayerKill(PlayerDeathEvent event) {
		event.setKeepInventory(false);
		Stream<ItemStack> drops = event.getDrops().stream().filter(Objects::nonNull);
		if(IS_INCLUDING) drops = drops.filter(itemStack -> !ITEMS.contains(itemStack.getType()));
		else drops = drops.filter(itemStack -> ITEMS.contains(itemStack.getType()));
		event.getDrops().removeAll(drops.toList());
	}
}
