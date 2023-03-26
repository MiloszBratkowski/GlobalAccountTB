package pl.techbrat.spigot.globalaccount.levelmanager;

import pl.techbrat.spigot.globalaccount.ConfigData;
import pl.techbrat.spigot.globalaccount.GlobalAccountTB;

public class CalculatorManager {

    private static CalculatorManager instance;

    private LevelCalculator levelCalculator;

    public CalculatorManager(boolean load) {
        instance = this;

        if (load) loadCalculator();
    }

    // Loading calculator from config
    public void loadCalculator() {
        String type = ConfigData.getInstance().getCalculatorType();
        LevelCalculator calculator;
        if (type.equalsIgnoreCase("quadratic")) {
            calculator = new QuadraticMethod();
        } else {
            GlobalAccountTB.getInstance().getLogger().info("No built-in level calculator loaded, waiting for implementation with API.");
            return;
        }
        loadCalculator(calculator);
    }

    // Loading calculator from made instance
    public void loadCalculator(LevelCalculator calculator) {
        if (calculator != null) {
            levelCalculator = calculator;
            GlobalAccountTB.getInstance().getLogger().info("Level calculator has been loaded!");
        }
    }

    public LevelCalculator getCalculator() {
        return levelCalculator;
    }

    public static CalculatorManager getInstance() {
        return instance;
    }
}
