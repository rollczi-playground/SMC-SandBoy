package pl.savemc.sandboy;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

public class Replacer {
	
	private final SMCsandboy plugin;
	private final Player player;
	
	public Replacer(Location loc, Material m, SMCsandboy plugin, int height, Player player) {
		
		this.plugin = plugin;
		this.player = player;

		if (m.equals(Material.SAND)) {
			run(loc, m, true, height);
			return;
		}
		
		run(loc, m, false, height);
	}
	
	private void run (Location loc, Material m, boolean isUp, int height) {
		
		int UorD = -1;
		
		if (isUp) {
			
			if (check(loc, Material.SANDSTONE)) {
				return;
			}

			UorD = 1;
			loc.setY(loc.getY() + 1);
		}
		
		final int finalUorD = UorD;
		
		new BukkitRunnable() {
			int i = 0;
			
            @Override
            public void run() {

            	if (check(loc, m) || i >= height || loc.getBlockY() < 0 || loc.getBlockY() > 255) {

					player.sendActionBar(SMCsandboy.preFix + "Zakończono pracę!");
		    		this.cancel();
		    	}
            	
            	loc.setY(loc.getY() + finalUorD);
		    	i++;
            }
            
        }.runTaskTimerAsynchronously(plugin, 0L, 4L);
	}

	private boolean check(Location loc, Material m) {
		
		if (loc.getBlock().getType().equals(Material.BEDROCK)) {
			return true;
		}
		
		Location locCopy = loc.clone();
		Bukkit.getScheduler().runTask(plugin, () -> locCopy.getBlock().setType(m));
		
		return false;
	}
	
}
