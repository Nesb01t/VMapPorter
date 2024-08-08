package cn.nescraft.vmapporter.Service;

import org.bukkit.Location;
import org.bukkit.entity.Player;

public class TeleportService {
    public static void TeleportPlayerToLocation(Player player, Location location) {
        player.teleport(location);
    }
}
