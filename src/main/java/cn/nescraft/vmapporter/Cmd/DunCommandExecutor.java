package cn.nescraft.vmapporter.Cmd;

import cn.nescraft.vmapporter.Gui.AdminPanelUI;
import cn.nescraft.vmapporter.Gui.DungeonUI;
import cn.nescraft.vmapporter.Service.DungeonProgressService;
import cn.nescraft.vmapporter.Utils.DungeonUtils;
import cn.nescraft.vmapporter.VMapPorter;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.io.IOException;

/**
 * /dun 打开地牢界面
 * /dun <name> 前往地牢
 * /dun admin 打开管理员传送界面
 * /dun set <name> <layer> <locName> 设置玩家当前位置为地牢点位, layer 为 1~3, locName 为小副本名字
 */
public class DunCommandExecutor implements CommandExecutor {
    private final VMapPorter plugin;

    public DunCommandExecutor(VMapPorter plugin) {
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String s, String[] strings) {
        if (!(sender instanceof Player)) {
            sender.sendMessage("此命令只能由玩家执行。");
            return false;
        }

        Player player = (Player) sender;
        player.sendMessage("你执行了 /dun 指令！");

        if (strings.length == 0) {
            DungeonUI.OpenDungeonUI(player);
            return true;
        }

        if (strings.length == 1) {
            if (strings[0].equalsIgnoreCase("set")) {
                player.sendMessage("请指定地牢名称和层数。");
                return false;
            }

            if (strings[0].equalsIgnoreCase("admin")) {
                // 打开管理员传送界面
                AdminPanelUI.openAdminPanel(player);
                return true;
            }

            // 前往地牢
            DungeonProgressService.launchDungeon(strings[0], player);
        }

        if (strings.length == 4) {
            if (!strings[0].equalsIgnoreCase("set")) {
                player.sendMessage("请指定地牢名称和层数。");
                return false;
            }

            // 设置玩家当前位置为地牢点位
            try {
                DungeonUtils.setDungeonPort(strings[1], strings[3], Integer.parseInt(strings[2]), player.getLocation());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return false;
    }
}
