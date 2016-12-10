package ns.jovial.command;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import ns.jovial.Main;
import ns.jovial.NNOLogger;
import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandMap;
import org.bukkit.command.CommandSender;
import org.bukkit.command.PluginCommand;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.command.TabExecutor;

public abstract class NNOCommand implements CommandExecutor, TabExecutor
{

    protected static CommandMap cmap;
    protected final String command;
    protected final String description;
    protected final List<String> alias;
    protected final String usage;
    protected final String permission;

    public NNOCommand(String command, String permission)
    {
        this(command, null, null, null, permission);
    }
    
    public NNOCommand(String command, String usage, String permission)
    {
        this(command, usage, null, null, permission);
    }
    
    public NNOCommand(String command, String usage, String description, String permission)
    {
        this(command, usage, description, null, permission);
    }

    public NNOCommand(String command, String usage, String description, List<String> aliases, String permission)
    {
        this.command = command.toLowerCase();
        this.usage = usage;
        this.description = description;
        this.alias = aliases;
        this.permission = permission;
    }

    public void register()
    {
        ReflectCommand cmd = new ReflectCommand(this.command);
        if (this.alias != null)
        {
            cmd.setAliases(this.alias);
        }
        if (this.description != null)
        {
            cmd.setDescription(this.description);
        }
        if (this.usage != null)
        {
            cmd.setUsage(this.usage);
        }
        if (!getCommandMap().register("", cmd))
        {
            this.unRegisterBukkitCommand(Bukkit.getPluginCommand(cmd.getName()));
            getCommandMap().register("", cmd);
        }
        cmd.setExecutor(this);
    }

    final CommandMap getCommandMap()
    {
        if (cmap == null)
        {
            try
            {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (IllegalAccessException | IllegalArgumentException | NoSuchFieldException | SecurityException e)
            {
                NNOLogger.severe(e);
            }
        }
        else if (cmap != null)
        {
            return cmap;
        }
        return getCommandMap();
    }

    public abstract boolean onCommand(CommandSender sender, Command cmd, String label, String[] args, boolean isConsole);

    public List<String> onTabComplete(CommandSender sender, Command cmd, String label, String[] args, boolean isConsole)
    {
        return null;
    }

    private Object getPrivateField(Object object, String field) throws SecurityException,
            NoSuchFieldException, IllegalArgumentException, IllegalAccessException
    {
        Class<?> cls = object.getClass();
        Field objectField = cls.getDeclaredField(field);
        objectField.setAccessible(true);
        Object result = objectField.get(object);
        objectField.setAccessible(false);
        return result;
    }

    private void unRegisterBukkitCommand(PluginCommand cmd)
    {
        try
        {
            Object result = getPrivateField(Main.plugin.getServer().getPluginManager(), "commandMap");
            SimpleCommandMap commandMap = (SimpleCommandMap) result;
            Object map = getPrivateField(commandMap, "knownCommands");
            @SuppressWarnings("unchecked")
            HashMap<String, Command> knownCommands = (HashMap<String, Command>) map;
            knownCommands.remove(cmd.getName());
            cmd.getAliases().forEach((registeredalias) -> {
                knownCommands.remove(registeredalias);
            });
        } catch (SecurityException | IllegalArgumentException | NoSuchFieldException | IllegalAccessException ex) {
            NNOLogger.severe(ex);
        }
    }

    public final class ReflectCommand extends Command
    {

        private NNOCommand exe = null;

        protected ReflectCommand(String command)
        {
            super(command);
        }

        public void setExecutor(NNOCommand exe)
        {
            this.exe = exe;
        }

        @Override
        public boolean execute(CommandSender sender, String commandLabel, String[] args)
        {
            if (exe != null)
            {
                if (!sender.hasPermission(permission)) {
                    sender.sendMessage(Main.MSG_NO_PERMS);
                    return true;
                }
                if (!exe.onCommand(sender, this, commandLabel, args))
                {
                    sender.sendMessage(this.usageMessage.replaceAll("<command>", command));
                    return false;
                }
                return true;
            }
            return false;
        }

        @Override
        public List<String> tabComplete(CommandSender sender, String alias, String[] args)
        {
            if (exe != null)
            {
                return exe.onTabComplete(sender, this, alias, args);
            }
            return null;
        }
    }
}