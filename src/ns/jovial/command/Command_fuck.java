package ns.jovial.command;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import ns.jovial.*;

@CommandParameters(name="fuck", description="Slam the fuckhammer over people!", usage="/<command> <playerName>", permission="nno.fuck")
public class Command_fuck {
        public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args, boolean isConsole) {
        
            if (args.length != 1) {
                return false;
            }
            
            Player p = (Player) sender;
            Player t = Main.server.getPlayer(args[0]);
            
            if (t == null) {
                sender.sendMessage(Main.PLAYER_NOT_FOUND);
                return true;
            }
            
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> " + t + ", WHAT THE FUCK IS WRONG WITH YOU, YOU FUCKING DUMBASS??");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> YOU'RE ON FUCKING THIN ICE, YOU HEAR ME??");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> I WILL RIP YOUR ENTIRE ASS APART, YOU'RE CLOSE TO A PERM BAN!");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> I WILL RIP ALL YOUR ORGANS OUT. YOU FUCKING IGNORANT DICK-SUCKING RETARD");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> IF YOU DO THAT SHIT ONE MORE FUCKING TIME, IT'S PERM BAN FUCKING BITCH!!!");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> I BET YOU'RE NOT EVEN LISTENING, YOU PIECE OF FUCKING SHIT! IF YOU DO THAT SHIT AGAIN, IT'S PERM BAN!!");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> YOU'RE ON MOTHERFUCKING THIN ICE.");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> IF YOU DO THAT SHIT AGAIN, IT'S PERM BAN!");
            Bukkit.broadcastMessage(ChatColor.WHITE + "<" + ChatColor.RED + "Server" + ChatColor.WHITE + "> NOW GET YOUR FUCKING SHIT TOGETHER, YOU IGNORANT ASSHOLE.");
            
            t.setHealth(0.0);
            
            for (int i = 0; i < 100; i++) {
            t.getWorld().strikeLightning(t.getLocation());
        }
            t.setFireTicks(10000);
            
            return true;
        }
        
}

