package pl.savemc.sandboy.replacer;

import org.bukkit.Location;

@FunctionalInterface
public interface Replacer {

    void replacer(Location location);

}
