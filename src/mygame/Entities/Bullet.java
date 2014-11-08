/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Entities;

import com.jme3.math.Vector3f;
import mygame.Main;
import mygame.Config.Options;

/**
 *
 * @author lukas
 */
public class Bullet extends Entity {
    public Bullet(Main game, float x, float y, Vector3f direction) {
        super(game, EntityTypes.Bullet, x, y);
        this.physics.setLinearVelocity(direction.mult(Options.BULLET_SPEED));
    }

    @Override
    public void actOnCollision(Entity e) {
    }
}
