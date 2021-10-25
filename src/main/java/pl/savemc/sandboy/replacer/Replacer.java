package pl.savemc.sandboy.replacer;

import org.bukkit.Location;

public interface Replacer {

    void replacer(Location location);

    void addValidator(ReplacerValidator validator);

    void removeValidator(ReplacerValidator validator);

}
