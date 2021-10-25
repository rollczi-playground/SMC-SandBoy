package pl.savemc.sandboy.replacer;

import org.apache.commons.lang.Validate;
import org.bukkit.inventory.ItemStack;
import panda.std.Option;
import panda.std.stream.PandaStream;
import pl.savemc.sandboy.utils.ItemBuilder;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ReplacerService {

    private final Map<String, ReplacerData> replacers = new ConcurrentHashMap<>();

    public void registerReplacer(String name, Replacer replacer, ItemBuilder item) {
        replacers.put(name, new ReplacerData(replacer, item));
    }

    public Option<ReplacerData> unregisterReplacer(String name) {
        return Option.of(replacers.remove(name));
    }

    public Option<Replacer> getReplacer(String name) {
        return Option.of(replacers.get(name)).map(ReplacerData::getReplacer);
    }

    public Option<ReplacerData> getReplacerData(String name) {
        return Option.of(replacers.get(name));
    }

    public Option<Replacer> getReplacer(ItemStack item) {
        return PandaStream.of(replacers.values())
                .find(replacerData -> replacerData.getItem().isSimilar(item))
                .map(ReplacerData::getReplacer);
    }

    public Collection<Replacer> getReplacers() {
        return PandaStream.of(replacers.values())
                .map(ReplacerData::getReplacer)
                .toList();
    }

    public List<ItemBuilder> getReplacersItems() {
        return PandaStream.of(replacers.values())
                .map(ReplacerData::getItem)
                .toList();
    }

    public RegisterBuilder registerBuilder() {
        return new RegisterBuilder(this);
    }

    public static class ReplacerData {

        private final Replacer replacer;
        private ItemBuilder item;

        public ReplacerData(Replacer replacer, ItemBuilder item) {
            this.replacer = replacer;
            this.item = ItemBuilder.of(item);
        }

        public Replacer getReplacer() {
            return replacer;
        }

        public ItemBuilder getItem() {
            return ItemBuilder.of(item);
        }

        public void setItem(ItemBuilder item) {
            Validate.notNull(item, "The item is null");
            this.item = ItemBuilder.of(item);
        }

        public void setItem(ItemStack item) {
            Validate.notNull(item, "The item is null");
            this.item = ItemBuilder.of(item);
        }

    }

    public static class RegisterBuilder {

        private final ReplacerService replacerService;
        private String name;
        private Replacer replacer;
        private ItemBuilder item;

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
            this.item = ItemBuilder.of(item);
            return this;
        }

        public RegisterBuilder item(ItemBuilder item) {
            this.item = ItemBuilder.of(item);
            return this;
        }

        public void register() {
            Validate.notNull(name, "The name is null");
            Validate.notNull(replacer, "The replacer is null");
            Validate.notNull(item, "The item is null");

            replacerService.registerReplacer(name, replacer, item);
        }

    }

}
