package xyz.motz.classe.listeners;

import de.tr7zw.nbtapi.NBTTileEntity;
import net.kyori.adventure.text.Component;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.Particle;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.util.RayTraceResult;
import xyz.motz.classe.Classe;
import xyz.motz.classe.util.mechanics.BreedingDispenser;

public class DispenserListener implements Listener {
    @EventHandler
    public void onDispense(BlockDispenseEvent e) {
        if (e.getBlock().getState() instanceof Dispenser dispenser) {
            if (Classe.getInstance().getConfig().getBoolean("dispenser.can-breed-animals")) {
                Directional dispenserDirection = (Directional) dispenser.getBlockData();

                Bukkit.getServer().sendMessage(Component.text(dispenser.getLocation().toString()));
                Bukkit.getServer().sendMessage(Component.text(dispenserDirection.getFacing().getDirection().toString()));

                RayTraceResult result = e.getBlock().getWorld().rayTraceEntities(dispenser.getLocation(), dispenserDirection.getFacing().getDirection(), 2, 0.1,
                        (Entity entity) -> {
                            if (entity instanceof Animals animal) {
                                return animal.canBreed() && animal.isAdult() && animal.getLoveModeTicks() == 0;
                            } else return false;
                        });
                if (result == null) {
                    Bukkit.getServer().sendMessage(Component.text("nothing to breed!"));
                    return;
                }

                Entity entity = result.getHitEntity();
                if (entity == null) return;

                Bukkit.getServer().sendMessage(Component.text(entity.toString()));

                if (entity instanceof Animals animal) {
                    Bukkit.getServer().sendMessage(Component.text(animal.canBreed()));
                    if (animal.canBreed() && animal.isBreedItem(e.getItem())) {
                        // breeding success
                        animal.setLoveModeTicks(600);

                        animal.getWorld().spawnParticle(Particle.HEART, animal.getLocation().add(0, 1, 0), 1);
                        Bukkit.getServer().sendMessage(Component.text("breeded!"));
                    }
                }
            }
        }
    }

    @EventHandler
    public void onPlace(BlockPlaceEvent e) {
        if (BreedingDispenser.isBreedingDispenser(e.getItemInHand())) {
            new NBTTileEntity(e.getBlock().getState()).getPersistentDataContainer().setString("classe.type", BreedingDispenser.id());
        }
    }
}
