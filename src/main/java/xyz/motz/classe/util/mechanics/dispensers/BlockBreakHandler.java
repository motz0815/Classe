package xyz.motz.classe.util.mechanics.dispensers;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.block.Dispenser;
import org.bukkit.block.data.Directional;
import org.bukkit.event.block.BlockDispenseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import xyz.motz.classe.Classe;

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

            // TODO config option to enable / disable durability loss
            // TODO respect unbreaking
            ItemStack newPickaxe = e.getItem().clone();
            newPickaxe.editMeta(Damageable.class, meta -> {
                meta.setDamage(meta.getDamage() + 1);
            });

            Bukkit.getScheduler().runTask(Classe.getInstance(), () -> {
                dispenser.getInventory().removeItem(e.getItem());
                dispenser.getInventory().addItem(newPickaxe);
            });
        }
        return true;
    }
}
