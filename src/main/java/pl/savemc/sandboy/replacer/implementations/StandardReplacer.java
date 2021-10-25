package pl.savemc.sandboy.replacer.implementations;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import pl.savemc.sandboy.SMCSandBoy;
import pl.savemc.sandboy.replacer.NextMove;
import pl.savemc.sandboy.replacer.Replacer;
import pl.savemc.sandboy.replacer.ReplacerValidator;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class StandardReplacer implements Replacer {

    protected final Material to;
    protected final NextMove nextMove;
    protected final Set<ReplacerValidator> validators = new HashSet<>();

    public StandardReplacer(Material to, NextMove nextMove, ReplacerValidator... validators) {
        this.to = to;
        this.nextMove = nextMove;
        this.validators.addAll(Arrays.asList(validators));
        this.validators.add(ReplacerValidator.WORLD_HEIGHT);
    }

    @Override
    public void replacer(Location location) {
        this.nextReplace(location, 1);
    }

    @Override
    public void addValidator(ReplacerValidator validator) {
        validators.add(validator);
    }

    @Override
    public void removeValidator(ReplacerValidator validator) {
        validators.remove(validator);
    }

    private void nextReplace(Location location, int round) {
        location.getBlock().setType(to);

        Location next = nextMove.apply(location);

        for (ReplacerValidator validator : validators) {
            if (!validator.test(next, next.getBlock().getType(), round)) {
                return;
            }
        }

        Bukkit.getScheduler().runTaskLater(SMCSandBoy.getInstance(), () -> nextReplace(next, round + 1), 10L);
    }

}
