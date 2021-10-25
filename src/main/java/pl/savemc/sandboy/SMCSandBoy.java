package pl.savemc.sandboy;

import net.dzikoysk.funnycommands.FunnyCommands;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;
import pl.savemc.sandboy.command.SandBoyGiveAllCommand;
import pl.savemc.sandboy.replacer.ReplacerPlaceListener;
import pl.savemc.sandboy.replacer.ReplacerService;
import pl.savemc.sandboy.replacer.ReplacerServiceFactory;

public class SMCSandBoy extends JavaPlugin {

    private static SMCSandBoy instance;

    private ReplacerService replacerService;
    private FunnyCommands funnyCommands;

    @Override
    public void onEnable() {
        instance = this;

        this.replacerService = ReplacerServiceFactory.createDefault();
        this.funnyCommands = FunnyCommands.configuration(() -> this)
                .command(SandBoyGiveAllCommand.class)
                .bind(resources -> resources.on(SMCSandBoy.class).assignInstance(this))
                .bind(resources -> resources.on(ReplacerService.class).assignInstance(replacerService))
                .install();

        Bukkit.getPluginManager().registerEvents(new ReplacerPlaceListener(this), this);
    }

    public ReplacerService getReplacerService() {
        return replacerService;
    }

    public FunnyCommands getFunnyCommands() {
        return funnyCommands;
    }

    public static SMCSandBoy getInstance() {
        return instance;
    }

}
