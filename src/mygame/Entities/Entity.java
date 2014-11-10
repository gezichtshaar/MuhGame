package mygame.Entities;

import com.jme3.bullet.collision.shapes.CapsuleCollisionShape;
import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.texture.Texture;
import java.util.Random;
import mygame.Config.AudioMap;
import mygame.Config.Options;
import mygame.Main;
import mygame.TextureMap;
import mygame.WorkingQuad;

public abstract class Entity extends Geometry {
    protected RigidBodyControl physics;
    protected TextureMap textureMap;
    private float lastAnimationUpdate;
    protected AudioMap audioMap;
    private int health;

    public Entity(Main game, EntityTypes gameObject, float x, float y) {
        super("Entity", new WorkingQuad(new Vector2f(-gameObject.getWidth() / 2, -gameObject.getHeight() / 2), gameObject.getWidth(), gameObject.getHeight()));
        this.textureMap = new TextureMap(game.getAssetManager(), gameObject.getTextureNames());
        this.lastAnimationUpdate = 0f;
        this.audioMap = game.getAudioMap();
        this.health = 100;

        Material material = new Material(game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        material.setTexture("DiffuseMap", textureMap.getFront());
        material.setBoolean("UseAlpha", true);
        material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        this.setMaterial(material);

        this.physics = new RigidBodyControl(new CapsuleCollisionShape(gameObject.getWidth() / 2, gameObject.getHeight() / 2), gameObject.getWeight());
        this.physics.setAngularFactor(0f);
        this.addControl(physics);
        game.getBulletAppState().getPhysicsSpace().add(this.physics);

        this.physics.setPhysicsLocation(new Vector3f(x, y, 0));
    }

    @Override
    public final void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);
        this.setLocalTranslation(this.getLocalTranslation().setZ(0));
        physics.setPhysicsLocation(physics.getPhysicsLocation().setZ(0));

        if (physics.getPhysicsLocation().y < -5) {
            physics.setPhysicsLocation(physics.getPhysicsLocation().setY(38));
            physics.setPhysicsLocation(physics.getPhysicsLocation().setX(new Random().nextInt(80)));
            physics.setLinearVelocity(physics.getLinearVelocity().setY(0));
        }

        if (lastAnimationUpdate > Options.ENTITY_ANIMATION_TIMER_TRESHOLD) {
            updateAnimation();
            lastAnimationUpdate = 0f;
        } else {
            lastAnimationUpdate += tpf;
        }
        
        if (this.health <= 0) {
            this.death();
        }else{
            updateEntity(tpf);
        }
    }
    
    protected abstract void updateEntity(float tpf);

    private void updateAnimation() {
        if (this.physics.getLinearVelocity().x > Options.ENTITY_ANIMATION_MOVEMENT_TRESHOLD) {
            this.updateTexture(textureMap.getNextRight());
        } else if (this.physics.getLinearVelocity().x < -Options.ENTITY_ANIMATION_MOVEMENT_TRESHOLD) {
            this.updateTexture(textureMap.getNextLeft());
        } else {
            this.updateTexture(textureMap.getFront());
        }
    }
    
    public Vector3f facing() {
        Vector3f v = new Vector3f();
        if (textureMap.facing() == 0) {
            v.setY(1);
        }else{
            v.setX(textureMap.facing());
        }
        return v;
    }

    protected void updateTexture(Texture texture) {
        this.material.setTexture("DiffuseMap", texture);
    }

    public abstract void actOnCollision(Entity e);

    public Vector3f getLocation() {
        return this.physics.getPhysicsLocation();
    }
    
    public Vector3f getVelocity() {
        return this.physics.getLinearVelocity();
    }

    public int getHealth() {
        return health;
    }
    
    public void setDamage(float amount) {
        this.health -= amount;
    }
    
    public void reset() {
        this.health = 10;
    }
    
    public abstract void death();
}
