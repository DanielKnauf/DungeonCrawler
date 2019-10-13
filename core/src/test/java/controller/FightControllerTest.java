package controller;

import model.components.GameFigure;
import model.fighting.Move;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import viewcontroller.request.RequestHandler;
import viewcontroller.request.api.input.InputRequestCallback;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static viewcontroller.request.Event.*;

public class FightControllerTest {

    private final RequestHandler requestHandlerMock = Mockito.mock(RequestHandler.class);
    private final GameFigure heroMock = Mockito.mock(GameFigure.class);
    private final GameFigure monsterMock = Mockito.mock(GameFigure.class);

    private FightController controller;

    @Before
    public void setup() {
        controller = new FightController(requestHandlerMock);
    }

    @Test
    public void verify_moveSetting() {
        controller.startFightingRound(heroMock, monsterMock);

        Mockito.verify(monsterMock).makeMoveAtRandom();

        Mockito.verify(requestHandlerMock).startViewRequest(
                eq(CHOOSE_MOVE),
                any(InputRequestCallback.class),
                eq(Move.class),
                eq(Arrays.asList(Move.values()))
        );
    }

    @Test
    public void noMoves_noFight() {
        Mockito.when(monsterMock.getMove()).thenReturn(null);
        Mockito.when(heroMock.getMove()).thenReturn(null);

        controller.startFightingRound(heroMock, monsterMock);

        Mockito.verify(requestHandlerMock, Mockito.times(0)).startViewRequest(
                eq(INTRODUCE_FIGHT_ROUND),
                any(Move.class),
                any(Move.class)
        );
    }

    @Test
    public void bothSameMove_displayDraw() {
        Mockito.when(monsterMock.getMove()).thenReturn(Move.SPOCK);
        Mockito.when(heroMock.getMove()).thenReturn(Move.SPOCK);

        controller.startFightingRound(heroMock, monsterMock);

        triggerCallback();

        Mockito.verify(requestHandlerMock).startViewRequest(INTRODUCE_FIGHT_ROUND, Move.SPOCK, Move.SPOCK);
        Mockito.verify(requestHandlerMock).startViewRequest(FIGHT_RESULT, null, null);
        Mockito.verify(requestHandlerMock).startViewRequest(FINALIZE_FIGHT_ROUND, monsterMock, heroMock);
        Mockito.verify(heroMock, Mockito.times(0)).gotHit();
        Mockito.verify(monsterMock, Mockito.times(0)).gotHit();
    }

    @Test
    public void heroWins_displayResult_reduceMonsterHealth() {
        Mockito.when(monsterMock.getMove()).thenReturn(Move.PAPER);
        Mockito.when(heroMock.getMove()).thenReturn(Move.SCISSOR);

        controller.startFightingRound(heroMock, monsterMock);

        triggerCallback();

        Mockito.verify(requestHandlerMock).startViewRequest(INTRODUCE_FIGHT_ROUND, Move.PAPER, Move.SCISSOR);
        Mockito.verify(requestHandlerMock).startViewRequest(FIGHT_RESULT, heroMock, monsterMock);
        Mockito.verify(requestHandlerMock).startViewRequest(FINALIZE_FIGHT_ROUND, monsterMock, heroMock);
        Mockito.verify(monsterMock).gotHit();
    }

    @Test
    public void monsterWins_displayResult_reduceHeroHealth() {
        Mockito.when(monsterMock.getMove()).thenReturn(Move.SCISSOR);
        Mockito.when(heroMock.getMove()).thenReturn(Move.PAPER);

        controller.startFightingRound(heroMock, monsterMock);

        triggerCallback();

        Mockito.verify(requestHandlerMock).startViewRequest(INTRODUCE_FIGHT_ROUND, Move.SCISSOR, Move.PAPER);
        Mockito.verify(requestHandlerMock).startViewRequest(FIGHT_RESULT, monsterMock, heroMock);
        Mockito.verify(requestHandlerMock).startViewRequest(FINALIZE_FIGHT_ROUND, monsterMock, heroMock);
        Mockito.verify(heroMock).gotHit();
    }

    private void triggerCallback() {
        ArgumentCaptor<InputRequestCallback> captor = ArgumentCaptor.forClass(InputRequestCallback.class);

        Mockito.verify(requestHandlerMock).startViewRequest(
                eq(CHOOSE_MOVE),
                captor.capture(),
                eq(Move.class),
                eq(Arrays.asList(Move.values()))
        );

        InputRequestCallback<Move> callback = captor.getValue();

        callback.receiveAnswer(Move.SPOCK);
    }
}
