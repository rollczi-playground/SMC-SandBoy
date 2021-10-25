package pl.savemc.sandboy.utils.kotlin

import org.bukkit.command.CommandSender
import pl.savemc.sandboy.utils.ChatUtils

fun CommandSender.colorMessage(message: String) {
    this.sendMessage(ChatUtils.color(message))
}