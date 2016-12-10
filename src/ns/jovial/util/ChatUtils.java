/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.jovial.util;

import ns.jovial.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.SerializationUtils;
import org.apache.commons.lang.ObjectUtils;
import org.bukkit.*;
import org.bukkit.entity.Player;

public class ChatUtils {
    private static Player p;
    private static ArrayUtils au;
    private static StringUtils su;
    private static SerializationUtils seru;
    private static ObjectUtils ou;
    private static final Server SERVER;
    private static final Main PLUGIN;
    
    private ChatUtils() {
        throw new AssertionError();
    }
    
    static {
        PLUGIN = Main.plugin;
        SERVER = Bukkit.getServer();
        
    }
}
