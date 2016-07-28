package net.samagames.dimensions.listeners;

import net.samagames.dimensions.Dimensions;
import net.samagames.dimensions.arena.RandomItem;
import net.samagames.dimensions.utils.Metadatas;
import net.samagames.tools.MojangShitUtils;
import org.bukkit.*;
import org.bukkit.block.Chest;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.EnchantmentStorageMeta;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.material.Dye;
import org.bukkit.potion.Potion;
import org.bukkit.potion.PotionType;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Updated by Reelwens on 28/07/16.
 */
public class ChestListener implements Listener {

    protected Dimensions plugin;
    protected ArrayList<RandomItem> items = new ArrayList<>();

    @SuppressWarnings("deprecation")
    public ChestListener(Dimensions plugin) {
        this.plugin = plugin;
        plugin.getServer().getPluginManager().registerEvents(this, plugin);

        // Ici on fait les registers de chaque item //

        // ARMURES //
        this.registerItem(new RandomItem(new ItemStack(Material.LEATHER_LEGGINGS, 1), 700));
        this.registerItem(new RandomItem(new ItemStack(Material.LEATHER_BOOTS, 1), 1000));
        this.registerItem(new RandomItem(new ItemStack(Material.LEATHER_CHESTPLATE, 1), 700));
        this.registerItem(new RandomItem(new ItemStack(Material.LEATHER_HELMET, 1), 1000));

        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_HELMET, 1), 700));
        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_BOOTS, 1), 700));
        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_CHESTPLATE, 1), 1000));
        registerItem(new RandomItem(new ItemStack(Material.CHAINMAIL_LEGGINGS, 1), 1000));

        registerItem(new RandomItem(new ItemStack(Material.IRON_LEGGINGS, 1), 700));
        registerItem(new RandomItem(new ItemStack(Material.IRON_BOOTS, 1), 700));
        registerItem(new RandomItem(new ItemStack(Material.IRON_CHESTPLATE, 1), 700));
        registerItem(new RandomItem(new ItemStack(Material.IRON_HELMET, 1), 700));

        registerItem(new RandomItem(new ItemStack(Material.DIAMOND_LEGGINGS, 1), 25));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND_BOOTS, 1), 25));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND_CHESTPLATE, 1), 25));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND_HELMET, 1), 25));

        // OUTILS ET ARMES //
        registerItem(new RandomItem(new ItemStack(Material.STONE_PICKAXE, 1), 1000));
        registerItem(new RandomItem(new ItemStack(Material.STONE_SWORD, 1), 2500));
        registerItem(new RandomItem(new ItemStack(Material.IRON_SWORD, 1), 500));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND_SWORD, 1), 50));

        // RESSOURCES //
        registerItem(new RandomItem(new ItemStack(Material.IRON_INGOT), 4000, new int[]{2, 3, 4, 5, 6}));
        registerItem(new RandomItem(new ItemStack(Material.DIAMOND), 50, new int[]{1,2,3}));
        registerItem(new RandomItem(new ItemStack(Material.COOKED_CHICKEN), 2000, new int[]{4, 5, 6, 7, 8, 9, 10}));
        registerItem(new RandomItem(new ItemStack(Material.COOKED_BEEF), 5000, new int[]{2, 3, 4, 5}));
        registerItem(new RandomItem(new ItemStack(Material.EXP_BOTTLE), 1000, new int[]{5, 6, 7, 8, 9, 10, 11, 12}));
        final Dye dye = new Dye();
        dye.setColor(DyeColor.BLUE);
        this.registerItem(new RandomItem(dye.toItemStack(), 3000, new int[]{4, 5, 6, 7, 8}));
        registerItem(new RandomItem(new ItemStack(Material.STICK), 2000, new int[]{2, 3, 4, 5}));
        registerItem(new RandomItem(new ItemStack(Material.WORKBENCH, 1), 1000));
        registerItem(new RandomItem(new ItemStack(Material.BOW), 1000));

        // POTIONS //
        registerItem(new RandomItem(new Potion(PotionType.INSTANT_HEAL).splash().toItemStack(1), 800));
        registerItem(new RandomItem(new Potion(PotionType.REGEN).toItemStack(1), 100));
        registerItem(new RandomItem(MojangShitUtils.getPotion("poison", true, true), 500));
        registerItem(new RandomItem(new Potion(PotionType.INSTANT_DAMAGE).splash().toItemStack(1), 500));
        registerItem(new RandomItem(new Potion(PotionType.SPEED).toItemStack(1), 500));

        // Enchants
        ItemStack sharpness = new ItemStack(Material.ENCHANTED_BOOK);
        EnchantmentStorageMeta meta = (EnchantmentStorageMeta) sharpness.getItemMeta();
        meta.addStoredEnchant(Enchantment.DAMAGE_ALL, 1, false);
        sharpness.setItemMeta(meta);

        ItemStack protection = new ItemStack(Material.ENCHANTED_BOOK);
        meta = (EnchantmentStorageMeta) protection.getItemMeta();
        meta.addStoredEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 1, false);
        protection.setItemMeta(meta);

        registerItem(new RandomItem(sharpness, 700));
        registerItem(new RandomItem(protection, 700));

        ItemStack bow = new ItemStack(Material.BOW);
        bow.addEnchantment(Enchantment.ARROW_DAMAGE, 1);
        registerItem(new RandomItem(bow, 300));
        // MISC //
        registerItem(new RandomItem(new ItemStack(Material.ARROW), 3000, new int[]{3, 4, 5, 6, 7, 8, 9, 10}));
        registerItem(new RandomItem(new ItemStack(Material.GOLDEN_APPLE), 500));

        ItemStack axe = new ItemStack(Material.IRON_AXE);
        ItemMeta imeta = axe.getItemMeta();
        imeta.setDisplayName(ChatColor.GOLD+"Hache de papy Sama");
        axe.setItemMeta(imeta);
        axe.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 2);
        axe.addUnsafeEnchantment(Enchantment.FIRE_ASPECT, 1);
        axe.addUnsafeEnchantment(Enchantment.DURABILITY, 2);
        registerItem(new RandomItem(axe, 5));
        registerItem(new RandomItem(new ItemStack(Material.GOLDEN_APPLE, 1, (short)1), 2));

        // NEW RANDOM ITEMS //
        registerItem(new RandomItem(new ItemStack(Material.SHIELD, 1), 200));
        registerItem(new RandomItem(new ItemStack(Material.ELYTRA, 1), 50));
        registerItem(new RandomItem(new ItemStack(Material.WATER_BUCKET, 1), 100));
        registerItem(new RandomItem(new ItemStack(Material.LAVA_BUCKET, 1), 50));
        registerItem(new RandomItem(new ItemStack(Material.ENDER_PEARL), 100, new int[]{1, 2, 3}));
    }

    public static void launchfw(Location loc, final FireworkEffect effect)
    {
        loc = loc.add(0.5,0.5,0.5);
        final Firework fw = (Firework) loc.getWorld().spawnEntity(loc, EntityType.FIREWORK);
        FireworkMeta fwm = fw.getFireworkMeta();
        fwm.addEffect(effect);
        fwm.setPower(0);
        fw.setFireworkMeta(fwm);
        Bukkit.getScheduler().runTaskLater(Dimensions.instance, fw::detonate, 2);
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

    @EventHandler
    public void onInventoryClose(final InventoryCloseEvent event) {
        final InventoryHolder holder = event.getInventory().getHolder();
        if (holder instanceof Chest) {
            final Chest chest = (Chest)holder;
            launchfw(chest.getLocation(), FireworkEffect.builder().withColor(new Color[] { Color.WHITE, Color.GRAY, Color.BLACK }).with(FireworkEffect.Type.STAR).build());
            chest.getBlock().setType(Material.AIR);
        }
    }
}