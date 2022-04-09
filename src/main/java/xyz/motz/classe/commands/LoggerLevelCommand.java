package xyz.motz.classe.commands;

import dev.jorel.commandapi.CommandAPICommand;
import dev.jorel.commandapi.arguments.MultiLiteralArgument;
import dev.jorel.commandapi.executors.CommandExecutor;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.command.CommandSender;
import xyz.motz.classe.Classe;

import java.util.logging.Level;

public class LoggerLevelCommand extends CommandAPICommand implements CommandExecutor {
    public LoggerLevelCommand() {
        super("logger-level");
        withPermission("classe.admin");
        withArguments(new MultiLiteralArgument("default", "debug"));
        executes(this);
    }

    @Override
    public void run(CommandSender sender, Object[] args) {
        if ("debug".equals(args[0])) {
            Classe.getInstance().getLogger().setLevel(Level.FINEST);
            sender.sendMessage(MiniMessage.miniMessage().deserialize(Classe.getPrefix() + "<green>Set logger level to <red>DEBUG</red>."));
        } else {
            Classe.getInstance().getLogger().setLevel(Level.INFO);
            sender.sendMessage(MiniMessage.miniMessage().deserialize(Classe.getPrefix() + "<green>Reset logger level to <red>DEFAULT</red>."));
        }
    }
}
