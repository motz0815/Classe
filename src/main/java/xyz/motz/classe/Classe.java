package xyz.motz.classe;

import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import xyz.motz.classe.commands.ClasseCommand;
import xyz.motz.classe.listeners.CreeperListener;
import xyz.motz.classe.listeners.DispenserListener;
import xyz.motz.classe.util.mechanics.BreedingDispenser;

public class Classe extends JavaPlugin {
    @Getter
    private static Classe instance;

    @Getter
    private static final String prefix = "<gold>[<red>Classe</red>] <reset>";

    @Override
    public void onLoad() {
        instance = this;
        // Commands
        new ClasseCommand().register();
        getLogger().info("Commands registered!");

        getLogger().info("Classe loaded!");
    }

    @Override
    public void onEnable() {
        // Config
        saveDefaultConfig();
        getLogger().info("Loaded Config!");

        // Listeners
        Bukkit.getPluginManager().registerEvents(new DispenserListener(), this);
        Bukkit.getPluginManager().registerEvents(new CreeperListener(), this);

        // Recipes
        Bukkit.addRecipe(BreedingDispenser.recipe());

        getLogger().info("Classe enabled!");
    }
}
