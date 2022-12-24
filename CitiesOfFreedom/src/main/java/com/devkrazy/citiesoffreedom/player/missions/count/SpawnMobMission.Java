public class GolemSpawnMission extends CountMission {

    public GolemSpawnMission(String name, String description, Player player, Material guiMaterial, int xpReward, int emeraldsReward, int goal, MissionScope missionScope) {
        super(name, description, player, guiMaterial, xpReward, emeraldsReward, goal, missionScope);
    }

    @Override
    public void processEvent(Event event) {
        if (event instanceof EntitySpawnEvent) {
            Entity entity = ((EntitySpawnEvent) event).getEntity();
            if (entity.getType() == EntityType.IRON_GOLEM || entity.getType() == EntityType.SNOW_GOLEM) {
                this.incrementCounterOf(1);
                this.checkAdvancementAndReward();
            }
        }
    }
}
