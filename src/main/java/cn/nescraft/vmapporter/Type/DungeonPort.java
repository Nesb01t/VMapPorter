package cn.nescraft.vmapporter.Type;

import org.bukkit.Location;
import org.bukkit.entity.Player;

import java.util.List;

public class DungeonPort {
    public String name; // 检查点名字
    public Location location; // 固定坐标
    public List<Location> randomLocations; // 随机坐标

    public DungeonPort(String name, Location location, List<Location> randomLocations) {
        this.name = name;
        this.location = location;
        this.randomLocations = randomLocations;
    }

    // 到达检查点的触发
    public void touchCallbackMethod(Player player) {

    }

    public void teleportTo(Player player) {
        // 优先随机传送，否则传送到固定坐标
        if (randomLocations.isEmpty()) {
            player.teleport(location);
        } else {
            player.teleport(randomLocations.get((int) (Math.random() * randomLocations.size())));
        }

        touchCallbackMethod(player);
    }
}
