package cn.nescraft.vmapporter.Gui;

import cn.nescraft.vmapporter.Utils.DungeonUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Map;

/**
 * AdminPanel 穷举所有 DungeonPort, 用来快捷传送调试
 */
public class AdminPanelUI {
    public final static String TITLE = "管理员传送点";

    public static void openAdminPanel(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, TITLE);

        Map<String, Object> dungeons = DungeonUtils.getAllDungeons();

        for (String key : dungeons.keySet()) {
            Bukkit.broadcastMessage(key);
            ItemStack item = new ItemStack(Material.ENDER_PEARL);
            ItemMeta meta = item.getItemMeta();
            meta.setDisplayName(key);
            item.setItemMeta(meta);
            inv.addItem(item);
        }

        player.openInventory(inv);
    }
}
