package ns.jovial.command;

import ns.jovial.Main;
import org.bukkit.command.*;
import org.bukkit.ChatColor;

@CommandParameters(name="nno", description="Plugin info.", usage="/<command>", permission="nno.nno")
public class Command_nno {
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args) {
        sender.sendMessage(ChatColor.GREEN 
                + "The " 
                + ChatColor.DARK_GRAY 
                + "No" 
                + ChatColor.AQUA 
                + "Named" 
                + ChatColor.DARK_GRAY 
                + "Org" 
                + ChatColor.GREEN 
                + "plugin was created by " 
                + ChatColor.BLUE + Main.plugin.pAuthors.toString() 
                + ChatColor.GREEN + " for the NoNamedOrg minecraft server.\n");
        sender.sendMessage(ChatColor.YELLOW + "The " + Main.plugin.pName + " plugin's current version is " 
                + Main.plugin.pVersion + ".");
        
        return true;
    }
}
