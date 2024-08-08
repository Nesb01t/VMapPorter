package cn.nescraft.vmapporter.Gui;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

/**
 * DungeonUI 显示几个副本
 */
public class DungeonUI {
    public final static String TITLE = "世界镜面板";

    public static void OpenDungeonUI(Player player) {
        Inventory inv = Bukkit.createInventory(player, 9, TITLE);

        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        if (meta != null) {
            meta.setDisplayName("传送到地牢");
        }
        item.setItemMeta(meta);

        inv.setItem(4, item);

        player.openInventory(inv);
    }
}
