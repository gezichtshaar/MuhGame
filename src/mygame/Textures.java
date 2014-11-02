/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame;

import com.jme3.math.ColorRGBA;

/**
 *
 * @author lukas
 */
public enum Textures {
    SintHead("sint_head", false, ColorRGBA.Red),
    SintBody("sint_body", false, ColorRGBA.Red),
    Floor("floor", true, ColorRGBA.Green.mult(0.3f));
    
    private String name;
    private boolean repeat;
    private ColorRGBA color;
    private Textures(String name, boolean repeat, ColorRGBA color) {
        this.name = name;
        this.repeat = repeat;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public ColorRGBA getColor() {
        return color;
    }
}
