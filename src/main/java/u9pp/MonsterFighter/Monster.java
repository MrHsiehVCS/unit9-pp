package u9pp.MonsterFighter;


public class Monster extends Combatant {

    private int expGiven = 0;

    public Monster(String name, int maxHealth, int attack, int expGiven) {
        super(name, maxHealth, attack);
        this.expGiven = Math.max(expGiven, 0);
    }

    public String takeTurn(Combatant target) {
        target.getAttacked(this.getAttackPower());
        return String.format("%s hit %s with %s power!", this.getName(), target.getName(), this.getAttackPower());
    }

    public int getExpGiven() {
        return expGiven;
    }
}