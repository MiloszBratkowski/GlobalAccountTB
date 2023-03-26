package pl.techbrat.spigot.globalaccount.dependencies;

import org.bukkit.Bukkit;

public class APILoader {
    private static APILoader instance;

    private PlaceHolderAPI placeHolderAPI;

    public APILoader() {
        instance = this;

        //placeHolderAPI = new PlaceHolderAPI();
    }

    public PlaceHolderAPI getPlaceHolderAPI() {
        return placeHolderAPI;
    }

    public static APILoader getInstance() {
        return instance;
    }
}
