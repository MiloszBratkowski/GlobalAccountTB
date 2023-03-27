package pl.techbrat.spigot.globalaccount.account;

import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import pl.techbrat.spigot.globalaccount.levelmanager.CalculatorManager;
import pl.techbrat.spigot.globalaccount.levelmanager.LevelCalculator;

import java.util.UUID;

public class PlayerAccount {

    private long experience;

    private final String playerName;
    private final UUID playerUUID;

    public PlayerAccount(Player player) {
        this(player.getName(), player.getUniqueId().toString());
    }

    public PlayerAccount(String playerName, String playerUUID) {
        this.playerName = playerName;
        this.playerUUID = UUID.fromString(playerUUID);
    }

    public OfflinePlayer getOfflinePlayer() {
        return Bukkit.getOfflinePlayer(playerUUID);
    }

    public long getExperience() {
        return experience;
    }

    public long addExperience(long value) {
        return experience+=value;
    }

    public long getNextLevelExperience() {
        return CalculatorManager.getInstance().getCalculator().getNextLevelExperience(experience);
    }

    public long getWantingExperience() {
        return CalculatorManager.getInstance().getCalculator().getWantingExperience(experience);
    }

    public long getLevelExperience() {
        return CalculatorManager.getInstance().getCalculator().getLevelExperience(experience);
    }

    public long getRoundedLevel() {
        return CalculatorManager.getInstance().getCalculator().getRoundedLevel(experience);
    }

    public long getNextLevel() {
        return CalculatorManager.getInstance().getCalculator().getNextLevel(experience);
    }

    public double getAccurateLevel() {
        return CalculatorManager.getInstance().getCalculator().getAccurateLevel(experience);
    }

    public double getLevelProgress() {
        return CalculatorManager.getInstance().getCalculator().getLevelProgress(experience);
    }
}
