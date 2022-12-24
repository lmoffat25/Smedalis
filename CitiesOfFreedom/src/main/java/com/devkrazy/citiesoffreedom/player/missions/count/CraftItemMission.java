
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class ItemCraftMission extends CountMission {

    private Material itemType;

    public ItemCraftMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, Material itemType) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.itemType = itemType;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof CraftItemEvent) {
            ItemStack result = ((CraftItemEvent) event).getRecipe().getResult();
            if (result.getType() == this.itemType) {
                this.incrementCounterOf(result.getAmount());
                this.checkAdvancementAndReward();
            }
        }
    }
}
