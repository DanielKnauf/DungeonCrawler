import controller.AdventureController;
import controller.DungeonController;
import controller.FightController;
import model.world.DungeonBuilder;
import model.world.DungeonBuilderUtils;
import model.world.map.DungeonMapBuilder;
import utils.RandomNumberGenerator;
import view.*;
import viewcontroller.request.RequestHandler;

public class AdventureTime {

    public static void main(String[] args) {
        final RandomNumberGenerator numberGenerator = new RandomNumberGenerator();

        //views
        final View view = new View();
        final FightingView fightView = new FightingView();
        final DungeonView dungeonView = new DungeoningView();
        final AdventuringView adventuringView = new AdventuringView();

        final ViewInterpreter viewInterpreter = new ViewInterpreter(view, fightView, dungeonView);
        final RequestHandler handler = new RequestHandler(viewInterpreter);

        //model
        final DungeonMapBuilder dungeonMapBuilder = new DungeonMapBuilder();
        final DungeonBuilderUtils builderUtils = new DungeonBuilderUtils(numberGenerator);
        final DungeonBuilder dungeonBuilder = new DungeonBuilder(builderUtils, dungeonMapBuilder);

        //controller
        final FightController fightController = new FightController(handler);
        final DungeonController dungeonController = new DungeonController(handler, dungeonBuilder, fightController);
        final AdventureController adventureController = new AdventureController(adventuringView, dungeonController);

        adventureController.goOnAnAdventure();
    }
}
