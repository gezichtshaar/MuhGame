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
import mygame.Weapons.Gun;
import mygame.Weapons.Weapon;

public class Player extends Entity implements AnalogListener {
    private Camera cam;
    private float vel;
    private float zoom;
    private Weapon weapon;
    private PointLight light;
    private int score;

    public Player(Main game, Camera cam, float x, float y) {
        super(game, EntityTypes.Enemy1, x, y);
        this.cam = cam;
        this.vel = 0f;
        this.zoom = 0f;
        this.weapon = new Gun(game, this, 10);
        this.score = 100;

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
            this.weapon.setAttacking(true);
        }
    }

    @Override
    public void updateEntity(float tpf) {
        weapon.update(tpf);

        updateMovement(tpf);
        updateCamera(tpf);

        this.light.setPosition(this.physics.getPhysicsLocation().add(0, 0, 3));
    }

    @Override
    public void actOnCollision(Entity e) {
    }

    private void updateCamera(float tpf) {
        if (this.physics.getLinearVelocity().length() / Options.PLAYER_MAX_VELOCITY > zoom) {
            zoom += 1 * tpf;
        } else {
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

    public int getScore() {
        return score;
    }
    
    public String getWeaponInfo(){
        return this.weapon.toString();
    }

    @Override
    public void death() {
        this.score = 0;
        this.reset();
    }

    public void increaseScore(int score) {
        this.score += score;
    }
}
