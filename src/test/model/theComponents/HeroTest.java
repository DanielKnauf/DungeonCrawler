package model.theComponents;

import org.junit.Before;
import org.junit.Test;

import static model.rpslsFighting.Move.SPOCK;
import static org.junit.Assert.*;

public class HeroTest {

    private Hero hero;

    @Before
    public void setup() {
        hero = new Hero("Sam", 10);
    }

    @Test
    public void constructor_allValuesCorrect() {
        assertEquals("Sam", hero.getName());
        assertEquals(10, hero.getHitPoints());
        assertNull(hero.getMove());
    }

    @Test
    public void constructor_nameIsMissing() {
        hero = new Hero(null, 10);

        assertEquals("Alice", hero.getName());
        assertEquals(10, hero.getHitPoints());
        assertNull(hero.getMove());
    }

    @Test
    public void constructor_hitPointsIsLowerZero() {
        hero = new Hero("John", -1);

        assertEquals("John", hero.getName());
        assertEquals(1, hero.getHitPoints());
        assertNull(hero.getMove());
    }

    @Test
    public void set_get_Move() {
        assertNull(hero.getMove());

        hero.setMove(SPOCK);

        assertEquals(SPOCK, hero.getMove());
    }

    @Test
    public void gotHit_notDead() {
        assertEquals(10, hero.getHitPoints());

        assertFalse(hero.gotHit());

        assertEquals(9, hero.getHitPoints());
    }


    @Test
    public void gotHit_isDead() {
        hero = new Hero("John", 1);

        assertEquals(1, hero.getHitPoints());

        assertTrue(hero.gotHit());

        assertEquals(0, hero.getHitPoints());
    }
}
