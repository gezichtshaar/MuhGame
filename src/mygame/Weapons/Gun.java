package mygame.Weapons;

import com.jme3.audio.AudioNode;
import java.util.LinkedList;
import mygame.Entities.Bullet;
import mygame.Entities.Entity;
import mygame.Main;

public class Gun extends Weapon {
    private Main game;
    private LinkedList<Bullet> bullets;
    private int amount;
        
    public Gun(Main game, Entity owner, int amount) {
        super(owner);
        this.game = game;
        this.amount = amount;
        this.bullets = new LinkedList<Bullet>();
    }

    @Override
    public void attack(float tpf) {
        if (amount > 0) {
            if (bullets.size() >= 10) {
                owner.getParent().detachChild(bullets.removeLast());
            }
            Bullet bullet = new Bullet(this.game, owner.getLocation().x + owner.facing().x, owner.getLocation().y + owner.facing().y + 1, owner.facing().setY(0));
            bullets.add(bullet);
            owner.getParent().attachChild(bullet);
            
            //amount--;
        }
    }

    @Override
    public String toString() {
        return String.format("Gun, Ammo: %d\n", amount);
    }

    @Override
    protected float getAPS() {
        return 0.5f;
    }
}
