/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Weapons;

import com.jme3.math.Vector3f;
import java.util.LinkedList;
import mygame.Entities.Bullet;
import mygame.Entities.Entity;
import mygame.Main;

/**
 *
 * @author lukas
 */
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
                bullets.removeLast();
            }
            Bullet bullet = new Bullet(this.game, owner.getLocation().x, owner.getLocation().y + 1, new Vector3f(1, 0, 0));
            bullets.add(bullet);
            owner.getParent().attachChild(bullet);
            
            amount--;
        }
        setAttacking(false);
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
