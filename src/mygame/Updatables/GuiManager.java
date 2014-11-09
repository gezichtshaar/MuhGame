/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Updatables;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import mygame.Main;

/**
 *
 * @author Skrylax
 */
public class GuiManager implements Updatable{

    private BitmapFont guiFont;
    private BitmapText scoreText;
    private BitmapText playerAmmo;
    private BitmapText playerHealth;

    public GuiManager(Main game) {
        this.guiFont = game.getGuiFont();
        this.scoreText = createBitmapText();
        this.playerAmmo = createBitmapText();
        this.playerHealth = createBitmapText();
    }
    
    private BitmapText createBitmapText() {

        BitmapText text = new BitmapText(guiFont, false);
        text.setSize(guiFont.getCharSet().getRenderedSize());      
        text.setColor(ColorRGBA.Blue);                             
        text.setText("");
        text.setLocalTranslation(300, text.getLineHeight(), 0);

        return text;
    }

    public void update(float tpf) {
        
    }
    
    
}
