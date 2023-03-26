package pl.techbrat.spigot.globalaccount.levelmanager;

import org.bukkit.Bukkit;

public class QuadraticMethod implements LevelCalculator{

    int param = 100;

    @Override
    public double getAccurateLevel(long experience) {
        return (Math.sqrt(param*(param+8*experience))-param)/(2*param);
    }

    @Override
    public long getExperience(double level) {
        return (long) (param/2*level*(level+1));
    }
}
