import controller.AdventureController;
import controller.DungeonController;
import controller.FightController;
import model.theWorld.DungeonBuilder;
import model.theWorld.DungeonBuilderUtils;
import model.theWorld.map.DungeonMapBuilder;
import model.utils.RandomNumberGenerator;
import view.DungeonView;
import view.FightingView;
import view.View;

public class AdventureTime {

    public static void main(String[] args) {
        final RandomNumberGenerator numberGenerator = new RandomNumberGenerator();

        final View view = new View();
        final FightingView fightingView = new FightingView();
        final DungeonView dungeonView = new DungeonView();

        final FightController fightController = new FightController(fightingView);

        final DungeonMapBuilder dungeonMapBuilder = new DungeonMapBuilder();
        final DungeonBuilderUtils builderUtils = new DungeonBuilderUtils(numberGenerator);
        final DungeonBuilder dungeonBuilder = new DungeonBuilder(builderUtils, dungeonView, dungeonMapBuilder);

        final DungeonController dungeonController = new DungeonController(view, dungeonBuilder, fightController);
        final AdventureController adventureController = new AdventureController(view, dungeonController);

        adventureController.goOnAnAdventure();
    }
}
