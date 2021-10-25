package pl.savemc.sandboy.replacer;

import org.bukkit.Location;

import java.util.function.Function;

@FunctionalInterface
public interface NextMove extends Function<Location, Location> {

    NextMove UP = location -> location.clone().add(0, 1, 0);

    NextMove DOWN = location -> location.clone().add(0, - 1, 0);

}
