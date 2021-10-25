package pl.savemc.sandboy.command

import net.dzikoysk.funnycommands.stereotypes.FunnyCommand
import net.dzikoysk.funnycommands.stereotypes.FunnyComponent
import org.bukkit.Bukkit
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import pl.savemc.sandboy.utils.kotlin.colorMessage
import pl.savemc.sandboy.replacer.ReplacerService
import pl.savemc.sandboy.utils.InventoryUtils

@FunnyComponent
class SandBoyGiveAllCommand {

    @FunnyCommand(
        name = "sandboy",
        permission = "pl.savemc.sandboy"
    )
    fun execute(replacerService: ReplacerService, sender: CommandSender, args: Array<String>) {
        if (args.isNotEmpty()) {
            val player = Bukkit.getPlayer(args[0])

            if (player == null) {
                sender.colorMessage("&cGracz jest offline!")
                return
            }

            replacerService.replacersItems.forEach { item -> InventoryUtils.giveOrDrop(player, item) }
            return
        }

        if (sender !is Player) {
            sender.colorMessage("&cKomenda tylko dla gracza!")
            return
        }

        replacerService.replacersItems.forEach { item -> InventoryUtils.giveOrDrop(sender, item) }
    }
}
