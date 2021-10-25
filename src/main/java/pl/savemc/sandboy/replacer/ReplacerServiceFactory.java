package pl.savemc.sandboy.replacer;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import pl.savemc.sandboy.replacer.implementations.FloorReplacer;
import pl.savemc.sandboy.replacer.implementations.StandardReplacer;
import pl.savemc.sandboy.utils.ItemBuilder;

public class ReplacerServiceFactory {

    public static ReplacerService createDefault() {
        ReplacerService replacerService = new ReplacerService();

        // TODO: Wszystko dodać do konfiguracji, na razie będzie bez ;P

        replacerService.registerBuilder()
                .name("boyFarmer")
                .replacer(new StandardReplacer(Material.OBSIDIAN, NextMove.DOWN, ReplacerValidator.AIR_ONLY))
                .item(new ItemBuilder(Material.OBSIDIAN)
                        .setName("&9Boy Farmer")
                        .setLore("Po postawieniu niszczy wszystkie bloki pod sobą,", "dopóki nie napotka na drodze bedrocka.")
                        .setEnchantment(Enchantment.DURABILITY, 10)
                ).register();

        replacerService.registerBuilder()
                .name("sandFarmer")
                .replacer(new FloorReplacer(Material.SAND, Material.SANDSTONE, NextMove.UP, ReplacerValidator.AIR_ONLY))
                .item(new ItemBuilder(Material.SAND)
                        .setName("&9Sand Farmer")
                        .setLore("Po postawieniu tworzy ścianę z obsydianu na głębokość 80 kratek w dół.", "Kończy pracę, gdy napotka blok.")
                        .setEnchantment(Enchantment.DURABILITY, 10)
                ).register();

        replacerService.registerBuilder()
                .name("digger")
                .replacer(new StandardReplacer(Material.AIR, NextMove.DOWN, ReplacerValidator.BEDROCK_STOP))
                .item(new ItemBuilder(Material.BEDROCK)
                        .setName("&9Kopacz")
                        .setLore("Po postawieniu buduje ścianę z piasku na wysokość 60 kratek w górę.", "Kończy pracę, gdy napotka blok.")
                        .setEnchantment(Enchantment.DURABILITY, 10)
                ).register();

        return replacerService;
    }

}
