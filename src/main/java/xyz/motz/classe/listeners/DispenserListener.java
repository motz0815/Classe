package xyz.motz.classe.listeners;

import de.tr7zw.nbtapi.NBTTileEntity;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.util.RayTraceResult;
import xyz.motz.classe.util.mechanics.BreedingDispenser;

public class DispenserListener implements Listener {
    @EventHandler
    public void onDispense(BlockDispenseEvent e) {
        if (BreedingDispenser.isBreedingDispenser(e.getBlock())) {
            Bukkit.getServer().sendMessage(Component.text("breeding dispenser activated"));
            Dispenser dispenser = (Dispenser) e.getBlock().getState();
            Directional dispenserDirection = (Directional) dispenser.getBlockData();

            Bukkit.getServer().sendMessage(Component.text(dispenser.getLocation().toString()));
            Bukkit.getServer().sendMessage(Component.text(dispenserDirection.getFacing().getDirection().toString()));

            RayTraceResult result = e.getBlock().getWorld().rayTraceEntities(dispenser.getLocation(), dispenserDirection.getFacing().getDirection(), 2, 0.1);
            if (result == null) {
                e.setCancelled(true);
                Bukkit.getServer().sendMessage(Component.text("stage 1"));
                return;
            }

            Entity entity = result.getHitEntity();
            if (entity == null) {
                Bukkit.getServer().sendMessage(Component.text("stage 2"));
                return;
            }
            Bukkit.getServer().sendMessage(Component.text(entity.getLocation().toString()));

            if (!(entity instanceof Animals animal)) {
                Bukkit.getServer().sendMessage(Component.text("stage 3"));
                return;
            }
            Bukkit.getServer().sendMessage(Component.text(animal.canBreed()));
            if (animal.canBreed() && animal.isBreedItem(e.getItem())) {
                // breeding success
                animal.setBreed(true);
                Bukkit.getServer().sendMessage(Component.text("breeded"));
            }
            Bukkit.getServer().sendMessage(Component.text("done"));
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (BreedingDispenser.isBreedingDispenser(e.getItemInHand())) {
            new NBTTileEntity(e.getBlock().getState()).getPersistentDataContainer().setString("classe.type", BreedingDispenser.id());
        }
    }
}
