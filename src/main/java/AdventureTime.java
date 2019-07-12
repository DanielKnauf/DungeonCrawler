import controller.AdventureController;
import controller.DungeonController;
import controller.FightController;
import model.world.DungeonBuilder;
import model.world.DungeonBuilderUtils;
import model.world.map.DungeonMapBuilder;
import utils.RandomNumberGenerator;
import view.DungeonView;
import view.DungeoningView;
import view.FightingView;
import view.View;

public class AdventureTime {

    public static void main(String[] args) {
        final RandomNumberGenerator numberGenerator = new RandomNumberGenerator();

        final View view = new View();
        final FightingView fightView = new FightingView();
        final DungeonView dungeonView = new DungeoningView();

        final FightController fightController = new FightController(fightView);

        final DungeonMapBuilder dungeonMapBuilder = new DungeonMapBuilder();
        final DungeonBuilderUtils builderUtils = new DungeonBuilderUtils(numberGenerator);
        final DungeonBuilder dungeonBuilder = new DungeonBuilder(builderUtils, dungeonView, dungeonMapBuilder);

        final DungeonController dungeonController = new DungeonController(view, dungeonBuilder, fightController);
        final AdventureController adventureController = new AdventureController(view, dungeonController);

        adventureController.goOnAnAdventure();
    }
}
