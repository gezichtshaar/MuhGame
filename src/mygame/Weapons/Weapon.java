package mygame.Weapons;

import mygame.Entities.Entity;

public abstract class Weapon {
    protected Entity owner;
    private float time;
    private boolean isAttacking;
    
    public Weapon(Entity owner) {
        this.owner = owner;
        this.time = 0f;
        this.isAttacking = false;
    }
    
    public void setAttacking(boolean isAttacking) {
        this.isAttacking = isAttacking;
    }
    
    public void update(float tpf) {
        if (isAttacking && this.time >= this.getAPS()) {
            attack(tpf);
            this.time = 0f;
        }
        this.isAttacking = false;
        this.time += tpf;
    }
    
    public abstract void attack(float tpf);
    
    protected abstract float getAPS(); //Attacks per second

    @Override
    public String toString() {
        return "Unknown";
    }
}
