package mygame;

import com.jme3.app.SimpleApplication;
import com.jme3.bullet.BulletAppState;
import com.jme3.light.AmbientLight;
import com.jme3.light.DirectionalLight;
import com.jme3.math.Vector3f;
import com.jme3.renderer.RenderManager;

/**
 * test
 *
 * @author normenhansen
 */
public class Main extends SimpleApplication {

    private BulletAppState bulletAppState;

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

        rootNode.attachChild(new Wall(this, 5, 0, 10));
        rootNode.attachChild(new Enemy(this, 5, 1));
        rootNode.attachChild(new Player(this, 0, 10));

        DirectionalLight sun = new DirectionalLight();
        sun.setDirection(new Vector3f(0, 0, -1));
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

    public BulletAppState getBulletAppState() {
        return bulletAppState;
    }
}
