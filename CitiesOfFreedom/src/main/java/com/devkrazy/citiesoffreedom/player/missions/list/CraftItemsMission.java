import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.inventory.CraftItemEvent;
import org.bukkit.inventory.ItemStack;

public class CraftItemsMission extends ListMission<Material> {

    public CraftItemsMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, Material... initialItems) {
        super(name,description, player, guiMaterial, xpReward, emeraldsReward, missionScope, initialItems);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof CraftItemEvent) {
            ItemStack result = ((CraftItemEvent) event).getRecipe().getResult();
            if (result.getType().isBlock()) {
                this.completeItem(result.getType());
                this.checkAdvancementAndReward();
            }
        }
    }
}
