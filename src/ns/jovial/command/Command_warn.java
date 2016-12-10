package ns.jovial.command;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;
import ns.jovial.*;

@CommandParameters(name="warn", description="Warn a player", usage="/<command> <playerName>", permission="nno.warn")
public class Command_warn {
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args, boolean isConsole) {
        if (args.length < 2) {
            return false;
        }
        Player p = (Player) sender;
        Player t = Main.server.getPlayer(args[0]);
        int n = 0;
        
        if (t == null) {
            p.sendMessage(Main.PLAYER_NOT_FOUND);
            return true;
        }
        
        if (!isConsole) {
            if (t.equals(p)) {
                p.sendMessage("Please, don't try to warn yourself.");
            }
        }
        
        String warnReason = StringUtils.join(ArrayUtils.subarray(args, 1, args.length), " ");
        n++;
        if (n == 3) {
            t.kickPlayer("You have been warned too many times!");
        }
        else {
            t.getWorld().strikeLightning(t.getLocation());
            t.sendMessage(ChatColor.RED + "[WARNING] You have received a warning: " + warnReason);
            t.sendMessage(ChatColor.RED + "Warning level " + n);
        }
        
        p.sendMessage(ChatColor.GREEN + "You have successfully warned " + t.getName());
        return true;
    }
}
