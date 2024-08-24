package cn.nescraft.vmapporter.Event;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerChatEvent;

public class PlugmanReloadSelfListener implements Listener {
    @EventHandler
    public void onAdminSayR(PlayerChatEvent event) {
        if (event.getMessage().equals("r")) {
            event.getPlayer().sendMessage("重载插件中...");
            event.getPlayer().performCommand("plugman reload vMapPorter");
            event.setCancelled(true);
        }
    }
}
