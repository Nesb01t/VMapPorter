package cn.nescraft.vmapporter;

import cn.nescraft.vmapporter.Event.MapPorterListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VMapPorter extends JavaPlugin {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(new MapPorterListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
