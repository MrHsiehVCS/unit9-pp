package u9pp.MonsterFighter;

public class Hero extends Combatant {
    private int exp = 0;
    private int level = 1;
    private boolean isDefending = false;
    private int focusLevel = 0;

    public Hero(String name, int maxHealth, int attack) {
        super(name, maxHealth, attack);
    }

    public String defend() {
        isDefending = true;
        return "The hero defended, halving any damage that they take this turn.";
    }

    public String focus() {
        focusLevel++;
        isDefending = false;
        if(focusLevel == 1) {
            return "The hero focused, doubling the power of their next attack.";
        } else if (focusLevel == 2) {
            return String.format("The hero focused harder, doubling the power of their next attack again.");
        } else {
            return String.format("The hero focused even harder, doubling the power of their next attack again.");
        }
    }
    
    public String attack(Combatant target) {
        String output = "The hero attacked for %s damage.";
        int damage = this.getAttackPower() * (int)Math.pow(2, focusLevel);
        if(focusLevel == 0) {
            output = String.format("The hero attacked for %s damage.", this.getAttackPower());
        }
        else if(focusLevel == 1) {
            output = String.format("The hero attacked for %s damage, expending %s turn of focus.", damage, focusLevel);
        } else {
            output = String.format("The hero attacked for %s damage, expending %s turns of focus.", damage, focusLevel);
        }
        target.getAttacked(damage);
        isDefending = false;
        focusLevel = 0;
        return output;
    }

    public void getAttacked(int amount) {
        if(isDefending) {
            amount /= 2;
        }
        super.getAttacked(amount);
    }

    public int getLevel() {
        return this.level;
    }

    public boolean gainExp(int exp) {
        this.exp += exp;
        boolean leveledUp = false;
        while (this.exp >= this.level) {
            this.exp -= this.level;
            this.level++;
            this.gainAttackPower(1);
            super.increaseMaxHealth(10);
            this.heal(this.getMaxHealth());
            leveledUp = true;
        }
        return leveledUp;
    }

    public String getStatus() {
        String output = String.format("Hero %s:" +
        " %s/%s health, %s attack, level %s", this.getName(), this.getHealth(), this.getMaxHealth(), this.getAttackPower(), this.getLevel());
        return output;
    }
}
