package ns.jovial;

import java.util.List;
import ns.jovial.command.CommandRegistry;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.*;

public class Main extends JavaPlugin {
    public static Main plugin;
    public String pName;
    public String pVersion;
    public List<String> pAuthors;
    private static CommandRegistry creg;
    public static Server server = Bukkit.getServer();
    
    //Command messages
    public static final String MSG_NO_PERMS = ChatColor.RED + "You don't have permission to use this command!";
    public static final String PLAYER_NOT_FOUND = ChatColor.YELLOW + "That player cannot be found!";
    public static final String NOT_FROM_CONSOLE = "This command cannot be used from the console.";
    
    @Override
    public void onLoad() {
        this.pName = this.getDescription().getName();
        this.pVersion = this.getDescription().getVersion();
        this.pAuthors = this.getDescription().getAuthors(); 
        
        Main.plugin = this;
    }
    
    @Override
    public void onEnable() {
        registerEvents();
        
    }
    
    @Override
    public void onDisable() {
        
    }
    
    private void registerEvents() {
        creg = new CommandRegistry();
    }
}
