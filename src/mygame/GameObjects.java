/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

/**
 *
 * @author lukas
 */
public enum GameObjects {
    Enemy(new String[] {"Enemy0-0", "Enemy0-1", "Enemy0-2", "Enemy0-3", "Enemy0-4", "Enemy0-6", "Enemy0-7", "Enemy0-8", "Enemy0-9"}, 1f, 2f, 1f);
    
    private String[] textureNames;
    private float width;
    private float height;
    private float weight;
    private GameObjects(String[] textureNames, float width, float height, float weight) {
        this.textureNames = textureNames;
        this.width = width * 2;
        this.height = height * 2;
        this.weight = weight;
    }

    public String[] getTextureNames() {
        return textureNames;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getWeight() {
        return weight;
    }
}
