/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.control.RigidBodyControl;
import com.jme3.material.Material;
import com.jme3.math.ColorRGBA;
import com.jme3.scene.Geometry;
import com.jme3.scene.shape.Box;

/**
 *
 * @author lukas
 */
public class Wall extends Geometry {
    public Wall(Main game, float x, float y, float width) {
        super("Wall", new Box(width, 0.2f, 2));
        this.setLocalTranslation(x, y - 0.1f, -1);
        
        Material material = new Material(game.getAssetManager(), "Common/MatDefs/Light/Lighting.j3md");
        material.setColor("Diffuse", ColorRGBA.Blue);
        material.setColor("Ambient", ColorRGBA.Blue);
        this.setMaterial(material);
        
        RigidBodyControl physics = new RigidBodyControl(0f);
        this.addControl(physics);
        game.getBulletAppState().getPhysicsSpace().add(physics);
    }
}
