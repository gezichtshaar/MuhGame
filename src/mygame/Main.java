package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.bullet.collision.PhysicsCollisionEvent;
import com.jme3.bullet.collision.PhysicsCollisionListener;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;
import java.util.ArrayList;
import java.util.List;
import mygame.Entities.Enemy;
import mygame.Entities.Entity;
import mygame.Entities.Player;
import mygame.Objects.Floor;
import mygame.Updatebles.Thunder;
import mygame.Updatebles.Updateble;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication implements PhysicsCollisionListener {
    private List<Updateble> updatebles;
    private BulletAppState bulletAppState;
    private Player player;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main() {
        super();
        this.updatebles = new ArrayList<Updateble>();
        this.bulletAppState = new BulletAppState();
    }

    @Override
    public void simpleInitApp() {
        this.flyCam.setEnabled(false);
        this.cam.setLocation(new Vector3f(0, 0, 20));
        
        stateManager.attach(bulletAppState);
        this.bulletAppState.getPhysicsSpace().addCollisionListener(this);
        
        updatebles.add(new Thunder(this));

        buildFloors();
        this.rootNode.attachChild(player = new Player(this, this.cam, 0, 15));
        spawnEnemies();
    }

    @Override
    public void simpleUpdate(float tpf) {
        for(Updateble updateble : updatebles) {
            updateble.update(tpf);
        }
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    @Override
    public final void collision(PhysicsCollisionEvent event) {
        if ("Entity".equals(event.getNodeA().getName()) && "Entity".equals(event.getNodeB().getName())) {
            ((Entity)event.getNodeA()).actOnCollision((Entity)event.getNodeB());
            ((Entity)event.getNodeB()).actOnCollision((Entity)event.getNodeA());
        }
    }
    
    private void buildFloors() {
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 5; y++) {
                rootNode.attachChild(new Floor(this, x * 15, y * 8, 5f));
            }
        }
    }

    private void spawnEnemies() {
        for (int n = 0; n < 4; n++) {
            this.rootNode.attachChild(new Enemy(this, n * 20, 30));
        }
    }
    
    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }

    public Player getPlayer() {
        return player;
    }
}
