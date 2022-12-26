import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.player.PlayerTeleportEvent;

public class NPCMoveMission extends CountMission {

    private String NPCName;
    private int targetX;
    private int targetY;
    private int targetZ;

    public NPCMoveMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope, String NPCName, int targetX, int targetY, int targetZ) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
        this.NPCName = NPCName;
        this.targetX = targetX;
        this.targetY = targetY;
        this.targetZ = targetZ;
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof PlayerTeleportEvent) {
            PlayerTeleportEvent teleportEvent = (PlayerTeleportEvent) event;
            if (teleportEvent.getEntity() instanceof NPC && teleportEvent.getEntity().getCustomName().equals(NPCName) && teleportEvent.getTo().getBlockX() == targetX && teleportEvent.getTo().getBlockY() == targetY && teleportEvent.getTo().getBlockZ() == targetZ) {
                this.incrementCounter();
                this.checkAdvancementAndReward();
            }
        }
    }
}
