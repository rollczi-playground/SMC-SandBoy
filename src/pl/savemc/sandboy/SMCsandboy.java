package pl.savemc.sandboy;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;
import pl.savemc.core.SMCcore;

public class SMCsandboy extends JavaPlugin implements Listener, CommandExecutor {

    private Registers registers;

    public static final String preFix = org.bukkit.ChatColor.DARK_GRAY + " » " + org.bukkit.ChatColor.GRAY;
    public static final String subFix = org.bukkit.ChatColor.DARK_GRAY + " « " + org.bukkit.ChatColor.GRAY;

    @Override
    public void onEnable() {
        PluginManager pm = Bukkit.getServer().getPluginManager();

        pm.registerEvents(this, this);
        registers = new Registers();
    }

    @Override
    public void onDisable() {
        if (this.getServer().getPluginManager().getPlugin("SMC-Core") != null) {
            if (this.getServer().getPluginManager().getPlugin("SMC-Core").isEnabled()) {
                this.getServer().getPluginManager().getPlugin("SMC-Core");
            }
        }
    }

    public SMCsandboy getInstance() {
        return this;
    }

    public Registers getRegisters() {
        return registers;
    }

    @EventHandler(priority = EventPriority.HIGHEST)
    public void onPlace(BlockPlaceEvent e) {

        if (e.isCancelled()) {
            return;
        }

        if (!e.getPlayer().getItemInHand().getItemMeta().hasDisplayName()) {
            return;
        }

        for (int i = 0; i < getRegisters().getSize(); i++) {

            if (e.getBlock().getType().equals(getRegisters().getItem(i).getType())) {

                if (e.getPlayer().getItemInHand().getItemMeta().getDisplayName().equals(getRegisters().getItem(i).getItemMeta().getDisplayName())) {

                    if (getRegisters().getItem(i).getType().equals(Material.BEDROCK)) {
                        e.getBlock().setType(Material.AIR);
                    }

                    new Replacer(e.getBlock().getLocation(), getRegisters().getType(i), this, getRegisters().getHeight(i), e.getPlayer());
                    return;
                }
            }
        }
    }

    @Override
    public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {

        if (cmd.getName().equalsIgnoreCase("sandboy")) {

            if (sender instanceof Player) {
                Player p = (Player) sender;

                if (p.hasPermission("pl.savemc.sandboy")) {

                    for(int i = 0; i < getRegisters().getSize(); i++) {
                        ItemStack item = getRegisters().getItem(i);
                        Inventory inv = p.getInventory();

                        if(isInvEmpy(inv, item)) {
                            inv.addItem(item);
                        } else {
                            p.getWorld().dropItemNaturally(p.getLocation(), item);
                        }
                    }

                    return true;
                }

                sender.sendMessage(preFix + "Nie posiadasz permisji do tego polecenia.");
                return true;
            }

            sender.sendMessage(preFix + "Tylko gracz może użyć tego polecenia.");
            return true;
        }

        return false;
    }

    public static boolean isInvEmpy(Inventory inv, ItemStack item) {

        if (inv.firstEmpty() != -1) return true;

        for (ItemStack itemi : inv.getContents()) {

            if(itemi == null) {
                return false;
            }

            if(itemi.isSimilar(item)) {

                if(itemi.getMaxStackSize() > itemi.getAmount()) {
                    return true;
                }

            }
        }

        return false;
    }
}



