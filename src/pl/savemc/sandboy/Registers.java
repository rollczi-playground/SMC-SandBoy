package pl.savemc.sandboy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.mysql.fabric.xmlrpc.base.Array;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import net.md_5.bungee.api.ChatColor;


public class Registers {

	private final ArrayList<In> reg = new ArrayList<>();
	
	public Registers() {

		reg.add(new In(Material.BEDROCK, 0, SMCsandboy.preFix + ChatColor.BOLD + "Kopacz Fos" + SMCsandboy.subFix,
				Arrays.asList(
						"",
						SMCsandboy.preFix + "Po postawieniu niszczy wszystkie bloki pod sobą,      ",
						ChatColor.GRAY + "  dopóki nie napotka na drodze bedrocka.",
						""
				), Material.AIR, 255)
		);

		reg.add(new In(Material.OBSIDIAN, 0, SMCsandboy.preFix + ChatColor.BLUE + ChatColor.BOLD + "Boy Farmer" + SMCsandboy.subFix,
				Arrays.asList(
						"",
						SMCsandboy.preFix + "Po postawieniu tworzy ścianę z obsydianu na głębokość 80 kratek w dół.      ",
						ChatColor.GRAY + "  Kończy pracę, gdy napotka blok.",
						""
				), Material.OBSIDIAN, 80)
		);

		reg.add(new In(Material.SAND, 0, SMCsandboy.preFix + ChatColor.YELLOW + ChatColor.BOLD + "Sand Farmer" + SMCsandboy.subFix,
				Arrays.asList(
						"",
						SMCsandboy.preFix + "Po postawieniu buduje ścianę z piasku na wysokość 60 kratek w górę.      ",
						ChatColor.GRAY + "  Kończy pracę, gdy napotka blok.",
						""
				), Material.SAND, 60)
		);

	}
	
	public Material getType(int id) {
		return reg.get(id).getType();
	}
	
	public ItemStack getItem(int id) {
		return reg.get(id).getItem();
	}
	
	public int getHeight(int id) {
		return reg.get(id).getHeight();
	}
	
	public int getSize() {
		return reg.size();
	}

	
	
}

class In {

	private final ItemStack item;
	private final Material m;
	private final int height;
	
	public In(Material type, int data, String display, List<String> lore, Material m, int height) {
		ItemStack item = new ItemStack(type, 1, (byte) data);
		ItemMeta meta = item.getItemMeta();
		
		meta.setDisplayName(display);
		meta.setLore(lore);
		meta.addEnchant(Enchantment.DURABILITY, 10, true);
		item.setItemMeta(meta);
		
		this.item = item;
		this.m = m;
		this.height = height;
	}
	
	public ItemStack getItem() {
		return item;
	}
	
	public Material getType() {
		return m;
	}
	
	public int getHeight() {
		return height;
	}
	
}
