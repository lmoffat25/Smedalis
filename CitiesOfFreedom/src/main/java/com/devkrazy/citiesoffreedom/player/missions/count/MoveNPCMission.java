import org.bukkit.Material;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerInteractEntityEvent;

public class NPCMoveMission extends CountMission {

    private int targetX;
    private int targetY;
    private int targetZ;
    private String npcName;
    private EntityType npcType;

    public NPCMoveMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, int targetX, int targetY, int targetZ, String npcName, EntityType npcType) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
        this.npcName = npcName;
        this.npcType = npcType;
    }

    public boolean isCorrectNPC(Entity entity) {
        // Check if the entity is the correct type and has the correct name
        return entity.getType() == npcType && (npcName.equals(entity.getCustomName()) || npcName.equals(entity.getName()));
    }

        @Override
    public void processEvent(Event event) {
        if (event instanceof PlayerInteractEntityEvent) {
            PlayerInteractEntityEvent interactEvent = (PlayerInteractEntityEvent) event;
            Entity entity = interactEvent.getRightClicked();
            // Check if the entity is within the target radius and is the correct NPC
            if (Math.abs(entity.getLocation().getBlockX() - targetX) <= 10 && Math.abs(entity.getLocation().getBlockY() - targetY) <= 10 && Math.abs(entity.getLocation().getBlockZ() - targetZ) <= 10 && isCorrectNPC(entity)) {
                this.incrementCounter();
                this.checkAdvancementAndReward();
            }
        }
    }
}
