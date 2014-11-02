/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.bullet.collision.PhysicsRayTestResult;
import com.jme3.collision.Collidable;
import com.jme3.collision.CollisionResult;
import com.jme3.collision.CollisionResults;
import com.jme3.input.InputManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import com.jme3.scene.Geometry;
import com.jme3.scene.Node;
import com.jme3.scene.Spatial;
import java.util.List;

/**
 *
 * @author lukas
 */
public class Player extends Entity implements AnalogListener {
    private Main game;
    private Camera cam;
    private float vel;
    private PointLight light;

    public Player(Main game, float x, float y) {
        super(game, GameObjects.Enemy1, x, y);
        this.game = game;
        this.cam = game.getCamera();
        this.vel = 0f;
        
        this.light = new PointLight();
        this.light.setRadius(Options.PLAYER_LIGHT_RADIUS);
        this.light.setColor(ColorRGBA.White.mult(2));
        game.getRootNode().addLight(this.light);

        physics.setFriction(3f);

        initInput(game.getInputManager());
    }

    private void initInput(InputManager inputManager) {
        inputManager.addMapping(Keybindings.PlayerLeft.getName(), new KeyTrigger(Keybindings.PlayerLeft.getKeyInput()));
        inputManager.addMapping(Keybindings.PlayerRight.getName(), new KeyTrigger(Keybindings.PlayerRight.getKeyInput()));
        inputManager.addMapping(Keybindings.PlayerShoot.getName(), new KeyTrigger(Keybindings.PlayerShoot.getKeyInput()));

        inputManager.addListener(this, Keybindings.PlayerLeft.getName(), Keybindings.PlayerRight.getName(), Keybindings.PlayerShoot.getName());
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        if (Keybindings.PlayerLeft.getName().equals(name)) {
            vel = -1;
        }
        if (Keybindings.PlayerRight.getName().equals(name)) {
            vel = 1;
        }
        
        if (Keybindings.PlayerShoot.getName().equals(name)) {
            System.out.println("Blaat");
            game.getRootNode().attachChild(new Bullet(game, this.physics.getPhysicsLocation().x, this.physics.getPhysicsLocation().y, new Vector3f(vel, 0, 0)));
        }
    }

    @Override
    public void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);
        if (this.vel != 0f) {
            this.physics.setLinearVelocity(this.physics.getLinearVelocity().setX(vel * Options.PLAYER_MAX_VELOCITY));
        }
        this.vel = 0f;
        
        this.cam.setLocation(new Vector3f(this.physics.getPhysicsLocation()).setZ(Options.PLAYER_CAMERA_DISTANCE));
        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));
    }

    public Vector3f getLocation() {
        return this.physics.getPhysicsLocation();
    }
}
