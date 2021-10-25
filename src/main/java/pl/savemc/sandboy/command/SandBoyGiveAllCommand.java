package pl.savemc.sandboy.command;

import net.dzikoysk.funnycommands.stereotypes.FunnyCommand;
import net.dzikoysk.funnycommands.stereotypes.FunnyComponent;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.savemc.sandboy.replacer.ReplacerService;
import pl.savemc.sandboy.utils.ChatUtils;
import pl.savemc.sandboy.utils.InventoryUtils;

@FunnyComponent
public
class SandBoyGiveAllCommand {

    @FunnyCommand(
            name = "sandboy",
            permission = "pl.savemc.sandboy"
    )
    void execute(ReplacerService replacerService, CommandSender sender, String[] args) {
        if (args.length != 0) {
            Player player = Bukkit.getPlayer(args[0]);

            if (player == null) {
                sender.sendMessage(ChatUtils.color("&cGracz jest offline!"));
                return;
            }

            replacerService.getReplacersItems().forEach(item -> InventoryUtils.giveOrDrop(player, item));
            return;
        }

        if (!(sender instanceof Player player)) {
            sender.sendMessage(ChatUtils.color("&cKomenda tylko dla gracza!"));
            return;
        }

        replacerService.getReplacersItems().forEach(item -> InventoryUtils.giveOrDrop(player, item));
    }

}