package xyz.motz.classe.util.mechanics.dispensers;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.event.block.BlockDispenseEvent;

import java.util.EnumSet;
import java.util.Map;
import java.util.Set;

import static org.bukkit.Material.*;

public class PlantHandler {
    final static Set<Material> SEEDS = EnumSet.of(WHEAT_SEEDS, BEETROOT_SEEDS, MELON_SEEDS, PUMPKIN_SEEDS, POTATO, CARROT);
    final static Map<Material, Material> SEEDMAP = Map.of(
            WHEAT_SEEDS, WHEAT,
            BEETROOT_SEEDS, BEETROOTS,
            MELON_SEEDS, MELON_STEM,
            PUMPKIN_SEEDS, PUMPKIN_STEM,
            POTATO, POTATOES,
            CARROT, CARROTS);

    public static boolean handle(BlockDispenseEvent e, Dispenser dispenser) {
        if (!SEEDS.contains(e.getItem().getType())) return false;

        Directional dispenserDirection = (Directional) dispenser.getBlockData();

        // two tries to reach farmland
        Block block = dispenser.getLocation().add(dispenserDirection.getFacing().getDirection()).getBlock();
        if (block.getType() != FARMLAND)
            block = dispenser.getLocation().add(dispenserDirection.getFacing().getDirection().multiply(2)).getBlock();
        if (block.getType() != FARMLAND) return false;

        // only place the seed if the farmland is empty
        Block crop = block.getRelative(BlockFace.UP);
        if (crop.getType() == AIR) {
            // place seed
            crop.setType(SEEDMAP.get(e.getItem().getType()), true);

            // cancel event so the item doesn't get dropped
            e.setCancelled(true);
            // remove the item from the dispenser's inventory
            dispenser.getInventory().removeItem(e.getItem());
        }
        return true;
    }
}
