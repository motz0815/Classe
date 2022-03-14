package xyz.motz.classe.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.executors.CommandExecutor;
import org.bukkit.command.CommandSender;

public class ClasseCommand extends CommandAPICommand implements CommandExecutor {

    public ClasseCommand() {
        super("classe");
        withSubcommand(new ConfigReloadCommand());
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {

    }
}
