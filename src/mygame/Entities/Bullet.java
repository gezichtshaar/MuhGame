/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Entities;

import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import mygame.Main;
import mygame.Config.Options;

/**
 * @author lukas
 */
public class Bullet extends Entity {
    private PointLight light;
    
    public Bullet(Main game, float x, float y, Vector3f direction) {
        super(game, EntityTypes.Bullet, x, y);
        this.light = new PointLight();
        this.light.setRadius(Options.PLAYER_LIGHT_RADIUS);
        this.light.setColor(ColorRGBA.White.mult(1));
        game.getRootNode().addLight(this.light);
        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));
        
        this.physics.setLinearVelocity(direction.mult(Options.BULLET_SPEED));
    }

    @Override
    public void actOnCollision(Entity e) {
        //removeFromParent();
    }
}
