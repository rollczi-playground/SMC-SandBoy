package pl.savemc.sandboy.replacer.implementations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import pl.savemc.sandboy.SMCSandBoy;
import pl.savemc.sandboy.replacer.NextMove;
import pl.savemc.sandboy.replacer.Replacer;
import pl.savemc.sandboy.replacer.ReplacerValidator;

public class StandardReplacer implements Replacer {

    protected final Material to;
    protected final NextMove nextMove;
    protected final ReplacerValidator validator;

    public StandardReplacer(Material to, NextMove nextMove, ReplacerValidator validator) {
        this.to = to;
        this.nextMove = nextMove;
        this.validator = validator;
    }

    @Override
    public void replacer(Location location) {
        this.nextReplace(location);
    }

    private void nextReplace(Location location) {
        location.getBlock().setType(to);

        Location next = nextMove.apply(location);

        if (!validator.test(next, to)) {
            return;
        }

        Bukkit.getScheduler().runTaskLater(SMCSandBoy.getInstance(), () -> replacer(next), 10L);
    }

}
