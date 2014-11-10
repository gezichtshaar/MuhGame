package mygame.Objects;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.material.RenderState;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector2f;
import com.jme3.math.Vector3f;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.shape.Box;
import com.jme3.scene.shape.Quad;
import com.jme3.texture.Texture;
import com.jme3.texture.Texture.WrapMode;
import mygame.Main;

public class Floor extends Node {
    private RigidBodyControl physics;
    
    public Floor(Main game, float x, float y, float width) {
        super();
        
        Material floorMat = new Material(game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        floorMat.setBoolean("UseMaterialColors", true);
        floorMat.setColor("Ambient", ColorRGBA.Green.mult(0.2f));
        floorMat.setColor("Diffuse", ColorRGBA.Green.mult(0.2f));
        
        Material grassMat = new Material(game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        Texture tex = game.getAssetManager().loadTexture(String.format("Textures/%s.png", "grass"));     
        tex.setWrap(WrapMode.Repeat);
        grassMat.setTexture("DiffuseMap", tex);
        grassMat.setBoolean("UseAlpha", true);
        grassMat.getAdditionalRenderState().setBlendMode(RenderState.BlendMode.Alpha);
        
        Geometry grassFront = new Geometry("Grass", new Quad(width * 2, 1));
        grassFront.getMesh().scaleTextureCoordinates(new Vector2f(width, 1));
        grassFront.setMaterial(grassMat);
        
        grassFront.setLocalTranslation(-width/2, 0, 2);
        this.attachChild(grassFront);
        
        Geometry floor = new Geometry("Floor", new Box(new Vector3f(width/2, 0.05f, 0.5f), width, 0.1f, 1));
        floor.setMaterial(floorMat);
        this.attachChild(floor);
        
        this.physics = new RigidBodyControl(0f);
        this.addControl(physics);
        game.getBulletAppState().getPhysicsSpace().add(physics);
        
        physics.setPhysicsLocation(new Vector3f(x -width/2, y, 0));
        physics.setFriction(3f);
    }
}
