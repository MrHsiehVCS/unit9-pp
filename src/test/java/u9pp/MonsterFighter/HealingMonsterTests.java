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
public class HealingMonsterTests {

    private final String NAME = "TestSlowMonster"; 
    private final int MAX_HEALTH = 10; 
    private final int ATTACK = 3; 
    private final int EXPERIENCE = 5; 
    private final int HEAL = 2;
    HealingMonster testMonster;
    Combatant target;


    @BeforeEach
    public void beforeEach() {
        testMonster = new HealingMonster(NAME, MAX_HEALTH, ATTACK, EXPERIENCE, HEAL);
        testMonster = Mockito.spy(testMonster);

        target = new Hero("TestHero", MAX_HEALTH, ATTACK);;
        target = Mockito.spy(target);
    }

    @Test
    public void healingMonster_isChildOfCombatant() {
        assertTrue( testMonster instanceof Combatant, "HealingMonster class must be a child class of Combatant");        
    }

    @Test
    public void slowMonster_isChildOfMonster() {
        assertTrue( testMonster instanceof Monster, "HealingMonster class must be a child of Monster");        
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
    public void constructor_setsHealCorrectly() {
        assertEquals(HEAL, testMonster.getHealingPerTurn(), "After calling the constructor, testMonster.getHealingPerTurn() should return the given Heal amount.");
    }



    @Test 
    public void takeTurn_DoesDamage() {
        testMonster.takeTurn(target);
        assertEquals(MAX_HEALTH-ATTACK, target.getHealth(), "testMonster should do damage to the given target");
        verify(target, Mockito.times(1).description("testMonster should call takeDamage on its target")).getAttacked(ATTACK);
    }

    @Test 
    public void takeTurn_doesHeal() {
        testMonster.getAttacked(ATTACK);
        assertEquals(MAX_HEALTH-ATTACK, testMonster.getHealth(), "testMonster should have takeDamage work as in Monster");

        testMonster.takeTurn(target);
        assertEquals(MAX_HEALTH-ATTACK+HEAL, testMonster.getHealth(), "testMonster should heal when taking turn");
        verify(testMonster, Mockito.times(1).description("testMonster.takeTurn should call .heal(int) exactly once")).heal(HEAL);
    }

    @Test
    public void takeTurn_returnsStringWithProperInfo() {
        String s = testMonster.takeTurn(target);
        assertFalse(s.isBlank(), "testMonster.takeTurn() is returning a blank string.");
        assertAll(
            () -> assertTrue(s.contains("" + testMonster.getName()), "takeTurn should return string with monster's name"),
            () -> assertTrue(s.contains("" + testMonster.getAttackPower()), "takeTurn should return a string with monster's attack"),
            () -> assertTrue(s.contains("" + target.getName()), "takeTurn should return a string with target's name"),
            () -> assertTrue(s.contains("" + testMonster.getHealingPerTurn()), "takeTurn should return a string with the amount healed")
        );
    }

}
