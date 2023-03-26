package pl.techbrat.spigot.globalaccount.account;

import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.HandlerList;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.util.HashMap;

public class AccountsManager implements Listener {
    private static AccountsManager instance;

    private final HashMap<String, PlayerAccount> playerAccounts = new HashMap<>();

    public AccountsManager() {
        instance = this;

        for (Player p : Bukkit.getOnlinePlayers()) {
            createAccount(p);
        }
    }

    public void createAccount(Player player) {
        if (!hasCreatedAccount(player)) {
            playerAccounts.put(getIdentifier(player), new PlayerAccount(player));
        }
    }

    public boolean hasCreatedAccount(Player player) {
        return (playerAccounts.containsKey(getIdentifier(player)));
    }

    public PlayerAccount getAccount(Player player) {
        return playerAccounts.get(getIdentifier(player));
    }

    private String getIdentifier(Player player) {
        return player.getName()+"-"+player.getUniqueId().toString();
    }

    public void stopListening() {
        HandlerList.unregisterAll(this);
    }

    public void onPlayerJoin(PlayerJoinEvent e) {
        createAccount(e.getPlayer());
    }

    public static AccountsManager getInstance() {
        return instance;
    }
}
