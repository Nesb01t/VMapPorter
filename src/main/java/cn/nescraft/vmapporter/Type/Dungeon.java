package cn.nescraft.vmapporter.Type;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import java.util.HashMap;
import java.util.Map;

@SerializableAs("Dungeon")
public final class Dungeon implements ConfigurationSerializable {
    public String name; // 副本的显示名字
    public boolean isEnable; // 是否启用
    public boolean isFriendly; // 是否为友善地区, 友善地区只有 1 层

    public Map<String, DungeonPort> layerOne;
    public Map<String, DungeonPort> layerTwo;
    public Map<String, DungeonPort> layerThree;

    public Dungeon(String name, boolean isEnable, boolean isFriendly, Map<String, DungeonPort> layerOne, Map<String, DungeonPort> layerTwo, Map<String, DungeonPort> layerThree) {
        this.name = name;
        this.isEnable = isEnable;
        this.isFriendly = isFriendly;
        this.layerOne = layerOne;
        this.layerTwo = layerTwo;
        this.layerThree = layerThree;
    }

    public static Dungeon deserialize(Map<String, Object> map) {
        return new Dungeon(
                (String) map.get("name"),
                (boolean) map.get("isEnable"),
                (boolean) map.get("isFriendly"),
                (Map<String, DungeonPort>) map.get("layerOne"),
                (Map<String, DungeonPort>) map.get("layerTwo"),
                (Map<String, DungeonPort>) map.get("layerThree")
        );
    }

    public Map<String, DungeonPort> getLayer(int layer) {
        switch (layer) {
            case 1:
                return layerOne;
            case 2:
                return layerTwo;
            case 3:
                return layerThree;
            default:
                return null;
        }
    }

    @Override
    public Map<String, Object> serialize() {
        Map<String, Object> map = new HashMap<>();
        map.put("name", name);
        map.put("isEnable", isEnable);
        map.put("isFriendly", isFriendly);
        map.put("layerOne", layerOne);
        map.put("layerTwo", layerTwo);
        map.put("layerThree", layerThree);
        return map;
    }
}
