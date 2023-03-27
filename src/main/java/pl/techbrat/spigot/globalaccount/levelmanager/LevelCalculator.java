package pl.techbrat.spigot.globalaccount.levelmanager;

public interface LevelCalculator {

    double getAccurateLevel(long experience);

    long getExperience(double level);

    default long getNextLevel(long experience) {
        return (long) Math.floor(getAccurateLevel(experience))+1;
    }

    default long getRoundedLevel(long experience) {
        return (long) Math.floor(getAccurateLevel(experience));
    }

    default long getNextLevelExperience(long experience) {
        return getExperience(getNextLevel(experience));
    }

    default long getWantingExperience(long experience) {
        return getNextLevelExperience(experience) - experience;
    }

    default long getLevelExperience(long experience) {
        return experience - getExperience(getRoundedLevel(experience));
    }

    default double getLevelProgress(long experience) {
        return Math.round(100.0*getLevelExperience(experience)/getNextLevelExperience(experience))/100.0;
    }

}
