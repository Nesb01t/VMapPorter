package cn.nescraft.vmapporter.Event;

import cn.nescraft.vmapporter.Gui.AdminPanelUI;
import cn.nescraft.vmapporter.Gui.DungeonUI;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class MapPorterListener implements Listener {
    @EventHandler
    public void onPortPanelClicked(InventoryClickEvent event) {
        String title = event.getView().getTitle();
        if (title.contains(DungeonUI.TITLE) || title.contains(AdminPanelUI.TITLE)) {
            event.setCancelled(true);
        }
    }
}
