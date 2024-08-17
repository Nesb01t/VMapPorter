package cn.nescraft.vmapporter.Utils;

import cn.nescraft.vmapporter.Type.Dungeon;
import cn.nescraft.vmapporter.Type.DungeonPort;
import cn.nescraft.vmapporter.VMapPorter;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import javax.annotation.Nullable;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DungeonUtils {
    public static Dungeon getDungeonByName(String name) {
        File file = new File(VMapPorter.instance.getDataFolder(), "dungeon-location.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        return configuration.getObject(name, Dungeon.class);
    }

    public static Map<String, Object> getAllDungeons() {
        File file = new File(VMapPorter.instance.getDataFolder(), "dungeon-location.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        return configuration.getValues(false);
    }

    public static void setDungeonPort(String dunName, String locName, int layer, Location location) throws IOException {
        File file = new File(VMapPorter.instance.getDataFolder(), "dungeon-location.yml");
        FileConfiguration configuration = YamlConfiguration.loadConfiguration(file);

        Dungeon dungeon = getDungeonByName(dunName);
        if (dungeon == null) {
            Bukkit.broadcastMessage("Dungeon 不存在, 创建新的 Dungeon: " + dunName);
            Map<String, DungeonPort> layer1 = new HashMap<>();
            Map<String, DungeonPort> layer2 = new HashMap<>();
            Map<String, DungeonPort> layer3 = new HashMap<>();

            // 初始化 dungeon 位置
            if (layer == 1) {
                layer1.put(locName, new DungeonPort(locName, location, new ArrayList<>()));
            } else if (layer == 2) {
                layer2.put(locName, new DungeonPort(locName, location, new ArrayList<>()));
            } else if (layer == 3) {
                layer3.put(locName, new DungeonPort(locName, location, new ArrayList<>()));
            }

            dungeon = new Dungeon(dunName, true, false, layer1, layer2, layer3);
            configuration.set(dunName, dungeon);
            configuration.save(file);
            return;
        }

        // 添加新的 location, 如果 location 存在就加到 random 里面
        Map<String, DungeonPort> layerPortMap = getLayerByIdx(dungeon, layer);
        if (layerPortMap != null) {
            Bukkit.broadcastMessage("Layer: " + layer);
            if (layerPortMap.containsKey(locName)) {
                Bukkit.broadcastMessage("Location: " + locName + " 已存在, 添加到 random 里面");
                putLocationIntoPort(layerPortMap.get(locName), location);
            } else {
                Bukkit.broadcastMessage("Location: " + locName + " 不存在, 创建新的 Location");
                layerPortMap.put(locName, new DungeonPort(locName, location, new ArrayList<>()));
            }
        }

        configuration.set(dunName, dungeon);
        configuration.save(file);
    }

    protected static void putLocationIntoPort(@Nullable DungeonPort port, Location location) {
        if (port == null) {
            return;
        }

        port.randomLocations.add(location);
    }

    protected static Map<String, DungeonPort> getLayerByIdx(Dungeon dungeon, int idx) {
        switch (idx) {
            case 1:
                return dungeon.layerOne;
            case 2:
                return dungeon.layerTwo;
            case 3:
                return dungeon.layerThree;
            default:
                return null;
        }
    }
}
