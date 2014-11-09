/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.asset.AssetManager;
import com.jme3.texture.Texture;

/**
 *
 * @author lukas
 */
public class TextureMap {
    private Texture[] textures;
    private int centerTexture;
    private int lastTexture;
    
    public TextureMap(AssetManager assetManager, String[] textureNames) {
        this.textures = new Texture[textureNames.length];
        
        for(int n = 0; n < textureNames.length; n++) {
            this.textures[n] = assetManager.loadTexture(String.format("Textures/%s.png", textureNames[n]));
            this.textures[n].setMagFilter(Texture.MagFilter.Nearest);
        }
        
        centerTexture = textures.length / 2;
        lastTexture = centerTexture;
    }
    
    public Texture getFront() {
        lastTexture = centerTexture;
        return getTexture();
    }
    
    public Texture getNextLeft() {
        if (lastTexture < centerTexture) {
            if (--lastTexture < 0) {
                lastTexture = centerTexture -1;
            }
        }else{
            lastTexture = centerTexture - 1;
        }
        return getTexture();
    }
    
    public Texture getNextRight() {
        if (lastTexture > centerTexture) {
            if (++lastTexture > textures.length-1) {
                lastTexture = centerTexture + 1;
            }
        }else{
            lastTexture = centerTexture + 1;
        }
        return getTexture();
    }
    
    public Texture getTexture() {
        return textures[this.lastTexture];
    }
}
