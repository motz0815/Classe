package xyz.motz.classe.util.mechanics.dispensers;

import org.bukkit.Particle;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Entity;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.util.RayTraceResult;
import xyz.motz.classe.Classe;

public class BreedHandler {
    public static boolean handle(BlockDispenseEvent e, Dispenser dispenser) {
        Directional dispenserDirection = (Directional) dispenser.getBlockData();

        Classe.getInstance().getLogger().fine(dispenser.getLocation().toString());
        Classe.getInstance().getLogger().fine(dispenserDirection.getFacing().getDirection().toString());

        RayTraceResult result = e.getBlock().getWorld().rayTraceEntities(dispenser.getLocation(), dispenserDirection.getFacing().getDirection(), 2, 0.1, (Entity entity) -> {
            if (entity instanceof Animals animal) {
                return animal.canBreed() && animal.isAdult() && animal.getLoveModeTicks() == 0;
            } else return false;
        });
        if (result == null) {
            Classe.getInstance().getLogger().fine("nothing to breed!");
            return false;
        }

        Entity entity = result.getHitEntity();
        if (entity == null) return false;

        Classe.getInstance().getLogger().fine(entity.toString());

        if (entity instanceof Animals animal) {
            Classe.getInstance().getLogger().fine(String.valueOf(animal.canBreed()));
            if (animal.canBreed() && animal.isBreedItem(e.getItem())) {
                // breeding success
                animal.setLoveModeTicks(600);

                // remove item from dispenser inventory
                dispenser.getInventory().removeItem(e.getItem());
                e.setCancelled(true);

                animal.getWorld().spawnParticle(Particle.HEART, animal.getLocation().add(0, 1, 0), 1);
                Classe.getInstance().getLogger().fine("breeded!");
                return true;
            }
        }
        return false;
    }
}
