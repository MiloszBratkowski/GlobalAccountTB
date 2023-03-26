package pl.techbrat.spigot.globalaccount;

import org.bukkit.ChatColor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FormatMessages {
    private static final Pattern HEX_PATTERN = Pattern.compile("&#([A-Fa-f0-9]{6})");;
    private static FormatMessages instance;

    private ConfigData configData;

    public FormatMessages() {
        instance = this;
        updateConfig();
    }

    public void updateConfig() {
        configData = ConfigData.getInstance();
    }

    public String getPrefix() {
        return configData.getMsg("prefix");
    }

    public String replacePrefix(String message) {
        return message.replace("<prefix>", getPrefix());
    }

    public String hexToColor(final String message) {
        final char colorChar = ChatColor.COLOR_CHAR;

        final Matcher matcher = HEX_PATTERN.matcher(message);
        final StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);

        while (matcher.find()) {
            final String group = matcher.group(1);

            matcher.appendReplacement(buffer, colorChar + "x"
                    + colorChar + group.charAt(0) + colorChar + group.charAt(1)
                    + colorChar + group.charAt(2) + colorChar + group.charAt(3)
                    + colorChar + group.charAt(4) + colorChar + group.charAt(5));
        }

        return matcher.appendTail(buffer).toString();
    }

    public String addColors(String message) {
        message = hexToColor(message);
        return ChatColor.translateAlternateColorCodes('&', message);
    }

    public String formatMessage(final String patch) {
        return addColors(replacePrefix(configData.getMsg(patch)));
    }

    public String getAccountInfo(String player, String exp, String level, String wantingExp, String summedExp, String progress) {
        return addColors(replacePrefix(configData.getMsg("command.globalaccount.user.info"))).
                replace(", ", "\n").
                replace("<player>", player).
                replace("<level>", level).
                replace("<exp>", exp).
                replace("<wanting_exp>", wantingExp).
                replace("<summed_exp>", summedExp).
                replace("<progress>", progress);
    }

    public static FormatMessages getInstance() {
        return instance;
    }
}
