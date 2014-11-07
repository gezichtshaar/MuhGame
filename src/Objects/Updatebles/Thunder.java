/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects.Updatebles;

import com.jme3.light.AmbientLight;
import com.jme3.scene.Node;
import java.util.Random;
import mygame.Main;

/**
 *
 * @author lukas
 */
public class Thunder extends AmbientLight implements Updateble {
    private Node attachedNode;
    private float timer;
    private boolean isOn;
    
    public Thunder(Main game) {
        super();
        
        this.attachedNode = game.getRootNode();
        reset();
    }

    public void update(float tpf) {
        if (timer <= 0) {
            reset();
        }else if (timer <= 0.1f) {
            if (isOn) {
                this.attachedNode.removeLight(this);
            }else{
                this.attachedNode.addLight(this);
            }
            this.isOn = !isOn;
        }
        
        timer -= tpf;
    }

    private void reset() {
        this.attachedNode.removeLight(this);
        this.timer = new Random().nextFloat() * 10;
        this.isOn = false;
    }
}
