/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
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
import mygame.Config.Options;
import mygame.Config.TextureMap;
import mygame.Main;
import mygame.WorkingQuad;

/**
 *
 * @author lukas
 */
public class Entity extends Geometry {
    protected RigidBodyControl physics;
    protected TextureMap textureMap;
    private float lastAnimationUpdate;
            
    public Entity(Main game, GameObjects gameObject, float x, float y) {
        super("Entity", new WorkingQuad(new Vector2f(-gameObject.getWidth()/2, -gameObject.getHeight()/2), gameObject.getWidth(), gameObject.getHeight()));
        this.textureMap = new TextureMap(game.getAssetManager(), gameObject.getTextureNames());
        this.lastAnimationUpdate = 0f;
        
        Material material = new Material(game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        material.setTexture("DiffuseMap", textureMap.getFront());
        material.setBoolean("UseAlpha", true);
        material.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        this.setMaterial(material);
        
        this.physics = new RigidBodyControl(new CapsuleCollisionShape(gameObject.getWidth()/2, gameObject.getHeight()/2), gameObject.getWeight());
        this.physics.setAngularFactor(0f);
        this.addControl(physics);
        game.getBulletAppState().getPhysicsSpace().add(this.physics);
        
        this.physics.setPhysicsLocation(new Vector3f(x, y, 0));
    }
    
    @Override
    public void updateLogicalState(float tpf) {
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
       }else{
           lastAnimationUpdate += tpf;
       }
    }
    
    private void updateAnimation() {
        if (this.physics.getLinearVelocity().x > Options.ENTITY_ANIMATION_MOVEMENT_TRESHOLD) {
            this.updateTexture(textureMap.getNextRight());
        }else if(this.physics.getLinearVelocity().x < -Options.ENTITY_ANIMATION_MOVEMENT_TRESHOLD) {
            this.updateTexture(textureMap.getNextLeft());
        }else{
            this.updateTexture(textureMap.getFront());
        }
    }
    
    protected void updateTexture(Texture texture) {
        this.material.setTexture("DiffuseMap", texture);
    }
}
