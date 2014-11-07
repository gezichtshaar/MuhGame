/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Entities;

import com.jme3.math.Vector3f;
import Objects.GameObjects;
import mygame.Main;
import mygame.Config.Options;

/**
 *
 * @author lukas
 */
public class Enemy extends Entity {
    private Player player;
    
    public Enemy(Main game, float x, float y) {
        super(game, GameObjects.Enemy2, x, y);
        this.player = game.getPlayer();
    }

    @Override
    public void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);
        if (player.getLocation().subtract(this.physics.getPhysicsLocation()).length() < Options.ENEMY_SPOT_RADIUS) {
            Vector3f vel = new Vector3f(player.getLocation().subtract(this.physics.getPhysicsLocation()).normalize().x * 5, 0, 0);
            this.physics.setLinearVelocity(this.physics.getLinearVelocity().setX(vel.x));
        }
    }
}
