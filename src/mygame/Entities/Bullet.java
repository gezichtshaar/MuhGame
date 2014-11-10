package mygame.Entities;

import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import mygame.Config.AudioMap;
import mygame.Main;
import mygame.Config.Options;

public class Bullet extends Entity {

    private PointLight light;

    public Bullet(Main game, float x, float y, Vector3f direction) {
        super(game, EntityTypes.Bullet, x, y);
        this.light = new PointLight();
        this.light.setRadius(Options.BULLET_LIGHT_RADIUS);
        this.light.setColor(ColorRGBA.White.mult(1));
        game.getRootNode().addLight(this.light);
        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));

        this.physics.setLinearVelocity(direction.mult(Options.BULLET_SPEED));
        this.audioMap.getAudio(AudioMap.Types.GUN).play();
    }

    @Override
    public void actOnCollision(Entity e) {
        if (e.getClass() == Enemy.class) {
            e.setDamage(1);
            removeFromParent();
        }
    }

    @Override
    protected void updateEntity(float tpf) {
    }

    @Override
    protected void death() {
        removeFromParent();
    }
}
