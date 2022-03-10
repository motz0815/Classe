package xyz.motz.classe;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.motz.classe.listeners.DispenserListener;
import xyz.motz.classe.util.mechanics.BreedingDispenser;

public class Classe extends JavaPlugin {
    @Getter
    private static Classe instance;

    @Override
    public void onLoad() {
        instance = this;
        // Commands

        getLogger().info("Classe loaded!");
    }

    @Override
    public void onEnable() {
        // Listeners
        Bukkit.getPluginManager().registerEvents(new DispenserListener(), this);

        // Recipes
        Bukkit.addRecipe(BreedingDispenser.recipe());

        getLogger().info("Classe enabled!");
    }
}
