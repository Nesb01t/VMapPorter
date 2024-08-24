package cn.nescraft.vmapporter.Gui;

import cn.nescraft.vmapporter.Type.Dungeon;
import cn.nescraft.vmapporter.Utils.DungeonUtils;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * AdminPanel 穷举所有 DungeonPort, 用来快捷传送调试
 */
public class AdminPanelUI {
    public final static String TITLE = "[ADMIN ONLY] 副本列表";

    public static void openAdminPanel(Player player) {
        Inventory inv = Bukkit.createInventory(player, 54, TITLE);

        Map<String, Object> dungeons = DungeonUtils.getAllDungeons();

        Bukkit.broadcastMessage("dungeons: " + dungeons.size());

        for (String key : dungeons.keySet()) {
            Bukkit.broadcastMessage(key);
            ItemStack item = generateDungeonIntroItem((Dungeon) dungeons.get(key));

            inv.addItem(item);
        }

        player.openInventory(inv);
    }

    public static ItemStack generateDungeonIntroItem(Dungeon dungeon) {
        ItemStack item = new ItemStack(Material.ENDER_PEARL);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName(dungeon.name);

        // 设置 lore
        meta.setLore(getLayerLore(dungeon));

        item.setItemMeta(meta);
        return item;
    }

    protected static List<String> getLayerLore(Dungeon dungeon) {
        List<String> lores = new ArrayList<>();
        for (int i = 1; i <= 3; i++) {
            lores.add("第 " + i + " 层: ");
            lores.add(" " + DungeonUtils.getLayerPlaceNum(dungeon.getLayer(i)) + " 个子副本");
            lores.add(" " + DungeonUtils.getLayerPlaceLocationSum(dungeon.getLayer(i)) + " 个传送点");
        }
        return lores;
    }
}
