package pl.techbrat.spigot.globalaccount;

import org.apache.commons.io.FileUtils;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;

public class ConfigData {
    private static final GlobalAccountTB plugin = GlobalAccountTB.getInstance();

    private static ConfigData instance;
    public static ConfigData getInstance() {
        return instance;
    }

    private final HashMap<String, String> perms = new HashMap<>();

    private final HashMap<String, Object> defaultMessages;
    private final HashMap<String, Object> messages;

    private final HashMap<String, Object> defaultConfig;
    private final HashMap<String, Object> config;


    public ConfigData() {
        instance = this;

        createConfigs(false);

        plugin.getLogger().log(Level.INFO, "Loading config file...");

        perms.put("update", "globaccttb.update");

        File messagesFile = new File(plugin.getDataFolder()+"/messages.yml");
        messages = (HashMap<String, Object>) YamlConfiguration.loadConfiguration(messagesFile).getConfigurationSection("").getValues(true);
        File temp = new File(plugin.getDataFolder()+"/messages.temp");
        try {FileUtils.copyInputStreamToFile(plugin.getResource("messages.yml"), temp);} catch (IOException e) {e.printStackTrace();}
        defaultMessages = (HashMap<String, Object>)YamlConfiguration.loadConfiguration(temp).getConfigurationSection("").getValues(true);
        temp.delete();

        File configFile = new File(plugin.getDataFolder()+"/config.yml");
        config = (HashMap<String, Object>) YamlConfiguration.loadConfiguration(configFile).getConfigurationSection("").getValues(true);
        temp = new File(plugin.getDataFolder()+"/config.temp");
        try {FileUtils.copyInputStreamToFile(plugin.getResource("config.yml"), temp);} catch (IOException e) {e.printStackTrace();}
        defaultConfig = (HashMap<String, Object>)YamlConfiguration.loadConfiguration(temp).getConfigurationSection("").getValues(true);
        temp.delete();

        plugin.getLogger().log(Level.INFO, "Config file loaded.");
    }

    private Object getReliabilityConfig(String value) {
        if (config.containsKey(value)) return config.get(value);
        else {
            plugin.getLogger().warning("");
            plugin.getLogger().warning("Can't find '"+value+"' in config.yml!");
            plugin.getLogger().warning("Default value has been got! ("+defaultConfig.get(value)+")");
            plugin.getLogger().warning("To set that option, config file must be recreated!");
            plugin.getLogger().warning("Paste manually new structures or delete config.yml to auto recreate!");
            plugin.getLogger().warning("");
            return defaultConfig.get(value);
        }
    }

    private Object getReliabilityMessage(String value) {
        if (messages.containsKey(value)) return messages.get(value);
        else {
            plugin.getLogger().warning("");
            plugin.getLogger().warning("Can't find '"+value+"' in messages.yml!");
            plugin.getLogger().warning("Default value has been got! ("+defaultConfig.get(value)+")");
            plugin.getLogger().warning("To set that option, config file must be recreated!");
            plugin.getLogger().warning("Paste manually new structures or delete messages.yml to auto recreate!");
            plugin.getLogger().warning("");
            return defaultMessages.get(value);
        }
    }

    public String getMsg(String value) {
        return getReliabilityMessage(value).toString();
    }

    public String getCalculatorType() {
        return "quadratic";
    }

    public String getPerms(String value) {
        return perms.get(value);
    }

    private void createConfigs(boolean forceCopy) {
        String[] files = {"config.yml", "messages.yml"};
        for (String element : files) {
            File file = new File(plugin.getDataFolder()+"/"+element);
            if (!file.isFile() || forceCopy) {
                try {
                    plugin.getLogger().log(Level.INFO, "Creating "+element+" ...");
                    FileUtils.copyInputStreamToFile(plugin.getResource(element), file);
                    plugin.getLogger().log(Level.INFO, "File "+element+" created");
                } catch (Exception e) {e.printStackTrace(); plugin.stopPlugin();}
            }
        }
    }

}
