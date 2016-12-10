package ns.jovial.command;

import org.bukkit.*;
import org.bukkit.entity.Player;
import org.bukkit.command.*;
import ns.jovial.*;

@CommandParameters(name="cclr", description="Clear global chat, or just clear your own. (Clearing your own can be done with debug keys!)", usage="/<command> [self]", permission="nno.cclr")
public class Command_cclr {
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args, boolean isConsole) {        
        if (args.length == 1) {
            if (args[0].equalsIgnoreCase("self")) {
                if (isConsole) {
                    sender.sendMessage(Main.NOT_FROM_CONSOLE);
                    return true;
                }
                Player p = (Player) sender;
                int n = 0;
                do {
                    p.sendMessage("\n");
                    n++;
                } while(n <= 100);
                
                return true;
            }
        }
        int x = 0;
        if (isConsole) {
            do {
                Main.server.broadcastMessage("\n");
                x++;
            } while (x <= 100);
            return true;
        }
        //For global permission
        if (sender.hasPermission("nno.cclrglobal")) {
            do {
                Main.server.broadcastMessage("\n");
                x++;
            } while (x <= 100);
        } else { 
            sender.sendMessage(Main.MSG_NO_PERMS);
            return true;
        }
        
        return true;
    }
}
