package xyz.motz.classe.listeners;

import org.bukkit.entity.EntityType;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityExplodeEvent;
import xyz.motz.classe.Classe;

public class CreeperListener implements Listener {
    @EventHandler
    public void onExplode(EntityExplodeEvent e) {
        if (Classe.getInstance().getConfig().getBoolean("disable-creeper-block-damage")) {
            if (e.getEntityType() == EntityType.CREEPER)
                e.blockList().clear();
        }
    }
}
