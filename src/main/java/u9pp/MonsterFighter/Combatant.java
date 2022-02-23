package u9pp.MonsterFighter;

public abstract class Combatant {
    private int health;
    private int maxHealth;
    private String name;
    private int attackPower;

    public Combatant(String name, int maxHealth, int attack) {
        if(maxHealth < 1) {
            maxHealth = 1;
        }
        if(attack < 0) {
            attack = 0;
        }
        this.name = name;
        this.maxHealth = maxHealth;
        this.health = maxHealth;
        this.attackPower = attack;
    }

    public void increaseMaxHealth(int amount) {
        if (amount <= 0) {
            return;
        }
        maxHealth += amount;
    }

    public int getMaxHealth() {
        return this.maxHealth;
    }

    public int getHealth() {
        return this.health;
    }

    public void getAttacked(int attackPower) {
        if(attackPower <= 0) {
            return;
        }
        setHealth(this.health - attackPower);
    }

    public void heal(int amount) {
        if(amount <= 0) {
            return;
        }
        setHealth(this.health + amount);
    }

    private int setHealth(int newHealth) {
        this.health = newHealth;
        if(this.health > maxHealth) {
            this.health = maxHealth;
        } 
        return this.health;
    }

    public void gainAttackPower(int buffAmount) {
        this.attackPower += buffAmount;
        if(attackPower < 0) {
            attackPower = 0;
        }
     }

    public int getAttackPower() {
        return attackPower;
    }

    public boolean canFight() {
        return health > 0;
    }

    public String getStatus() {
        return String.format("%s: %s/%s health", name, health, maxHealth);
    }

    public String getName() {
        return this.name;
    }
}
