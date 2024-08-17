package cn.nescraft.vmapporter;

import cn.nescraft.vmapporter.Cmd.DunCommandExecutor;
import cn.nescraft.vmapporter.Event.MapPorterListener;
import org.bukkit.plugin.java.JavaPlugin;

public final class VMapPorter extends JavaPlugin {

    public static VMapPorter instance;

    @Override
    public void onEnable() {
        instance = this;
        this.getCommand("dun").setExecutor(new DunCommandExecutor(this));
        getServer().getPluginManager().registerEvents(new MapPorterListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
