package ns.jovial.command;

import ns.jovial.Main;
import org.bukkit.*;
import org.bukkit.command.*;
import org.bukkit.entity.Player;

@CommandParameters(name="nope", description="Nope out of the server.", usage="/<command>", permission="nno.nope")
public class Command_nope {
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args, boolean isConsole) {
        if (isConsole) {
            sender.sendMessage(Main.NOT_FROM_CONSOLE);
        }
        
        Player p = (Player) sender;
        
        Main.server.broadcastMessage(ChatColor.RED + sender.getName() + " has noped the fuck out of the server!");
        p.kickPlayer(ChatColor.RED + "NOPE!");
        
        return true;
    }
}
