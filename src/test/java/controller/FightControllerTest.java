package controller;

import model.rpslsFighting.Move;
import model.theComponents.GameFigure;
import org.junit.Before;
import org.junit.Test;
import view.FightingView;

import static org.mockito.Mockito.*;

public class FightControllerTest {

    private final FightingView fightingViewMock = mock(FightingView.class);
    private final GameFigure heroMock = mock(GameFigure.class);
    private final GameFigure monsterMock = mock(GameFigure.class);

    private FightController controller;

    @Before
    public void setup() {
        controller = new FightController(fightingViewMock);
    }

    @Test
    public void verify_moveSetting() {
        controller.startFightingRound(heroMock, monsterMock);

        verify(monsterMock).makeMoveAtRandom();
        verify(fightingViewMock).heroChooses();
    }

    @Test
    public void noMoves_noFight() {
        when(monsterMock.getMove()).thenReturn(null);
        when(heroMock.getMove()).thenReturn(null);

        controller.startFightingRound(heroMock, monsterMock);

        verify(fightingViewMock, times(0)).introduceRound(any(Move.class), any(Move.class));
    }

    @Test
    public void bothSameMove_displayDraw() {
        when(monsterMock.getMove()).thenReturn(Move.SPOCK);
        when(heroMock.getMove()).thenReturn(Move.SPOCK);

        controller.startFightingRound(heroMock, monsterMock);

        verify(fightingViewMock).introduceRound(Move.SPOCK, Move.SPOCK);
        verify(fightingViewMock).displayResult(null, null);
        verify(fightingViewMock).finalizeRound(monsterMock, heroMock);
        verify(heroMock, times(0)).gotHit();
        verify(monsterMock, times(0)).gotHit();
    }

    @Test
    public void heroWins_displayResult_reduceMonsterHealth() {
        when(monsterMock.getMove()).thenReturn(Move.PAPER);
        when(heroMock.getMove()).thenReturn(Move.SCISSOR);

        controller.startFightingRound(heroMock, monsterMock);

        verify(fightingViewMock).introduceRound(Move.PAPER, Move.SCISSOR);
        verify(fightingViewMock).displayResult(heroMock, monsterMock);
        verify(fightingViewMock).finalizeRound(monsterMock, heroMock);
        verify(monsterMock).gotHit();
    }

    @Test
    public void monsterWins_displayResult_reduceHeroHealth() {
        when(monsterMock.getMove()).thenReturn(Move.SCISSOR);
        when(heroMock.getMove()).thenReturn(Move.PAPER);

        controller.startFightingRound(heroMock, monsterMock);

        verify(fightingViewMock).introduceRound(Move.SCISSOR, Move.PAPER);
        verify(fightingViewMock).displayResult(monsterMock, heroMock);
        verify(fightingViewMock).finalizeRound(monsterMock, heroMock);
        verify(heroMock).gotHit();
    }
}
