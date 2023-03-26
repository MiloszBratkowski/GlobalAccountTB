package pl.techbrat.spigot.globalaccount.dependencies;

import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import pl.techbrat.spigot.globalaccount.GlobalAccountTB;
import pl.techbrat.spigot.globalaccount.account.AccountsManager;
import pl.techbrat.spigot.globalaccount.account.PlayerAccount;

public class PlaceHolderAPI extends PlaceholderExpansion {
    @Override
    public @NotNull String getIdentifier() {
        return "globacct";
    }

    @Override
    public @NotNull String getAuthor() {
        return "TechBrat";
    }

    @Override
    public @NotNull String getVersion() {
        return GlobalAccountTB.getInstance().getDescription().getVersion();
    }

    @Override
    public @Nullable String onPlaceholderRequest(Player player, @NotNull String params) {
        PlayerAccount account = AccountsManager.getInstance().getAccount(player);
        if (params.equals("level")) return String.valueOf(account.getRoundedLevel());
        else if (params.equals("next_level")) return String.valueOf(account.getNextLevel());
        else if (params.equals("accurate_level")) return String.valueOf(account.getAccurateLevel());
        else if (params.equals("exp")) return String.valueOf(account.getLevelExperience());
        else if (params.equals("summed_exp")) return String.valueOf(account.getExperience());
        else if (params.equals("wanting_exp")) return String.valueOf(account.getWantingExperience());
        return "WRONG PLACEHOLDER LABEL";
    }
}
