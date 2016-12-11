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
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.scheduler.BukkitRunnable;
import org.bukkit.scheduler.BukkitTask;
import org.bukkit.util.Vector;

@CommandParameters(name="superdoom", description="When a normal doom isn't enough. SUPER doom!", usage="/<command> <playerName>", permission="nno.superdoom")
public class Command_superdoom {
    /* left these in here for now
    private BanList = b1;
    private BanList = b12;
    */
    
    public boolean onCommand(CommandSender sender, Command cmd, String lbl, String[] args, boolean isConsole) {
        
        int x = 0;
        
        if (args.length != 1) {
            return false;
        }
        
        Player p = (Player) sender;
        Player t = Main.server.getPlayer(args[0]);
        
        if (t == null) {
            sender.sendMessage(Main.PLAYER_NOT_FOUND);
            return true;
        }
        
        Bukkit.broadcastMessage(ChatColor.DARK_RED + p.getName() + " - Superdooming " + t.getName() + " to a never-ending oblivion in the deepest burning pits of hell");
        Bukkit.broadcastMessage(ChatColor.RED + t.getName() + " shall be sent to mars!");
        
        do {
            Bukkit.getOnlinePlayers().stream().map((player) -> {
                player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 100, 2);
                return player;
            }).map((player) -> {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100, 2);
                return player;
            }).forEachOrdered((player) -> {
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 100, 2);
            });
            
            x++;
        } while (x <= 100);
        
        
        // close inventory
        t.closeInventory();
        
        // create explosion
        t.getWorld().createExplosion(t.getLocation(), 4F);
        
        // strike lightning
        t.getWorld().strikeLightning(t.getLocation());
        
        // kill player
        t.setHealth(0.0);
        
        // ignite player
        t.setFireTicks(10000);
        
        // shoot the player different directions
        t.setVelocity(t.getVelocity().clone().add(new Vector(10, 30, 10)));
        
        // change gamemode
        t.setGameMode(GameMode.SURVIVAL);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                
            }
        }.runTaskLater(plugin, 2L * 20L);
        
        
        return true;
    }
}
