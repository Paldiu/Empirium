package ns.jovial.command;

import java.util.ArrayList;
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
import org.bukkit.util.Vector;


@CommandParameters(name="hell", description="Unleash hell upon a player!", usage="/<command> <playerName>", permission="nno.hell")
public class Command_hell {
    private BanList bl;
    private BanList bl2;
    
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
        
        Bukkit.broadcastMessage(ChatColor.DARK_RED + sender.getName() + " - Unleashing hell upon " + t.getName() + "!");
        Bukkit.broadcastMessage(ChatColor.RED + sender.getName() + " - Calling Satan to open the gates of hell for " + t.getName());
        Bukkit.broadcastMessage(ChatColor.DARK_RED + t.getName() + " shall be sent to the deepest burning pits of hell!");
        
        ItemStack hell = new ItemStack(Material.NETHERRACK, 2600);
        ItemMeta hellmeta = hell.getItemMeta();
        ArrayList<String> lorelist = new ArrayList<>();
        hellmeta.setDisplayName(ChatColor.DARK_RED + "" + ChatColor.BOLD + "HELL IS RISING UPON YOU");
        lorelist.add(ChatColor.RED + "" + ChatColor.ITALIC + "Hell is being unleashed upon you by " + sender.getName() + "!");
        lorelist.add(ChatColor.RED + "" + ChatColor.ITALIC + "Expect hell to rise!");
        hellmeta.setLore(lorelist);
        hell.setItemMeta(hellmeta);
        
        do {
            Bukkit.getOnlinePlayers().stream().map((player) -> {
            // play some sounds 
            player.playSound(player.getLocation(), Sound.ENTITY_GHAST_SCREAM, 100, 1);
            return player;
            }).map((player) -> {
                player.playSound(player.getLocation(), Sound.ENTITY_ENDERDRAGON_GROWL, 100, 2);
                return player;
            }).forEachOrdered((player) -> {
                player.playSound(player.getLocation(), Sound.ENTITY_WITHER_AMBIENT, 100, 2);
            });
            
            x++;
        } while(x <= 100);
        
        // forcefully close inventory to watch theirself get doomed
        t.closeInventory();
        
        // flood user's inventory with a hell surprise ;)
        t.getInventory().addItem(new ItemStack(hell));
        
        // create explosion
        t.getWorld().createExplosion(t.getLocation(), 1F, false);
        
        // kill player
        t.setHealth(0.0);
        
        // ignite player
        t.setFireTicks(10000);
        
        // throw the player x-position +50 times
        t.setVelocity(t.getVelocity().clone().add(new Vector(50, 0, 0)));
        
        // change gamemode to survival
        t.setGameMode(GameMode.SURVIVAL);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                
                Bukkit.broadcastMessage(ChatColor.DARK_RED + "The gates to hell have opened! Let the wrath of " + p.getName() + " condem " + t.getName() + "!");
                
                // strike lightning
                t.getWorld().strikeLightning(t.getLocation());
                
                // ignite player
                t.setFireTicks(10000);
                
                Bukkit.getOnlinePlayers().forEach((player) -> {
                    // play realistic sound of woman screaming to all online players
                    player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 100, 2);
                });
                
                Bukkit.broadcastMessage(ChatColor.RED + t.getName() + " - *screams*");
                
                // throw the player z-position +50 times
                t.setVelocity(t.getVelocity().clone().add(new Vector(0, 0, 50)));
                
                // clear inventory
                t.getInventory().clear();

                // flood inventory
                t.getInventory().addItem(new ItemStack(hell));
            }
        }.runTaskLater(plugin, 2L * 20L);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                
                // clear inventory
                t.getInventory().clear();
                
                // flood inventory
                t.getInventory().addItem(new ItemStack(hell));
                
                // strike lightning
                t.getWorld().strikeLightning(t.getLocation());
                
                // ignite player
                t.setFireTicks(10000);
                
                // kill player
                t.setHealth(0.0);
                
                Bukkit.getOnlinePlayers().forEach((player) -> {
                    // play sound effect - woman screaming
                    player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 100, 2);
                });
                
                // force chat
                t.chat("Oh no! SORRY!");
            }
        }.runTaskLater(plugin, 3L * 20L);
        
        new BukkitRunnable() {
            @Override
            public void run() {
                t.getWorld().strikeLightning(t.getLocation());
                
                Bukkit.getOnlinePlayers().stream().map((player) -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 100, 2);
                    return player;
                }).map((player) -> {
                    player.playSound(player.getLocation(), Sound.AMBIENT_CAVE, 100, 2);
                    return player;
                }).forEachOrdered((player) -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 100, 2);
                });
                
                // shoot player into sky
                t.setVelocity(t.getVelocity().clone().add(new Vector(0, 50, 0)));
                
                // create explosion
                t.getWorld().createExplosion(t.getLocation(), 1F);
                
                // ignite player
                t.setFireTicks(10000);
                
                // force chat
                t.chat("Please " + p.getName() + "! I'm so sorry for my stupid behaviour!");
                t.chat("Please do not call Satan upon me!! :(");
                p.chat("It's too late. Good day sir.");
                p.chat("No apologies and exceptions, bye!");
            }
        }.runTaskLater(plugin, 4L * 20L);
        
        new BukkitRunnable() {
            @Override
            public void run() {

                t.getWorld().strikeLightning(t.getLocation());
                t.setFireTicks(10000);
                Bukkit.broadcastMessage(ChatColor.DARK_RED + t.getName() + " has been sent to hell!");

                Bukkit.getOnlinePlayers().stream().map((player) -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_WOLF_HOWL, 100, 2);
                    return player;
                }).forEachOrdered((player) -> {
                    player.playSound(player.getLocation(), Sound.ENTITY_LIGHTNING_THUNDER, 100, 2);
                });
                               
                
                if (t.isOp() == false) {
                    bl = Bukkit.getBanList(BanList.Type.NAME);
                    bl2 = Bukkit.getBanList(BanList.Type.IP);
                    bl.addBan(t.getName(), ChatColor.RED + "FUCKOFF, and get your shit together!", null, p.getName());
                    bl2.addBan(t.getAddress().getAddress().getHostAddress(), "FUCKOFF, and get your shit together!", null, p.getName());
                }
                
                t.kickPlayer(ChatColor.DARK_RED + "WELCOME TO HELL, MOTHERFUCKER.");
            }
        }.runTaskLater(plugin, 5L * 20L);
                
        return true;
    }
}
