package pl.techbrat.spigot.globalaccount.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import pl.techbrat.spigot.globalaccount.FormatMessages;
import pl.techbrat.spigot.globalaccount.account.AccountsManager;
import pl.techbrat.spigot.globalaccount.account.PlayerAccount;
import pl.techbrat.spigot.globalaccount.levelmanager.CalculatorManager;
import pl.techbrat.spigot.globalaccount.levelmanager.LevelCalculator;

public class GlobalAccountCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        FormatMessages formater = FormatMessages.getInstance();
        if (args[0].equalsIgnoreCase("user")) {
            if (args[1].equalsIgnoreCase("info")) {
                PlayerAccount account = AccountsManager.getInstance().getAccount(Bukkit.getPlayer(args[2]));
                sender.sendMessage(formater.getAccountInfo(args[2],
                        String.valueOf(account.getLevelExperience()),
                        String.valueOf(account.getRoundedLevel()),
                        String.valueOf(account.getWantingExperience()),
                        String.valueOf(account.getExperience()),
                        "23"));
                return true;
            }
        }
        if (args[0].equals("calculate")) {
            LevelCalculator calculator = CalculatorManager.getInstance().getCalculator();
            if (args[1].equals("level")) {
                long experience = Long.parseLong(args[2]);
                sender.sendMessage("Aktualny: "+calculator.getAccurateLevel(experience));
                sender.sendMessage("Zaokraglony: "+calculator.getRoundedLevel(experience));
                sender.sendMessage("Nastepny: "+calculator.getNextLevel(experience));
            } else if (args[1].equals("experience") || args[1].equals("exp")) {
                double level = Double.parseDouble(args[2]);
                sender.sendMessage("Aktualny: "+calculator.getExperience(level));
                sender.sendMessage("Z tego levela: "+calculator.getLevelExperience(calculator.getExperience(level)));
                sender.sendMessage("Do nastepnego levela: "+calculator.getWantingExperience(calculator.getExperience(level)));
            }
        }
        return true;
    }
}
