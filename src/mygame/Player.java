/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.input.InputManager;
import com.jme3.input.controls.AnalogListener;
import com.jme3.input.controls.KeyTrigger;
import com.jme3.light.PointLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.Camera;

/**
 *
 * @author lukas
 */
public class Player extends Entity implements AnalogListener {
    private Camera cam;
    private Vector3f vel;
    private PointLight light;

    public Player(Main game, float x, float y) {
        super(game, GameObjects.Enemy, x, y);
        this.cam = game.getCamera();
        this.vel = new Vector3f();
        this.light = new PointLight();
        this.light.setRadius(Options.PLAYER_LIGHT_RADIUS);
        this.light.setColor(ColorRGBA.White);
        game.getRootNode().addLight(this.light);
        
        initInput(game.getInputManager());
    }
    
    private void initInput(InputManager inputManager) {
        inputManager.addMapping(Keybindings.PlayerLeft.getName(), new KeyTrigger(Keybindings.PlayerLeft.getKeyInput()));
        inputManager.addMapping(Keybindings.PlayerRight.getName(), new KeyTrigger(Keybindings.PlayerRight.getKeyInput()));
        inputManager.addMapping(Keybindings.PlayerUp.getName(), new KeyTrigger(Keybindings.PlayerUp.getKeyInput()));
        inputManager.addMapping(Keybindings.PlayerDown.getName(), new KeyTrigger(Keybindings.PlayerDown.getKeyInput()));
        
        inputManager.addListener(this, Keybindings.PlayerLeft.getName(), Keybindings.PlayerUp.getName(), Keybindings.PlayerRight.getName());
    }

    @Override
    public void onAnalog(String name, float value, float tpf) {
        //vel.zero();
        if (Keybindings.PlayerLeft.getName().equals(name)) {
            vel.setX(-20);
        }
        if (Keybindings.PlayerRight.getName().equals(name)) {
            vel.setX(20);
        }
        if (Keybindings.PlayerUp.getName().equals(name)) {
            vel.setY(30);
        }
    }
    
    @Override
    public void updateLogicalState(float tpf) {
        super.updateLogicalState(tpf);
        this.physics.applyCentralForce(this.vel);
        this.vel.zero();
        
        this.cam.setLocation(new Vector3f(this.physics.getPhysicsLocation()).setZ(20));
        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));
    }
}
