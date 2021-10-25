package pl.savemc.sandboy.replacer;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import panda.std.function.TriPredicate;

import java.util.Arrays;

@FunctionalInterface
public interface ReplacerValidator extends TriPredicate<Location, Material, Integer> {

    boolean test(Location nextLocation, Material nextType, Integer round);

    ReplacerValidator WORLD_HEIGHT = (nextLocation, nextType, round) -> {
        World world = nextLocation.getWorld();

        if (world == null) {
            return false;
        }

        return nextLocation.getY() > world.getMinHeight() && nextLocation.getY() < world.getMaxHeight();
    };

    ReplacerValidator AIR_ONLY = (nextLocation, nextType, round) -> Arrays.asList(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR).contains(nextType);

    ReplacerValidator BEDROCK_STOP = (nextLocation, nextType, round) -> nextType != Material.BEDROCK;

}
