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
            while (bullets.size() >= 10) {
                bullets.removeLast().death();
            }
            Bullet bullet = new Bullet(this.game, owner.getLocation().x + owner.facing().x, owner.getLocation().y + owner.facing().y * 2, owner.facing());
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
        return 0.2f;
    }
}
