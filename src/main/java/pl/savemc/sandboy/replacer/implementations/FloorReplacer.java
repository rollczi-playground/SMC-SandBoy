package pl.savemc.sandboy.replacer.implementations;

import org.bukkit.Location;
import org.bukkit.Material;
import pl.savemc.sandboy.replacer.NextMove;
import pl.savemc.sandboy.replacer.ReplacerValidator;

public class FloorReplacer extends StandardReplacer {

    private final Material floor;

    public FloorReplacer(Material to, Material floor, NextMove nextMove, ReplacerValidator validator) {
        super(to, nextMove, validator);
        this.floor = floor;
    }

    @Override
    public void replacer(Location location) {
        location.getBlock().setType(floor);

        Location next = nextMove.apply(location);

        for (ReplacerValidator validator : validators) {
            if (!validator.test(next, next.getBlock().getType(), 0)) {
                return;
            }
        }

        super.replacer(next);
    }

}
