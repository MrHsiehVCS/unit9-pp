package u9pp.MonsterFighter;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.verify;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertAll;

import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class SlowMonsterTests {

    private final String NAME = "TestSlowMonster"; 
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    private final int EXPERIENCE = 5; 
    SlowMonster testMonster;
    Combatant target;

    @BeforeEach
    public void beforeEach() {
        testMonster = new SlowMonster(NAME, MAX_HEALTH, ATTACK, EXPERIENCE);
        testMonster = Mockito.spy(testMonster);
        target = new Hero("TestHero", MAX_HEALTH, ATTACK);
        target = Mockito.spy(target);
    }

    @Test
    public void slowMonster_isChildOfCombatant() {
        assertTrue( testMonster instanceof Combatant);        
    }

    @Test
    public void slowMonster_isChildOfMonster() {
        assertTrue( testMonster instanceof Monster);        
    }

    @Test
    public void constructor_callsSuperConstructorCorrectly() {
        assertTrue(testMonster.canFight(), "After calling the constructor, testMonster.canFight() should be true");
        assertEquals(MAX_HEALTH, testMonster.getMaxHealth(), "After calling the constructor, testMonster.getMaxHealth() should be the given maxHealth");
        assertEquals(MAX_HEALTH, testMonster.getHealth(), "After calling the constructor, testMonster.getHealth() should be the give maxHealth");
        assertEquals(ATTACK, testMonster.getAttackPower(), "After calling the constructor, testMonster.getAttack() should be the given attack");
        assertEquals(EXPERIENCE, testMonster.getExpGiven(), "After calling the constructor, testMonster.getExpGiven() should be equal to the given experience");
    }



    @Test 
    public void takeTurn_firstTurn_DoesNoDamage() {
        testMonster.takeTurn(target);
        assertEquals(MAX_HEALTH, target.getHealth(), "on 1st turn, testMonster should do no damage to the given target");
        verify(target, Mockito.never().description("on 1st turn, testMonster should NOT call takeDamage on its target")).getAttacked(ATTACK);
    }

    @Test
    public void takeTurn_firstTurn_returnsStringWithProperInfo() {
        String s = testMonster.takeTurn(target);
        assertFalse(s.isBlank(), "testMonster.takeTurn() is returning a blank string.");
        assertAll(
            () -> assertTrue(s.contains("" + testMonster.getName()), "1st takeTurn should return string with monster's name"),
            () -> assertFalse(s.contains("" + testMonster.getAttackPower()), "1st takeTurn should NOT return a string with monster's attack"),
            () -> assertFalse(s.contains("" + target.getName()), "1st takeTurn should NOT return a string with target's name")
        );
    }

    @Test 
    public void takeTurn_secondTurn_DoesDamage() {
        testMonster.takeTurn(target);
        testMonster.takeTurn(target);
        assertEquals(MAX_HEALTH-ATTACK, target.getHealth(), "After 2 turns, testMonster should do damage to the given target");
        verify(target, Mockito.times(1).description("After 2 turns, testMonster should call takeDamage once on its target")).getAttacked(ATTACK);
    }

    @Test
    public void takeTurn_secondTurn_returnsStringWithProperInfo() {
        testMonster.takeTurn(target);
        String s = testMonster.takeTurn(target);
        assertFalse(s.isBlank(), "testMonster.takeTurn() is returning a blank string.");
        assertAll(
            () -> assertTrue(s.contains("" + target.getName()), "2nd takeTurn should return string with target's name"),
            () -> assertTrue(s.contains("" + testMonster.getName()), "2nd takeTurn should return string with monster's name"),
            () -> assertTrue(s.contains("" + testMonster.getAttackPower()), "2nd takeTurn should return string with monster's attack")
        );
    }



}
