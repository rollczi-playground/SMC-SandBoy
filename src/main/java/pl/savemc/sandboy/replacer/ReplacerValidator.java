package pl.savemc.sandboy.replacer;

import org.bukkit.Location;
import org.bukkit.Material;

import java.util.Arrays;
import java.util.function.BiPredicate;

@FunctionalInterface
public interface ReplacerValidator extends BiPredicate<Location, Material> {

    ReplacerValidator TO_BEDROCK = (location, next) -> location.getBlock().getType() != Material.BEDROCK;

    ReplacerValidator ONLY_AIR = (location, next) -> Arrays.asList(Material.AIR, Material.CAVE_AIR, Material.VOID_AIR).contains(location.getBlock().getType());

}
