package xyz.motz.classe.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import xyz.motz.classe.Classe;

public class ConfigReloadCommand extends CommandAPICommand implements CommandExecutor {

    public ConfigReloadCommand() {
        super("reload");
        withPermission("classe.admin");
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
        Classe.getInstance().reloadConfig();
        sender.sendMessage(MiniMessage.miniMessage().deserialize(Classe.getPrefix() + "<green>Config reloaded!"));
    }
}
