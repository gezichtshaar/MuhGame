package mygame.Config;

import com.jme3.asset.AssetManager;
import com.jme3.audio.AudioNode;
import java.util.HashMap;
import java.util.Map;

public class AudioMap {
        private Map<Types, AudioNode> audio;
       
        public AudioMap(AssetManager assetManager, Types[] audioTypes) {
                this.audio = new HashMap<Types, AudioNode>();
                AudioNode audioNode;
                for (Types type : audioTypes) {
                        audioNode = new AudioNode(assetManager, String.format("Sounds/%s.wav", type.getLocation()), type.getStream());
                        audioNode.setVolume(type.getVolume());
                        this.audio.put(type, audioNode);
                }
        }
       
        public AudioNode getAudio(Types type) {
                if (audio.containsKey(type)) {
                        return audio.get(type).clone();
                }
                return null;
        }
       
        public enum Types {
                GUN("gunshot", false, 3f),
                AXE("axe", false, 3f),
                AMBIENCE("ambience", false, 3f);
       
                private String location;
                private boolean stream;
                private float volume;
                private Types(String location, boolean stream, float volume) {
                        this.location = location;
                        this.stream = stream;
                        this.volume = volume;
                }
               
                public String getLocation() {
                        return location;
                }
               
                public Boolean getStream() {
                        return stream;
                }
               
                public float getVolume() {
                        return volume;
                }
        }
}