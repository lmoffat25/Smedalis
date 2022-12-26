import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.entity.EntitySpawnEvent;

public class SpawnMobListMission extends ListMission<EntityType> {

    public SpawnMobListMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, MissionScope missionScope, EntityType... initialItems) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, missionScope, initialItems);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof EntitySpawnEvent) {
            EntitySpawnEvent spawnEvent = (EntitySpawnEvent) event;
            EntityType entityType = spawnEvent.getEntity().getType();
            // Check if the spawned entity is in the list of mobs to track
            if (this.items.contains(entityType)) {
                this.completeItem(entityType);
                this.checkAdvancementAndReward();
            }
        }
    }
}
