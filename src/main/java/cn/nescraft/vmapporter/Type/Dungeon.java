package cn.nescraft.vmapporter.Type;

import java.util.List;

public class Dungeon {
    public String name; // 副本的显示名字
    public boolean isEnable; // 是否启用
    public boolean isFriendly; // 是否为友善地区, 友善地区只有 1 层

    public List<DungeonPort> layerOne;
    public List<DungeonPort> layerTwo;
    public List<DungeonPort> layerThree;


}
