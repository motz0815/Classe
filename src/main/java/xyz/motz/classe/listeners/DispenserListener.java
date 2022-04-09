package xyz.motz.classe.listeners;

import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import xyz.motz.classe.Classe;
import xyz.motz.classe.util.mechanics.dispensers.BlockBreakHandler;
import xyz.motz.classe.util.mechanics.dispensers.BreedHandler;

public class DispenserListener implements Listener {
    @EventHandler
    public void onDispense(BlockDispenseEvent e) {
        if (e.getBlock().getState() instanceof Dispenser dispenser) {
            if (Classe.getInstance().getConfig().getBoolean("dispenser.can-breed-animals")) {
                if (BreedHandler.handle(e, dispenser)) {
                    Classe.getInstance().getLogger().fine("breeding succeeded");
                    return;
                }
                Classe.getInstance().getLogger().fine("breeding failed");
            }

            if (Classe.getInstance().getConfig().getBoolean("dispenser.can-break-blocks")) {
                if (BlockBreakHandler.handle(e, dispenser)) {
                    Classe.getInstance().getLogger().fine("block breaking succeeded");
                    return;
                }
                Classe.getInstance().getLogger().fine("block breaking failed");
            }
        }
    }
}
