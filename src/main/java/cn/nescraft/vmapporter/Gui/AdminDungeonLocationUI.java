package cn.nescraft.vmapporter.Gui;

import cn.nescraft.vmapporter.Type.Dungeon;
import cn.nescraft.vmapporter.Type.DungeonPort;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

public class AdminDungeonLocationUI {
    public final static String TITLE = "[ADMIN ONLY] 副本列表";

    public static void openAdminDungeonSelectUI(Player player, Dungeon dungeon) {
        Inventory inv = Bukkit.createInventory(player, 27, TITLE);

        int invIdx = 0;
        for (int i = 0; i < 3; i++) {
            if (invIdx < i * 9) {
                invIdx = i * 9;
            }

            Map<String, DungeonPort> layer = dungeon.getLayer(i + 1);
            if (layer == null) {
                continue;
            }

            for (String key : layer.keySet()) {
                inv.setItem(invIdx++, generateDungeonPortItem(layer.get(key)));
            }
        }

        player.openInventory(inv);
    }

    protected static ItemStack generateDungeonPortItem(DungeonPort port) {
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(port.name);
        item.setItemMeta(meta);
        return item;
    }
}
