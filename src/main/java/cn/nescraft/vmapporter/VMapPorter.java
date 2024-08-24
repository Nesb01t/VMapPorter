package cn.nescraft.vmapporter;

import cn.nescraft.vmapporter.Cmd.DunCommandExecutor;
import cn.nescraft.vmapporter.Event.MapPorterListener;
import cn.nescraft.vmapporter.Event.PlugmanReloadSelfListener;
import cn.nescraft.vmapporter.Type.Dungeon;
import cn.nescraft.vmapporter.Type.DungeonPort;
import cn.nescraft.vutils.VLogger;
import org.bukkit.configuration.serialization.ConfigurationSerialization;
import org.bukkit.plugin.java.JavaPlugin;

public final class VMapPorter extends JavaPlugin {

    public static VMapPorter instance;

    static {
        ConfigurationSerialization.registerClass(Dungeon.class, "Dungeon");
        ConfigurationSerialization.registerClass(DungeonPort.class, "DungeonPort");
    }

    public static String getFolderPath() {
        return "plugins/VMapPorter";
    }

    @Override
    public void onEnable() {
        instance = this;

        VLogger.info("VMapPorter is enabled.");
        this.getCommand("dun").setExecutor(new DunCommandExecutor(this));
        getServer().getPluginManager().registerEvents(new MapPorterListener(), this);
        getServer().getPluginManager().registerEvents(new PlugmanReloadSelfListener(), this);
    }

    @Override
    public void onDisable() {
    }
}
