/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.Vector3f;

/**
 *
 * @author lukas
 */
public class Bullet extends Entity {
    public Bullet(Main game, float x, float y, Vector3f direction) {
        super(game, GameObjects.Bullet, x, y);
        this.physics.setLinearVelocity(direction.mult(Options.BULLET_SPEED));
    }
    
}
