package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.font.BitmapFont;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import java.util.ArrayList;
import java.util.List;
import mygame.Config.AudioMap;
import mygame.Entities.Enemy;
import mygame.Entities.Entity;
import mygame.Entities.Player;
import mygame.Objects.Floor;
import mygame.Updatables.GuiManager;
import mygame.Updatables.Thunder;
import mygame.Updatables.Updatable;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication implements PhysicsCollisionListener {

    private List<Updatable> updatables;
    private BulletAppState bulletAppState;
    private Player player;
    private AudioMap audioMap;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main() {
        super();
        this.updatables = new ArrayList<Updatable>();
        this.bulletAppState = new BulletAppState();
    }

    @Override
    public void simpleInitApp() {
        this.flyCam.setEnabled(false);
        this.audioMap = new AudioMap(assetManager, new AudioMap.Types[]{AudioMap.Types.GUN, AudioMap.Types.AXE});
        this.cam.setLocation(new Vector3f(0, 0, 20));

        stateManager.attach(bulletAppState);
        this.bulletAppState.getPhysicsSpace().addCollisionListener(this);

        updatables.add(new Thunder(this));
        updatables.add(new GuiManager(this));

        buildFloors();
        this.rootNode.attachChild(player = new Player(this, this.cam, 0, 15));
        spawnEnemies();
    }

    @Override
    public void simpleUpdate(float tpf) {
        for (Updatable updateble : updatables) {
            updateble.update(tpf);
        }
        //audioMap.getAudio(AudioMap.Types.GUN).play();
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }

    @Override
    public final void collision(PhysicsCollisionEvent event) {
        if ("Entity".equals(event.getNodeA().getName()) && "Entity".equals(event.getNodeB().getName())) {
            ((Entity) event.getNodeA()).actOnCollision((Entity) event.getNodeB());
            ((Entity) event.getNodeB()).actOnCollision((Entity) event.getNodeA());
        }
    }

    private void buildFloors() {
        for (int x = 0; x < 10; x++) {
            for (int y = 0; y < 5; y++) {
                rootNode.attachChild(new Floor(this, x * 15, y * 8, 5f));
            }
        }
    }

    private void spawnEnemies() {
        for (int n = 0; n < 20; n++) {
            this.rootNode.attachChild(new Enemy(this, n * 20, 30));
        }
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }

    public Player getPlayer() {
        return player;
    }

    public AudioMap getAudioMap() {
        return audioMap;
    }

    public BitmapFont getGuiFont() {
        return guiFont;
    }
}
