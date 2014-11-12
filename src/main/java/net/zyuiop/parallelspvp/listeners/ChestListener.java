package net.zyuiop.parallelspvp.listeners;

import net.zyuiop.parallelspvp.ParallelsPVP;
import net.zyuiop.parallelspvp.arena.ChestDescriptor;
import net.zyuiop.parallelspvp.arena.RandomItem;
import net.zyuiop.parallelspvp.utils.Metadatas;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Created by zyuiop on 26/09/14.
 */
public class ChestListener implements Listener {

    protected ParallelsPVP plugin;
    protected ArrayList<RandomItem> items = new ArrayList<RandomItem>();

    public ChestListener(ParallelsPVP plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Ici on fait les registers de chaque item //

        // ARMURES //
        registerItem(new RandomItem(new ItemStack(Material.LEATHER_LEGGINGS, 1), 2000));
        registerItem(new RandomItem(new ItemStack(Material.LEATHER_BOOTS, 1), 2000));
        registerItem(new RandomItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1), 2000));
        registerItem(new RandomItem(new ItemStack(Material.LEATHER_HELMET, 1), 2000));

        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_HELMET, 1), 1500));
        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_BOOTS, 1), 1300));
        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1), 1000));
        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1), 1000));

        registerItem(new RandomItem(new ItemStack(Material.IRON_LEGGINGS, 1), 10));
        registerItem(new RandomItem(new ItemStack(Material.IRON_BOOTS, 1), 10));
        registerItem(new RandomItem(new ItemStack(Material.IRON_CHESTPLATE, 1), 10));
        registerItem(new RandomItem(new ItemStack(Material.IRON_HELMET, 1), 10));

        // OUTILS ET ARMES //
        registerItem(new RandomItem(new ItemStack(Material.STONE_PICKAXE, 1), 3000));
        registerItem(new RandomItem(new ItemStack(Material.STONE_SWORD, 1), 2000));
        registerItem(new RandomItem(new ItemStack(Material.STONE_AXE, 1), 3000));
        registerItem(new RandomItem(new ItemStack(Material.IRON_SWORD, 1), 500));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND_SWORD, 1), 50));
        registerItem(new RandomItem(new ItemStack(Material.WOOD_SWORD, 1), 3000));

        // RESSOURCES //
        registerItem(new RandomItem(new ItemStack(Material.IRON_INGOT), 2000, new int[]{1, 2, 3, 4, 5, 6, 7}));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND), 50, new int[]{1,2,3}));
        registerItem(new RandomItem(new ItemStack(Material.BAKED_POTATO), 3000, new int[]{4, 5, 6, 7, 8, 9, 10}));
        registerItem(new RandomItem(new ItemStack(Material.COOKED_BEEF), 3000, new int[]{2, 3, 4, 5}));
        registerItem(new RandomItem(new ItemStack(Material.EXP_BOTTLE), 1000, new int[]{4, 5, 6, 7}));
        registerItem(new RandomItem(new ItemStack(Material.LOG), 2000, new int[]{2, 3, 4}));
        registerItem(new RandomItem(new ItemStack(Material.GLOWSTONE_DUST, 1), 400));
        registerItem(new RandomItem(new ItemStack(Material.STRING), 1000, new int[]{2, 3, 4}));
        registerItem(new RandomItem(new ItemStack(Material.FEATHER, 1), 1000));
        registerItem(new RandomItem(new ItemStack(Material.FLINT), 1000, new int[]{2, 3}));
        registerItem(new RandomItem(new ItemStack(Material.COBBLESTONE), 1500, new int[]{3, 4, 5, 6}));

        // POTIONS //
        registerItem(new RandomItem(new Potion(PotionType.INSTANT_HEAL).toItemStack(1), 1000));
        registerItem(new RandomItem(new Potion(PotionType.INSTANT_HEAL).splash().toItemStack(1), 700));
        registerItem(new RandomItem(new Potion(PotionType.INSTANT_HEAL, 2).toItemStack(1), 700));
        registerItem(new RandomItem(new Potion(PotionType.STRENGTH).toItemStack(1), 300));
        registerItem(new RandomItem(new Potion(PotionType.REGEN).toItemStack(1), 200));
        registerItem(new RandomItem(new Potion(PotionType.POISON).splash().toItemStack(1), 500));
        registerItem(new RandomItem(new Potion(PotionType.INSTANT_DAMAGE).splash().toItemStack(1), 1000));
        registerItem(new RandomItem(new Potion(PotionType.SPEED).toItemStack(1), 1000));

        // Enchants
        ItemStack sharpness = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) sharpness.getItemMeta();
        meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, false);
        sharpness.setItemMeta(meta);

        ItemStack protection = new ItemStack(Material.ENCHANTED_BOOK);
        meta = (EnchantmentStorageMeta) protection.getItemMeta();
        meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        protection.setItemMeta(meta);

        registerItem(new RandomItem(sharpness, 500));
        registerItem(new RandomItem(protection, 500));

        // MISC //
        registerItem(new RandomItem(new ItemStack(Material.ARROW), 3000, new int[]{3, 4, 5, 6, 7, 8, 9, 10}));
        registerItem(new RandomItem(new ItemStack(Material.GOLDEN_APPLE), 100));
        registerItem(new RandomItem(new ItemStack(Material.TNT), 1000, new int[]{1, 2, 3}));
        registerItem(new RandomItem(new ItemStack(Material.APPLE), 1000));

        ItemStack axe = new ItemStack(Material.IRON_AXE);
        ItemMeta imeta = axe.getItemMeta();
        imeta.setDisplayName(ChatColor.GOLD+"Hache du Troll des Cavernes");
        axe.setItemMeta(imeta);
        axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        axe.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
        axe.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
        registerItem(new RandomItem(axe, 5));
        registerItem(new RandomItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short)1), 2));
    }

    public void registerItem(RandomItem item) {
        this.items.add(item);
    }

    @EventHandler(priority = EventPriority.HIGH, ignoreCancelled = true)
    public void onChestOpen(PlayerInteractEvent event) {
        if (event.getAction().equals(Action.RIGHT_CLICK_BLOCK) && event.getClickedBlock().getType().equals(Material.CHEST)) {
            Chest chest = (Chest) event.getClickedBlock().getState();

            Boolean wasOpened = (Boolean) Metadatas.getMetadata(chest, "opened");
            if (wasOpened == null || !wasOpened) {
                Metadatas.setMetadata(chest, "opened", true);

                // Ici, on set le contenu du coffre
                Inventory inv = chest.getInventory();
                inv.clear();

                Collections.shuffle(items); // On shuffle a chaque fois. Si trop long faudra passer en async
                int addedItems = 0;
                Random rnd = new Random();
                int slot = 0;
                for (RandomItem item : items) {
                    if (addedItems > 20)
                        break;

                    int freq = item.getFrequency();
                    if (rnd.nextInt(10000) <= freq) {
                        ItemStack stack = item.getItem();
                        stack.setAmount(item.getQuantity());
                        while (inv.getItem(slot) != null)
                            slot++;
                        inv.setItem(slot, item.getItem());
                        addedItems++;
                    }
                    slot++;

                    if (slot > 26)
                        slot = 0;
                }

            }
        }
    }
}
