package xyz.motz.classe.util.mechanics;

import de.tr7zw.nbtapi.NBTItem;
import de.tr7zw.nbtapi.NBTTileEntity;
import net.kyori.adventure.text.minimessage.MiniMessage;
import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.Tag;
import org.bukkit.block.Block;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.RecipeChoice;
import org.bukkit.inventory.ShapedRecipe;
import xyz.motz.classe.Classe;

public class BreedingDispenser {
    public static String id() {
        return "breeding_dispenser";
    }

    public static Recipe recipe() {
        ShapedRecipe recipe = new ShapedRecipe(new NamespacedKey(Classe.getInstance(), id()), itemStack());
        recipe.shape("CWC", "CDC", "SSS");
        recipe.setIngredient('C', Material.COMPOSTER);
        recipe.setIngredient('W', Material.RED_WOOL);
        recipe.setIngredient('D', Material.DISPENSER);
        recipe.setIngredient('S', new RecipeChoice.MaterialChoice(Tag.WOODEN_SLABS));

        return recipe;
    }

    public static boolean isBreedingDispenser(Block block) {
        if (!(block.getType() == Material.DISPENSER)) return false;
        return id().equals(new NBTTileEntity(block.getState()).getPersistentDataContainer().getString("classe.type"));
    }

    public static boolean isBreedingDispenser(ItemStack item) {
        return id().equals(new NBTItem(item).getString("classe.type"));
    }

    public static ItemStack itemStack() {
        ItemStack item = new ItemStack(Material.DISPENSER); // create the base item
        item.editMeta(meta -> meta.displayName(MiniMessage.miniMessage().deserialize("<reset>Breeding Dispenser")));

        NBTItem nbtItem = new NBTItem(item); // get the NBTItem

        nbtItem.setString("classe.type", id()); // set the type to the id

        return nbtItem.getItem(); // return the refreshed item
    }
}
