package mygame.Updatables;

import com.jme3.audio.AudioNode;
import com.jme3.light.AmbientLight;
import com.jme3.scene.Node;
import java.util.Random;
import mygame.Config.AudioMap;
import mygame.Config.Options;
import mygame.Main;

public class Thunder extends AmbientLight implements Updatable {
    private Node attachedNode;
    private float timer;
    private boolean isOn;
    private AudioNode ambience;
    
    public Thunder(Main game) {
        super();
        
        this.attachedNode = game.getRootNode();
        this.ambience = game.getAudioMap().getAudio(AudioMap.Types.AMBIENCE);
        reset();
    }

    public void update(float tpf) {
        if (timer <= 0) {
            reset();
        }else if (timer <= Options.THUNDER_AMOUNT) {
            if (isOn) {
                this.attachedNode.removeLight(this);
            }else{
                this.attachedNode.addLight(this);
            }
            this.isOn = !isOn;
            ambience.play();
        }
        
        timer -= tpf;
    }

    private void reset() {
        this.attachedNode.removeLight(this);
        this.timer = new Random().nextFloat() * Options.THUNDER_TIME;
        this.isOn = false;
    }
}
