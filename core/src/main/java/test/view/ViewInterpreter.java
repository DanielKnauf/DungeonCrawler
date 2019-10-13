package test.view;

import model.components.GameFigure;
import model.fighting.Move;
import model.world.Direction;
import model.world.Dungeon;
import model.world.room.DungeonRoom;
import view.DungeonView;
import viewcontroller.ViewStateInterpreter;
import viewcontroller.request.api.display.DisplayRequestBase;
import viewcontroller.request.api.event.EventRequest;

public class ViewInterpreter implements ViewStateInterpreter {

    private final View view;
    private final FightingView fightingView;
    private final DungeonView dungeonView;

    public ViewInterpreter(View view,
                           FightingView fightingView,
                           DungeonView dungeonView) {
        this.view = view;
        this.fightingView = fightingView;
        this.dungeonView = dungeonView;
    }

    @Override
    public void handleRequest(EventRequest eventRequest) {
        System.out.println("***** Request for " + eventRequest.getClass().getSimpleName() + " - " + eventRequest.getEvent() + " *****");

        if (eventRequest.getEvent() != null) {
            switch (eventRequest.getEvent()) {
                case ENTER_DUNGEON:
                    dungeonView.playerEntersDungeon(eventRequest);
                    break;
                case LEAVE_DUNGEON:
                    dungeonView.heroLeavesDungeon((DisplayRequestBase<GameFigure>) eventRequest);
                    break;
                case DUNGEON_MAP:
                    dungeonView.displayDungeon((DisplayRequestBase<Dungeon>) eventRequest);
                    break;
                case DIRECTION_INPUT:
                    dungeonView.requestDirection((DisplayRequestBase<Direction>) eventRequest);
                    break;
                case HEALTH_BAR:
                    view.displayHealth((DisplayRequestBase<GameFigure>) eventRequest);
                    break;
                case MONSTER_ENCOUNTER:
                    fightingView.displayMonsterEncounter(eventRequest);
                    break;
                case MONSTER_MOVEMENT:
                    dungeonView.displayMonsterMovement((DisplayRequestBase<DungeonRoom>) eventRequest);
                    break;
                case HERO_IS_DEAD:
                    dungeonView.displayEndOfAdventure(eventRequest);
                    break;
                case CHOOSE_MOVE:
                    fightingView.heroChooses((DisplayRequestBase<Move>) eventRequest);
                    break;
                case INTRODUCE_FIGHT_ROUND:
                    fightingView.introduceRound((DisplayRequestBase<Move>) eventRequest);
                    break;
                case FIGHT_RESULT:
                    fightingView.displayResult((DisplayRequestBase<GameFigure>) eventRequest);
                    break;
                case FINALIZE_FIGHT_ROUND:
                    fightingView.finalizeRound((DisplayRequestBase<GameFigure>) eventRequest);
                    break;
                default:
                    // do nothing
                    eventRequest.isFinished();
            }
        }
    }
}
