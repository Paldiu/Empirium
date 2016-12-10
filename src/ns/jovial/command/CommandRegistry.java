/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ns.jovial.command;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.security.CodeSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import ns.jovial.Main;
import ns.jovial.NNOLogger;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;

public class CommandRegistry
{
    private static CommandMap cmap = getCommandMap();
    private static final ArrayList<String> COMMANDS = new ArrayList<>();

    public CommandRegistry()
    {
        registerCommands();
    }
    
    public static void unregisterCommands()
    {
        COMMANDS.stream().map((name) -> cmap.getCommand(name)).forEachOrdered((cmd) -> {
            cmd.unregister(cmap);
        });
    }

    public static void registerCommands()
    {
        try
        {
            Pattern PATTERN = Pattern.compile("ns/jovial/command/(Command_[^\\$]+)\\.class");
            CodeSource codeSource = Main.class.getProtectionDomain().getCodeSource();
            if (codeSource != null)
            {
                ZipInputStream zip = new ZipInputStream(codeSource.getLocation().openStream());
                ZipEntry zipEntry;
                while ((zipEntry = zip.getNextEntry()) != null)
                {
                    String entryName = zipEntry.getName();
                    Matcher matcher = PATTERN.matcher(entryName);
                    if (matcher.find())
                    {
                        try
                        {
                            Class<?> commandClass = Class.forName("ns.jovial.command." + matcher.group(1));
                            if (commandClass.isAnnotationPresent(CommandParameters.class))
                            {
                                Annotation annotation = commandClass.getAnnotation(CommandParameters.class);
                                CommandParameters params = (CommandParameters) annotation;
                                NNOCommand command = new BlankCommand(params.name(), params.usage(), params.description(), Arrays.asList(params.aliases()), params.permission(), commandClass);
                                command.register();
                                COMMANDS.add(params.name());
                            }
                            else
                            {
                                Constructor construct = commandClass.getConstructor();
                                NNOCommand command = (NNOCommand) construct.newInstance();
                                command.register();
                                COMMANDS.add(command.command);
                            }
                        } catch (ClassNotFoundException | NoSuchMethodException | SecurityException | InstantiationException | IllegalAccessException | IllegalArgumentException | InvocationTargetException ex)
                        {
                            Bukkit.broadcastMessage("" + ex);
                        }
                    }
                }
            }
        } catch (IOException ex)
        {
            NNOLogger.severe(ex);
        }
    }

    public static boolean isNNOCommand(String name)
    {
        return CommandRegistry.COMMANDS.contains(name.toLowerCase());
    }

    private static CommandMap getCommandMap()
    {
        if (cmap == null)
        {
            try
            {
                final Field f = Bukkit.getServer().getClass().getDeclaredField("commandMap");
                f.setAccessible(true);
                cmap = (CommandMap) f.get(Bukkit.getServer());
                return getCommandMap();
            } catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e)
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

}