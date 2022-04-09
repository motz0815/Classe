package xyz.motz.classe.util.mechanics.dispensers;

import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.event.block.BlockDispenseEvent;

import static org.bukkit.Material.AIR;
import static org.bukkit.Material.NETHERITE_PICKAXE;

public class BlockBreakHandler {
    public static boolean handle(BlockDispenseEvent e, Dispenser dispenser) {
        if (e.getItem().getType() != NETHERITE_PICKAXE) return false;

        e.setCancelled(true);

        Directional dispenserDirection = (Directional) dispenser.getBlockData();

        Block block = dispenser.getLocation().add(dispenserDirection.getFacing().getDirection()).getBlock();
        if (block.getType() != AIR) {
            block.breakNaturally(e.getItem(), true);

            // TODO remove durability from pickaxe
        }
        return true;
    }
}
