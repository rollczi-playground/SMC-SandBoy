package pl.savemc.sandboy.replacer;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import pl.savemc.sandboy.SMCSandBoy;

public class ReplacerPlaceListener implements Listener {

    private final SMCSandBoy plugin;

    public ReplacerPlaceListener(SMCSandBoy plugin) {
        this.plugin = plugin;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlace(BlockPlaceEvent event) {
        plugin.getReplacerService().getReplacer(event.getItemInHand())
                .peek(replacer -> replacer.replacer(event.getBlock().getLocation()));
    }

}
