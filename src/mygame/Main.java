package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.ColorRGBA;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {
    private BulletAppState bulletAppState;
    private Player player;

    public static void main(String[] args) {
        Main app = new Main();
        app.start();
    }

    public Main() {
        super();
        this.bulletAppState = new BulletAppState();
    }

    @Override
    public void simpleInitApp() {
        this.flyCam.setEnabled(false);
        this.cam.setLocation(new Vector3f(0, 0, 20));
        stateManager.attach(bulletAppState);

        buildFloors();
        rootNode.attachChild(player = new Player(this, 0, 15));
        spawnEnemies();
        
        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0, 0, -1));
        sun.setColor(ColorRGBA.White);
        //rootNode.addLight(new AmbientLight());
    }

    @Override
    public void simpleUpdate(float tpf) {
        //TODO: add update code
    }

    @Override
    public void simpleRender(RenderManager rm) {
        //TODO: add render code
    }
    
    private void buildFloors() {
        for(int x = 0; x < 10; x++) {
            for(int y = 0; y < 5; y++) {
                rootNode.attachChild(new Floor(this, x * 15, y * 8, 5f));
            }
        }
    }

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }

    public Player getPlayer() {
        return player;
    }

    private void spawnEnemies() {
        for (int n = 0; n < 20; n++) {
            rootNode.attachChild(new Enemy(this, n * 20, 30));
        }
    }
}
