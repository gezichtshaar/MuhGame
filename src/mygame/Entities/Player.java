/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Entities;

import com.jme3.input.InputManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;
import mygame.Config.Keybindings;
import mygame.Config.Options;
import mygame.Main;

/**
 *
 * @author lukas
 */
public class Player extends Entity implements AnalogListener {
    private Camera cam;
    private float vel;
    private float zoom;
    private PointLight light;

    public Player(Main game, Camera cam, float x, float y) {
        super(game, GameObjects.Enemy1, x, y);
        this.cam = cam;
        this.vel = 0f;
        this.zoom = 0f;
        
        this.light = new PointLight();
        this.light.setRadius(Options.PLAYER_LIGHT_RADIUS);
        this.light.setColor(ColorRGBA.White.mult(1));
        game.getRootNode().addLight(this.light);
        
        
        this.physics.setLinearDamping(0.4f);

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
        }
    }

    @Override
    public void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);
        
        updateMovement(tpf);
        updateCamera(tpf);
        
        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));
    }
    
    private void updateCamera(float tpf) {
        if(this.physics.getLinearVelocity().length() / Options.PLAYER_MAX_VELOCITY > zoom) {
            zoom += 1 * tpf;
        }else{
            zoom -= 2 * tpf;
        }
        this.cam.setLocation(new Vector3f(this.getLocation()).setZ(Options.PLAYER_CAMERA_DISTANCE + 5 * zoom));
    }

    private void updateMovement(float tpf) {
        if (this.vel != 0f) {
            this.physics.setLinearVelocity(this.physics.getLinearVelocity().setX(vel * Options.PLAYER_MAX_VELOCITY));
        }
        this.vel = 0f;
    }
    
    public Vector3f getLocation() {
        return this.physics.getPhysicsLocation();
    }
}
