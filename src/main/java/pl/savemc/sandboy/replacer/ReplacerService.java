package pl.savemc.sandboy.replacer;

import org.bukkit.inventory.ItemStack;
import panda.std.Option;
import panda.std.Pair;
import panda.std.stream.PandaStream;
import pl.savemc.sandboy.utils.ItemBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReplacerService {

    private final Map<String, ReplacerSet> replacers = new ConcurrentHashMap<>();

    public void registerReplacer(String name, Replacer replacer, ItemStack item) {
        replacers.put(name, new ReplacerSet(replacer, item));
    }

    public Option<Replacer> getReplacer(String name) {
        return Option.of(replacers.get(name)).map(Pair::getFirst);
    }

    public Option<Replacer> getReplacer(ItemStack item) {
        return PandaStream.of(replacers.values())
                .find(replacerSet -> replacerSet.getSecond().isSimilar(item))
                .map(Pair::getFirst);
    }

    public Collection<Replacer> getReplacers() {
        return PandaStream.of(replacers.values())
                .map(Pair::getFirst)
                .toList();
    }

    public List<ItemStack> getReplacersItems() {
        return PandaStream.of(replacers.values())
                .map(Pair::getSecond)
                .toList();
    }

    public RegisterBuilder registerBuilder() {
        return new RegisterBuilder(this);
    }

    public static class ReplacerSet extends Pair<Replacer, ItemStack> {

        public ReplacerSet(Replacer first, ItemStack second) {
            super(first, second);
        }

    }

    public static class RegisterBuilder {

        private final ReplacerService replacerService;
        private String name;
        private Replacer replacer;
        private ItemStack item;

        public RegisterBuilder(ReplacerService replacerService) {
            this.replacerService = replacerService;
        }

        public RegisterBuilder name(String name) {
            this.name = name;
            return this;
        }

        public RegisterBuilder replacer(Replacer replacer) {
            this.replacer = replacer;
            return this;
        }

        public RegisterBuilder item(ItemStack item) {
            this.item = item;
            return this;
        }

        public RegisterBuilder item(ItemBuilder item) {
            this.item = item.build();
            return this;
        }

        public ReplacerService register() {
            if (name == null || replacer == null || item == null) {
                throw new NullPointerException();
            }

            replacerService.registerReplacer(name, replacer, item);
            return replacerService;
        }

    }

}
