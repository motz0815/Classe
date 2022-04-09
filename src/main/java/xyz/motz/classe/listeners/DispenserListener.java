package xyz.motz.classe.listeners;

import org.bukkit.block.Dispenser;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockDispenseEvent;
import xyz.motz.classe.Classe;
import xyz.motz.classe.util.mechanics.dispensers.BreedHandler;

public class DispenserListener implements Listener {
    @EventHandler
    public void onDispense(BlockDispenseEvent e) {
        if (e.getBlock().getState() instanceof Dispenser dispenser) {
            if (Classe.getInstance().getConfig().getBoolean("dispenser.can-breed-animals")) {
                if (BreedHandler.handle(e, dispenser)) return;
            }

            if (Classe.getInstance().getConfig().getBoolean("dispenser.can-break-blocks")) {

            }
        }
    }
}
