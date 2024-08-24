package cn.nescraft.vmapporter.Type;

import org.bukkit.Location;
import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.Map;

// 子副本，副本的港口
@SerializableAs("DungeonPort")
public final class DungeonPort implements ConfigurationSerializable {
    public String name; // 检查点名字
    public Location location; // 固定坐标
    public List<Location> randomLocations; // 随机坐标

    public DungeonPort(String name, Location location, List<Location> randomLocations) {
        this.name = name;
        this.location = location;
        this.randomLocations = randomLocations;
    }

    public static DungeonPort deserialize(Map<String, Object> map) {
        return new DungeonPort(
                (String) map.get("name"),
                (Location) map.get("location"),
                (List<Location>) map.get("randomLocations")
        );
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

    @Override
    public Map<String, Object> serialize() {
        return Map.of(
                "name", name,
                "location", location,
                "randomLocations", randomLocations
        );
    }
}
