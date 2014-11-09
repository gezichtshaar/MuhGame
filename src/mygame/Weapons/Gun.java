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
public class Gun implements Weapon {
    private Main game;
    private LinkedList<Bullet> bullets;
    private int amount;
    
    public Gun(Main game, int amount) {
        this.game = game;
        this.amount = amount;
        this.bullets = new LinkedList<Bullet>();
    }

    public void attack(Entity e) {
        if (amount-- > 0) {
            if (bullets.size() >= 10) {
                bullets.removeLast();
            }
            bullets.add(new Bullet(game, e.getLocation().x, e.getLocation().y + 1, new Vector3f(1, 0, 0)));
        }
    }
}
