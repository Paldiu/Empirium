/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.jovial.command;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import ns.jovial.NNOLogger;
import org.bukkit.entity.Player;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
//thanks to Camzie99 for this!

public class BlankCommand extends NNOCommand
{
    Class cls;
    Object object;

    public BlankCommand(String name, String usage, String description, List<String> aliases, String permission, Class cls) throws NoSuchMethodException
    {
        super(name, usage, description, aliases, permission);
        this.cls = cls;
        try
        {
            this.object = cls.getConstructor().newInstance();
        } catch (InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            NNOLogger.severe(ex);
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args, boolean isConsole)
    {
        isConsole = !(sender instanceof Player);
        try
        {
            return (boolean) cls.getMethod("onCommand", CommandSender.class, Command.class, String.class, String[].class, Boolean.class).invoke(object, sender, cmd, label, args, isConsole);
        } catch (NoSuchMethodException | SecurityException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
        {
            Logger.getLogger(BlankCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean onCommand(CommandSender cs, Command cmnd, String string, String[] strings) {
        throw new UnsupportedOperationException("This shouldn't be used!"); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<String> onTabComplete(CommandSender cs, Command cmnd, String string, String[] strings) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
