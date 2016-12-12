package ns.jovial.command;

import ns.jovial.*;
import static ns.jovial.Main.plugin;
import org.bukkit.Bukkit;
import org.bukkit.BanList;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.GameMode;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.util.Vector;

@CommandParameters(name="oblivion", description="Cast oblivion over someone naughty.", usage="/<command> <playerName>", permission="nno.oblivion", aliases="obv")
public class Command_oblivion {
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args, boolean isConsole) {
        
        if (args.length != 1) {
            return false;
        }
        
        Player p = (Player) sender;
        Player t = Main.server.getPlayer(args[0]);
        
        if (t == null)
        {
            sender.sendMessage(Main.PLAYER_NOT_FOUND);
            return true;
        }
        
        p.chat("Hey " + t.getName() + ", I have a present for you, would you like to see it? :)");
        t.chat("Sure, what is it?");
        
        new BukkitRunnable() {
            @Override
            public void run() {
                p.chat("It's.... OBLIVION!");
                
                for (Player player : Bukkit.getOnlinePlayers()) {
                    player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 100, 2);
                }
            }
        }.runTaskLater(plugin, 1L * 20L);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                Bukkit.broadcastMessage(ChatColor.DARK_RED + p.getName() + " - Casting a complete dark, fiery shadow of oblivion over " + t.getName());
                Bukkit.broadcastMessage(ChatColor.DARK_PURPLE + t.getName() + " will be completely holy obliviated by " + p.getName() + "'s dark, fiery power!");
                
            }
        }.runTaskLater(plugin, 2L * 20L);
        
        return true;
    }
}
