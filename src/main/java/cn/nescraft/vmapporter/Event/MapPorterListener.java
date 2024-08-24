package cn.nescraft.vmapporter.Event;

import cn.nescraft.vmapporter.Gui.AdminDungeonLocationUI;
import cn.nescraft.vmapporter.Gui.AdminPanelUI;
import cn.nescraft.vmapporter.Gui.DungeonUI;
import cn.nescraft.vmapporter.Utils.DungeonUtils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MapPorterListener implements Listener {
    @EventHandler
    public void onPortPanelClicked(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if (title.contains(DungeonUI.TITLE) || title.contains(AdminPanelUI.TITLE) || title.contains(AdminDungeonLocationUI.TITLE)) {
            event.setCancelled(true);
        }
    }

    @EventHandler
    public void onAdminDungeonClicked(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        if (!player.isOp()) {
            return;
        }

        String title = event.getView().getTitle();
        if (title.contains(AdminPanelUI.TITLE)) {
            String dunName = event.getCurrentItem().getItemMeta().getDisplayName();
            DungeonUtils.getDungeonByName(dunName);
            player.closeInventory();
            AdminDungeonLocationUI.openAdminDungeonSelectUI(player, DungeonUtils.getDungeonByName(dunName));
            event.setCancelled(true);
        }
    }
}
