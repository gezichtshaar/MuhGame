/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mygame.Updatables;

import com.jme3.font.BitmapFont;
import com.jme3.font.BitmapText;
import com.jme3.math.ColorRGBA;
import mygame.Entities.Enemy;
import mygame.Main;

/**
 *
 * @author Skrylax
 */
public class GuiManager implements Updatable{

    private Main game;
    private BitmapText scoreText;
    private BitmapText playerAmmo;
    private BitmapText playerHealth;

    public GuiManager(Main game) {
        this.game = game;
        game.getGuiNode().attachChild(this.scoreText = createBitmapText(1,1));
        game.getGuiNode().attachChild(this.playerAmmo = createBitmapText(1,2));
        game.getGuiNode().attachChild(this.playerHealth = createBitmapText(1,3));
    }
    
    private BitmapText createBitmapText(int x, int y) {
        BitmapText text = new BitmapText(game.getGuiFont(), false);
        text.setSize(game.getGuiFont().getCharSet().getRenderedSize());      
        text.setColor(ColorRGBA.White);                             
        text.setText("");
        text.setLocalTranslation(x, text.getLineHeight()*y, 0);

        return text;
    }

    public void update(float tpf) {
        scoreText.setText(String.format("Score: %d\n", game.getPlayer().getScore()));
        playerAmmo.setText(String.format("Ammo: %d\n", game.getPlayer().getAmmo()));
        playerHealth.setText(String.format("Health: %d\n", game.getPlayer().getHealth()));
    }
    
    
}
