/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Entities;

import com.jme3.audio.AudioNode;
import com.jme3.math.Vector3f;
import mygame.Config.AudioMap;
import mygame.Main;
import mygame.Config.Options;

/**
 *
 * @author lukas
 */
public class Enemy extends Entity {

    private Player player;
    private AudioNode attack;

    public Enemy(Main game, float x, float y) {
        super(game, EntityTypes.Enemy2, x, y);
        this.player = game.getPlayer();
        this.attack = audioMap.getAudio(AudioMap.Types.AXE);
                
        this.physics.setFriction(0f);
    }

    @Override
    public void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);
        if (player.getLocation().subtract(this.physics.getPhysicsLocation()).length() < Options.ENEMY_SPOT_RADIUS) {
            Vector3f vel = new Vector3f(player.getLocation().subtract(this.physics.getPhysicsLocation()).normalize().x * 5, 0, 0);
            this.physics.setLinearVelocity(this.physics.getLinearVelocity().setX(vel.x));
        }
    }

    @Override
    public void actOnCollision(Entity e) {
        if (e.getClass() == Player.class) {
            Vector3f impulse = this.physics.getLinearVelocity().clone();
            e.physics.applyImpulse(impulse.setY(0).normalize().mult(3), Vector3f.ZERO);

            attack.play();
        }
    }
}
