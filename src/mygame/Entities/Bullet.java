package mygame.Entities;

import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.scene.Node;
import mygame.Config.AudioMap;
import mygame.Main;
import mygame.Config.Options;

public class Bullet extends Entity {
    private Node owner;
    private PointLight light;

    public Bullet(Main game, float x, float y, Vector3f direction) {
        super(game, EntityTypes.Bullet, x, y);
        this.owner = game.getRootNode();
        
        this.light = new PointLight();
        this.light.setRadius(Options.BULLET_LIGHT_RADIUS);
        this.light.setColor(ColorRGBA.White.mult(1));
        owner.addLight(this.light);
        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));

        this.physics.setLinearVelocity(direction.mult(Options.BULLET_SPEED));
        this.audioMap.getAudio(AudioMap.Types.GUN).play();
    }

    @Override
    public void actOnCollision(Entity e) {
        if (e.getClass() == Enemy.class) {
            e.physics.applyImpulse(this.getVelocity().normalize().mult(5), Vector3f.ZERO);
            e.setDamage(100);
            death();
        }
    }

    @Override
    protected void updateEntity(float tpf) {
        if (this.getVelocity().equals(new Vector3f())) {
            death();
        }
    }

    @Override
    public void death() {
        owner.removeLight(light);
        removeFromParent();
        this.physics.setEnabled(false);
    }
}
