package pl.techbrat.spigot.globalaccount;

import org.bukkit.plugin.java.JavaPlugin;
import pl.techbrat.spigot.globalaccount.account.AccountsManager;
import pl.techbrat.spigot.globalaccount.commands.GlobalAccountCommand;
import pl.techbrat.spigot.globalaccount.dependencies.APILoader;
import pl.techbrat.spigot.globalaccount.levelmanager.CalculatorManager;

public final class GlobalAccountTB extends JavaPlugin {
    private static GlobalAccountTB instance;

    @Override
    public void onEnable() {
        instance = this;

        reload();
        new AccountsManager();

        getCommand("globalaccount").setExecutor(new GlobalAccountCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public void stopPlugin() {
        this.getServer().getPluginManager().disablePlugin(this);
    }

    public void destroyLoaded() {
        UpdateChecker.getInstance().stopListening();
        AccountsManager.getInstance().stopListening();
    }

    public void reload() {
        new ConfigData();
        new FormatMessages();
        new CalculatorManager(true);
        new UpdateChecker(this, true);
        new APILoader();
    }

    public static GlobalAccountTB getInstance() {
        return instance;
    }
}
