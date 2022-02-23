package u9pp.MonsterFighter;


public class SlowMonster extends Monster { 

    boolean shouldAttack = false;

    public SlowMonster(String name, int maxHealth, int attack, int expGiven) {
        super(name, maxHealth, attack, expGiven);
    }

    public String takeTurn(Combatant target) {
        String output = "";
        if(shouldAttack) {
            output = super.takeTurn(target);
        } else {
            output = String.format("%s is lazing about...", this.getName());
        }
        shouldAttack = !shouldAttack;
        return output;
    }
}
