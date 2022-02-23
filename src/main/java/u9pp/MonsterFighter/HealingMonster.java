package u9pp.MonsterFighter;

public class HealingMonster extends Monster {
    private int healingPerTurn = 0;
    public HealingMonster(String name, int maxHealth, int attack, int expGiven, int healingPerTurn) {
        super(name, maxHealth, attack, expGiven);
        this.healingPerTurn = healingPerTurn;
    }

    public int getHealingPerTurn() {
        return this.healingPerTurn;
    }

    public String takeTurn(Combatant target) {
        String output = "";
        this.heal(healingPerTurn);
        output += String.format("%s healed for %s health. ", this.getName(), healingPerTurn);
        output += super.takeTurn(target);
        return output;
    }
}
